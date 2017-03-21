package com.hualala.app.wechat.config.client;

import com.hualala.core.grpc.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiangbin on 2016/10/19.
 */
@Configuration
public class GrpcClientConfiguration {

    private Logger logger = LoggerFactory.getLogger(GrpcClientConfiguration.class);

    @Autowired
    private GrpcClientProperties grpcClientProperties;

    @Bean(name = "com.hualala.app.wechat", destroyMethod = "clean")
    public GrpcClient grpcClient() {
        logger.info("Initializing com.hualala.app.shop gRpc client...");
        GrpcClient client = new GrpcClient(grpcClientProperties.getWechat());
        return client;
    }

//    @Bean(name = "com.hualala.app.shop.crm", destroyMethod = "clean")
//    public GrpcClient crmGrpcClient() {
//        logger.info("Initializing com.hualala.app.shop.crm gRpc client...");
//        GrpcClient client = new GrpcClient(grpcClientProperties.getExample());
//        return client;
//    }
}
