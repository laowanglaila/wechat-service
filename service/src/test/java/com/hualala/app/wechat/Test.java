package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
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
    @org.junit.Test
    public void test6(){
        System.out.println(WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS);
    }
    @org.junit.Test
    public void test7() throws IOException {
        int lineNumber = 0;
        File file = null;
        InputStreamReader read = null;
        LineNumberReader lineNumberReader = null;
        String lineTxt = null;
        String encoding="UTF-8";
        file = new File("E:\\GitRepository\\wechat-service\\service\\src\\main\\java\\com\\hualala\\app\\wechat\\common\\ErrorCodes.java");
        read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String content = "";
        String message = "";
        content = bufferedReader.readLine();
        while(content != null) {
                if (content.contains("//")) {
                    message = content.trim();
//                System.out.println(content);
                } else if ("".equals(content.trim()) || content.contains(".") || content.contains("}")) {
                    message = "";
                } else {
                    String[] split = content.trim().split(" ");
                    System.out.println(split[1]+"("+split[3].replace(";","")+",\""+message.replace("//","").trim()+"\"),");
                    message = "";
            }
            content = bufferedReader.readLine();
        }
    }

    @org.junit.Test
    public void test8(){
        int i = 1;
        Object i1 = i;
        System.out.println(i1 instanceof Integer);
    }
    @org.junit.Test
    public void test9(){
        Object s = "";
        String s1 = String.valueOf(s);
        System.out.println(s1);
    }
}
