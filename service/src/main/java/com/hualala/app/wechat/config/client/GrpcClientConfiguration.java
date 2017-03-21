package com.hualala.app.wechat.config.client;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.LoggingReporter;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.mysql.MySQLStatementInterceptorManagementBean;
import com.hualala.core.app.Logger;
import com.hualala.core.config.zipkin.ZipkinProperties;
import com.hualala.core.grpc.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.urlconnection.URLConnectionSender;

/**
 * Created by xiangbin on 2016/10/19.
 */
@Configuration
public class GrpcClientConfiguration {

    private Logger logger = Logger.of(GrpcClientConfiguration.class);

    @Autowired
    private GrpcClientProperties grpcClientProperties;

//    @Bean(name = "com.hualala.app.wechat", destroyMethod = "clean")
//    public GrpcClient grpcClient(Brave brave) {
//        logger.info(() -> "Initializing com.hualala.app.pay gRpc client... [" + grpcClientProperties.getWechat() + "]");
//        GrpcClient client = new GrpcClient(grpcClientProperties.getWechat(), brave);
//        return client;
//    }

//    @Bean
//    public GrpcIdgenClient idgenClient(Brave brave) {
//        logger.info(() -> "initializing idgen client ... [" + grpcClientProperties.getIdgen() + "]");
//        GrpcIdgenClient client = new GrpcIdgenClient(grpcClientProperties.getIdgen(), brave);
//        return client;
//    }

}
