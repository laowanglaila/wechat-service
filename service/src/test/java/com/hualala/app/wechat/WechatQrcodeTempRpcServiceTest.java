package com.hualala.app.wechat;

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
    BaseRpcClient baseRpcClient;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(HttpApiService.class);


    @Test
    public void  test(){
        WechatQRCodeRpcSerivce rpcClient = baseRpcClient.getRpcClient(WechatQRCodeRpcSerivce.class);
        WechatQRCodeRpcSerivce.WechatQRCodeReq wechatQRCodeReq = new WechatQRCodeRpcSerivce.WechatQRCodeReq();
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i<40 ;i++) {
            wechatQRCodeReq.setQrcodeName("123");
            wechatQRCodeReq.setQrcodeType(WechatQRTypeEnum.QUEUE);

            String param1 = "任鉴非" + 0;
            wechatQRCodeReq.setParam1(param1);
            wechatQRCodeReq.setParam2("测试二维码:Param2");
            wechatQRCodeReq.setParam3("测试二维码:Param3");
            wechatQRCodeReq.setExpireSeconds(3600 * 12);
            wechatQRCodeReq.setShopID("５");
            wechatQRCodeReq.setBrandID("5");
            wechatQRCodeReq.setGroupID("5");
            wechatQRCodeReq.setShopName("测试二维码:ShopName");
//        wechatQRCodeReq.setMpID("doulaofang");
            wechatQRCodeReq.setDescription("测试二维码:Description");
            wechatQRCodeReq.setLocationName("测试二维码:LocationName");

            long start = System.currentTimeMillis();
            WechatQRCodeRpcSerivce.WechatQRCodeRes qrCode = rpcClient.createQRCode(wechatQRCodeReq);
            long end = System.currentTimeMillis();
            String param11 = qrCode.getParam1();
            System.out.println("-------------" + (end - start) + "ms------------------>" + param11);
//            list.add("-------------" + (end - start) + "ms------------------>" + param11);
//        }
//        System.out.println(list);
    }
}
