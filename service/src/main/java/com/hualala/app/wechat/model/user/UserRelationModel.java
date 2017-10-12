package com.hualala.app.wechat.model.user;

import com.hualala.core.base.BaseItem;
import java.io.Serializable;

public class UserRelationModel extends BaseItem implements Serializable {
    private Long itemID;

    /**
     * 用户ID
     */
    private Long userID;

    /**
     * 公众号ID
     */
    private String mpID;

    /**
     * 微信用户公众号唯一标识，同一公众号内唯一
     */
    private String openid;

    /**
     * 1代表已关注，2代表取消关注，0未知。
     */
    private Integer subscribe;

    private static final long serialVersionUID = 1L;

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getMpID() {
        return mpID;
    }

    public void setMpID(String mpID) {
        this.mpID = mpID == null ? null : mpID.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemID=").append(itemID);
        sb.append(", userID=").append(userID);
        sb.append(", mpID=").append(mpID);
        sb.append(", openid=").append(openid);
        sb.append(", subscribe=").append(subscribe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}