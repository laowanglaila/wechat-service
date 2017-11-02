package com.hualala.app.wechat.impl.user;

import com.hualala.app.user.UserInfoWechatRpcService;
import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
import org.junit.Test;

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

    @Test
   public void test1() {

       UserInfoWechatRpcService rpcClient = baseRpcClient.getRpcClient( UserInfoWechatRpcService.class );
       UserInfoWechatRpcService.UserInfoWechatAddReqData userInfoWechatAddReqData = new UserInfoWechatRpcService.UserInfoWechatAddReqData();
       UserInfoWechatRpcService.UserInfoWechat userInfoWechat1 = new UserInfoWechatRpcService.UserInfoWechat();
       userInfoWechat1.setCity( "墨西哥城" );
       userInfoWechat1.setCountry( "墨西哥" );
       userInfoWechat1.setHeadImgUrl( "" );
       userInfoWechat1.setNickName( "麦克阿瑟" );
       userInfoWechat1.setProvince( "" );
       userInfoWechat1.setSex( "1" );
       userInfoWechat1.setUnionID( "1" );
       userInfoWechat1.setUserID( 123123123L );

       userInfoWechatAddReqData.setUserInfoWechat( userInfoWechat1 );
       UserInfoWechatRpcService.UserInfoWechatResData userInfoWechatResData = rpcClient.userInfoWechatAdd( userInfoWechatAddReqData );
       System.out.println(userInfoWechatResData.getMessageParams());
    }

}
