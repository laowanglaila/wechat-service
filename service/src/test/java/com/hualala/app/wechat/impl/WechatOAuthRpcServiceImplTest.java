package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatOAuthRpcService;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WechatOAuthRpcServiceImplTest {
    @Autowired
    public BaseRpcClient rpcClient;
    @Test
    public void wechatOAuthUrl() throws Exception {

        WechatOAuthRpcService wechatOAuthRpcService = rpcClient.getRpcClient(WechatOAuthRpcService.class);
        WechatOAuthRpcService.WechatOAuthReqData reqData = new  WechatOAuthRpcService.WechatOAuthReqData();
        reqData.setCallBackUrl("http://dohko.m.hualala.com");
        wechatOAuthRpcService.wechatOAuthUrl(reqData);

    }

}