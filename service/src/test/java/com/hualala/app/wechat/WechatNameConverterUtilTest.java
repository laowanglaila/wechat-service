package com.hualala.app.wechat;

import com.hualala.app.wechat.util.WechatNameConverterUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/10.
 */
public class WechatNameConverterUtilTest {
    @Test
    public void test() {
        //将下划线替换成驼峰命名
        String dbKeys = "max_increase_bonus";
        String javaKey = WechatNameConverterUtil.getJavaKey(dbKeys);
        System.out.println(javaKey);
        System.out.println("-----------------------------------");
        //将驼峰命名替换成数据库风格的下划线命名
        String javaFieldNames = "userHomeAddr";
        String dbKey = WechatNameConverterUtil.getDBKey(javaFieldNames);
        System.out.println(dbKey);
    }
    @Test
    public void test1() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        String dbKeys = "max_increase_bonus";
        map3.put("max_increase_bonus",dbKeys);
        map2.put("max_increase_bonus",map3);
        map1.put("max_increase_bonus",map2);
        Map Key = WechatNameConverterUtil.convertToJavaStyle(map1);
        System.out.println(Key);
        System.out.println("-----------------------------------");

        String javaKey = "maxIncreaseBonus";
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        Map<String, Object> map6 = new HashMap<>();
        map6.put("maxIncreaseBonus",javaKey);
        map5.put("maxIncreaseBonus",map6);
        map4.put("maxIncreaseBonus",map5);
        Map Key2 = WechatNameConverterUtil.convertToDBStyle(map4);
        System.out.println(Key2);
        System.out.println("-----------------------------------");
    }

}
