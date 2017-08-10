package com.hualala.app.wechat.common;

/**
 * Created by renjianfei on 2017/8/4.
 */

public enum WechatExceptionTypeEnum {

    WECHAT_EXCEPTION("123","345678"),
    WECHAT_CARD_LOCK_ERROR("00112155","会员卡更新消息时间小于或等于上一次更新时间！"),
    WECHAT_MP_PERMISSION_DENIED("00112158","接口功能未授权，请确认公众号已获得该权限");



    private String code;
    private String message;
    WechatExceptionTypeEnum(String errorCode, String errorMessage){
        this.code = errorCode;
        message = errorMessage;
    }

    public static WechatExceptionTypeEnum parseEnum(String code) {
        for (WechatExceptionTypeEnum each : WechatExceptionTypeEnum.class.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }


}
