package com.hualala.app.wechat;

import com.hualala.app.wechat.service.MemberInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renjianfei on 2017/3/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberInfoSerivceTest {

    @Autowired
    private MemberInfoService memberInfoService;

    @Test
    public void memberInfoTest(){
        long startTime = System.currentTimeMillis();    //获取开始时间
        memberInfoService.setAppId("wxca431740658cd706");
        memberInfoService.setAppSecret("12e0401d258b7e7423c84c0a8704c8e0");
        memberInfoService.setAccessToken("7uWr7N6PY3kSfzI1t98FNFZh-xYPlbpSZoGXHQEj93qvEcJ5b1fl0uI3dYqVQ3-NL0WwAVUdvbhB7OB-1zg7q1mchMC8XTPNWc7W9lI0N7HRs2DdnrWJWzix7gBNWx-GOZOaABAKVK");
        memberInfoService.setStartLine(1);
        memberInfoService.loadInfo("C:\\Users\\Administrator\\Desktop\\user_code0327.txt");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        System.out.println("accessToken : [" + memberInfoService.getAccessToken() + "]");

    }

}
