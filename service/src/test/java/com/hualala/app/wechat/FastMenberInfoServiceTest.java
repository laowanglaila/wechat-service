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
        String accessToken = "fN7UjjtD3ipQzC6yjkUrowK_O0pKQgGVu5-2rV2-iCFQjsMoSdEStpJ_qjoG2YHZaKNhdJSLAVEqHfpgYnl88VTL1QXEoQMlJe_YEGYrHB4FSSbAHATGG";
//        accessToken = memberInfoService.getAccessToken();
        memberInfoService.setAccessToken(accessToken);
        memberInfoService.setStartLine(1);
        memberInfoService.setCacheNo(1000);
        memberInfoService.setThreadNO(50);

        memberInfoService.loadInfo("C:\\Users\\Administrator\\Desktop\\user_code0329.txt");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/60*1000 + "s");    //输出程序运行时间
        System.out.println("accessToken : [" + memberInfoService.getAccessToken() + "]");

    }

}
