package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * 客服消息类型枚举
 */
public enum WechatMessageEnum implements ValueEnum {
    MESSAGE_DEFAULT(0),
    MESSAGE_TEXT(1)
    ;

    private int value;

    @Override
    public int getValue() {
        return value;
    }

    WechatMessageEnum(int value){
        this.value = value;
    }
}
