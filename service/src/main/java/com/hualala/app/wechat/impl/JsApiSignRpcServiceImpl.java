package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.JsApiSignRpcService;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.service.ApiTicketService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/6/28.
 */
@Service
public class JsApiSignRpcServiceImpl implements JsApiSignRpcService {

    @Autowired
    private ApiTicketService apiTicketService;

    @Override
    public JsApiSignResData getSign(JsApiSignReqData jsApiSignReqData) {
        String mpID = jsApiSignReqData.getMpID().trim();
        String url = jsApiSignReqData.getUrl().trim();
        if (StringUtils.isBlank(mpID)){
            return new JsApiSignResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }
        if (StringUtils.isBlank(url)) {
            return new JsApiSignResData().setResultInfo(ErrorCodes.WECHAT_SIGN_URL_EMPTY, "url不能为空！");
        }
        JSONObject jsApiTicket = apiTicketService.getJsApiTicket(mpID);
        if (!jsApiTicket.getBoolean(WechatMessageType.IS_SUCCESS)){
            return new JsApiSignResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }

        //todo  api_jsSDK

        return null;
    }
}
