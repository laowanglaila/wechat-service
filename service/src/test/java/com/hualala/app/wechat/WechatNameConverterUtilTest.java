package com.hualala.app.wechat;

import com.hualala.app.wechat.util.WechatNameConverterUtil;
import org.junit.Test;

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

}
