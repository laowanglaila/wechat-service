package com.hualala.app.wechat.sdk.mp.builder.kefu;

import com.hualala.app.wechat.sdk.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxMpKefuMessage m = WxMpKefuMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 *
 * @author chanjarster
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {
  private List<WxMpKefuMessage.WxArticle> articles = new ArrayList<>();

  public NewsBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_NEWS;
  }

  public NewsBuilder addArticle(WxMpKefuMessage.WxArticle... articles) {
    Collections.addAll(this.articles, articles);
    return this;
  }

  public NewsBuilder articles(List<WxMpKefuMessage.WxArticle> articles) {
    this.articles = articles;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
