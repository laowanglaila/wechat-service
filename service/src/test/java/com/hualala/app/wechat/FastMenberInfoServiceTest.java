package com.hualala.app.wechat;

import com.hualala.app.wechat.service.FastMemberInfoService;
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
public class FastMenberInfoServiceTest {

    @Autowired
    private FastMemberInfoService memberInfoService;

    @Test
    public void memberInfoTest(){
        long startTime = System.currentTimeMillis();    //获取开始时间
        memberInfoService.setAppId("wxca431740658cd706");
        memberInfoService.setAppSecret("12e0401d258b7e7423c84c0a8704c8e0");
        String accessToken = "CNa2aR3atb7HWLP1ewMDfK3jE4LLpq-VDg4Otlni8dKu0dKJtQMsP-z6uP_Kx22vzDuW5DyV061JzqhERBMZin2GvAPxVM9n_5UnrU7QnOJ5nGO-Ioh4sessI5zMz6VvASChAFAGYI";
//        accessToken = memberInfoService.getAccessToken();
        memberInfoService.setAccessToken(accessToken);
        memberInfoService.setStartLine(1);
        memberInfoService.setCacheNo(500);
        memberInfoService.loadInfo("C:\\Users\\Administrator\\Desktop\\user_code0327.txt");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        System.out.println("accessToken : [" + memberInfoService.getAccessToken() + "]");

    }

}
