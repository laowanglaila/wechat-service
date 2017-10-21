package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/10/21.
 */
public enum TemplateItemTypeEnum implements ValueEnum {
    TEMPLATE_ITEM_TYPE_DEFAULT(0,""),
    TEMPLATE_ITEM_TYPE_FIRST(1,"first"),
    TEMPLATE_ITEM_TYPE_REMARK(2,"remark"),
    TEMPLATE_ITEM_TYPE_KEYNOTE_1(3,"keyword1"),
    TEMPLATE_ITEM_TYPE_KEYNOTE_2(4,"keyword2"),
    TEMPLATE_ITEM_TYPE_KEYNOTE_3(5,"keyword3"),
    TEMPLATE_ITEM_TYPE_KEYNOTE_4(6,"keyword4"),
    TEMPLATE_ITEM_TYPE_KEYNOTE_5(7,"keyword5"),
    TEMPLATE_ITEM_TYPE_KEYNOTE_6(8,"keyword6")
    ;

    private String name;
    private int value;
    TemplateItemTypeEnum(int value,String name){
        this.value = value;
        this.name = name;

    }
    public String getName(){
        return name;
    }
    @Override
    public int getValue() {
        return 0;
    }
}
