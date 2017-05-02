package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum TimelimitTypeEnum implements ValueEnum {
    DEFAULT_TIMELIMIT_TYPE(0),
    MONDAY(1,"MONDAY"),//
    TUESDAY(2,"TUESDAY"),//
    WEDNESDAY(3,"WEDNESDAY"),//
    THURSDAY(4,"THURSDAY"),//
    FRIDAY(5,"FRIDAY"),//
    SATURDAY(6,"SATURDAY"),//
    SUNDAY(7,"SUNDAY");//

    private int value;
    private String timeLimitName;
    TimelimitTypeEnum(int value) {
        this.value = value;
    }
    TimelimitTypeEnum(int value, String timeLimitName) {
        this.value = value;
        this.timeLimitName = timeLimitName;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return timeLimitName;
    }
}
