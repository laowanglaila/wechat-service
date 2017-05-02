package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum DateInfoTypeEnum implements ValueEnum {
    DEFAULT_DATE_TYPE(0),
    DATE_TYPE_FIX_TIME_RANGE(1,"DATE_TYPE_FIX_TIME_RANGE"),//表示固定日期区间
    DATE_TYPE_FIX_TERM(2,"DATE_TYPE_FIX_TERM");//表示固定时长
//    DATE_TYPE_PERMANENT(3,"DATE_TYPE_PERMANENT");//永久有效

    private int value;
    private String dateInfoType;
    DateInfoTypeEnum(int value) {
        this.value = value;
    }
    DateInfoTypeEnum(int value, String dateInfoType) {
        this.value = value;
        this.dateInfoType = dateInfoType;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return dateInfoType;
    }
}
