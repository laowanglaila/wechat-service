package com.hualala.app.wechat.impl.user;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.UserAuthorizationRpcService;

/**
 * Created by renjianfei on 2017/9/25.
 */
public class UserAuthorizationRpcServiceTest extends BaseRpcTest {
    @Override
    public void test() throws InterruptedException {
        String mpID = "hualala_com";
        UserAuthorizationRpcService rpcClient = super.baseRpcClient.getRpcClient( UserAuthorizationRpcService.class );
        UserAuthorizationRpcService.UserAuthorizationReq userAuthorizationReq = new UserAuthorizationRpcService.UserAuthorizationReq();
        userAuthorizationReq.setMpID( mpID );
        userAuthorizationReq.setCode( "011EqQoe1pFU5u0JrNpe1YaOoe1EqQow" );
        UserAuthorizationRpcService.UserAuthorizationRes userAuthorizationRes = rpcClient.userAuthorization( userAuthorizationReq );
        System.out.println(userAuthorizationRes.getMessageParams());
    }
}
