package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.bean.result.WxMpUser;
import com.hualala.app.wechat.service.BaseHttpService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by renjianfei on 2017/9/7.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2 {
//    @Autowired
//    private TestRpcServiceGrpc.TestRpcServiceBlockingStub testRpcServiceBlockingStub;
    @Autowired
    private WxGroupMpService wxGroupMpService;
    @Autowired
    private BaseHttpService baseHttpService;
    @org.junit.Test
    public void test(){
        String url = "https://api.weixin.qq.com/card/membercard/activate/geturl";
        String params = "{" +
                            "\"card_id\" : \"pACwGs1o30yZFhsTqysXImX5QHDA\"" +
//                            "\"outer_str\" : \""++"\"\n" +
                        "}";
        String mpID = "hualala_com";
        JSONObject jsonObject = baseHttpService.commonHttpPost( url, params, mpID );
        System.out.println(jsonObject);
    }
    @org.junit.Test
    public void test1(){
        String url = "https://api.weixin.qq.com/card/membercard/activatetempinfo/get";
        String params = "       {" +
                      "    \"activate_ticket\" : \"fDZv9eMQAFfrNr3XBoqhb9EZtrX0GFC28omj%2FN%2BrHJW6eK6Rx%2FU27q7eZ%2BedAnJ%2BDRPzHVOm0CNN%2BI%2Bv0Pxr0gl5BJHaU7yfpebGGxrnIHc%3D\"" +
                "        }";
        String mpID = "hualala_com";
        JSONObject jsonObject = baseHttpService.commonHttpPost( url, params, mpID );
        System.out.println(jsonObject);
    }
//   grpc stream test
    @org.junit.Test
    public void test2(){
        String dou = "10.12";
        BigDecimal bigDecimal = new BigDecimal( dou );
        int i = bigDecimal.intValue();
        System.out.println(i);

    }
    @org.junit.Test
    public void test3(){
        String token = "hualala666";
        String timestamp = "" + System.currentTimeMillis();
        String nonce = "234234";
        String echostr = "hello";
        String signature = SHA1.gen( token, timestamp, nonce );
        log.info("http://127.0.0.1:9090/access?signature={}&timestamp={}&nonce={}&echostr={}" , signature,timestamp,nonce,echostr );
    }

    @org.junit.Test
    public void test4() throws WxErrorException {
//        String mpID = "hualala_com";
//        String openID = "oACwGs4QPjQ_JoAVBOCQgwC12yFk";
        String mpID = "dohko1155";
        String openID = "oFx2ov1lGXfEzIDNrX0r5hjKYjao";
        WxMpUser wxMpUser = wxGroupMpService.getUserService( mpID ).userInfo( openID );
        log.info( JSONObject.toJSONString( wxMpUser ) );
    }


    @org.junit.Test
    public void test5() throws WxErrorException {
        String mpID = "dohko1155";
        String url = "http://mmbiz.qpic.cn/mmbiz/PiajxSqBRaEIQxibpLbyuSKyv7L5vzxedzFIrQHpc87HdR681GnDTdcgjNvmLLtdgDY4iae5C7t8UZnyMQfAPO9vg/0?wx_fmt=png";
        String pdf = wxGroupMpService.getWxMpInvoiceService( mpID ).uploadPdf( url );
        log.info( pdf );
    }


}
