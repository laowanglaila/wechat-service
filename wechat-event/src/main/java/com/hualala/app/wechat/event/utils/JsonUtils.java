package com.hualala.app.wechat.event.utils;


import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;

/**
 * @author renjianfei
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        return WxMpGsonBuilder.create().toJson(obj);
    }
}
