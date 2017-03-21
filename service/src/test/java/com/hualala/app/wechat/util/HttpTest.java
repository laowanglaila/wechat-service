package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * Created by xkia on 2017/2/17.
 */
public class HttpTest {

    public String accessToken = "wm-PAOid_QK09EinRKI-CojMYoZFsQtfnA-ZaUZwB6OdjVWYY8UbycANewAsgv39NXfw5iO4dau-JUqNBzUc9MYBVUnfXbzonojQeS6RgshlE-qxytgi4WO9tPq4mS4yWDIgADABIN";
    @Test
    public void httpGetTest(){

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd4d2b6a258304a1d&secret=109b0476d5f94a42ec93cd2bf3961035";
        System.out.print(HttpApiUtil.httpGet(url));
    }
    @Test
    public void httpPostTest(){
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, String> map = new HashMap<>();
        map.put("touser","o2kumjlM7Gdpt3AkjKRZbKZYy1fQ");
        map.put("msgtype","text");
        map.put("text","{\"content\":\"Hello World\"}");
        String json = "{\"touser\":\"o2kumjlM7Gdpt3AkjKRZbKZYy1fQ\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\"}}";
        System.out.print(HttpApiUtil.httpPost(url,json));

        /**
         * {"touser":"OPENID","msgtype":"text","text":{"content":"Hello World"}}
         */

    }
    @Test
    public void HttpGet(){
      //  String str = "d881a83d-6f31-4a26-8b52-5e92b65a3cea, 7e6a4114-4022-4f36-8997-1e4554889404, 743e03fc-7140-4e61-b6d5-4ad60230cc01, 62eadb58-77a0-4767-96d2-73ae20481234, 583ae18a-f208-4662-9bec-db4944150c0c, 572466da-e86e-42f3-b29a-8b265981e0ca, 2c2ccc27-ec70-4992-aa27-70316ca2001e, 1c0811b2-8a21-4062-9ab1-bc0c491c12d5, 0ce699ac-9c9d-4074-9ba4-3e86470152fe";
      String str = "433e2caf-298f-49d6-87a4-f7521e0ba4b7, e2232c34-b5a8-4657-a07d-3b7178899833, 50a0432b-dd79-4741-9412-9bb97a759286, b488e56e-b78a-4fa7-8264-09c9f74e9588";
        String url = "http://m.hualala.com/trdInterface/queryOrderPayStatus.svc?orderKey=";
        String[] strLst = str.split(", ");
        List<String> lst = Arrays.asList(strLst);
        int i = 0;
        for (String orderKey : lst){
            JSONObject json = HttpApiUtil.httpGet(url + orderKey);
            System.out.println(json.get("resultmsg") + "---" + orderKey + "  " + i);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(i);

    }
    @Test
    public void HttpTest(){
        String url = "http://m.hualala.com/trdInterface/queryOrderPayStatus.svc?orderKey=";
        String str = "433e2caf-298f-49d6-87a4-f7521e0ba4b7";
       System.out.print(HttpApiUtil.httpGet(url + str));
    }


    public static String reloadPrice(String foodName, String makingMethodList) {
        // 做法1@G5,做法2@F5
        String foodPrice = "0";
        if(makingMethodList.indexOf(foodName) >= 0) {
            List<String> lst = Arrays.asList(makingMethodList.split(","));
            for(String str : lst){
                if(str.indexOf(foodName) >= 0){
                    foodPrice = str.substring(str.indexOf("@") + 2,str.length());
                    return foodPrice;
                }
            }
        }
        return foodPrice;
    }
    @Test
    public void TestPrice() {
        System.out.println(reloadPrice("做法1","做法1@G5,做法2@F5"));
    }
}
