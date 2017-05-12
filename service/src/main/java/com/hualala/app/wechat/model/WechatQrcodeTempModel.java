package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;

public class WechatQrcodeTempModel extends BaseItem {

    private Long itemID;
    private String mpID;
    private Integer qrcodeType;
    private Integer sceneID;
    private String qrcodeName;
    private String description;
    private String param1;
    private String param2;
    private String param3;
    private String locationName;
    private String shopID;
    private String ticket;
    private String wxUrl;
    private Long deadTime;
    private int qrcodeStatus;

    public int getQrcodeStatus(){
        return this.qrcodeStatus;
    }

    public WechatQrcodeTempModel setQrcodeStatus(int qrcodeStatus){
        this.qrcodeStatus = qrcodeStatus;
        return this;
    }
    public Long getItemID() {
        return this.itemID;
    }

    public WechatQrcodeTempModel setItemID(Long itemID) {
        this.itemID = itemID;
        return this;
    }

    public String getMpID() {
        return this.mpID;
    }

    public WechatQrcodeTempModel setMpID(String mpID) {
        this.mpID = mpID;
        return this;
    }

    public Integer getQrcodeType() {
        return this.qrcodeType;
    }

    public WechatQrcodeTempModel setQrcodeType(Integer qrcodeType) {
        this.qrcodeType = qrcodeType;
        return this;
    }

    public String getQrcodeName() {
        return this.qrcodeName;
    }

    public WechatQrcodeTempModel setQrcodeName(String qrcodeName) {
        this.qrcodeName = qrcodeName;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public WechatQrcodeTempModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getParam1() {
        return this.param1;
    }

    public WechatQrcodeTempModel setParam1(String param1) {
        this.param1 = param1;
        return this;
    }

    public String getParam2() {
        return this.param2;
    }

    public WechatQrcodeTempModel setParam2(String param2) {
        this.param2 = param2;
        return this;
    }

    public String getParam3() {
        return this.param3;
    }

    public WechatQrcodeTempModel setParam3(String param3) {
        this.param3 = param3;
        return this;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public WechatQrcodeTempModel setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getShopID() {
        return this.shopID;
    }

    public WechatQrcodeTempModel setShopID(String shopID) {
        this.shopID = shopID;
        return this;
    }

    public String getTicket() {
        return this.ticket;
    }

    public WechatQrcodeTempModel setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public String getWxUrl() {
        return this.wxUrl;
    }

    public WechatQrcodeTempModel setWxUrl(String wxUrl) {
        this.wxUrl = wxUrl;
        return this;
    }

    public Long getDeadTime() {
        return this.deadTime;
    }

    public WechatQrcodeTempModel setDeadTime(Long deadTime) {
        this.deadTime = deadTime;
        return this;
    }

    public Integer getSceneID() {
        return sceneID;
    }

    public WechatQrcodeTempModel setSceneID(Integer sceneID) {
        this.sceneID = sceneID;
        return this;
    }

}