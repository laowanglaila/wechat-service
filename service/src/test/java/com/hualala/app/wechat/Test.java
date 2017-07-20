package com.hualala.app.wechat;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by renjianfei on 2017/7/12.
 */
@SpringBootTest
public class Test {

    @org.junit.Test
    public void test(){
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(l/1000);
        String s = "123x";
        try {
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            System.out.println(s);
        }

    }


}
