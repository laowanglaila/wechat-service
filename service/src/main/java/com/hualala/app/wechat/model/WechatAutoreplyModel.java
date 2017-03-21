package com.hualala.app.output.model;

import com.hualala.core.base.BaseItem;
import java.lang.String;
import java.math.BigDecimal;
import java.lang.Integer;
import java.lang.Long;

public class WechatAutoreplyModel extends BaseItem {

    private Long itemID;
    public Long getItemID () {
       return this.itemID;
    }
    public WechatAutoreplyModel setItemID (Long itemID) {
    	this.itemID = itemID;
        return this;
    }
	private String mpID;
	public String getMpID () {
	    return this.mpID;
	}
	public WechatAutoreplyModel setMpID (String mpID) {
    	this.mpID = mpID;
    	return this;
    }
	private String resourceID;
	public String getResourceID () {
	    return this.resourceID;
	}
	public WechatAutoreplyModel setResourceID (String resourceID) {
    	this.resourceID = resourceID;
    	return this;
    }
	private String pushMsgType;
	public String getPushMsgType () {
	    return this.pushMsgType;
	}
	public WechatAutoreplyModel setPushMsgType (String pushMsgType) {
    	this.pushMsgType = pushMsgType;
    	return this;
    }
	private String pushContent;
	public String getPushContent () {
	    return this.pushContent;
	}
	public WechatAutoreplyModel setPushContent (String pushContent) {
    	this.pushContent = pushContent;
    	return this;
    }
    private Integer pushContentType;
    public Integer getPushContentType () {
    	return this.pushContentType;
    }
	public WechatAutoreplyModel setPushContentType (Integer pushContentType) {
    	this.pushContentType = pushContentType;
        return this;
    }
	private String pushEvent;
	public String getPushEvent () {
	    return this.pushEvent;
	}
	public WechatAutoreplyModel setPushEvent (String pushEvent) {
    	this.pushEvent = pushEvent;
    	return this;
    }
	private String pushEventKey;
	public String getPushEventKey () {
	    return this.pushEventKey;
	}
	public WechatAutoreplyModel setPushEventKey (String pushEventKey) {
    	this.pushEventKey = pushEventKey;
    	return this;
    }
	private String replyMsgType;
	public String getReplyMsgType () {
	    return this.replyMsgType;
	}
	public WechatAutoreplyModel setReplyMsgType (String replyMsgType) {
    	this.replyMsgType = replyMsgType;
    	return this;
    }
	private String serviceName;
	public String getServiceName () {
	    return this.serviceName;
	}
	public WechatAutoreplyModel setServiceName (String serviceName) {
    	this.serviceName = serviceName;
    	return this;
    }
    private Integer isActive;
    public Integer getIsActive () {
    	return this.isActive;
    }
	public WechatAutoreplyModel setIsActive (Integer isActive) {
    	this.isActive = isActive;
        return this;
    }
}