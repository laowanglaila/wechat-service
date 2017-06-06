package com.hualala.app.wechat.config.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 所有需要的Grpc客户端
 * Created by xiangbin on 2016/10/19.
 */
@Data
@Component
@ConfigurationProperties(prefix = "grpc.client.hosts")
public class GrpcClientProperties {
    private String wechat;
//    private String idgen;
    private String sem;
}
