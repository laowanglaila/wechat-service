package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.AuthorizationCheckRpcService;
import com.hualala.app.wechat.WechatFuctionEnum;
import com.hualala.app.wechat.WechatQRCodeRpcSerivce;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.lock.RedisLock;
import com.hualala.app.wechat.sdk.mp.common.ErrorCodes;
import com.hualala.app.wechat.sdk.mp.common.RedisKeys;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.config.RabbitQueueProps;
import com.hualala.app.wechat.sdk.mp.exception.WechatException;
import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import com.hualala.app.wechat.model.WechatQrcodeTempModel;
import com.hualala.app.wechat.model.mq.QrcodeInfoModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.Qrcode.QrcodeCreateSceneIDService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.app.Logger;
import com.hualala.core.utils.DataUtils;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by renjianfei on 2017/4/12.
 */
@Setter
@Service
@ConfigurationProperties(prefix = "wechat.mpID")
class WechatQRCodeRpcSerivceImpl implements WechatQRCodeRpcSerivce, RedisKeys {

    private Logger log = Logger.of(WechatQRCodeRpcSerivceImpl.class);
    private String qrcodeDefaultMpID;
    private static final String WECHAT_QR_QUERY_CACHE_LOCK = "Wechat_QrCode_QueryLock";
    private static final String COLON = ":";
    private static final int SIZE = 1;
    private static final int USED_QRCODE_STATUS = 2;
    private static final Long LOCKED_TIME_OUT_SECONDS = 10L;


    @Autowired
    private WechatQrcodeTempMapper qrcodeTempMapper;

    @Autowired
    private QrcodeCreateSceneIDService qrcodeCreateSceneIDService;

    @Autowired
    private BaseHttpService baseHttpService;

    @Autowired
    private MpInfoService mpInfoService;

//    @Autowired
//    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitQueueProps rabbitQueueProps;

    @Autowired
    private RedisLock redisLockHandler;
    @Autowired
    private StringRedisTemplate stringRedisTemplate2;

