package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum CouponTypeEnum implements ValueEnum {
    GROUPON(1,"GROUPON","groupon"),
    CASH(2,"CASH","cash"),
    DISCOUNT(3,"DISCOUNT","discount"),
    GIFT(4,"GIFT","gift"),
    GENERAL_COUPON(5,"GENERAL_COUPON","general_coupon");

    private int value;
    private String couponType;
    private String couponValue;
    CouponTypeEnum(int value) {
        this.value = value;
    }
    CouponTypeEnum(int value, String couponType,String couponValue) {
        this.value = value;
        this.couponType = couponType;
        this.couponValue = couponValue;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return couponType;
    }
    public String getCouponValue(){
        return couponValue;
    }
}
