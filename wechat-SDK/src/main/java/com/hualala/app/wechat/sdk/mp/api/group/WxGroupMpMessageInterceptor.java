package com.hualala.app.wechat.sdk.mp.api.group;

import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

import java.util.Map;

/**
 * 微信消息拦截器，可以用来做验证
 *
 * @author Daniel Qian
 */
public interface WxGroupMpMessageInterceptor {

  /**
   * 拦截微信消息
   *
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxMpService
   * @param sessionManager
   * @return true代表OK，false代表不OK
   */
  boolean intercept(WxMpXmlMessage wxMessage,
                    Map <String, Object> context,
                    WxGroupMpService wxMpService,
                    WxSessionManager sessionManager) throws WxErrorException;

}
