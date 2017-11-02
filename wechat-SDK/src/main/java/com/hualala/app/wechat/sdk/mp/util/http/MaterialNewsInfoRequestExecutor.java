package com.hualala.app.wechat.sdk.mp.util.http;

import com.hualala.app.wechat.sdk.mp.bean.material.WxMpMaterialNews;
import com.hualala.app.wechat.sdk.mp.util.http.apache.ApacheMaterialNewsInfoRequestExecutor;
import com.hualala.app.wechat.sdk.mp.util.http.okhttp.OkhttpMaterialNewsInfoRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;


public abstract class MaterialNewsInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialNews, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMpMaterialNews, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMaterialNewsInfoRequestExecutor(requestHttp);
      case JODD_HTTP:
//        return new JoddMaterialNewsInfoRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMaterialNewsInfoRequestExecutor(requestHttp);
      default:
        //TODO 需要优化抛出异常
        return null;
    }
  }

}
