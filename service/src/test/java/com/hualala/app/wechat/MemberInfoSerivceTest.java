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

        memberInfoService.setAppId("");
        memberInfoService.setAppSecret("");
        memberInfoService.setAccessToken("KJz_JKOY-IxKxUJOwCi3sR9fszJhM6tjc1te5jYB0zcAlvi-DKJ7YbBF7Ng4ejPcvabin_Yk-FqfkR6I_61PKygZw44gCX-_vSjTUWX53xepWlZov9GMEOjYJf_uM6o7QKJfAAATNM");
        memberInfoService.setStartLine(1);
        memberInfoService.loadInfo("C:\\Users\\Administrator\\Desktop\\user_code01.txt");

    }

}
