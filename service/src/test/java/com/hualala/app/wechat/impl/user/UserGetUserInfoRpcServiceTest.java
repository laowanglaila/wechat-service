package com.hualala.app.wechat.impl.user;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.UserGetUserInfoRpcService;

/**
 * Created by renjianfei on 2017/8/17.
 */
public class UserGetUserInfoRpcServiceTest extends BaseRpcTest {
    public static Integer times = 1000;

    @Override
    public void test() throws InterruptedException {
        UserGetUserInfoRpcService rpcClient = baseRpcClient.getRpcClient(UserGetUserInfoRpcService.class);
        UserGetUserInfoRpcService.UserInfoReqData userInfoReqData = new UserGetUserInfoRpcService.UserInfoReqData();
        userInfoReqData.setMpID("wangxiangyuanceshi");
        userInfoReqData.setOpenID("oD8-BwMSDDhlbWKfGgMy2p7GLd9s");
        UserGetUserInfoRpcService.UserInfoResData userInfoByOpenID = rpcClient.getUserInfoByOpenID(userInfoReqData);
        System.out.println((times--) + "-------------------" + userInfoByOpenID.toString());
    }

}
