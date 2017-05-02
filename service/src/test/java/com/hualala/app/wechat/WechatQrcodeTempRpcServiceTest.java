package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.impl.WechatQRCodeRpcSerivceImpl;
import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import com.hualala.app.wechat.service.HttpApiService;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renjianfei on 2017/4/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatQrcodeTempRpcServiceTest {

    @Autowired
    private WechatQrcodeTempMapper wechatTemplateMapper;
    @Autowired
    private WechatQRCodeRpcSerivceImpl wechatQRCodeRpcSerivce;

    @Autowired
    BaseRpcClient baseRpcClient;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(HttpApiService.class);


    @Test
    public void  test(){
        WechatQRCodeRpcSerivce rpcClient = baseRpcClient.getRpcClient(WechatQRCodeRpcSerivce.class);
        WechatQRCodeRpcSerivce.WechatQRCodeReq wechatQRCodeReq = new WechatQRCodeRpcSerivce.WechatQRCodeReq();
        wechatQRCodeReq.setQrcodeName("123");
        wechatQRCodeReq.setQrcodeType(WechatQRTypeEnum.QUEUE);
        wechatQRCodeReq.setParam1("5-76022695-A-5");
        wechatQRCodeReq.setParam2("测试二维码:Param2");
        wechatQRCodeReq.setParam3("测试二维码:Param3");
        wechatQRCodeReq.setExpireSeconds(3600*24);
        wechatQRCodeReq.setShopID("５");
        wechatQRCodeReq.setBrandID("5");
        wechatQRCodeReq.setGroupID("5");
        wechatQRCodeReq.setShopName("测试二维码:ShopName");
//        wechatQRCodeReq.setMpID("doulaofang");
        wechatQRCodeReq.setDescription("测试二维码:Description");
        wechatQRCodeReq.setLocationName("测试二维码:LocationName");

        WechatQRCodeRpcSerivce.WechatQRCodeRes qrCode = rpcClient.createQRCode(wechatQRCodeReq);
        String s = JSONObject.toJSONString(qrCode);
        System.out.println(s);
    }
}
