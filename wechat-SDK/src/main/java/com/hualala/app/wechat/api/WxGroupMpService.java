package com.hualala.app.wechat.api;

import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * 微信API的Service,多公众号
 */
public interface WxGroupMpService  {

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
