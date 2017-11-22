package com.hualala.app.wechat.sdk.mp.bean.membercard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <pre>
 * 控制原生消息结构体，包含各字段的消息控制字段。
 *
 * 用于 `7 更新会员信息` 的接口参数调用
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283
 * </pre>
 *
 * @author YuJian(mgcnrx11@gmail.com)
 * @version 2017/7/15
 */
public class NotifyOptional implements Serializable {
  private static final long serialVersionUID = 4488842021504939176L;

  /**
   * 积分变动时是否触发系统模板消息，默认为true
   */
  @SerializedName("is_notify_bonus")
  private Boolean isNotifyBonus;

  /**
   * 余额变动时是否触发系统模板消息，默认为true
   */
  @SerializedName("is_notify_balance")
  private Boolean isNotifyBalance;

  /**
   * 自定义group1变动时是否触发系统模板消息，默认为false。（2、3同理）
   */
  @SerializedName("is_notify_custom_field1")
  private Boolean isNotifyCustomField1;

  @SerializedName("is_notify_custom_field2")
  private Boolean isNotifyCustomField2;

  @SerializedName("is_notify_custom_field3")
  private Boolean isNotifyCustomField3;

  public Boolean getNotifyBonus() {
    return isNotifyBonus;
  }

  public void setNotifyBonus(Boolean notifyBonus) {
    isNotifyBonus = notifyBonus;
  }

  public Boolean getNotifyBalance() {
    return isNotifyBalance;
  }

  public void setNotifyBalance(Boolean notifyBalance) {
    isNotifyBalance = notifyBalance;
  }

  public Boolean getNotifyCustomField1() {
    return isNotifyCustomField1;
  }

  public void setNotifyCustomField1(Boolean notifyCustomField1) {
    isNotifyCustomField1 = notifyCustomField1;
  }

  public Boolean getNotifyCustomField2() {
    return isNotifyCustomField2;
  }

  public void setNotifyCustomField2(Boolean notifyCustomField2) {
    isNotifyCustomField2 = notifyCustomField2;
  }

  public Boolean getNotifyCustomField3() {
    return isNotifyCustomField3;
  }

  public void setNotifyCustomField3(Boolean notifyCustomField3) {
    isNotifyCustomField3 = notifyCustomField3;
  }
}
