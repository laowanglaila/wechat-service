package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.WechatAccessTokenRpcService;
import com.hualala.app.wechat.grpc.WechatAccessTokenRpcData;
import com.hualala.app.wechat.grpc.WechatAccessTokenRpcServiceGrpc;
import com.hualala.core.grpc.GrpcClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/11/24.
 */
public class WechatAccessTokenRpcServiceImplTest extends BaseRpcTest {
    @Autowired
    @Qualifier("com.hualala.app.wechat")
    private GrpcClient grpcClient;
    @Override
    public void test() throws InterruptedException {
        WechatAccessTokenRpcService rpcClient = super.baseRpcClient.getRpcClient( WechatAccessTokenRpcService.class );
        WechatAccessTokenRpcService.AccessTokenReq accessTokenReq = new WechatAccessTokenRpcService.AccessTokenReq();
        accessTokenReq.setMpID( "doulaofangceshi" );
        WechatAccessTokenRpcService.AccessTokenRes accessToken = rpcClient.getAccessToken( accessTokenReq );
        Assert.assertEquals( "000", accessToken.getCode());
    }
    @Test
    public void test2() throws InterruptedException, ExecutionException {
        WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub futureStub = (WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub)grpcClient.getFutureStub( WechatAccessTokenRpcServiceGrpc.class );
        WechatAccessTokenRpcData.AccessTokenReq accessTokenReq = WechatAccessTokenRpcData.AccessTokenReq.newBuilder().setMpID( "doulaofangceshi" ).build();
        WechatAccessTokenRpcData.AccessTokenRes accessTokenRes = futureStub.getAccessToken( accessTokenReq ).get();
        Assert.assertEquals( "000", accessTokenRes.getResult().getCode());

    }
}
