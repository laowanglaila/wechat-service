package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum CodeTypeEnum implements ValueEnum {
    CODE_TYPE_TEXT(1,"CODE_TYPE_TEXT"),
    CODE_TYPE_BARCODE(2,"CODE_TYPE_BARCODE"),
    CODE_TYPE_QRCODE(3,"CODE_TYPE_QRCODE"),
    CODE_TYPE_ONLY_QRCODE(4,"CODE_TYPE_ONLY_QRCODE"),
    CODE_TYPE_ONLY_BARCODE(5,"CODE_TYPE_ONLY_BARCODE");

    private int value;
    private String codeType;
    CodeTypeEnum(int value) {
        this.value = value;
    }
    CodeTypeEnum(int value,String codeType) {
        this.value = value;
        this.codeType = codeType;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return codeType;
    }
}
