package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatOAuthRpcService;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.service.WechatUserOAuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatOAuthRpcServiceImpl implements WechatOAuthRpcService {

    @Autowired
    private WechatUserOAuthService wechatUserOAuthService;

    @Override
    public WechatOAuthResData wechatOAuthUrl(WechatOAuthReqData reqData) {

        String mpID = reqData.getMpID();
        String state = WechatMessageType.STATE_SHOP_OAUTH;
        if(StringUtils.isEmpty(mpID) || WechatMessageType.HUALALA_COM.equals(mpID)){
            mpID = WechatMessageType.HUALALA_COM;
            state = WechatMessageType.STATE_HLL_OAUTH;
        }
        String oauthUrl = wechatUserOAuthService.OAuthApIUrl(mpID,state,reqData.getCallBackUrl());
        WechatOAuthResData resData = new WechatOAuthResData();
        resData.setMpID(mpID);
        resData.setOauthUrl(oauthUrl);
        return resData;
    }
}
