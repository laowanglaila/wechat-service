package com.hualala.app.wechat.sdk.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDeviceQrCodeResult extends AbstractDeviceBean {
  private static final long serialVersionUID = -4312858303060918266L;

  @SerializedName("deviceid")
  private String deviceId;
  @SerializedName("qrticket")
  private String qrTicket;
  @SerializedName("devicelicence")
  private String deviceLicence;
  @SerializedName("base_resp")
  private BaseResp baseResp;

  public static WxDeviceQrCodeResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxDeviceQrCodeResult.class);
  }

  public String getDeviceLicence() {
    return deviceLicence;
  }

  public void setDeviceLicence(String deviceLicence) {
    this.deviceLicence = deviceLicence;
  }

  public BaseResp getBaseResp() {
    return baseResp;
  }

  public void setBaseResp(BaseResp baseResp) {
    this.baseResp = baseResp;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getQrTicket() {
    return qrTicket;
  }

  public void setQrTicket(String qrTicket) {
    this.qrTicket = qrTicket;
  }
}
