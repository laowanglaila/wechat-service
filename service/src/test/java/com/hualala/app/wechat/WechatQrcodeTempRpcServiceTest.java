package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.service.HttpApiService;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/13.
 */
//@RunWith(SpringRunner.class)
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

    /**
     * 获取多个缓存二维码
     */
    @Test
    public void  test1(){
        WechatQRCodeRpcSerivce rpcClient = baseRpcClient.getRpcClient(WechatQRCodeRpcSerivce.class);
        WechatQRCodeRpcSerivce.WechatQRCodeListReq wechatQRCodeListReq = new WechatQRCodeRpcSerivce.WechatQRCodeListReq();
        wechatQRCodeListReq.setMpID("doulaofangceshi");
        wechatQRCodeListReq.setQrcodeType(WechatQRTypeEnum.QUEUE);
        wechatQRCodeListReq.setExpireSeconds(3600*24);
        wechatQRCodeListReq.setSize(15);
        List<WechatQRCodeRpcSerivce.WechatQRCodeData> list = new ArrayList<>();
        for (int i = 0 ; i < 3; i++) {
            WechatQRCodeRpcSerivce.WechatQRCodeData wechatQRCodeData = new WechatQRCodeRpcSerivce.WechatQRCodeData();
            wechatQRCodeData.setQrcodeName("QrcodeName");
            wechatQRCodeData.setParam1("param1");
            list.add(wechatQRCodeData);
        }
        wechatQRCodeListReq.setWechatQRCodeDataList(list);
        long start = System.currentTimeMillis();
        WechatQRCodeRpcSerivce.WechatQRCodeListRes qrCodeList = rpcClient.createQRCodeList(wechatQRCodeListReq);
        long end = System.currentTimeMillis();
        System.out.println("--------------"+(end-start)+"ms");
        System.out.println(qrCodeList.getMessage());
        System.out.println(qrCodeList.getWechatQRCodeResList()==null?0:qrCodeList.getWechatQRCodeResList().size());
        try {
            Thread.sleep(300000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取去一个二维码Http接口测试
     */
    @Test
    public void  test3(){

        WechatQRCodeRpcSerivce.WechatQRCodeReq wechatQRCodeReq = new WechatQRCodeRpcSerivce.WechatQRCodeReq();

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

        String jsonString = JSONObject.toJSONString(wechatQRCodeReq);
        long start = System.currentTimeMillis();
//        JSONObject jsonObject = HttpApiUtil.httpPost("http://127.0.0.1:8090/wechat/getQrcode.html", jsonString);
        JSONObject jsonObject = HttpApiUtil.httpGet("http://127.0.0.1:8090/wechat/getQrcode.html" +
//                "?async=true"+
                "?brandID=5" +
//                "&description=Description" +
//                "&expireSeconds=43200" +
                "&groupID=5" +
//                 &locationName=LocationName" +
//                "&param1=任鉴非0" +
//                "&param2=Param2" +
//                "&param3=Param3" +
//                "&qrcodeName=123" +
                "&qrcodeType=QUEUE" +
                "&shopID=５");
        long end = System.currentTimeMillis();
String s = "http://127.0.0.1:8090/wechat/getQrcode.html?async=true&brandID=5&description=Description&expireSeconds=43200&groupID=5&locationName=LocationName&param1=任鉴非0&param2=Param2&param3=Param3&qrcodeName=123&qrcodeType=QUEUE&shopID=５&shopName=ShopName";
        System.out.println("-------------" + (end - start) + "ms------------------>" +jsonObject.toJSONString());

    }


    /**
     * Http接口获取多个缓存二维码
     */
    @Test
    public void  test4(){
        long start = System.currentTimeMillis();
        Map<String,String> params = new HashMap<>();
        params.put("async","false");
        params.put("expireSeconds","86400");
        params.put("size","5");
        params.put("mpID","doulaofangceshi");
        params.put("qrcodeType","QUEUE");
        params.put("wechatQRCodeDataList",
                "[" +
                        "{\"param1\":\"param1\",\"qrcodeName\":\"QrcodeName\"}," +
                        "{\"param1\":\"param1\",\"qrcodeName\":\"QrcodeName\"}," +
                        "{\"param1\":\"param1\",\"qrcodeName\":\"QrcodeName\"}" +
                "]");

        JSONObject jsonObject = HttpApiUtil.httpPost("http://127.0.0.1:8090/wechat/getQrcodeList.html", params);
        System.out.println(jsonObject.toJSONString());

        long end = System.currentTimeMillis();
        System.out.println("--------------"+(end-start)+"ms");


    }
}
