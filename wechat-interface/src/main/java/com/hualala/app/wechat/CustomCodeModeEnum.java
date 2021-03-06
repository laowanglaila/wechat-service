package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum CustomCodeModeEnum implements ValueEnum {
    DEFAULT_CUSTOM_CODE_MODE(0),
    GET_CUSTOM_CODE_MODE_DEPOSIT(1,"GET_CUSTOM_CODE_MODE_DEPOSIT");


    private int value;
    private String mode;
    CustomCodeModeEnum(int value) {
        this.value = value;
    }
    CustomCodeModeEnum(int value, String mode) {
        this.value = value;
        this.mode = mode;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return mode;
    }
}
