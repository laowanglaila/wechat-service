package com.hualala.app.wechat.sdk.mp.api.impl;

import com.hualala.app.wechat.sdk.mp.api.WxMpService;
import com.hualala.app.wechat.sdk.mp.api.WxMpShakeService;
import com.hualala.app.wechat.sdk.mp.bean.WxMpShakeInfoResult;
import com.hualala.app.wechat.sdk.mp.bean.WxMpShakeQuery;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * Created by rememberber on 2017/6/5.
 * @author rememberber
 */
public class WxMpShakeServiceImpl implements WxMpShakeService {

  private WxMpService wxMpService;

  public WxMpShakeServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  /**
   * <pre>
   * 获取设备及用户信息<br/>
   * 获取设备信息，包括UUID、major、minor，以及距离、openID等信息。
   * 详情请见: https://mp.weixin.qq.com/wiki?action=doc&id=mp1443447963
   * http请求方式: POST（请使用https协议）
   * 接口地址：https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKE
   * </pre>
   *
   * @param wxMpShakeQuery  查询参数
   */
  @Override
  public WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/user/getshakeinfo";
    String postData = wxMpShakeQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeInfoResult.fromJson(responseContent);
  }
}
