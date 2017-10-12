package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.JsApiSignRpcService;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.service.ApiTicketService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.util.JsApiSignUtil;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/6/28.
 */
@Service
public class JsApiSignRpcServiceImpl implements JsApiSignRpcService {

    @Autowired
    private ApiTicketService apiTicketService;
    @Autowired
    private MpInfoService mpInfoService;

    private static final String HUALALA_COM = WechatMessageType.HUALALA_COM;
    @Override
    public JsApiSignResData getSign(JsApiSignReqData jsApiSignReqData) {
        String mpID = jsApiSignReqData.getMpID();
        String url = jsApiSignReqData.getUrl();
        if (StringUtils.isBlank(mpID)){
            mpID = HUALALA_COM;
        }
        if (StringUtils.isBlank(url)) {
            return new JsApiSignResData().setResultInfo(ErrorCodes.WECHAT_SIGN_URL_EMPTY, "url不能为空！");
        }
        JSONObject jsApiTicket = apiTicketService.getJsApiTicket(mpID);
        if (!jsApiTicket.getBoolean(WechatMessageType.IS_SUCCESS)){
            return ResultUtil.getResultInfoBean(jsApiTicket,JsApiSignResData.class);
        }
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("mpID",mpID);
        Map<String, Object> stringObjectMap = mpInfoService.queryMpInfo(hashMap);
        String appID = String.valueOf(stringObjectMap.get("appID"));
        if (StringUtils.isBlank(appID)){
            return new JsApiSignResData().setResultInfo(ErrorCodes.WECHAT_MPID_NOT_FOUND, "appID没有找到！");
        }
        //todo  api_jsSDK
        Map<String, String> signMap = JsApiSignUtil.sign(jsApiTicket.getString("ticket"), url);

        JsApiSignResData jsApiSignResData = new JsApiSignResData();
        jsApiSignResData.setAppId(appID);
        jsApiSignResData.setNonceStr(signMap.get("nonceStr"));
        jsApiSignResData.setTimeStamp(signMap.get("timestamp"));
        jsApiSignResData.setSignature(signMap.get("signature"));

        return jsApiSignResData;
    }
}
