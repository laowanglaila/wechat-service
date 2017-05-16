package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.WechatQRCodeRpcSerivce;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.config.RabbitQueueProps;
import com.hualala.app.wechat.mapper.WechatQrcodeMapper;
import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import com.hualala.app.wechat.model.WechatQrcodeTempModel;
import com.hualala.app.wechat.model.mq.QrcodeInfoModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.Qrcode.QrcodeCacheService;
import com.hualala.app.wechat.service.Qrcode.QrcodeCreateSceneIDService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.app.Logger;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by renjianfei on 2017/4/12.
 */
@Service
class WechatQRCodeRpcSerivceImpl implements WechatQRCodeRpcSerivce {

    private static final String WECHAT_QR_QUERY_CACHE_LOCK = "Wechat_QrCode_QueryLock";
    private static final String COLON = ":";
    private Logger logger = Logger.of(WechatQRCodeRpcSerivceImpl.class);
    private static final int SIZE = 1;
    private static final int USED_QRCODE_STATUS = 2;
    private static final int LOCKED_TIME_OUT = 10;

    @Autowired
    private WechatQrcodeTempMapper qrcodeTempMapper;

    @Autowired
    private QrcodeCreateSceneIDService qrcodeCreateSceneIDService;

    @Autowired
    private BaseHttpService baseHttpService;

    @Autowired
    private MpInfoService mpInfoService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitQueueProps rabbitQueueProps;

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
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }

        WechatQRTypeEnum qrcodeType = qrCodeReq.getQrcodeType();

        //有效时间
        int expireSeconds = qrCodeReq.getExpireSeconds();
        expireSeconds = (expireSeconds == 0 ? qrcodeType.getDeadTime() : expireSeconds);
        Long deadTime = getDate(expireSeconds);
        // 发消息缓存一个二维码qrcodeCacheService.cache(qrcodeType, 1, expireSeconds, mpID);
        cacheQecode(mpID, expireSeconds, qrcodeType,1);
        // 根据mpID qrcodeType getDeadTime() 查询数据库
        RLock locker = redissonClient.getLock(WECHAT_QR_QUERY_CACHE_LOCK + COLON + mpID + COLON + qrcodeType.name());
        WechatQrcodeTempModel wechatQrcodeTempModel = null;
        try {
            locker.lock(LOCKED_TIME_OUT, TimeUnit.SECONDS);
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
                        .setDeadTime(deadTime)
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
                wechatQRCodeRes.setExpireSeconds(qrCodeReq.getExpireSeconds());
                wechatQRCodeRes.setParam1(qrCodeReq.getParam1());
                wechatQRCodeRes.setParam2(qrCodeReq.getParam2());
                wechatQRCodeRes.setParam3(qrCodeReq.getParam3());
                return wechatQRCodeRes;
            }

        } finally {
            if (locker != null) {
                locker.unlock();
            }
        }
        //如果缓存获取失败证明已经没有缓存，发送消息多缓存一次，这样总的缓存个数就增加一个，缓存够用接永远执行不到这一句。
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
                .setDeadTime(getDate(expireSeconds))
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
     * 发送消息缓存微信二维码
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
        rabbitTemplate.convertAndSend(rabbitQueueProps.getCacheQrcodeExchange(),
                null, JSONObject.toJSONString(qrcodeInfo));
    }

    @Override
    public WechatQRCodeListRes createQRCodeList(WechatQRCodeListReq qrCodeReqList) {
        //判断mpID,没有则调方法获取
        String mpID = qrCodeReqList.getMpID();
        if (StringUtils.isBlank(mpID)) {
            String brandID = qrCodeReqList.getBrandID();
            String groupID = qrCodeReqList.getGroupID();
            String shopID = qrCodeReqList.getShopID();
            if (StringUtils.isBlank(brandID) || StringUtils.isBlank(groupID)) {
                return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(Long.parseLong(groupID), Long.parseLong(brandID), Long.parseLong(shopID));
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }
        WechatQRTypeEnum qrcodeType = qrCodeReqList.getQrcodeType();
        Integer size = qrCodeReqList.getSize();
        if (size == null){
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_ARGUMENTS_NULL, "必须参数size不能为空");
        }
        List<WechatQRCodeData> wechatQRCodeDataList = qrCodeReqList.getWechatQRCodeDataList();
       if (wechatQRCodeDataList != null && size<wechatQRCodeDataList.size()){
            return new WechatQRCodeRes().setResultInfo(ErrorCodes.WECHAT_ARGUMENTS_ILLEGALITY, "参数size不可以小于wechatQRCodeDataList.size()");
        }

        //有效时间
        int expireSeconds = qrCodeReqList.getExpireSeconds();
        expireSeconds = (expireSeconds == 0 ? qrcodeType.getDeadTime() : expireSeconds);
        // 发消息缓存二维码
        cacheQecode(mpID, expireSeconds, qrcodeType,size);
        Long deadTime = getDate(expireSeconds);
        //获取缓存好的二维码
        // 根据mpID qrcodeType getDeadTime() 查询数据库
        RLock locker = redissonClient.getLock(WECHAT_QR_QUERY_CACHE_LOCK + COLON + mpID + COLON + qrcodeType.name());
        List<WechatQrcodeTempModel> qrcodeModelList;
        List<WechatQrcodeTempModel> wechatQrcodeTempModels = new ArrayList<>();
        try {
            locker.lock(LOCKED_TIME_OUT, TimeUnit.SECONDS);
            Map<String, Object> params = new HashMap<>();
            params.put("mpID", mpID);
            params.put("deadTime", deadTime);
            params.put("size", size);
            params.put("qrcodeType", qrcodeType.getValue());
            qrcodeModelList = qrcodeTempMapper.queryCacheQrcode(params);

            for (WechatQrcodeTempModel wechatQrcodeTempModel:qrcodeModelList){
                wechatQrcodeTempModel.setQrcodeStatus(USED_QRCODE_STATUS)
                        .setDeadTime(deadTime);
                qrcodeTempMapper.updateByPrimaryKeySelective(wechatQrcodeTempModel);
                wechatQrcodeTempModels.add(wechatQrcodeTempModel);
            }

        } finally {
            if (locker != null) {
                locker.unlock();
            }
        }
        if (wechatQrcodeTempModels.size() < size){
            //如果缓存获取失败证明已经没有缓存，发送消息多缓存一次，这样总的缓存个数就增加一个，缓存够用接永远执行不到这一句。
            cacheQecode(mpID, expireSeconds, qrcodeType,1);

            //从redis获取场景值
            int tempSenceID = qrcodeCreateSceneIDService.getTempSenceID(mpID);
            JSONObject jsonObject = baseHttpService.createQrCode(getModel(expireSeconds, qrcodeType.getWechatType(), tempSenceID), mpID);

            if (!jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
                WechatQRCodeRes resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, WechatQRCodeRes.class);
            }
        }

        return null;
    }


//    private int getTempSenceID(String mpID) {
////        临时Wechat_SenceID_Temp
//        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps("Wechat_SenceID_Temp:" + mpID);
//        //获取去之前先判断redis是否存在key值==0
//        if (StringUtils.isBlank(ops.get())) {
//            //加分布式锁，数据库获取SenceID最大值
//            //获取SenceID最大值存入redis
//            int i = qrcodeTempMapper.queryMaxSenceID(mpID);
//            if (StringUtils.isBlank(ops.get())) {
//                ops.set("" + i);
//            }
//        }
//        return ops.increment(1L).intValue();
//    }


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
