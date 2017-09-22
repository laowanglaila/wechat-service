package com.hualala.app.wechat.impl.user;

import com.hualala.app.wechat.UserAuthorizationRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.service.UserTokenService;
import com.hualala.app.wechat.vo.UserAuthVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/9/22.
 */
@Service
public class UserAuthorizationRpcServiceImpl implements UserAuthorizationRpcService {
    @Autowired
    private UserTokenService userTokenService;
    @Override
    public UserAuthorizationRes userAuthorization(UserAuthorizationReq userAuthorizationReq) {
        UserAuthVO userAuth = null;
        try {
            userAuth = userTokenService.getUserAuth( userAuthorizationReq.getMpID(), userAuthorizationReq.getCode() );
        } catch (WechatInnerException e) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_USER_AUTHORIZATION_FIELD ,e.getMessage());
        }
        UserAuthorizationRes userAuthorizationRes = new UserAuthorizationRes();
        BeanUtils.copyProperties( userAuth,userAuthorizationRes );
        return userAuthorizationRes;
    }
}
