package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.HttpApiService;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.core.client.BaseRpcClient;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/13.
 */
@RunWith(SpringRunner.class)
@Data
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
        wechatQRCodeReq.setMpID("doulaofangceshi");
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
        wechatQRCodeListReq.setMpID("AfTiEFjiRU0897bd");
//        wechatQRCodeListReq.setMpID("5fWNjiboDX32dcec");
//        wechatQRCodeListReq.setBrandID("0");
//        wechatQRCodeListReq.setGroupID("5");
//        wechatQRCodeListReq.setShopID("0");
        wechatQRCodeListReq.setQrcodeType(WechatQRTypeEnum.INVOICE);
//        wechatQRCodeListReq.setExpireSeconds(3600*24);
        wechatQRCodeListReq.setSize(20);
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
        params.put("expireSeconds","1296000");
        params.put("size","200");
//        params.put("mpID","doulaofangceshi");
        params.put("groupID","5");
        params.put("brandID","0");
        params.put("shopID","76022898");
        params.put("qrcodeType","INVOICE");
        params.put("wechatQRCodeDataList",
                "[" +
                        "{\"param1\":\"param1\",\"qrcodeName\":\"QrcodeName\"}," +
                        "{\"param1\":\"param1\",\"qrcodeName\":\"QrcodeName\"}," +
                        "{\"param1\":\"param1\",\"qrcodeName\":\"QrcodeName\"}" +
                "]");

//        JSONObject jsonObject = HttpApiUtil.httpPost("http://127.0.0.1:8090/wechat/getQrcodeList.html", params);
        JSONObject jsonObject = HttpApiUtil.httpPost("http://dohko.eshopapi.hualala.com/wechat/getQrcodeList.html", params);
        System.out.println(jsonObject.toJSONString());

        long end = System.currentTimeMillis();
        System.out.println("--------------"+(end-start)+"ms");


    }
    @Autowired
    private BaseHttpService baseHttpService;
    /**
     * 获取会员卡二维码
     */
    @Test
    public void  test5(){
        long start = System.currentTimeMillis();
        String jsonString = " {\n" +
                            "\"action_name\": \"QR_CARD\", \n" +
//                            "\"expire_seconds\": 1800,\n" +
                            "\"action_info\": {\n" +
                                "\"card\": {\n" +
                                "\"card_id\": \"pXpuzwZdfqUSuLQJrZG_XvDXapPA\", \n" +
//                                "\"code\": \"198372383512\"," +
//                                "\"openid\": \"oACwGswdcsCnQgJlarC9xIqQL2YI\"," +
//                                "\"openid\": \"oACwGs4QPjQ_JoAVBOCQgwC12yFk\"," +
//                                "\"is_unique_code\": true ,\n" +
                                "\"outer_id\":\"122134234\"\n" +
                            "  }\n" +
                            " }\n" +
                            "}";
        String mpID = "tut1Ceu1DX005996";

        JSONObject jsonObject = baseHttpService.commonHttpPost("https://api.weixin.qq.com/card/qrcode/create", jsonString,mpID);
//        JSONObject jsonObject = HttpApiUtil.httpPost("http://dohko.eshopapi.hualala.com/wechat/getQrcodeList.html", params);
        System.out.println(jsonObject.toJSONString());

        long end = System.currentTimeMillis();
        System.out.println("--------------"+(end-start)+"ms");


    }

    /**
     *
     * 登录码测试
     */
    @Test
    public void  test6(){
        WechatQRCodeRpcSerivce rpcClient = baseRpcClient.getRpcClient(WechatQRCodeRpcSerivce.class);
        WechatQRCodeRpcSerivce.WechatQRCodeReq wechatQRCodeReq = new WechatQRCodeRpcSerivce.WechatQRCodeReq();
        String param1 = "ID" ;
        wechatQRCodeReq.setParam1(param1);
//        wechatQRCodeReq.setExpireSeconds(3600);
        wechatQRCodeReq.setShopID("５");
        wechatQRCodeReq.setBrandID("5");
        wechatQRCodeReq.setGroupID("5");
        wechatQRCodeReq.setShopName("测试二维码:ShopName");
        wechatQRCodeReq.setDescription("测试二维码:Description");
        wechatQRCodeReq.setLocationName("测试二维码:LocationName");
        long start = System.currentTimeMillis();
        WechatQRCodeRpcSerivce.WechatQRCodeRes qrCode = rpcClient.createLoginQRCode(wechatQRCodeReq);
        long end = System.currentTimeMillis();
        System.out.println("-------------" + (end - start) + "ms------------------>" + qrCode.toJson());
    }
}
