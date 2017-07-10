package com.hualala.app.wechat.Massage;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.WechatQRTypeEnum;
import com.hualala.app.wechat.config.RabbitQueueProps;
import com.hualala.app.wechat.model.mq.QrcodeInfoModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renjianfei on 2017/5/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitQueueProps rabbitQueueProps;
    @Test
    public void test(){
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <10 ; i++){
            QrcodeInfoModel qrcodeInfo = new QrcodeInfoModel();
            qrcodeInfo.setMpID("doulaofangceshi");
            qrcodeInfo.setCacheNo(1);
            qrcodeInfo.setQrcodeType(WechatQRTypeEnum.QUEUE);
            rabbitTemplate.convertAndSend("mu_hll:service:wechat:exchange:cacheQrcode",
                    null, JSONObject.toJSONString(qrcodeInfo));
        }

    }

}
