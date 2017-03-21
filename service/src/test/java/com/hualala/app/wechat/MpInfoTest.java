package com.hualala.app.wechat;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Created by xkia on 2017/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpInfoTest {
    @Autowired
    MpInfoService mpInfoService;
    @Test
    public void myInfoQueryTest(){

        MpInfoService.MpInfoQueryReqData mpInfoQueryReqData = new MpInfoService.MpInfoQueryReqData();
        mpInfoQueryReqData.setMpID("doulaofang_caicai");
        MpInfoService.MpInfoQueryResData mpInfoQueryResData = mpInfoService.queryMpInfo(mpInfoQueryReqData);
        System.out.println(JSON.toJSONString(mpInfoQueryResData));
    }
}
