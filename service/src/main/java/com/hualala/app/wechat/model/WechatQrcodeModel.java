package com.hualala.app.output.model;

import com.hualala.core.base.BaseItem;
import java.lang.String;
import java.math.BigDecimal;
import java.lang.Integer;
import java.lang.Long;

public class WechatQrcodeModel extends BaseItem {

    private Long itemID;
    public Long getItemID () {
       return this.itemID;
    }
    public WechatQrcodeModel setItemID (Long itemID) {
    	this.itemID = itemID;
        return this;
    }
	private String mpID;
	public String getMpID () {
	    return this.mpID;
	}
	public WechatQrcodeModel setMpID (String mpID) {
    	this.mpID = mpID;
    	return this;
    }
    private Integer qrcodeType;
    public Integer getQrcodeType () {
    	return this.qrcodeType;
    }
	public WechatQrcodeModel setQrcodeType (Integer qrcodeType) {
    	this.qrcodeType = qrcodeType;
        return this;
    }
	private String qrcodeName;
	public String getQrcodeName () {
	    return this.qrcodeName;
	}
	public WechatQrcodeModel setQrcodeName (String qrcodeName) {
    	this.qrcodeName = qrcodeName;
    	return this;
    }
	private String description;
	public String getDescription () {
	    return this.description;
	}
	public WechatQrcodeModel setDescription (String description) {
    	this.description = description;
    	return this;
    }
	private String param1;
	public String getParam1 () {
	    return this.param1;
	}
	public WechatQrcodeModel setParam1 (String param1) {
    	this.param1 = param1;
    	return this;
    }
	private String param2;
	public String getParam2 () {
	    return this.param2;
	}
	public WechatQrcodeModel setParam2 (String param2) {
    	this.param2 = param2;
    	return this;
    }
	private String param3;
	public String getParam3 () {
	    return this.param3;
	}
	public WechatQrcodeModel setParam3 (String param3) {
    	this.param3 = param3;
    	return this;
    }
	private String locationName;
	public String getLocationName () {
	    return this.locationName;
	}
	public WechatQrcodeModel setLocationName (String locationName) {
    	this.locationName = locationName;
    	return this;
    }
	private String shopID;
	public String getShopID () {
	    return this.shopID;
	}
	public WechatQrcodeModel setShopID (String shopID) {
    	this.shopID = shopID;
    	return this;
    }
	private String ticket;
	public String getTicket () {
	    return this.ticket;
	}
	public WechatQrcodeModel setTicket (String ticket) {
    	this.ticket = ticket;
    	return this;
    }
	private String wxUrl;
	public String getWxUrl () {
	    return this.wxUrl;
	}
	public WechatQrcodeModel setWxUrl (String wxUrl) {
    	this.wxUrl = wxUrl;
    	return this;
    }
	private String qrcodeIP;
	public String getQrcodeIP () {
	    return this.qrcodeIP;
	}
	public WechatQrcodeModel setQrcodeIP (String qrcodeIP) {
    	this.qrcodeIP = qrcodeIP;
    	return this;
    }
    private Integer isLog;
    public Integer getIsLog () {
    	return this.isLog;
    }
	public WechatQrcodeModel setIsLog (Integer isLog) {
    	this.isLog = isLog;
        return this;
    }
    private Integer isActive;
    public Integer getIsActive () {
    	return this.isActive;
    }
	public WechatQrcodeModel setIsActive (Integer isActive) {
    	this.isActive = isActive;
        return this;
    }
}