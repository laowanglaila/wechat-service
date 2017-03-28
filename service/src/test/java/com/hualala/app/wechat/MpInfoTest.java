package com.hualala.app.wechat;

import com.alibaba.fastjson.JSON;
import com.hualala.app.wechat.util.WechatCacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Created by xkia on 2017/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpInfoTest {
    @Autowired
    MpInfoRpcService mpInfoRpcService;
    @Test
    public void myInfoQueryTest(){

        //System.out.println("---------------" + WechatCacheUtil.getKey());
//        MpInfoService.MpInfoQueryReqData mpInfoQueryReqData = new MpInfoService.MpInfoQueryReqData();
//        mpInfoQueryReqData.setMpID("doulaofang_caicai");
//        MpInfoService.MpInfoQueryResData mpInfoQueryResData = mpInfoService.queryMpInfo(mpInfoQueryReqData);
//        System.out.println(JSON.toJSONString(mpInfoQueryResData));
    }
}
