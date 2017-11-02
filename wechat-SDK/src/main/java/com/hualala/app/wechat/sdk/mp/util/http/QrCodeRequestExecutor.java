package com.hualala.app.wechat.sdk.mp.util.http;

import com.hualala.app.wechat.sdk.mp.bean.result.WxMpQrCodeTicket;
import com.hualala.app.wechat.sdk.mp.util.http.apache.ApacheQrCodeRequestExecutor;
import com.hualala.app.wechat.sdk.mp.util.http.okhttp.OkhttpQrCodeRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.File;

/**
 * 获得QrCode图片 请求执行器
 *
 * @author chanjarster
 */
public abstract class QrCodeRequestExecutor<H, P> implements RequestExecutor<File, WxMpQrCodeTicket> {
  protected RequestHttp<H, P> requestHttp;

  public QrCodeRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<File, WxMpQrCodeTicket> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrCodeRequestExecutor(requestHttp);
      case JODD_HTTP:
//        return new JoddQrCodeRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpQrCodeRequestExecutor(requestHttp);
      default:
        //TODO 需要优化，最好抛出异常
        return null;
    }
  }

}
