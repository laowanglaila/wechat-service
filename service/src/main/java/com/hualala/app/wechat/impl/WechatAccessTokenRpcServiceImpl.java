package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.WechatAccessTokenRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.service.AccessTokenService;
import com.hualala.app.wechat.util.RequestUtil;
import com.hualala.app.wechat.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/10/12.
 */
@Slf4j
@Service
public class WechatAccessTokenRpcServiceImpl implements WechatAccessTokenRpcService {
    @Autowired
    private AccessTokenService accessTokenService;
    @Override
    public AccessTokenRes getAccessToken(AccessTokenReq accessTokenReq) {
        String mpID = RequestUtil.getMpID( accessTokenReq );
        boolean forceRefresh = accessTokenReq.isForceRefresh();
        AccessTokenRes resultInfoBean;
        try {
            JSONObject accessToken = accessTokenService.getAccessToken( mpID ,forceRefresh);
            resultInfoBean = ResultUtil.getResultInfoBean( accessToken, AccessTokenRes.class );
        } catch (WechatInnerException e) {
            log.error( e.getMessage(),e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD,e.getMessage() );
        }
        return resultInfoBean;
    }
}
