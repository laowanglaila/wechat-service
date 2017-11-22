package com.hualala.app.wechat.impl.user;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.UserAuthorizationRpcService;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
import org.junit.Test;

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
    @Test
    public void test2() throws InterruptedException {
        String mpID = "hualala_com";
        UserGetUserInfoRpcService rpcClient = super.baseRpcClient.getRpcClient( UserGetUserInfoRpcService.class );
        UserGetUserInfoRpcService.UserInfoReqData userInfoReqData = new UserGetUserInfoRpcService.UserInfoReqData();
        userInfoReqData.setMpID( "1" );
        userInfoReqData.setUserID( 1L );
        userInfoReqData.setOpenID( "1" );
        UserGetUserInfoRpcService.UserInfoResData userInfo = rpcClient.findUserInfo( userInfoReqData );
        System.out.println(userInfo.getMessageParams());
    }
}
