package com.hualala.app.wechat.enumtype;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum CouponTypeEnum implements ValueEnum {
    GROUPON(1,"GROUPON"),
    CASH(2,"CASH"),
    DISCOUNT(3,"DISCOUNT"),
    GIFT(4,"GIFT"),
    GENERAL_COUPON(5,"GENERAL_COUPON");

    private int value;
    private String couponType;
    CouponTypeEnum(int value) {
        this.value = value;
    }
    CouponTypeEnum(int value, String couponType) {
        this.value = value;
        this.couponType = couponType;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return couponType;
    }
}
