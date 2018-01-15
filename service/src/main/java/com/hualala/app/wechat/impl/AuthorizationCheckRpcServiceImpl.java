package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.AuthorizationCheckRpcService;
import com.hualala.app.wechat.WechatFuctionEnum;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.model.mp.MpInfoCache;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.checkpermission.AuthorizationCheck;
import com.hualala.app.wechat.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/10/13.
 */
@Slf4j
@Service("getAuthorizationCheckRpcService")
public class AuthorizationCheckRpcServiceImpl implements AuthorizationCheckRpcService {
    @Autowired
    private MpInfoService mpInfoService;
    @Override
    public AuthorizationCheckRes check(AuthorizationCheckReq authorizationCheckReq) {
        String mpID = authorizationCheckReq.getMpID();
        WechatFuctionEnum interfaceType = authorizationCheckReq.getInterfaceType();
        MpInfoCache mpInfo;
        try {
            mpInfo = mpInfoService.getMpInfoByMpID( mpID );
        } catch (WechatInnerException e) {
            log.error( "检查公众号接口权限：[interfaceType："+interfaceType+"; mpID"+mpID+"]" ,e);
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_NULL,e.getMessage() );
        }
        String authorize = mpInfo.getAuthorize();
        String mpType = mpInfo.getMpType();
        if ("2".equals( authorize ) || !"21".equals( mpType )){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_AUTHORIZATION_MISS,"请检查公众号是否是认证服务号或已经取消授权" );
        }
        AuthorizationCheck authorizationCheck = AuthorizationCheck.create( interfaceType );
        if (authorizationCheck == null){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_AUTHORIZATION_MISS,"未找到匹配的校验器实现类");
        }
        Boolean success = authorizationCheck.handle( mpID );
        if (!success){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_AUTHORIZATION_MISS,"未获取权限:"+interfaceType);
        }
        return ResultUtil.success( AuthorizationCheckRes.class );
    }
}
