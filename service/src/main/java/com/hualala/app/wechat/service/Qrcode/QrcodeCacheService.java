package com.hualala.app.wechat.service.Qrcode;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.sdk.mp.common.RedisKeys;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import com.hualala.app.wechat.model.WechatQrcodeTempModel;
import com.hualala.app.wechat.service.BaseHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by renjianfei on 2017/5/12.
 */
@Service
public class QrcodeCacheService implements RedisKeys{
    @Autowired
    private QrcodeCreateSceneIDService qrcodeCreateSceneIDService;
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private WechatQrcodeTempMapper qrcodeTempMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate2;



    private static Logger logger = LoggerFactory.getLogger(QrcodeCacheService.class);

    /**
     * 缓存二维码到数据库
     *
     * @param mpID          公众号唯一标识
     * @param qrcodeType    二维码类型
     * @param cacheNo       本次缓存个数
     * @param expireSeconds 二位码存活时间，单位秒
     */
    public void cache(WechatQRTypeEnum qrcodeType, int cacheNo, int expireSeconds, String mpID) {
        for (int i = 0;i < cacheNo; i++) {
            switch (qrcodeType) {
                case INVOICE:
                    expireSeconds = qrcodeType.getDeadTime();
                    break;
                case QUEUE:
                    expireSeconds = qrcodeType.getDeadTime();
                    break;
                case LOGIN:
                    expireSeconds = qrcodeType.getDeadTime();
            }
            int tempSenceID = qrcodeCreateSceneIDService.getTempSenceID(mpID);
            String params = "{\"expire_seconds\": " + expireSeconds + "," +
                    " \"action_name\": \"" + qrcodeType.getWechatType() + "\"," +
                    " \"action_info\": {\"scene\": {\"scene_id\": " + tempSenceID + "}}}";
            JSONObject jsonObject = null;
            try {
                jsonObject = baseHttpService.createQrCode(params, mpID);
            }catch (WechatException e){
                logger.warn("二维码缓存获取失败：",e);
                //接口没有授权的错误放入redis，再次请求时返回错误
                if (WechatExceptionTypeEnum.WECHAT_MP_PERMISSION_DENIED.getCode().equals( e.getErrorCode() )){
                    BoundValueOperations<String, String> ops
                            = stringRedisTemplate2.boundValueOps( WECHAT_QRCODE_ERRO_CODE + mpID);
                    ops.set(WechatExceptionTypeEnum.WECHAT_MP_PERMISSION_DENIED.getCode(),10, TimeUnit.MINUTES);
                    return;
                }
                continue;
            }
            if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
//            //插入数据库
                WechatQrcodeTempModel qrcodeTempModel = new WechatQrcodeTempModel();
                qrcodeTempModel
                        .setMpID(mpID)
                        .setQrcodeType(qrcodeType.getValue())
                        .setDeadTime(getCacheDate(jsonObject.getInteger("expire_seconds")))
                        .setTicket(jsonObject.getString("ticket"))
                        .setWxUrl(jsonObject.getString("url"))
                        .setSceneID(tempSenceID)
                        .setQrcodeStatus(1);
                qrcodeTempMapper.insert(qrcodeTempModel);
            } else {
                logger.warn("二维码缓存获取失败：" + jsonObject.toJSONString());

            }
        }
    }

    private Long getCacheDate(Integer second) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, second);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.valueOf(sf.format(c.getTime()).toString());
    }

}