    @Autowired
    private AuthorizationCheckRpcService getAuthorizationCheckRpcService;
    /**
     * 获取一个临时二维码，优先获取缓存
     * @param qrCodeReq
     * @return
     */
    @Override
    public WechatQRCodeRes createQRCode(WechatQRCodeReq qrCodeReq) {

        //判断mpID,没有则调方法获取
        String mpID = qrCodeReq.getMpID();
        if (StringUtils.isBlank(mpID)) {
            String brandID = qrCodeReq.getBrandID();
            String groupID = qrCodeReq.getGroupID();
            String shopID = qrCodeReq.getShopID();
            if (StringUtils.isBlank(brandID) || StringUtils.isBlank(groupID)) {
                return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(Long.parseLong(groupID), Long.parseLong(brandID), Long.parseLong(shopID));
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_MP_NOT_EXIST, "获取mpID失败，或者公众号不存在！");
        }

        WechatQRTypeEnum qrcodeType = qrCodeReq.getQrcodeType();
        if (qrcodeType == null){
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_QRCODE_TYPE_NULL, "必须提供一个期望获得的QrcodeType！");
        }
        //有效时间
        int expireSeconds = qrCodeReq.getExpireSeconds();
        expireSeconds = (expireSeconds == 0 ? qrcodeType.getDeadTime() : expireSeconds);
        Long deadTime = getDate(expireSeconds);
        // 发消息缓存一个二维码qrcodeCacheService.cache(qrcodeType, 1, expireSeconds, mpID);
        cacheQecode(mpID, expireSeconds, qrcodeType,1);
        // 根据mpID qrcodeType getDeadTime() 查询数据库
        String locdKey = WECHAT_QR_QUERY_CACHE_LOCK + COLON + mpID + COLON + qrcodeType.name();
        boolean isLock = redisLockHandler.tryLock(locdKey, LOCKED_TIME_OUT_SECONDS);
        if (!isLock){
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WAIT_LOCK_TIMEOUT, "等待同步锁超时，LOCKED_TIME_OUT：" + LOCKED_TIME_OUT_SECONDS + "s");
        }
        WechatQrcodeTempModel wechatQrcodeTempModel = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("mpID", mpID);
            params.put("deadTime", deadTime);
            params.put("size", SIZE);
            params.put("qrcodeType", qrcodeType.getValue());
            List<WechatQrcodeTempModel> qrcodeModelList = qrcodeTempMapper.queryCacheQrcode(params);
            int listSize = qrcodeModelList.size();
            if (listSize > 0) {
                wechatQrcodeTempModel = qrcodeModelList.get(0);
                wechatQrcodeTempModel.setQrcodeStatus(USED_QRCODE_STATUS)
                        .setQrcodeType(qrcodeType.getValue())
//                        .setDeadTime(deadTime)
                        .setDescription(qrCodeReq.getDescription())
                        .setLocationName(qrCodeReq.getLocationName())
                        .setParam1(qrCodeReq.getParam1())
                        .setParam2(qrCodeReq.getParam2())
                        .setParam3(qrCodeReq.getParam3())
                        .setShopID(qrCodeReq.getShopID())
                        .setQrcodeName(qrCodeReq.getQrcodeName());
                qrcodeTempMapper.updateByPrimaryKeySelective(wechatQrcodeTempModel);

                //返回参数
                WechatQRCodeRes wechatQRCodeRes = new WechatQRCodeRes();
                wechatQRCodeRes.setItemID(wechatQrcodeTempModel.getItemID());
                wechatQRCodeRes.setTicket(wechatQrcodeTempModel.getTicket());
                wechatQRCodeRes.setWxUrl(wechatQrcodeTempModel.getWxUrl());
                wechatQRCodeRes.setExpireSeconds(expireSeconds);
                wechatQRCodeRes.setParam1(qrCodeReq.getParam1());
                wechatQRCodeRes.setParam2(qrCodeReq.getParam2());
                wechatQRCodeRes.setParam3(qrCodeReq.getParam3());
                return wechatQRCodeRes;
            }

        } finally {
            if (redisLockHandler != null) {
                redisLockHandler.realseLock(locdKey);
            }
        }
        //如果缓存获取失败证明已经没有缓存，发送消息多缓存一次，这样总的缓存个数就增加一个，缓存够用就永远执行不到这一句。
        cacheQecode(mpID, expireSeconds, qrcodeType,1);

        //从redis获取场景值
        int tempSenceID = qrcodeCreateSceneIDService.getTempSenceID(mpID);
        JSONObject jsonObject = baseHttpService.createQrCode(getModel(expireSeconds, qrcodeType.getWechatType(), tempSenceID), mpID);

        if (!jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            return ResultUtil.getResultInfoBean(jsonObject, WechatQRCodeRes.class);
        }
        //插入数据库
        WechatQrcodeTempModel qrcodeTempModel = new WechatQrcodeTempModel();
        qrcodeTempModel.setMpID(mpID)
                //判断类型，根据类型给定默认有效时间
                .setQrcodeType(qrcodeType.getValue())
                .setDeadTime(getDate(jsonObject.getInteger("expire_seconds")))
                .setDescription(qrCodeReq.getDescription())
                .setLocationName(qrCodeReq.getLocationName())
                .setParam1(qrCodeReq.getParam1())
                .setParam2(qrCodeReq.getParam2())
                .setParam3(qrCodeReq.getParam3())
                .setShopID(qrCodeReq.getShopID())
                .setTicket(jsonObject.getString("ticket"))
                .setWxUrl(jsonObject.getString("url"))
                .setSceneID(tempSenceID)
                .setQrcodeName(qrCodeReq.getQrcodeName())
                .setQrcodeStatus(USED_QRCODE_STATUS);


        qrcodeTempMapper.insert(qrcodeTempModel);


        //返回参数
        WechatQRCodeRes wechatQRCodeRes = new WechatQRCodeRes();
        wechatQRCodeRes.setItemID(qrcodeTempModel.getItemID());
        wechatQRCodeRes.setTicket(qrcodeTempModel.getTicket());
        wechatQRCodeRes.setWxUrl(qrcodeTempModel.getWxUrl());
        wechatQRCodeRes.setExpireSeconds(expireSeconds);
        wechatQRCodeRes.setParam1(qrCodeReq.getParam1());
        wechatQRCodeRes.setParam2(qrCodeReq.getParam2());
        wechatQRCodeRes.setParam3(qrCodeReq.getParam3());
        return wechatQRCodeRes;
    }

    /**
     * 发送消息缓存微信二维码，没有足够缓存返回（00112145）
     * @param mpID
     * @param expireSeconds
     * @param qrcodeType
     */
    private void cacheQecode(String mpID, int expireSeconds, WechatQRTypeEnum qrcodeType,int cacheNo) {
        QrcodeInfoModel qrcodeInfo = new QrcodeInfoModel();
        qrcodeInfo.setMpID(mpID);
        qrcodeInfo.setCacheNo(cacheNo);
        qrcodeInfo.setExpireSeconds(expireSeconds);
        qrcodeInfo.setQrcodeType(qrcodeType);
//        rabbitTemplate.setReceiveTimeout(1000);
        rabbitTemplate.convertAndSend(rabbitQueueProps.getCacheQrcodeExchange(),
                null, JSONObject.toJSONString(qrcodeInfo));
    }

    /**
     * 批量获取缓存二维码，
     * @param qrCodeReqList
     * @return
     */
    @Override
    public WechatQRCodeListRes createQRCodeList(WechatQRCodeListReq qrCodeReqList) {
        //判断mpID,没有则调方法获取
        String mpID = qrCodeReqList.getMpID();
        if (StringUtils.isBlank(mpID)) {
            String brandID = qrCodeReqList.getBrandID();
            String groupID = qrCodeReqList.getGroupID();
            String shopID = qrCodeReqList.getShopID();
            if (StringUtils.isBlank(brandID) || StringUtils.isBlank(groupID)) {
                return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(Long.parseLong(groupID), Long.parseLong(brandID), Long.parseLong(shopID));
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_MP_NOT_EXIST, "获取mpID失败,或者公众号不存在！");
        }
        WechatQRTypeEnum qrcodeType = qrCodeReqList.getQrcodeType();
        if (qrcodeType == null){
            return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_QRCODE_TYPE_NULL, "必须提供一个期望获得的QrcodeType！");
        }
        Integer size = qrCodeReqList.getSize();
        if (size == null){
            return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_ARGUMENTS_NULL, "必须参数size不能为空");
        }
        List<WechatQRCodeData> wechatQRCodeDataList = qrCodeReqList.getWechatQRCodeDataList();
       if (wechatQRCodeDataList != null && size<wechatQRCodeDataList.size()){
            return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_ARGUMENTS_ILLEGALITY, "参数size不可以小于wechatQRCodeDataList.size()");
        }

        //有效时间校验
        int expireSeconds = qrCodeReqList.getExpireSeconds();
        Integer deadTime1 = qrcodeType.getDeadTime();
        Integer defaultTime = deadTime1 - 3600;
        expireSeconds = (expireSeconds == 0 ? defaultTime : expireSeconds);
        if (expireSeconds > defaultTime){
            return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_QRCODE_EXPIRESECONDS_OVERSTEP,
                    "获取缓存（"+qrcodeType.name()+"）类型二维码最大有效时间不能超过: "+defaultTime+"s.");
        }

        Long deadTime = getDate(expireSeconds);
        //获取缓存好的二维码
        // 根据mpID qrcodeType getDeadTime() 查询数据库
        String locdKey = WECHAT_QR_QUERY_CACHE_LOCK + COLON + mpID + COLON + qrcodeType.name();
        boolean isLock = redisLockHandler.tryLock(locdKey, LOCKED_TIME_OUT_SECONDS);
        log.debug(() -> "isLock:" + isLock);
        if (!isLock){
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WAIT_LOCK_TIMEOUT, "等待同步锁超时，LOCKED_TIME_OUT：" + LOCKED_TIME_OUT_SECONDS + "s");
        }
        List<WechatQrcodeTempModel> qrcodeModelList;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("mpID", mpID);
            params.put("deadTime", deadTime);
            params.put("size", size);
            params.put("qrcodeType", qrcodeType.getValue());
            int queryCacheQrcodeCount = qrcodeTempMapper.queryCacheQrcodeCount(params);


            if (queryCacheQrcodeCount < size){
                //查询Redis缓存二维码接口执行状态，如果有错误直接返回
                this.checkErrorCode( mpID );
                //缓存数量少于期望数量的一半，获取期望数量的缓存，
                //缓存数量多于期望数量的一半，获取期望数量一半的缓存。
                int i = size - queryCacheQrcodeCount;
                Double d = i > size / 2.0 ? size : size / 2.0;
                Double ceil = Math.ceil(d);
                // 发消息缓存二维码
                cacheQecode(mpID, expireSeconds, qrcodeType,ceil.intValue());
                //数量不够返回提示正在缓存稍后再试
                return new WechatQRCodeListRes().setResultInfo(ErrorCodes.WECHAT_QRCODE_NOT_ENOUGH,
                        "当前缓存数量不足，请稍后再试！");
            }
            // 发消息缓存二维码
            cacheQecode(mpID, expireSeconds, qrcodeType,size);
            qrcodeModelList = qrcodeTempMapper.queryCacheQrcode(params);
            ArrayList<WechatQRCodeRes> wechatQRCodeList = new ArrayList<>();
            for (int i = 0; i < size ; i++){
                WechatQrcodeTempModel wechatQrcodeTempModel = qrcodeModelList.get(i);
                wechatQrcodeTempModel.setQrcodeStatus(USED_QRCODE_STATUS);
//                        .setDeadTime(deadTime);
                if (i < wechatQRCodeDataList.size()){
                    WechatQRCodeData wechatQRCodeData = wechatQRCodeDataList.get(i);
                    String description = wechatQRCodeData.getDescription();
                    String locationName = wechatQRCodeData.getLocationName();
                    String param1 = wechatQRCodeData.getParam1();
                    String param2 = wechatQRCodeData.getParam2();
                    String param3 = wechatQRCodeData.getParam3();
                    String qrcodeName = wechatQRCodeData.getQrcodeName();
//                    String shopName = wechatQRCodeData.getShopName();
                    wechatQrcodeTempModel
                            .setDescription(description)
                            .setLocationName(locationName)
                            .setParam1(param1)
                            .setParam2(param2)
                            .setParam3(param3)
                            .setQrcodeName(qrcodeName);
                }
                qrcodeTempMapper.updateByPrimaryKeySelective(wechatQrcodeTempModel);
                WechatQRCodeRes wechatQRCodeRes = DataUtils.copyProperties(wechatQrcodeTempModel, WechatQRCodeRes.class);
                wechatQRCodeRes.setExpireSeconds(expireSeconds);
                wechatQRCodeList.add(wechatQRCodeRes);
            }
            WechatQRCodeListRes wechatQRCodeListRes = new WechatQRCodeListRes();
            wechatQRCodeListRes.setWechatQRCodeResList(wechatQRCodeList);
            return wechatQRCodeListRes;
        } finally {
            if (redisLockHandler != null) {
                redisLockHandler.realseLock(locdKey);
            }
        }
    }

    /**
     * 查询Redis缓存二维码接口执行状态，如果有错误直接返回WechatException
     * @param mpID
     */
    private void checkErrorCode(String mpID) {
        BoundValueOperations<String, String> ops
                = stringRedisTemplate2.boundValueOps( WECHAT_QRCODE_ERRO_CODE + mpID);
        String errorCode = ops.get();
        if (StringUtils.isNotBlank(errorCode)){
            // 判断错误是否解决，如果已解决删除错误标记
            AuthorizationCheckRpcService.AuthorizationCheckReq authorizationCheckReq = new AuthorizationCheckRpcService.AuthorizationCheckReq();
            authorizationCheckReq.setMpID( mpID );
            authorizationCheckReq.setInterfaceType( WechatFuctionEnum.TEMPORARY_QR_CODE );
            AuthorizationCheckRpcService.AuthorizationCheckRes check = getAuthorizationCheckRpcService.check( authorizationCheckReq );
            if (check.success()){
                stringRedisTemplate2.delete( WECHAT_QRCODE_ERRO_CODE + mpID );
            }else {
                throw new WechatException( WechatExceptionTypeEnum.parseEnum(errorCode));
            }
        }
    }

    @Override
    public WechatQRCodeRes createLoginQRCode(WechatQRCodeReq qrCodeReq) {

        //判断mpID,没有则调方法获取
        String mpID = qrCodeReq.getMpID();
        if (StringUtils.isBlank(mpID)) {
            String brandID = qrCodeReq.getBrandID();
            String groupID = qrCodeReq.getGroupID();
            String shopID = qrCodeReq.getShopID();
            if (StringUtils.isNotBlank(shopID) && StringUtils.isNotBlank(brandID) && StringUtils.isNotBlank(groupID)) {
                //通过上面三个属性获取mpID，调用方法待定；
                mpID = mpInfoService.queryMpIDAuth(Long.parseLong(groupID), Long.parseLong(brandID), Long.parseLong(shopID));
            }
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            mpID = qrcodeDefaultMpID;
        }
        WechatQRTypeEnum qrcodeType = qrCodeReq.getQrcodeType();
        if (qrcodeType == null){
            qrcodeType = WechatQRTypeEnum.LOGIN;
        }
        //有效时间
        int expireSeconds = qrCodeReq.getExpireSeconds();
        expireSeconds = (expireSeconds == 0 ? 30 : expireSeconds);
//        expireSeconds = (expireSeconds == 0 ? qrcodeType.getDeadTime() : expireSeconds);
        //从redis获取场景值
        int tempSenceID = qrcodeCreateSceneIDService.getTempSenceID(mpID);
        JSONObject jsonObject = baseHttpService.createQrCode(getModel(expireSeconds, qrcodeType.getWechatType(), tempSenceID), mpID);

        if (!jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            return ResultUtil.getResultInfoBean(jsonObject, WechatQRCodeRes.class);
        }
        //插入数据库
        WechatQrcodeTempModel qrcodeTempModel = new WechatQrcodeTempModel();
        qrcodeTempModel.setMpID(mpID)
                //判断类型，根据类型给定默认有效时间
                .setQrcodeType(qrcodeType.getValue())
                .setDeadTime(getDate(jsonObject.getInteger("expire_seconds")))
                .setDescription(qrCodeReq.getDescription())
                .setLocationName(qrCodeReq.getLocationName())
                .setParam1(qrCodeReq.getParam1())
                .setParam2(qrCodeReq.getParam2())
                .setParam3(qrCodeReq.getParam3())
                .setShopID(qrCodeReq.getShopID())
                .setTicket(jsonObject.getString("ticket"))
                .setWxUrl(jsonObject.getString("url"))
                .setSceneID(tempSenceID)
                .setQrcodeName(qrCodeReq.getQrcodeName())
                .setQrcodeStatus(USED_QRCODE_STATUS);
        qrcodeTempMapper.insert(qrcodeTempModel);

        //返回参数
        WechatQRCodeRes wechatQRCodeRes = new WechatQRCodeRes();
        wechatQRCodeRes.setItemID(qrcodeTempModel.getItemID());
        wechatQRCodeRes.setTicket(qrcodeTempModel.getTicket());
        wechatQRCodeRes.setWxUrl(qrcodeTempModel.getWxUrl());
        wechatQRCodeRes.setExpireSeconds(expireSeconds);
        wechatQRCodeRes.setParam1(qrCodeReq.getParam1());
        wechatQRCodeRes.setParam2(qrCodeReq.getParam2());
        wechatQRCodeRes.setParam3(qrCodeReq.getParam3());
        wechatQRCodeRes.setMpID(mpID);
        return wechatQRCodeRes;
    }

    @Override
    public UpdateWechatQrcodeRes uploadQRcodeParam(UpdateWechatQrcodeReq qrCodeReq) {
        Long itemID = qrCodeReq.getItemID();
        String param1 = qrCodeReq.getParam1();
        String param2 = qrCodeReq.getParam2();
        String param3 = qrCodeReq.getParam3();
        WechatQrcodeTempModel wechatQrcodeTempModel = new WechatQrcodeTempModel();
        wechatQrcodeTempModel.setItemID( itemID );
        wechatQrcodeTempModel.setParam1( param1 );
        wechatQrcodeTempModel.setParam2( param2 );
        wechatQrcodeTempModel.setParam3( param3 );
        wechatQrcodeTempModel.setDeadTime( getDate( 3600 * 50 ) );
        qrcodeTempMapper.updateByPrimaryKeySelective( wechatQrcodeTempModel );
        return ResultUtil.success(UpdateWechatQrcodeRes.class);
    }

    private Long getDate(Integer second) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, second);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.valueOf(sf.format(c.getTime()).toString());
    }

    /**
     * 获取二维码，微信返回三个参数
     *
     * @param expireSeconds
     * @param WechatType
     * @param tempSenceID
     * @return
     */
    private String getModel(int expireSeconds, String WechatType, int tempSenceID) {
        return "{\"expire_seconds\": " + expireSeconds + ", \"action_name\": \"" + WechatType + "\", \"action_info\": {\"scene\": {\"scene_id\": " + tempSenceID + "}}}";
    }
}
