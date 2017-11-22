package com.hualala.app.wechat.sdk.mp.builder.kefu;

import com.hualala.app.wechat.sdk.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

/**
 * 卡券消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpKefuMessage.WXCARD().cardId(...).toUser(...).build();
 * </pre>
 *
 * @author mgcnrx11
 */
public final class WxCardBuilder extends BaseBuilder<WxCardBuilder> {
  private String cardId;

  public WxCardBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_WXCARD;
  }

  public WxCardBuilder cardId(String cardId) {
    this.cardId = cardId;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setCardId(this.cardId);
    return m;
  }
}
