package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/4/18.
 */
public enum ColorEnum implements ValueEnum {
    DEFAULT_COLOR(0),
    COLOR_010(1,"Color010","#63b359"),
    COLOR_020(2,"Color020","#2c9f67"),
    COLOR_030(3,"Color030","#509fc9"),
    COLOR_040(4,"Color040","#5885cf"),
    COLOR_050(5,"Color050","#9062c0"),
    COLOR_060(6,"Color060","#d09a45"),
    COLOR_070(7,"Color070","#e4b138"),
    COLOR_080(8,"Color080","#ee903c"),
    COLOR_081(9,"Color081","#f08500"),
    COLOR_082(10,"Color082","#a9d92d"),
    COLOR_090(11,"Color090","#dd6549"),
    COLOR_100(12,"Color100","#cc463d"),
    COLOR_101(13,"Color101","#cf3e36"),
    COLOR_102(14,"Color102","#5E6671");


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
