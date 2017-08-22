package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatMessageRpcService;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WechatMessageRpcServiceImplTest {

    @Autowired
    BaseRpcClient rpcClient;

    @Test
    public void WechatMessageSendTest(){

        WechatMessageRpcService wechatMessageRpcService = rpcClient.getRpcClient(WechatMessageRpcService.class);

        WechatMessageRpcService.WechatMessageReqData reqData = new WechatMessageRpcService.WechatMessageReqData();

       // reqData.setGroupID(5);
        reqData.setMpID("doulaofangceshi");
        //reqData.setOpenID("o7FjEuGrxL0V1qnfz2mxNCUhNxJY");
        reqData.setUserID(3574686);
        reqData.setContent("客服消息测试");
        wechatMessageRpcService.wechatMessageSend(reqData);
    }


}