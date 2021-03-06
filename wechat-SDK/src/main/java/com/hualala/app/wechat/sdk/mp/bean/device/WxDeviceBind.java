package com.hualala.app.wechat.sdk.mp.bean.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDeviceBind extends AbstractDeviceBean {
  private static final long serialVersionUID = 467559769037590880L;

  private String ticket;
  @SerializedName("device_id")
  private String deviceId;
  @SerializedName("openid")
  private String openId;

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }
}
