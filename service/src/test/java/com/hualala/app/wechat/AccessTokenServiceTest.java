package com.hualala.app.wechat;

import com.hualala.app.wechat.service.AccessTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Created by xkia on 2017/3/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessTokenServiceTest {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private StringRedisTemplate commonRedisTemplate;
    @Test
    public void getAccessTokenTest(){
        String mpID = "doulaofang_caicai";

        System.out.println(accessTokenService.getAccessToken(mpID));
    }


}
