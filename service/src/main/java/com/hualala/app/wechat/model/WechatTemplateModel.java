package com.hualala.app.output.model;

import com.hualala.core.base.BaseItem;
import java.lang.String;
import java.math.BigDecimal;
import java.lang.Integer;
import java.lang.Long;

public class WechatTemplateModel extends BaseItem {

    private Long itemID;
    public Long getItemID () {
       return this.itemID;
    }
    public WechatTemplateModel setItemID (Long itemID) {
    	this.itemID = itemID;
        return this;
    }
	private String mpID;
	public String getMpID () {
	    return this.mpID;
	}
	public WechatTemplateModel setMpID (String mpID) {
    	this.mpID = mpID;
    	return this;
    }
	private String modelID;
	public String getModelID () {
	    return this.modelID;
	}
	public WechatTemplateModel setModelID (String modelID) {
    	this.modelID = modelID;
    	return this;
    }
	private String modelTitle;
	public String getModelTitle () {
	    return this.modelTitle;
	}
	public WechatTemplateModel setModelTitle (String modelTitle) {
    	this.modelTitle = modelTitle;
    	return this;
    }
	private String templateType;
	public String getTemplateType () {
	    return this.templateType;
	}
	public WechatTemplateModel setTemplateType (String templateType) {
    	this.templateType = templateType;
    	return this;
    }
	private String templateID;
	public String getTemplateID () {
	    return this.templateID;
	}
	public WechatTemplateModel setTemplateID (String templateID) {
    	this.templateID = templateID;
    	return this;
    }
}