package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum ColorEnum implements ValueEnum {
    Color010(0,"Color010","#63b359"),
    Color020(2,"Color020","#2c9f67"),
    Color030(3,"Color030","#509fc9"),
    Color040(4,"Color040","#5885cf"),
    Color050(5,"Color050","#9062c0"),
    Color060(6,"Color060","#d09a45"),
    Color070(7,"Color070","#e4b138"),
    Color080(8,"Color080","#ee903c"),
    Color081(9,"Color081","#f08500"),
    Color082(10,"Color082","#a9d92d"),
    Color090(11,"Color090","#dd6549"),
    Color100(12,"Color100","#cc463d"),
    Color101(13,"Color101","#cf3e36"),
    Color102(14,"Color102","#5E6671");


    private int value;
    private String colorName;
    private String colorValue;
    ColorEnum(int value) {
        this.value = value;
    }
    ColorEnum(int value, String colorName,String colorValue) {
        this.value = value;
        this.colorName = colorName;
        this.colorValue = colorValue;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String getName() {
        return colorName;
    }
    public String getColorValue(){
        return this.colorValue;
    }
}
