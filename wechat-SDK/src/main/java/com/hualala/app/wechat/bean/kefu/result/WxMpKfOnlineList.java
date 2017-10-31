package com.hualala.app.wechat.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Binary Wang
 */
public class WxMpKfOnlineList implements Serializable {
  private static final long serialVersionUID = -6154705288500854617L;

  @SerializedName("kf_online_list")
  private List<WxMpKfInfo> kfOnlineList;

  public static WxMpKfOnlineList fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpKfOnlineList.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxMpKfInfo> getKfOnlineList() {
    return this.kfOnlineList;
  }

  public void setKfOnlineList(List<WxMpKfInfo> kfOnlineList) {
    this.kfOnlineList = kfOnlineList;
  }
}
