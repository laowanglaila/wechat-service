package com.hualala.app.wechat.sdk.mp.bean.template;

import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 模板列表信息
 * Created by renjianfei on 2016-10-17.
 * </pre>
 *
 * @author <a href="">renjianfei(renjianfei)</a>
 */
public class WxMpTemplate implements Serializable {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private static final long serialVersionUID = -7366474522571199372L;

  /**
   * template_id
   * 模板ID
   */
  @SerializedName("template_id")
  private String templateId;
  /**
   * title
   * 模板标题
   */
  @SerializedName("title")
  private String title;
  /**
   * primary_industry
   * 模板所属行业的一级行业
   */
  @SerializedName("primary_industry")
  private String primaryIndustry;
  /**
   * deputy_industry
   * 模板所属行业的二级行业
   */
  @SerializedName("deputy_industry")
  private String deputyIndustry;
  /**
   * content
   * 模板内容
   */
  @SerializedName("content")
  private String content;
  /**
   * example
   * 模板示例
   */
  @SerializedName("example")
  private String example;

  public static List<WxMpTemplate> fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(JSON_PARSER.parse(json).getAsJsonObject().get("template_list"),
      new TypeToken<List<WxMpTemplate>>() {
      }.getType());
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getTemplateId() {
    return this.templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPrimaryIndustry() {
    return this.primaryIndustry;
  }

  public void setPrimaryIndustry(String primaryIndustry) {
    this.primaryIndustry = primaryIndustry;
  }

  public String getDeputyIndustry() {
    return this.deputyIndustry;
  }

  public void setDeputyIndustry(String deputyIndustry) {
    this.deputyIndustry = deputyIndustry;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getExample() {
    return this.example;
  }

  public void setExample(String example) {
    this.example = example;
  }

}
