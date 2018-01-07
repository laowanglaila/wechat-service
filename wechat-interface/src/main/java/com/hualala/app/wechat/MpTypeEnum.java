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
    public static MpTypeEnum valueOf(int value){
        if (value == SUBSCRIPTIONS.getValue()){
            return SUBSCRIPTIONS;
        }else if (value == SUBSCRIPTIONS_AUTH.getValue()){
            return SUBSCRIPTIONS_AUTH;
        }else if (value == SERVICE.getValue()){
            return SERVICE;
        }else if (value == SERVICE_AUTH.getValue()){
            return SERVICE_AUTH;
        }
        return DEFAULT;
    }
}
