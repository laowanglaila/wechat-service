package com.hualala.app.wechat.service.Qrcode;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import com.hualala.app.wechat.model.WechatQrcodeTempModel;
import com.hualala.app.wechat.service.AccessTokenService;
import com.hualala.app.wechat.service.BaseHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by renjianfei on 2017/5/12.
 */
@Service
public class QrcodeCacheService {
    @Autowired
    private QrcodeCreateSceneIDService qrcodeCreateSceneIDService;
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private WechatQrcodeTempMapper qrcodeTempMapper;

    private static Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    /**
     * 缓存二维码到数据库
     *
     * @param mpID          公众号唯一标识
     * @param qrcodeType    二维码类型
     * @param cacheNo       本次缓存个数
     * @param expireSeconds 二位码存活时间，单位秒
     */
    public void cache(WechatQRTypeEnum qrcodeType, int cacheNo, int expireSeconds, String mpID) {

        int tempSenceID = qrcodeCreateSceneIDService.getTempSenceID(mpID);
        String params = "{\"expire_seconds\": " + expireSeconds + "," +
                " \"action_name\": \"" + qrcodeType.getWechatType() + "\"," +
                " \"action_info\": {\"scene\": {\"scene_id\": " + tempSenceID + "}}}";
        JSONObject jsonObject = baseHttpService.createQrCode(params, mpID);
        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
//            //插入数据库
            WechatQrcodeTempModel qrcodeTempModel = new WechatQrcodeTempModel();
            qrcodeTempModel
                    .setMpID(mpID)
                    .setQrcodeType(qrcodeType.getValue())
                    .setDeadTime(getDate(expireSeconds))
                    .setTicket(jsonObject.getString("ticket"))
                    .setWxUrl(jsonObject.getString("url"))
                    .setSceneID(tempSenceID)
                    .setQrcodeStatus(1);
            qrcodeTempMapper.insert(qrcodeTempModel);
        } else {
            logger.warn("二维码缓存获取失败：" + jsonObject.toJSONString());
        }


    }

    private Long getDate(Integer second) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, second);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.valueOf(sf.format(c.getTime()).toString());
    }

}
