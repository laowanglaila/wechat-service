package com.hualala.app.wechat;


import com.alibaba.fastjson.JSONObject;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renjianfei on 2017/4/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberInfoRpcServiceTest {
    @Autowired
    BaseRpcClient baseRpcClient;

    @Test
    public void test() throws Exception {
        MemberInfoRpcService memberInfoRpcService = baseRpcClient.getRpcClient(MemberInfoRpcService.class);

        MemberInfoRpcService.MemberInfoQueryReqData memberInfoQueryReqData = new MemberInfoRpcService.MemberInfoQueryReqData();
        memberInfoQueryReqData.setAppID("wxca431740658cd706");
        memberInfoQueryReqData.setAppSecret("12e0401d258b7e7423c84c0a8704c8e0");
        memberInfoQueryReqData.setCode("976694052682");
        memberInfoQueryReqData.setCardId("pQqgCs4wFvht1uuiJNgSktnB894E");
        MemberInfoRpcService.MemberInfoResData memberInfoResData = memberInfoRpcService.queryMemberInfo(memberInfoQueryReqData);
        String s = JSONObject.toJSONString(memberInfoResData);
        System.out.println(s);
    }
}
