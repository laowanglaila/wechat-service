package com.hualala.app.wechat.service;

import com.hualala.app.wechat.service.card.CreateCardKeyService;
import com.hualala.order.key.OrderKeyGen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateCardKeyServiceTest {
    @Autowired
    private CreateCardKeyService createCardKeyService;
    @Test
    public void test(){
        Long cardKey = null;
        Long aLong = null;
        try {
            cardKey = createCardKeyService.createCardKey(615611323423421l);
            aLong = OrderKeyGen.genKey();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(cardKey);
        System.out.println(aLong);
    }
}
