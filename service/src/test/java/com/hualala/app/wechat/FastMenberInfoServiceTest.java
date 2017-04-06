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
        memberInfoService.setAppId("");
        memberInfoService.setAppSecret("");
        String accessToken = "";
//        accessToken = memberInfoService.getAccessToken();
        memberInfoService.setAccessToken(accessToken);
        memberInfoService.setStartLine(1);
        memberInfoService.setCacheNo(1000);
        memberInfoService.setThreadNO(50);

        memberInfoService.loadInfo("C:\\Users\\Administrator\\Desktop\\user_code0329.txt");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        System.out.println("accessToken : [" + memberInfoService.getAccessToken() + "]");

    }

}
