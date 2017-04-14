package com.hualala.app.wechat.config.client;

import com.github.kristofa.brave.Brave;
import com.hualala.core.app.Logger;
import com.hualala.core.grpc.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by xiangbin on 2016/10/19.
 */
@Configuration
public class GrpcClientConfiguration {

    private Logger logger = Logger.of(GrpcClientConfiguration.class);

    @Autowired
    private GrpcClientProperties grpcClientProperties;

    @Bean(name = "com.hualala.app.wechat", destroyMethod = "clean")
    public GrpcClient grpcClient() {
        logger.info(() -> "Initializing com.hualala.app.pay gRpc client... [" + grpcClientProperties.getWechat() + "]");
        GrpcClient client = new GrpcClient(grpcClientProperties.getWechat());
        return client;
    }

    @Bean
    public GrpcIdgenClient idgenClient(Brave brave) {
        logger.info(() -> "initializing idgen client ... [" + grpcClientProperties.getIdgen() + "]");
        GrpcIdgenClient client = new GrpcIdgenClient(grpcClientProperties.getIdgen(), brave);
        return client;
    }

    @Bean (name = "com.hualala.message", destroyMethod="clean")
    public SemServiceClient semServiceClient() {
        logger.info(() -> "initializing idgen client ... [" + grpcClientProperties.getSem() + "]");
        SemServiceClient semServiceClient = new SemServiceClient(grpcClientProperties.getSem());
        return semServiceClient;
    }
}
