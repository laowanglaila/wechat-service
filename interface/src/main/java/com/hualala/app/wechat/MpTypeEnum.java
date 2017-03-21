package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by xkia on 2017/3/20.
 */
public enum MpTypeEnum implements ValueEnum {
    DEFAULT(0),
    SUBSCRIPTIONS(10),
    SUBSCRIPTIONS_AUTH(11),
    SERVICE(20),
    SERVICE_AUTH(21)
    ;
    private int value;
    MpTypeEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
