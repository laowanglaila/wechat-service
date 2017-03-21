package com.hualala.app.output.model;

import com.hualala.core.base.BaseItem;
import java.lang.String;
import java.math.BigDecimal;
import java.lang.Integer;
import java.lang.Long;

public class WechatMpModel extends BaseItem {

    private Long itemID;
    public Long getItemID () {
       return this.itemID;
    }
    public WechatMpModel setItemID (Long itemID) {
    	this.itemID = itemID;
        return this;
    }
	private String mpID;
	public String getMpID () {
	    return this.mpID;
	}
	public WechatMpModel setMpID (String mpID) {
    	this.mpID = mpID;
    	return this;
    }
	private String ghID;
	public String getGhID () {
	    return this.ghID;
	}
	public WechatMpModel setGhID (String ghID) {
    	this.ghID = ghID;
    	return this;
    }
	private String mpName;
	public String getMpName () {
	    return this.mpName;
	}
	public WechatMpModel setMpName (String mpName) {
    	this.mpName = mpName;
    	return this;
    }
    private Integer mpType;
    public Integer getMpType () {
    	return this.mpType;
    }
	public WechatMpModel setMpType (Integer mpType) {
    	this.mpType = mpType;
        return this;
    }
	private String token;
	public String getToken () {
	    return this.token;
	}
	public WechatMpModel setToken (String token) {
    	this.token = token;
    	return this;
    }
	private String appID;
	public String getAppID () {
	    return this.appID;
	}
	public WechatMpModel setAppID (String appID) {
    	this.appID = appID;
    	return this;
    }
    private Long brandID;
    public Long getBrandID () {
       return this.brandID;
    }
    public WechatMpModel setBrandID (Long brandID) {
    	this.brandID = brandID;
        return this;
    }
	private String appSecret;
	public String getAppSecret () {
	    return this.appSecret;
	}
	public WechatMpModel setAppSecret (String appSecret) {
    	this.appSecret = appSecret;
    	return this;
    }
	private String encodingAESKey;
	public String getEncodingAESKey () {
	    return this.encodingAESKey;
	}
	public WechatMpModel setEncodingAESKey (String encodingAESKey) {
    	this.encodingAESKey = encodingAESKey;
    	return this;
    }
	private String weixinURL;
	public String getWeixinURL () {
	    return this.weixinURL;
	}
	public WechatMpModel setWeixinURL (String weixinURL) {
    	this.weixinURL = weixinURL;
    	return this;
    }
	private String headImg;
	public String getHeadImg () {
	    return this.headImg;
	}
	public WechatMpModel setHeadImg (String headImg) {
    	this.headImg = headImg;
    	return this;
    }
	private String qrCodeURL;
	public String getQrCodeURL () {
	    return this.qrCodeURL;
	}
	public WechatMpModel setQrCodeURL (String qrCodeURL) {
    	this.qrCodeURL = qrCodeURL;
    	return this;
    }
	private String menuJson;
	public String getMenuJson () {
	    return this.menuJson;
	}
	public WechatMpModel setMenuJson (String menuJson) {
    	this.menuJson = menuJson;
    	return this;
    }
    private Long shopID;
    public Long getShopID () {
       return this.shopID;
    }
    public WechatMpModel setShopID (Long shopID) {
    	this.shopID = shopID;
        return this;
    }
    private Integer customerWithoutBindMobile;
    public Integer getCustomerWithoutBindMobile () {
    	return this.customerWithoutBindMobile;
    }
	public WechatMpModel setCustomerWithoutBindMobile (Integer customerWithoutBindMobile) {
    	this.customerWithoutBindMobile = customerWithoutBindMobile;
        return this;
    }
    private Integer subscribeToCcustomer;
    public Integer getSubscribeToCcustomer () {
    	return this.subscribeToCcustomer;
    }
	public WechatMpModel setSubscribeToCcustomer (Integer subscribeToCcustomer) {
    	this.subscribeToCcustomer = subscribeToCcustomer;
        return this;
    }
	private String tableMsgTemplate;
	public String getTableMsgTemplate () {
	    return this.tableMsgTemplate;
	}
	public WechatMpModel setTableMsgTemplate (String tableMsgTemplate) {
    	this.tableMsgTemplate = tableMsgTemplate;
    	return this;
    }
	private String funcInfo;
	public String getFuncInfo () {
	    return this.funcInfo;
	}
	public WechatMpModel setFuncInfo (String funcInfo) {
    	this.funcInfo = funcInfo;
    	return this;
    }
	private String alias;
	public String getAlias () {
	    return this.alias;
	}
	public WechatMpModel setAlias (String alias) {
    	this.alias = alias;
    	return this;
    }
    private Integer authorize;
    public Integer getAuthorize () {
    	return this.authorize;
    }
	public WechatMpModel setAuthorize (Integer authorize) {
    	this.authorize = authorize;
        return this;
    }
	private String authorizerRefreshToken;
	public String getAuthorizerRefreshToken () {
	    return this.authorizerRefreshToken;
	}
	public WechatMpModel setAuthorizerRefreshToken (String authorizerRefreshToken) {
    	this.authorizerRefreshToken = authorizerRefreshToken;
    	return this;
    }
    private Integer oauth;
    public Integer getOauth () {
    	return this.oauth;
    }
	public WechatMpModel setOauth (Integer oauth) {
    	this.oauth = oauth;
        return this;
    }
    private Integer isActiveUse;
    public Integer getIsActiveUse () {
    	return this.isActiveUse;
    }
	public WechatMpModel setIsActiveUse (Integer isActiveUse) {
    	this.isActiveUse = isActiveUse;
        return this;
    }
    private Long wechatEndDate;
    public Long getWechatEndDate () {
       return this.wechatEndDate;
    }
    public WechatMpModel setWechatEndDate (Long wechatEndDate) {
    	this.wechatEndDate = wechatEndDate;
        return this;
    }
}