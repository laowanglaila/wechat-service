package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.service.AccessTokenService;
import com.hualala.app.wechat.service.MediaUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Created by Administrator on 2017/3/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaUploadServiceTest {

    @Autowired
    private MediaUploadService mediaUploadService;
    @Autowired
    private AccessTokenService accessTokenService;

    @Test
    public void test(){
        File file = new File("C:\\Users\\Administrator\\Desktop\\注册.jpg");
        JSONObject jsonObject = mediaUploadService.uploadMemberShipBackground(file,"doulaofang_caicai");
        System.out.println(jsonObject.toJSONString());

    }

}
