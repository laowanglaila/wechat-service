package com.hualala.app.wechat.sdk.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * Created by keungtung on 16/12/2016.
 */
public class WxDeviceBindDeviceResult extends AbstractDeviceBean {
  private static final long serialVersionUID = 725870295905935355L;

  @SerializedName("resp_msg")
  private RespMsg respMsg;
  @SerializedName("openid")
  private String openId;
  @SerializedName("device_list")
  private List<Device> devices;

  public static WxDeviceBindDeviceResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxDeviceBindDeviceResult.class);
  }

  private class Device {
    @SerializedName("device_type")
    private String deviceType;
    @SerializedName("device_id")
    private String deviceId;

    public String getDeviceType() {
      return deviceType;
    }

    public void setDeviceType(String deviceType) {
      this.deviceType = deviceType;
    }

    public String getDeviceId() {
      return deviceId;
    }

    public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
    }
  }

}
