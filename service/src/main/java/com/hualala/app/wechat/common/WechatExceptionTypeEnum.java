package com.hualala.app.wechat.common;

/**
 * Created by renjianfei on 2017/8/4.
 */

public enum WechatExceptionTypeEnum {

    WECHAT_EXCEPTION("123","345678"),
    WECHAT_CARD_LOCK_ERROR("00112155","会员卡更新消息时间小于或等于上一次更新时间！");

    private String code;
    private String message;
    WechatExceptionTypeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
