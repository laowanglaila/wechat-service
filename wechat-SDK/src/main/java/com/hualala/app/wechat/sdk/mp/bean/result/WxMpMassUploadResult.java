package com.hualala.app.wechat.sdk.mp.bean.result;

import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;

/**
 * <pre>
 * 上传群发用的素材的结果
 * 视频和图文消息需要在群发前上传素材
 * </pre>
 *
 * @author chanjarster
 */
public class WxMpMassUploadResult implements Serializable {
  private static final long serialVersionUID = 6568157943644994029L;

  private String type;
  private String mediaId;
  private long createdAt;

  public static WxMpMassUploadResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMassUploadResult.class);
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public long getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
