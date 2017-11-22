package com.hualala.app.wechat.sdk.mp.util.http.okhttp;

import com.hualala.app.wechat.sdk.mp.bean.result.WxMpQrCodeTicket;
import com.hualala.app.wechat.sdk.mp.util.http.QrCodeRequestExecutor;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class OkhttpQrCodeRequestExecutor extends QrCodeRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkhttpQrCodeRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public File execute(String uri, WxMpQrCodeTicket data) throws WxErrorException, IOException {
    logger.debug("OkhttpQrCodeRequestExecutor is running");
    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();
    Request request = new Request.Builder().url(uri).get().build();
    Response response = client.newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException( WxError.fromJson(responseContent));
    }
    File temp = File.createTempFile(UUID.randomUUID().toString(), ".png");
    try (BufferedSink sink = Okio.buffer( Okio.sink(temp))) {
      sink.writeAll(response.body().source());
    }
    temp.deleteOnExit();

    return temp;
  }
}
