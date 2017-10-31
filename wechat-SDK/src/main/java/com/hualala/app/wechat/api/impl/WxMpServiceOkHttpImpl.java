package com.hualala.app.wechat.api.impl;

import com.hualala.app.wechat.api.WxMpConfigStorage;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.grpc.WechatAccessTokenRpcData;
import com.hualala.app.wechat.grpc.WechatAccessTokenRpcServiceGrpc;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WxMpServiceOkHttpImpl extends WxMpServiceAbstractImpl<OkHttpClient, OkHttpProxyInfo> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;
  public WxMpServiceOkHttpImpl(WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub accessTokenStub){
    super();
    this.accessTokenStub = accessTokenStub;
    this.initHttp();
  }
  public static final Integer MAXIDLE_CONNECTION = 20;
  public static final Long KEEP_ALIVE_DURATION = 10L;
  public static final Long RESPONSE_TIMOUT = 5L;


  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    logger.debug("WxMpServiceOkHttpImpl is running");
    WechatAccessTokenRpcData.AccessTokenReq accessTokenReq = WechatAccessTokenRpcData
            .AccessTokenReq
            .newBuilder()
            .setMpID( super.getMpID() )
            .build();
    WechatAccessTokenRpcData.AccessTokenRes accessTokenRes = null;
    try {
      accessTokenRes = super.accessTokenStub
              .withDeadlineAfter( 10L, TimeUnit.SECONDS )
              .getAccessToken( accessTokenReq )
              .get();
      String code = accessTokenRes.getResult().getCode();
      String message = accessTokenRes.getResult().getMessage();
      if (!"000".equals( code )){
        log.error( "获取accessToken失败：[" + code +"]:"+ message );
        throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD,message );
      }

    } catch (InterruptedException e) {
      log.error( "获取accessToken接口调用失败：" + e.getMessage() );
      throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD,"获取accessToken接口调用失败：" + e.getMessage() );
    } catch (ExecutionException e) {
      log.error("获取accessToken接口调用失败：" + e.getMessage() );
      throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD,"获取accessToken接口调用失败：" + e.getMessage() );
    }
    return accessTokenRes.getAccessToken();
  }

  @Override
  public void initHttp() {
    logger.debug("WxMpServiceOkHttpImpl initHttp");
    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();

    if (configStorage != null && configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.socks5Proxy(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }
    ConnectionPool connectionPool = new ConnectionPool(MAXIDLE_CONNECTION, KEEP_ALIVE_DURATION, TimeUnit.MINUTES);
    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
            .connectionPool(connectionPool)
            .connectTimeout( RESPONSE_TIMOUT,TimeUnit.SECONDS );
                //设置代理
    if (httpProxy != null) {
      clientBuilder.proxy(getRequestHttpProxy().getProxy());

      //设置授权
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
        }
      });
    }
    httpClient = clientBuilder.build();
  }

}
