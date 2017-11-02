package com.hualala.app.wechat;

import com.hualala.app.wechat.sdk.mp.exception.WechatInnerException;
import com.hualala.app.wechat.service.ComponentTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xkia on 2017/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentTokenServiceTest {

    @Autowired
    private ComponentTokenService componentTokenService;
    @Test
    public void initComponentTokenTest() throws WechatInnerException {
        System.out.println(componentTokenService.initComponentToken());
    }
}
