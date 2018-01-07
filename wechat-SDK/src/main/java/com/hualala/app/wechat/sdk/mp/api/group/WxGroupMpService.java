package com.hualala.app.wechat.sdk.mp.api.group;

import com.hualala.app.wechat.sdk.mp.api.*;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 微信API的Service,多公众号
 */
public interface WxGroupMpService  {
  /**
   * <pre>
   * 验证消息的确来自微信服务器
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 返回客服接口方法实现类，以方便调用其各个接口
   *
   * @return WxMpKefuService
   */
  WxMpKefuService getKefuService(String mpID);

  /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpMaterialService
   */
  WxMpMaterialService getMaterialService(String mpID);

  /**
   * 返回菜单相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpMenuService
   */
  WxMpMenuService getMenuService(String mpID);

  /**
   * 返回用户相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpUserService
   */
  WxMpUserService getUserService(String mpID);

  /**
   * 返回用户标签相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpUserTagService
   */
  WxMpUserTagService getUserTagService(String mpID);

  /**
   * 返回二维码相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpQrcodeService
   */
  WxMpQrcodeService getQrcodeService(String mpID);

  /**
   * 返回卡券相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpCardService
   */
  WxMpCardService getCardService(String mpID);

  /**
   * 返回数据分析统计相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpDataCubeService
   */
  WxMpDataCubeService getDataCubeService(String mpID);

  /**
   * 返回用户黑名单管理相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpUserBlacklistService
   */
  WxMpUserBlacklistService getBlackListService(String mpID);

  /**
   * 返回门店管理相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpStoreService
   */
  WxMpStoreService getStoreService(String mpID);

  /**
   * 返回模板消息相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpTemplateMsgService
   */
  WxMpTemplateMsgService getTemplateMsgService(String mpID);

  /**
   * 返回硬件平台相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpDeviceService
   */
  WxMpDeviceService getDeviceService(String mpID);

  /**
   * 返回摇一摇周边相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpShakeService
   */
  WxMpShakeService getShakeService(String mpID);

  /**
   * 返回会员卡相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxMpMemberCardService
   */
  WxMpMemberCardService getMemberCardService(String mpID);

  /**
   * 返回群发消息相关接口方法的实现类对象，以方便调用其各个接口
   * @return WxMpMassMessageService
   */
  WxMpMassMessageService getMassMessageService(String mpID);

  /**
   * 初始化WxMpConfigStorage
   * @param mpID
   */
  WxMpConfigStorage initWxMpConfigStorage(String mpID);
  WxMpConfigStorage initWxMpConfigStorage(String mpID, StringRedisTemplate stringRedisTemplate);

  /**
   * 获取WxMpConfigStorage 对象
   *
   * @return WxMpConfigStorage
   */
  WxMpConfigStorage getWxMpConfigStorage();

  /**
   * 注入 {@link WxMpConfigStorage} 的实现
   */
  void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider);
  /**
   * @return RequestHttp对象
   */
  RequestHttp getRequestHttp();

  void setKefuService(WxMpKefuService kefuService);

  void setMaterialService(WxMpMaterialService materialService);

  void setMenuService(WxMpMenuService menuService);

  void setUserService(WxMpUserService userService);

  void setTagService(WxMpUserTagService tagService);

  void setQrCodeService(WxMpQrcodeService qrCodeService);

  void setCardService(WxMpCardService cardService);

  void setStoreService(WxMpStoreService storeService);

  void setDataCubeService(WxMpDataCubeService dataCubeService);

  void setBlackListService(WxMpUserBlacklistService blackListService);

  void setTemplateMsgService(WxMpTemplateMsgService templateMsgService);

  void setDeviceService(WxMpDeviceService deviceService);

  void setShakeService(WxMpShakeService shakeService);

  void setMemberCardService(WxMpMemberCardService memberCardService);

  void setMassMessageService(WxMpMassMessageService massMessageService);
}
