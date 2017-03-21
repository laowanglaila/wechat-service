package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;

public class WechatAutoreplyModel extends BaseItem {

    private Long itemID;
    private String mpID;
    private String resourceID;
    private String pushMsgType;
    private String pushContent;
    private Integer pushContentType;
    private String pushEvent;
    private String pushEventKey;
    private String replyMsgType;
    private String serviceName;
    private Integer isActive;

    public Long getItemID() {
        return this.itemID;
    }

    public WechatAutoreplyModel setItemID(Long itemID) {
        this.itemID = itemID;
        return this;
    }

    public String getMpID() {
        return this.mpID;
    }

    public WechatAutoreplyModel setMpID(String mpID) {
        this.mpID = mpID;
        return this;
    }

    public String getResourceID() {
        return this.resourceID;
    }

    public WechatAutoreplyModel setResourceID(String resourceID) {
        this.resourceID = resourceID;
        return this;
    }

    public String getPushMsgType() {
        return this.pushMsgType;
    }

    public WechatAutoreplyModel setPushMsgType(String pushMsgType) {
        this.pushMsgType = pushMsgType;
        return this;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public WechatAutoreplyModel setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public Integer getPushContentType() {
        return this.pushContentType;
    }

    public WechatAutoreplyModel setPushContentType(Integer pushContentType) {
        this.pushContentType = pushContentType;
        return this;
    }

    public String getPushEvent() {
        return this.pushEvent;
    }

    public WechatAutoreplyModel setPushEvent(String pushEvent) {
        this.pushEvent = pushEvent;
        return this;
    }

    public String getPushEventKey() {
        return this.pushEventKey;
    }

    public WechatAutoreplyModel setPushEventKey(String pushEventKey) {
        this.pushEventKey = pushEventKey;
        return this;
    }

    public String getReplyMsgType() {
        return this.replyMsgType;
    }

    public WechatAutoreplyModel setReplyMsgType(String replyMsgType) {
        this.replyMsgType = replyMsgType;
        return this;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public WechatAutoreplyModel setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public WechatAutoreplyModel setIsActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }
}