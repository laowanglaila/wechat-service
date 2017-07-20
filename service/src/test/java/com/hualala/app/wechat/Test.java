package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
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

    @org.junit.Test
    public void test1(){
        String groupID = "123";
        String hualalaCardID = "hualalaCardID";
        String hualalaCardCode = "hualalaCardCode";
        String json = "{\"groupID\":\""+groupID+"\",\"hualalaCardID\":\""+hualalaCardID+"\",\"hualalaCardCode\":\""+hualalaCardCode+"\"}";
        System.out.println("json:" + json);
        String s = Base64.encodeBase64URLSafeString(json.getBytes());
        System.out.println("encode-json:" + s);
        byte[] bytes = Base64.decodeBase64(s);
        String s1 = new String(bytes);
        System.out.println("decode-json:" + s1);
        JSONObject jsonObject = JSONObject.parseObject(s1);
        System.out.println(jsonObject);
    }
}
