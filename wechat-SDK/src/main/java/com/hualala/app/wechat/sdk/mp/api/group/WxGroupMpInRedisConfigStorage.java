package com.hualala.app.wechat.sdk.mp.api.group;

import com.hualala.app.wechat.sdk.mp.api.WxMpInMemoryConfigStorage;
import com.hualala.app.wechat.sdk.mp.api.group.mp.MpInfoProvider;
import com.hualala.app.wechat.sdk.mp.api.group.mp.MpInfoRelation;
import com.hualala.app.wechat.sdk.mp.common.WechatDefaultValue;
import com.hualala.app.wechat.sdk.mp.util.WechatBeanFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 基于Redis的微信配置provider
 *
 * @author renjianfei
 */
@SuppressWarnings("hiding")
public class WxGroupMpInRedisConfigStorage extends WxMpInMemoryConfigStorage {

  private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

  private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

  private final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";

  private String mpID;
  private Long groupID;
  private StringRedisTemplate stringRedisTemplate;

  public WxGroupMpInRedisConfigStorage(String mpID,StringRedisTemplate stringRedisTemplate){
      super();
      this.mpID = mpID;
      this.stringRedisTemplate = stringRedisTemplate;
      MpInfoProvider mpInfoProvider = WechatBeanFactory.getBean( MpInfoProvider.class );
      MpInfoRelation mpInfoRelation = mpInfoProvider.getMpInfoByMpID( mpID );
      if ("1".equals( mpInfoRelation.getAuthorize() )){
        this.setAesKey( WechatDefaultValue.ENCODING_AES_KEY );
        this.setToken( WechatDefaultValue.TOKEN );
      }else {
        this.setAesKey( mpInfoRelation.getAesKey() );
        this.setToken( mpInfoRelation.getToken() );
      }
      this.setGroupID( mpInfoRelation.getGroupID() );
      this.setAppId( mpInfoRelation.getAppID() );
      this.setSecret( mpInfoRelation.getSecret() );
  }

  @Override
  public String getAccessToken() {
    return stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY.concat(appId));
  }

  @Override
  public boolean isAccessTokenExpired() {
    return stringRedisTemplate.getExpire(ACCESS_TOKEN_KEY.concat(appId), TimeUnit.SECONDS) < 2;
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_KEY.concat(appId), accessToken,expiresInSeconds,TimeUnit.SECONDS);
  }

  @Override
  public void expireAccessToken() {
    stringRedisTemplate.expire(ACCESS_TOKEN_KEY.concat(appId), 0, TimeUnit.SECONDS);
  }

  @Override
  public String getJsapiTicket() {
    return stringRedisTemplate.opsForValue().get(JSAPI_TICKET_KEY.concat(appId));
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return stringRedisTemplate.getExpire(JSAPI_TICKET_KEY.concat(appId),TimeUnit.SECONDS) < 2;
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    stringRedisTemplate.opsForValue().set(JSAPI_TICKET_KEY.concat(appId), jsapiTicket,expiresInSeconds,TimeUnit.SECONDS);
  }

  @Override
  public void expireJsapiTicket() {
    stringRedisTemplate.expire(JSAPI_TICKET_KEY.concat(appId), 0,TimeUnit.SECONDS);
  }

  /**
   * 卡券api_ticket
   */
  @Override
  public String getCardApiTicket() {
    return stringRedisTemplate.opsForValue().get(CARDAPI_TICKET_KEY.concat(appId));
  }

  @Override
  public boolean isCardApiTicketExpired() {
    return stringRedisTemplate.getExpire(CARDAPI_TICKET_KEY.concat(appId),TimeUnit.SECONDS) < 2;
  }

  @Override
  public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    stringRedisTemplate.opsForValue().set(CARDAPI_TICKET_KEY.concat(appId), cardApiTicket,expiresInSeconds,TimeUnit.SECONDS);
  }

  @Override
  public void expireCardApiTicket() {
    stringRedisTemplate.expire(CARDAPI_TICKET_KEY.concat(appId), 0,TimeUnit.SECONDS);
  }

  @Override
  public Long getGroupID(){
    return this.groupID;
  }
  @Override
  public void setGroupID(Long groupID){
    this.groupID = groupID;
  }
  @Override
  public String getMpID(){
    return this.mpID;
  }
  @Override
  public void setMpID(String mpID){
    this.mpID = mpID;
  }
}
