package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;

public class WechatTemplateModel extends BaseItem {

    private Long itemID;
    private String mpID;
    private String modelID;
    private String modelTitle;
    private String templateType;
    private String templateID;

    public Long getItemID() {
        return this.itemID;
    }

    public WechatTemplateModel setItemID(Long itemID) {
        this.itemID = itemID;
        return this;
    }

    public String getMpID() {
        return this.mpID;
    }

    public WechatTemplateModel setMpID(String mpID) {
        this.mpID = mpID;
        return this;
    }

    public String getModelID() {
        return this.modelID;
    }

    public WechatTemplateModel setModelID(String modelID) {
        this.modelID = modelID;
        return this;
    }

    public String getModelTitle() {
        return this.modelTitle;
    }

    public WechatTemplateModel setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
        return this;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public WechatTemplateModel setTemplateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public String getTemplateID() {
        return this.templateID;
    }

    public WechatTemplateModel setTemplateID(String templateID) {
        this.templateID = templateID;
        return this;
    }
}