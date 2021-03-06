package com.hualala.app.wechat.sdk.mp.api.group;

import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlOutMessage;
import com.hualala.app.wechat.sdk.mp.util.WechatBeanFactory;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class WxGroupMpMessageRouterRule {

  private final WxGroupMpMessageRouter routerBuilder;

  private boolean async = true;

  private String fromUser;

  private String msgType;

  private String event;

  private String eventKey;

  private String eventKeyRegex;

  private String content;

  private String rContent;

  private WxGroupMpMessageMatcher matcher;

  private boolean reEnter = false;

  private List<WxGroupMpMessageHandler> handlers = new ArrayList<>();

  private List<WxGroupMpMessageInterceptor> interceptors = new ArrayList<>();

  public WxGroupMpMessageRouterRule(WxGroupMpMessageRouter routerBuilder) {
    this.routerBuilder = routerBuilder;
  }

  /**
   * 设置是否异步执行，默认是true
   */
  public WxGroupMpMessageRouterRule async(boolean async) {
    this.async = async;
    return this;
  }

  /**
   * 如果msgType等于某值
   */
  public WxGroupMpMessageRouterRule msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  /**
   * 如果event等于某值
   */
  public WxGroupMpMessageRouterRule event(String event) {
    this.event = event;
    return this;
  }

  /**
   * 如果eventKey等于某值
   */
  public WxGroupMpMessageRouterRule eventKey(String eventKey) {
    this.eventKey = eventKey;
    return this;
  }

  /**
   * 如果eventKey匹配该正则表达式
   */
  public WxGroupMpMessageRouterRule eventKeyRegex(String regex) {
    this.eventKeyRegex = regex;
    return this;
  }

  /**
   * 如果content等于某值
   */
  public WxGroupMpMessageRouterRule content(String content) {
    this.content = content;
    return this;
  }

  /**
   * 如果content匹配该正则表达式
   */
  public WxGroupMpMessageRouterRule rContent(String regex) {
    this.rContent = regex;
    return this;
  }

  /**
   * 如果fromUser等于某值
   */
  public WxGroupMpMessageRouterRule fromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }

  /**
   * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
   */
  public WxGroupMpMessageRouterRule matcher(WxGroupMpMessageMatcher matcher) {
    this.matcher = matcher;
    return this;
  }
  public WxGroupMpMessageRouterRule matcher(Class<? extends WxGroupMpMessageMatcher> clazz) {
    WxGroupMpMessageMatcher matcher = WechatBeanFactory.getBean( clazz );
    this.matcher = matcher;
    return this;
  }

  /**
   * 设置微信消息拦截器
   */
  public WxGroupMpMessageRouterRule interceptor(WxGroupMpMessageInterceptor interceptor) {
    return interceptor(interceptor, (WxGroupMpMessageInterceptor[]) null);
  }

  /**
   * 设置微信消息拦截器
   */
  public WxGroupMpMessageRouterRule interceptor(WxGroupMpMessageInterceptor interceptor, WxGroupMpMessageInterceptor... otherInterceptors) {
    this.interceptors.add(interceptor);
    if (otherInterceptors != null && otherInterceptors.length > 0) {
      for (WxGroupMpMessageInterceptor i : otherInterceptors) {
        this.interceptors.add(i);
      }
    }
    return this;
  }

  /**
   * 设置微信消息处理器
   */
  public WxGroupMpMessageRouterRule handler(WxGroupMpMessageHandler handler) {
    return handler(handler, (WxGroupMpMessageHandler[]) null);
  }
  /**
   * 设置微信消息处理器
   */
  public WxGroupMpMessageRouterRule handler(Class<? extends WxGroupMpMessageHandler> handlerClass) {
    WxGroupMpMessageHandler handler = WechatBeanFactory.getBean( handlerClass );
    return handler(handler, (WxGroupMpMessageHandler[]) null);
  }

  /**
   * 设置微信消息处理器
   */
  public WxGroupMpMessageRouterRule handler(WxGroupMpMessageHandler handler, WxGroupMpMessageHandler... otherHandlers) {
    this.handlers.add(handler);
    if (otherHandlers != null && otherHandlers.length > 0) {
      for (WxGroupMpMessageHandler i : otherHandlers) {
        this.handlers.add(i);
      }
    }
    return this;
  }

  /**
   * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
   */
  public WxGroupMpMessageRouter end() {
    this.routerBuilder.putRule( this.msgType, this.event, this.eventKey,this );
    return this.routerBuilder;
  }

  /**
   * 规则结束，但是消息还会进入其他规则
   */
  public WxGroupMpMessageRouter next() {
    this.reEnter = true;
    return end();
  }

  /**
   * 将微信自定义的事件修正为不区分大小写,
   * 比如框架定义的事件常量为click，但微信传递过来的却是CLICK
   */
  protected boolean test(WxMpXmlMessage wxMessage) {
    return
      (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUser()))
        &&
        (this.msgType == null || this.msgType.equalsIgnoreCase(wxMessage.getMsgType()))
        &&
        (this.event == null || this.event.equalsIgnoreCase(wxMessage.getEvent()))
        &&
        (this.eventKey == null || this.eventKey.equalsIgnoreCase(wxMessage.getEventKey()))
        &&
        (this.eventKeyRegex == null || Pattern.matches(this.eventKeyRegex, StringUtils.trimToEmpty(wxMessage.getEventKey())))
        &&
        (this.content == null || this.content.equals( StringUtils.trimToNull(wxMessage.getContent())))
        &&
        (this.rContent == null || Pattern.matches(this.rContent, StringUtils.trimToEmpty(wxMessage.getContent())))
        &&
        (this.matcher == null || this.matcher.match(wxMessage))
      ;
  }

  /**
   * 处理微信推送过来的消息
   *
   * @param wxMessage
   * @return true 代表继续执行别的router，false 代表停止执行别的router
   */
  protected WxMpXmlOutMessage service(WxMpXmlMessage wxMessage,
                                      Map<String, Object> context,
                                      WxGroupMpService wxMpService,
                                      WxSessionManager sessionManager,
                                      WxErrorExceptionHandler exceptionHandler) {

    if (context == null) {
      context = new HashMap<>();
    }

    try {
      // 如果拦截器不通过
      for (WxGroupMpMessageInterceptor interceptor : this.interceptors) {
        if (!interceptor.intercept(wxMessage, context, wxMpService, sessionManager)) {
          return null;
        }
      }

      // 交给handler处理
      WxMpXmlOutMessage res = null;
      for (WxGroupMpMessageHandler handler : this.handlers) {
        // 返回最后handler的结果
        if (handler == null) {
          continue;
        }
        res = handler.handle(wxMessage, context, wxMpService, sessionManager);
      }
      return res;
    } catch (WxErrorException e) {
      exceptionHandler.handle(e);
    }
    return null;

  }

  public WxGroupMpMessageRouter getRouterBuilder() {
    return this.routerBuilder;
  }

  public boolean isAsync() {
    return this.async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }

  public String getFromUser() {
    return this.fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public String getMsgType() {
    return this.msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getEvent() {
    return this.event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getEventKey() {
    return this.eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getrContent() {
    return this.rContent;
  }

  public void setrContent(String rContent) {
    this.rContent = rContent;
  }

  public WxGroupMpMessageMatcher getMatcher() {
    return this.matcher;
  }

  public void setMatcher(WxGroupMpMessageMatcher matcher) {
    this.matcher = matcher;
  }

  public boolean isReEnter() {
    return this.reEnter;
  }

  public void setReEnter(boolean reEnter) {
    this.reEnter = reEnter;
  }

  public List<WxGroupMpMessageHandler> getHandlers() {
    return this.handlers;
  }

  public void setHandlers(List<WxGroupMpMessageHandler> handlers) {
    this.handlers = handlers;
  }

  public List<WxGroupMpMessageInterceptor> getInterceptors() {
    return this.interceptors;
  }

  public void setInterceptors(List<WxGroupMpMessageInterceptor> interceptors) {
    this.interceptors = interceptors;
  }
}
