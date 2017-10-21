package com.hualala.app.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by renjianfei on 2017/5/15.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat.mq.rabbitmq")
public class RabbitQueueProps {

    private String cacheQrcodeExchange;
    private String cacheQrcodeQueue;

    private String templateMessageExchange;
    private String templateMessageQueue;

}
