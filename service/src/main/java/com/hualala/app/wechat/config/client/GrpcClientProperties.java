package com.hualala.app.wechat.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 所有需要的Grpc客户端
 * Created by xiangbin on 2016/10/19.
 */
@lombok.Data
@Component
@ConfigurationProperties(prefix = "grpc.client.hosts")
public class GrpcClientProperties {
    private String wechat;
    private String idgen;
}
