package com.hualala.app.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xkia on 2017/4/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpInfoServiceTest {

    @Autowired
    MpInfoService mpInfoService;

    @Test
    public void queryMpInfo() throws Exception {

        String mpID = mpInfoService.queryMpIDAuth(5,5,0);
        System.out.println(mpID);
    }

}