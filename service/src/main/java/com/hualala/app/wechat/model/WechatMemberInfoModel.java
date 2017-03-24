package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;

public class WechatMemberInfoModel extends BaseItem {

	private String appID;
	private String cardID;
	private String code;
	private Integer errcode;
	private String wechatJson;


	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}


	public String getWechatJson() {
		return wechatJson;
	}

	public void setWechatJson(String wechatJson) {
		this.wechatJson = wechatJson;
	}





	public String getAppID () {
	    return this.appID;
	}
	public WechatMemberInfoModel setAppID (String appID) {
    	this.appID = appID;
    	return this;
    }

	public String getCardID () {
	    return this.cardID;
	}
	public WechatMemberInfoModel setCardID (String cardID) {
    	this.cardID = cardID;
    	return this;
    }

	public String getCode () {
	    return this.code;
	}
	public WechatMemberInfoModel setCode (String code) {
    	this.code = code;
    	return this;
    }

	@Override
	public String toString() {
		return "WechatMemberInfoModel{" +
				"appID='" + appID + '\'' +
				", cardID='" + cardID + '\'' +
				", code='" + code + '\'' +
				", errcode=" + errcode +
				", wechatJson='" + wechatJson + '\'' +
				'}';
	}
}