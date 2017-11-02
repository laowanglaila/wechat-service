package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.exception.WechatInnerException;
import com.hualala.app.wechat.service.AccessTokenService;
import com.hualala.app.wechat.util.HttpApiUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xkia on 2017/3/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessTokenServiceTest {

    @Autowired
    private AccessTokenService accessTokenService;

    //    @Autowired
//    private StringRedisTemplate commonRedisTemplate;
    @Test
    public void getAccessTokenTest() throws WechatInnerException {
        String mpID = "tut1Ceu1DX005996";

        System.out.println(accessTokenService.getAccessToken(mpID).getString("accessToken"));
    }

    @Test
    public void getAccessToken() {
        String mpID = "tut1Ceu1DX005996";
        String appID = "wx14660377951956b8";
        String appSecret = "10542cd0aa6966f1e1c89fe701e61cae";
        JSONObject jsonObject = HttpApiUtil.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appSecret);
        System.out.println(jsonObject.toJSONString());

    }


}
