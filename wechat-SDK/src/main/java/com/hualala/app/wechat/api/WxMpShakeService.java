package com.hualala.app.wechat.api;

import com.hualala.app.wechat.bean.WxMpShakeInfoResult;
import com.hualala.app.wechat.bean.WxMpShakeQuery;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 摇一摇周边的相关接口
 *
 * @author rememberber
 */
public interface WxMpShakeService {

  /**
   * <pre>
   * 获取设备及用户信息<br/>
   * 获取设备信息，包括UUID、major、minor，以及距离、openID等信息。
   * 详情请见: https://mp.weixin.qq.com/wiki?action=doc&id=mp1443447963
   * http请求方式: POST（请使用https协议）
   * 接口地址：https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKE
   * </pre>
   *
   * @param wxMpShakeQuery 查询参数
   */
  WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException;

}
