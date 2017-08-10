package com.hualala.app.wechat.util.template;

public class MessageUtil {

    public static String JsonTest(String openID, String content){
        return "{"
                    + "\"touser\":\"" + openID + "\","
                    + "\"msgtype\":\"text\","
                    + "\"text\":"
                        + "{"
                        + " \"content\":\""+content+"\""
                        + "}"
                +"}";
    }
}
