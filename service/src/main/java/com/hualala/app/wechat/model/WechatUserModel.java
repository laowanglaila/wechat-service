package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;

public class WechatUserModel extends BaseItem {

    private Long itemID;
    private String mpID;
    private Long userID;
    private String userLoginName;
    private String openid;
    private String nickname;
    private String userNickName;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String photoImage;
    private Integer isSubscribe;

    public Long getItemID() {
        return this.itemID;
    }

    public WechatUserModel setItemID(Long itemID) {
        this.itemID = itemID;
        return this;
    }

    public String getMpID() {
        return this.mpID;
    }

    public WechatUserModel setMpID(String mpID) {
        this.mpID = mpID;
        return this;
    }

    public Long getUserID() {
        return this.userID;
    }

    public WechatUserModel setUserID(Long userID) {
        this.userID = userID;
        return this;
    }

    public String getUserLoginName() {
        return this.userLoginName;
    }

    public WechatUserModel setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
        return this;
    }

    public String getOpenid() {
        return this.openid;
    }

    public WechatUserModel setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getNickname() {
        return this.nickname;
    }

    public WechatUserModel setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getUserNickName() {
        return this.userNickName;
    }

    public WechatUserModel setUserNickName(String userNickName) {
        this.userNickName = userNickName;
        return this;
    }

    public Integer getSex() {
        return this.sex;
    }

    public WechatUserModel setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getProvince() {
        return this.province;
    }

    public WechatUserModel setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return this.city;
    }

    public WechatUserModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public WechatUserModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public WechatUserModel setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
        return this;
    }

    public String getPhotoImage() {
        return this.photoImage;
    }

    public WechatUserModel setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
        return this;
    }

    public Integer getIsSubscribe() {
        return this.isSubscribe;
    }

    public WechatUserModel setIsSubscribe(Integer isSubscribe) {
        this.isSubscribe = isSubscribe;
        return this;
    }
}