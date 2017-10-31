package com.hualala.app.wechat.api.impl;

import com.hualala.app.wechat.api.*;
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

/**
 * Created by renjianfei on 2017/10/31.
 * 支持多公众号微信客户端
 */
public class WxGroupMpServiceOkHttpImpl extends WxMpServiceOkHttpImpl implements WxGroupMpService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OkHttpClient httpClient;
    private OkHttpProxyInfo httpProxy;
    public WxGroupMpServiceOkHttpImpl(WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub accessTokenStub){
        super();
        this.accessTokenStub = accessTokenStub;
        this.initHttp();
    }
    public static final Integer MAXIDLE_CONNECTION = 20;
    public static final Long KEEP_ALIVE_DURATION = 10L;
    public static final Long RESPONSE_TIMOUT = 5L;
    private String mpID;
    protected WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub accessTokenStub;

    @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    logger.debug("WxMpServiceOkHttpImpl is running");
    WechatAccessTokenRpcData.AccessTokenReq accessTokenReq = WechatAccessTokenRpcData
            .AccessTokenReq
            .newBuilder()
            .setMpID( this.mpID )
            .build();
    WechatAccessTokenRpcData.AccessTokenRes accessTokenRes = null;
    try {
      accessTokenRes = this.accessTokenStub
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
    public WxMpKefuService getKefuService(String mpID) {
        this.mpID = mpID;
        return this.getKefuService();
    }

    @Override
    public WxMpMaterialService getMaterialService(String mpID) {
        this.mpID = mpID;
        return this.getMaterialService();
    }

    @Override
    public WxMpMenuService getMenuService(String mpID) {
        this.mpID = mpID;
        return this.getMenuService();
    }

    @Override
    public WxMpUserService getUserService(String mpID) {
        this.mpID = mpID;
        return this.getUserService();
    }

    @Override
    public WxMpUserTagService getUserTagService(String mpID) {
        this.mpID = mpID;
        return this.getUserTagService();
    }

    @Override
    public WxMpQrcodeService getQrcodeService(String mpID) {
        this.mpID = mpID;
        return this.getQrcodeService();
    }

    @Override
    public WxMpCardService getCardService(String mpID) {
        this.mpID = mpID;
        return this.getCardService();
    }

    @Override
    public WxMpDataCubeService getDataCubeService(String mpID) {
        this.mpID = mpID;
        return this.getDataCubeService();
    }

    @Override
    public WxMpUserBlacklistService getBlackListService(String mpID) {
        this.mpID = mpID;
        return this.getBlackListService();
    }

    @Override
    public WxMpStoreService getStoreService(String mpID) {
        this.mpID = mpID;
        return this.getStoreService();
    }

    @Override
    public WxMpTemplateMsgService getTemplateMsgService(String mpID) {
        this.mpID = mpID;
        return this.getTemplateMsgService();
    }

    @Override
    public WxMpDeviceService getDeviceService(String mpID) {
        this.mpID = mpID;
        return this.getDeviceService();
    }

    @Override
    public WxMpShakeService getShakeService(String mpID) {
        this.mpID = mpID;
        return this.getShakeService();
    }

    @Override
    public WxMpMemberCardService getMemberCardService(String mpID) {
        this.mpID = mpID;
        return this.getMemberCardService();
    }


    @Override
    public WxMpMassMessageService getMassMessageService(String mpID) {
        this.mpID = mpID;
        return this.getMassMessageService();
    }

}
