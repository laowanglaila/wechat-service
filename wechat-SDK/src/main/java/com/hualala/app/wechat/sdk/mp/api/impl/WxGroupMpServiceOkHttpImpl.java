package com.hualala.app.wechat.sdk.mp.api.impl;

import com.hualala.app.wechat.grpc.WechatAccessTokenRpcData;
import com.hualala.app.wechat.grpc.WechatAccessTokenRpcServiceGrpc;
import com.hualala.app.wechat.sdk.mp.api.*;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.exception.WechatException;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.RequestExecutor;
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
    private ThreadLocal<String> mpIDProvider = new ThreadLocal <>();
    protected WechatAccessTokenRpcServiceGrpc.WechatAccessTokenRpcServiceFutureStub accessTokenStub;

    @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    logger.debug("WxMpServiceOkHttpImpl is running");
    WechatAccessTokenRpcData.AccessTokenReq accessTokenReq = WechatAccessTokenRpcData
            .AccessTokenReq
            .newBuilder()
            .setMpID( mpIDProvider.get() )
            .setIsForceRefresh( forceRefresh )
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

        mpIDProvider.set( mpID );
        return this.getKefuService();
    }

    @Override
    public WxMpMaterialService getMaterialService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getMaterialService();
    }

    @Override
    public WxMpMenuService getMenuService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getMenuService();
    }

    @Override
    public WxMpUserService getUserService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getUserService();
    }

    @Override
    public WxMpUserTagService getUserTagService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getUserTagService();
    }

    @Override
    public WxMpQrcodeService getQrcodeService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getQrcodeService();
    }

    @Override
    public WxMpCardService getCardService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getCardService();
    }

    @Override
    public WxMpDataCubeService getDataCubeService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getDataCubeService();
    }

    @Override
    public WxMpUserBlacklistService getBlackListService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getBlackListService();
    }

    @Override
    public WxMpStoreService getStoreService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getStoreService();
    }

    @Override
    public WxMpTemplateMsgService getTemplateMsgService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getTemplateMsgService();
    }

    @Override
    public WxMpDeviceService getDeviceService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getDeviceService();
    }

    @Override
    public WxMpShakeService getShakeService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getShakeService();
    }

    @Override
    public WxMpMemberCardService getMemberCardService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getMemberCardService();
    }


    @Override
    public WxMpMassMessageService getMassMessageService(String mpID) {
        mpIDProvider.set( mpID );
        return this.getMassMessageService();
    }


    @Override
    public <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
        if (uri.contains("access_token=")) {
            throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
        }
        String accessToken = getAccessToken(false);

        String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

        try {
            T result = executor.execute(uriWithAccessToken, data);
            this.log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, data, result);
            return result;
        } catch (WxErrorException e) {
            WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       * 40001 获取access_token时AppSecret错误，或者access_token无效
       * 42001 access_token超时
       * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
       */
            if (error.getErrorCode() == 42001) {
                // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
                getAccessToken(true);
                return this.execute(executor, uri, data);
            }

            if (error.getErrorCode() != 0) {
                this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, data, error);
                throw new WxErrorException(error, e);
            }
            return null;
        } catch (IOException e) {
            this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, data, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
