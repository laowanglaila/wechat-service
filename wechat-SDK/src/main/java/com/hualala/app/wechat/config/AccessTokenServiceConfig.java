package com.hualala.app.wechat.config;

import com.hualala.app.wechat.api.WxGroupMpService;
import com.hualala.app.wechat.api.impl.WxGroupMpServiceOkHttpImpl;
import com.hualala.app.wechat.grpc.WechatAccessTokenRpcServiceGrpc;
import com.hualala.core.grpc.GrpcClient;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by renjianfei on 2017/10/30.
 */
@Slf4j
@Configuration
public class AccessTokenServiceConfig {
    @Value( "${wechatsdk.host}" )
    private String host;

    @Bean(name = "accessTokenClient", destroyMethod = "clean")
    public GrpcClient grpcClient() {
        log.info("Initializing com.hualala.app.wechat.accessToken gRpc client... [" + host + "]");
        GrpcClient client = new GrpcClient(host);
        return client;
    }


    //    grpc stream test
    @Bean(name = "channel",destroyMethod = "shutdown")
    public Channel channel() {
        return ManagedChannelBuilder.forTarget(host).usePlaintext(true).build();
    }
    //grpc stream test
    @Bean(name = "wechatSDKAccessTokenRpcServiceFutureStub")
    public WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub sdkaccessTokenRpcServiceFutureStub(@Qualifier("channel") Channel client)  {
        return WechatAccessTokenRpcServiceGrpc.newFutureStub( client );
    }

    @Bean
    @ConditionalOnMissingBean
    public WxGroupMpService wxMpService(@Qualifier("wechatSDKAccessTokenRpcServiceFutureStub")  WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub stub) {
        WxGroupMpService wxMpService = new WxGroupMpServiceOkHttpImpl(stub);
        return wxMpService;
    }
}
