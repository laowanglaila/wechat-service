package com.hualala.app.wechat.util.http;

import com.hualala.app.wechat.bean.material.WxMpMaterial;
import com.hualala.app.wechat.bean.material.WxMpMaterialUploadResult;
import com.hualala.app.wechat.util.http.apache.ApacheMaterialUploadRequestExecutor;
import com.hualala.app.wechat.util.http.okhttp.OkhttpMaterialUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

public abstract class MaterialUploadRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMaterialUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
//        return new JoddMaterialUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMaterialUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
