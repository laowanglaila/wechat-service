package com.hualala.app.wechat.util;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hualala.app.wechat.mapper.card.BaseInfoModelMapperEXT;
import com.hualala.app.wechat.service.ApiTicketService;
import com.hualala.app.wechat.service.HttpApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxCardSignTest {

    private static Logger logger = LoggerFactory.getLogger(HttpApiService.class);
    @Autowired
    private ApiTicketService apiTicketService;
    @Autowired
    private BaseInfoModelMapperEXT baseInfoModelMapperEXT;
//    @Autowired
//    private HttpApiUtil httpApiUtil;
    @Test
    public void test() throws Exception{

        String apiTicket = apiTicketService.getWxCardApiTicket("hualala_com").getString("ticket");
        System.out.println(apiTicket);
        WxCardSign signer = new WxCardSign();
            signer.AddData("test1");
            signer.AddData(12312312);
            signer.AddData(55312312);
            signer.AddData(apiTicket);
//            signer.AddData("test2");
            System.out.println(signer.GetSignature());
    }


    @Test
    public void test1(){
        LocalDateTime time = LocalDateTime.now();

        System.out.println(time.getSecond());
        long l = System.currentTimeMillis();
        System.out.println(l);
        Long l1 = l / 1000;
        System.out.println(l1);
        System.out.println(l1.toString());
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY); // 时
        int minutes = calendar.get(Calendar.MINUTE);    // 分
        int seconds = calendar.get(Calendar.SECOND);    // 秒
        System.out.println(String.format("hours: %s, minutes: %s, seconds: %s", hours, minutes, seconds));
        int length = UUID.randomUUID().toString().replaceAll("-","").length();
        System.out.println(length);
    }

    @Test
    public void test2(){
        Map<String,Object> map = new HashMap<>();
        map.put("hualalaCardID",1);
        map.put("mpID","hualala_com");
        map.put("groupID","5");
        List<String> cardIds = baseInfoModelMapperEXT.giveOutCardId(map);
        System.out.println(cardIds);
    }

}