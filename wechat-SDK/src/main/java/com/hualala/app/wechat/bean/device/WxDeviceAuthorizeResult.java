package com.hualala.app.wechat.bean.device;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDeviceAuthorizeResult extends AbstractDeviceBean {
  private static final long serialVersionUID = 9105294570912249811L;

  private List<BaseResp> resp;

  public static WxDeviceAuthorizeResult fromJson(String response) {
    return WxGsonBuilder.create().fromJson(response, WxDeviceAuthorizeResult.class);
  }

  public List<BaseResp> getResp() {
    return resp;
  }

  public void setResp(List<BaseResp> resp) {
    this.resp = resp;
  }
}
