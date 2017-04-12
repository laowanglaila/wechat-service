package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/12.
 */
public enum WechatQRTypeEnum implements ValueEnum {

    TABLETYPE(1),
    PAY(2);

    private int value;
    WechatQRTypeEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
