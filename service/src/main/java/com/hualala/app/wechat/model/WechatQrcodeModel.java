package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;

public class WechatQrcodeModel extends BaseItem {

    private Long itemID;
    private String mpID;
    private Integer qrcodeType;
    private String qrcodeName;
    private String description;
    private String param1;
    private String param2;
    private String param3;
    private String locationName;
    private String shopID;
    private String ticket;
    private String wxUrl;
    private String qrcodeIP;
    private Integer isLog;
    private Integer isActive;

    public Long getItemID() {
        return this.itemID;
    }

    public WechatQrcodeModel setItemID(Long itemID) {
        this.itemID = itemID;
        return this;
    }

    public String getMpID() {
        return this.mpID;
    }

    public WechatQrcodeModel setMpID(String mpID) {
        this.mpID = mpID;
        return this;
    }

    public Integer getQrcodeType() {
        return this.qrcodeType;
    }

    public WechatQrcodeModel setQrcodeType(Integer qrcodeType) {
        this.qrcodeType = qrcodeType;
        return this;
    }

    public String getQrcodeName() {
        return this.qrcodeName;
    }

    public WechatQrcodeModel setQrcodeName(String qrcodeName) {
        this.qrcodeName = qrcodeName;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public WechatQrcodeModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getParam1() {
        return this.param1;
    }

    public WechatQrcodeModel setParam1(String param1) {
        this.param1 = param1;
        return this;
    }

    public String getParam2() {
        return this.param2;
    }

    public WechatQrcodeModel setParam2(String param2) {
        this.param2 = param2;
        return this;
    }

    public String getParam3() {
        return this.param3;
    }

    public WechatQrcodeModel setParam3(String param3) {
        this.param3 = param3;
        return this;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public WechatQrcodeModel setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getShopID() {
        return this.shopID;
    }

    public WechatQrcodeModel setShopID(String shopID) {
        this.shopID = shopID;
        return this;
    }

    public String getTicket() {
        return this.ticket;
    }

    public WechatQrcodeModel setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public String getWxUrl() {
        return this.wxUrl;
    }

    public WechatQrcodeModel setWxUrl(String wxUrl) {
        this.wxUrl = wxUrl;
        return this;
    }

    public String getQrcodeIP() {
        return this.qrcodeIP;
    }

    public WechatQrcodeModel setQrcodeIP(String qrcodeIP) {
        this.qrcodeIP = qrcodeIP;
        return this;
    }

    public Integer getIsLog() {
        return this.isLog;
    }

    public WechatQrcodeModel setIsLog(Integer isLog) {
        this.isLog = isLog;
        return this;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public WechatQrcodeModel setIsActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }
}