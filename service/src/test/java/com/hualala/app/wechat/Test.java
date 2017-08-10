package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatErrorCode;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;


/**
 * Created by renjianfei on 2017/7/12.
 */
//@RunWith(SpringRunner.class)
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
    @org.junit.Test
    public void test2(){
        String balance = "1000.99";
        BigDecimal bigDecimal = new BigDecimal(balance);
        BigDecimal bigDecimal1 = new BigDecimal("100");
        BigDecimal decimal = bigDecimal.multiply(bigDecimal1);
        System.out.println(decimal);
        System.out.println(decimal.longValue());
    }
    @org.junit.Test
    public void test3(){
        String numString="0-1-1-0";
        String s = numString.replaceAll("-", "");
        try {
            Long i = Long.parseLong(s);
        }catch (Throwable i){

        }

    }
    @org.junit.Test
    public void test4(){
        BigDecimal decimal1 = new BigDecimal(1.0000);
        BigDecimal decimal2 = new BigDecimal(1.000);
        int i = decimal1.compareTo(decimal2);
        System.out.println(i);

    }
    @org.junit.Test
    public void test5(){

        Set<String> keySet = WechatErrorCode.wechatError.keySet();
        int errorcode = 112300;
        for (String key : keySet){
            String value = WechatErrorCode.wechatError.get(key);
            System.out.println("WECHAT_ERROR_"+key+"(\"00"+ errorcode++ +"\",\""+ value +"\","+key+"),\r\n");
        }

    }

}
