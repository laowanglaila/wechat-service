package com.hualala.app.wechat.sdk.mp.api.group;

import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;

/**
 * 消息匹配器，用在消息路由的时候
 */
public interface WxGroupMpMessageMatcher {

  /**
   * 消息是否匹配某种模式
   *
   * @param message
   */
  boolean match(WxMpXmlMessage message);

}
