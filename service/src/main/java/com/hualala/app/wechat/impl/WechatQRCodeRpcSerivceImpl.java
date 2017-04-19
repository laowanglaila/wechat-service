package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.WechatQRCodeRpcSerivce;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatQrcodeMapper;
import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import com.hualala.app.wechat.model.WechatQrcodeTempModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.app.Logger;
import com.hualala.core.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/4/12.
 */
@Service
public class WechatQRCodeRpcSerivceImpl implements WechatQRCodeRpcSerivce {

    private Logger logger = Logger.of(WechatQRCodeRpcSerivceImpl.class);

    @Autowired
    private WechatQrcodeTempMapper qrcodeTempMapper;

    @Autowired
    private WechatQrcodeMapper qrcodeMapper;

//    @Autowired
//    private RedisTemplate<String,Integer> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BaseHttpService baseHttpService;

    @Autowired
    private MpInfoService mpInfoService;

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
        //从redis获取场景值
        int tempSenceID = getTempSenceID(mpID);


        JSONObject jsonObject = baseHttpService.createQrCode(getModel(expireSeconds,qrcodeType.getWechatType(),tempSenceID), mpID);

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
                .setQrcodeName(qrCodeReq.getQrcodeName());

        qrcodeTempModel.setAction(0);
        qrcodeTempModel.setCreateTime(DateUtils.getCurrentDateTimeLong());
        qrcodeTempMapper.insert(qrcodeTempModel);


        //返回参数
        WechatQRCodeRes wechatQRCodeRes = new WechatQRCodeRes();
        wechatQRCodeRes.setTicket(qrcodeTempModel.getTicket());
        wechatQRCodeRes.setWxUrl(qrcodeTempModel.getWxUrl());
        wechatQRCodeRes.setExpireSeconds(qrCodeReq.getExpireSeconds());
        wechatQRCodeRes.setParam1(qrCodeReq.getParam1());
        wechatQRCodeRes.setParam2(qrCodeReq.getParam2());
        wechatQRCodeRes.setParam3(qrCodeReq.getParam3());
        return wechatQRCodeRes;
    }

    //    public int getTempSenceID(){
////        临时Wechat_SenceID_Temp
//        BoundValueOperations<String, Integer> ops = redisTemplate.boundValueOps("Wechat_SenceID_Temp");
//        //获取去之前先判断redis是否存在key值==0
//        if(ops.get() == 0 ){
//            //加锁，数据库获取SenceID最大值
//            synchronized (qrcodeTempMapper){
//                //获取SenceID最大值存入redis
//                int i = qrcodeTempMapper.queryMacSenceID();
//                if(ops.get() == 0){
//                    ops.set(i);
//                }
//            }
//        }
//        return ops.increment(1L).intValue();
//    }
    private int getTempSenceID(String mpID) {
//        临时Wechat_SenceID_Temp
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps("Wechat_SenceID_Temp:" + mpID);
        //获取去之前先判断redis是否存在key值==0
        if (StringUtils.isBlank(ops.get())) {
            //加分布式锁，数据库获取SenceID最大值
            //获取SenceID最大值存入redis
            int i = qrcodeTempMapper.queryMaxSenceID(mpID);
            if (StringUtils.isBlank(ops.get())) {
                ops.set("" + i);
            }
        }
        return ops.increment(1L).intValue();
    }

    private Long getDate(Integer second) {
        long currentDateTimeLong = DateUtils.getCurrentDateTimeLong();
        return currentDateTimeLong + second * 1000L;
    }

    /**
     * 获取二维码，微信返回三个参数
     * @param expireSeconds
     * @param WechatType
     * @param tempSenceID
     * @return
     */
    private String getModel(int expireSeconds, String WechatType, int tempSenceID) {
        return "{\"expire_seconds\": " + expireSeconds + ", \"action_name\": \"" + WechatType + "\", \"action_info\": {\"scene\": {\"scene_id\": " + tempSenceID + "}}}";
    }
}
