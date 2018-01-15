package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.ComponentTokenRpcService;
import com.hualala.app.wechat.DefaultClass.DefaultRequestInfo;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.service.ComponentTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/8/31.
 */
@Slf4j
@Service
public class ComponentTokenRpcServiceImpl implements ComponentTokenRpcService {
    @Autowired
    private ComponentTokenService componentTokenService;
    @Override
    public PreAuthCodeResData createPreAuthCode(DefaultRequestInfo args) {
        String preAuthCode = null;
        try {
            preAuthCode = componentTokenService.createPreAuthCode();
        } catch (WechatInnerException e) {
            log.error( e.getMessage(), e);
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_GET_PREAUTHCODE_FIELD);
        }
        PreAuthCodeResData preAuthCodeResData = new PreAuthCodeResData();
        preAuthCodeResData.setPreAuthCode( preAuthCode );
        return preAuthCodeResData;
    }



    @Override
    public QueryAuthResData queryAuth(QueryAuthReqData queryAuthReqData) {
        String preAuthCode = queryAuthReqData.getPreAuthCode();
        if (StringUtils.isBlank( preAuthCode )){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"preAuthCode is null!" );
        }
        String authorizerAccessToken = null;
        try {
            authorizerAccessToken = componentTokenService.queryAuth( preAuthCode );
        } catch (WechatInnerException e) {
            log.error( e.getMessage(), e);
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_AUTHORIZER_ACCESSTOKEN_FIELD );
        }
        QueryAuthResData queryAuthResData = new QueryAuthResData();
        queryAuthResData.setAuthorizerAccessToken( authorizerAccessToken );
        return queryAuthResData;
    }
}
