package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.service.BaseHttpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
//    @org.junit.Test
//    public void test2(){
//        TestRpcData.TestReqData build = TestRpcData.TestReqData.newBuilder().setRequesq( "777" ).build();
//        Iterator<TestRpcData.TestResData> preAuthCode = testRpcServiceBlockingStub.createPreAuthCode( build );
//        preAuthCode.forEachRemaining( res -> System.out.println(res.getResponse()) );
//
//    }

}
