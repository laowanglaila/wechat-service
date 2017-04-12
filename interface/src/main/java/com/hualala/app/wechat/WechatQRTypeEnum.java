package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/12.
 */
public enum WechatQRTypeEnum implements ValueEnum {

    LOGIN(1),
    INVOICE(2),
    QUEUE(3);

    private int value;
    WechatQRTypeEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
