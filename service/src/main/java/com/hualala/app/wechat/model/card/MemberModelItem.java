package com.hualala.app.wechat.model.card;

import com.hualala.core.base.BaseItem;
import java.io.Serializable;
import java.math.BigDecimal;

public class MemberModelItem extends BaseItem implements Serializable {
    private Long itemID;

    private Integer errcode;

    private String openid;

    private String nickname;

    private Long membershipNumber;

    private Boolean hasActive;

    private Integer bonus;

    private BigDecimal balance;

    private String sex;

    private String userCardStatus;

    private Long phone;

    private String birthday;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Long getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(Long membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public Boolean getHasActive() {
        return hasActive;
    }

    public void setHasActive(Boolean hasActive) {
        this.hasActive = hasActive;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getUserCardStatus() {
        return userCardStatus;
    }

    public void setUserCardStatus(String userCardStatus) {
        this.userCardStatus = userCardStatus == null ? null : userCardStatus.trim();
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemID=").append(itemID);
        sb.append(", errcode=").append(errcode);
        sb.append(", openid=").append(openid);
        sb.append(", nickname=").append(nickname);
        sb.append(", membershipNumber=").append(membershipNumber);
        sb.append(", hasActive=").append(hasActive);
        sb.append(", bonus=").append(bonus);
        sb.append(", balance=").append(balance);
        sb.append(", sex=").append(sex);
        sb.append(", userCardStatus=").append(userCardStatus);
        sb.append(", phone=").append(phone);
        sb.append(", birthday=").append(birthday);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}