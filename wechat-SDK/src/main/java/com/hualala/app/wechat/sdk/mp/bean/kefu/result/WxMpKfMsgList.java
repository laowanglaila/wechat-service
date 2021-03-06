package com.hualala.app.wechat.sdk.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by renjianfei on 2016/7/15.
 */
public class WxMpKfMsgList implements Serializable {
  private static final long serialVersionUID = 4524296707435188202L;

  @SerializedName("recordlist")
  private List<WxMpKfMsgRecord> records;

  @SerializedName("number")
  private Integer number;

  @SerializedName("msgid")
  private Long msgId;

  public static WxMpKfMsgList fromJson(String responseContent) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(responseContent, WxMpKfMsgList.class);
  }

  public List<WxMpKfMsgRecord> getRecords() {
    return this.records;
  }

  public void setRecords(List<WxMpKfMsgRecord> records) {
    this.records = records;
  }

  public Integer getNumber() {
    return this.number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Long getMsgId() {
    return this.msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
