package com.hualala.app.wechat;

import com.hualala.app.wechat.util.WechatNameConverterUtil;

/**
 * Created by renjianfei on 2017/4/10.
 */
public class WechatNameConverterUtilTest {

    public static void main(String[] args) {
        //将下划线替换成驼峰命名
        String[] dbKeys = {"id", "user_age", "user_addr_","max_increase_bonus"};
        WechatNameConverterUtil.convertToJava(dbKeys);
        System.out.println("-----------------------------------");
        //将驼峰命名替换成数据库风格的下划线命名
        String javaFieldNames[] = {"id","userAge","userHomeAddr"};
        WechatNameConverterUtil.getDBKey(javaFieldNames);
    }

}
