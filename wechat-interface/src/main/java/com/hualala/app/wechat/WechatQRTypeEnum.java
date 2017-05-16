package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/12.
 */
public enum WechatQRTypeEnum implements ValueEnum {
    DEFAULT_QR_TYPE(0),
    LOGIN(1,604800,false),//七天
    INVOICE(2,259000,false),//三十天
    QUEUE(3,259200,false);//三天

    private int value;
    //类型默认有效时间
    private Integer deadTime;
    //临时false，永久true
    private boolean flag;
    private String wechatType;
    WechatQRTypeEnum(int value) {
        this.value = value;
    }
    WechatQRTypeEnum(int value,Integer deadTime,boolean flag) {
        this.value = value;
        this.deadTime = deadTime;
        this.flag = flag;
        if(flag){
            this.wechatType = "QR_LIMIT_SCENE";//永久
        }else {
            this.wechatType = "QR_SCENE";//临时
        }
    }
    public int getValue() {
        return value;
    }
    public Integer getDeadTime() {
        return this.deadTime;
    }
    public boolean getFlag(){
        return this.flag;
    }

    public String getWechatType(){
        return this.wechatType;
    }
}
