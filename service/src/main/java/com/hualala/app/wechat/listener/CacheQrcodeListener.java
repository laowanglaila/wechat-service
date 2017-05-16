package com.hualala.app.wechat.listener;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.config.RabbitQueueProps;
import com.hualala.app.wechat.service.Qrcode.QrcodeCacheService;
import com.hualala.core.app.Logger;
import com.hualala.core.client.BaseRpcClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/5/15.
 */
@Service
public class CacheQrcodeListener {

    private Logger logger = Logger.of(CacheQrcodeListener.class);
    @Autowired
    private QrcodeCacheService qrcodeCacheService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitQueueProps rabbitQueueProps;
    @Autowired
    private BaseRpcClient baseRpcClient;
    /**
     * consumer
     * @param message
     */
    @RabbitListener(containerFactory="rabbitListenerContainerFactory", queues = "${wechat.mq.rabbitmq.cacheQrcodeQueue}")
    public void cacheQrcode(Message message) {
        try {
            String cacheQrcodeMsg = new String(message.getBody());
            logger.info(() -> "orderNotify [" + cacheQrcodeMsg + "]");
            JSONObject jsonObject = JSONObject.parseObject(cacheQrcodeMsg);
            String qrcodeType = jsonObject.getString("qrcodeType");
            WechatQRTypeEnum wechatQRTypeEnum = null;
            if(StringUtils.isNotBlank(qrcodeType)){
                wechatQRTypeEnum = WechatQRTypeEnum.valueOf(qrcodeType);
            }
            Integer cacheNo = jsonObject.getInteger("cacheNo");
            Integer expireSeconds = jsonObject.getInteger("expireSeconds");
            String mpID = jsonObject.getString("mpID");

            qrcodeCacheService.cache(wechatQRTypeEnum,cacheNo,expireSeconds,mpID);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("caccheQrcode error", e);
        }
    }

}
