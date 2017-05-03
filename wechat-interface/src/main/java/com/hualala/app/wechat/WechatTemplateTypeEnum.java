package com.hualala.app.wechat;

import com.hualala.core.base.ValueEnum;

/**
 * 模板消息类型枚举
 * Created by xkia on 2017/4/20.
 */
public enum WechatTemplateTypeEnum implements ValueEnum {
    TEMPLATE_ENUM_DEFAULT(0,"","",""),
    TEMPLATE_ENUM_QUEUE(1, "queue", "alarm", ""),
    TEMPLATE_ENUM_ORDER(2, "order", "default", "");

    private int value;
    private String modelType;
    private String modelSubType;
    private String subType;

    WechatTemplateTypeEnum(int value, String modelType, String modelSubType, String subType) {
        this.value = value;
        this.modelType = modelType;
        this.modelSubType = modelSubType;
        this.subType = subType;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return modelType;
    }

    public String getModelType() {
        return modelType;
    }

    public String getModelSubType() {
        return modelSubType;
    }

    public String getSubType() {
        return subType;
    }
}
