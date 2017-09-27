package com.hualala.app.wechat.impl.user;

import com.hualala.app.wechat.UserAuthorizationRpcService;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
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
    @Autowired
    private UserGetUserInfoRpcService userGetUserInfoRpcService;
    @Override
    public UserAuthorizationRes userAuthorization(UserAuthorizationReq userAuthorizationReq) {
        String mpID = userAuthorizationReq.getMpID();
        UserAuthVO userAuth = null;
        try {
            userAuth = userTokenService.getUserAuth( mpID, userAuthorizationReq.getCode() );
        } catch (WechatInnerException e) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_USER_AUTHORIZATION_FIELD ,e.getMessage());
        }
        UserInfo userInfo = new UserInfo();
        UserAuthorizationRes userAuthorizationRes = new UserAuthorizationRes();
        BeanUtils.copyProperties( userAuth,userAuthorizationRes );
        if (!"hualala_com".equals( mpID )) {
            UserGetUserInfoRpcService.UserInfoReqData userInfoReqData = new UserGetUserInfoRpcService.UserInfoReqData();
            userInfoReqData.setOpenID( userAuth.getOpenID() );
            userInfoReqData.setMpID( mpID );
            userInfoReqData.setUserID( userAuthorizationReq.getUserID() );
            UserGetUserInfoRpcService.UserInfoResData userInfo1 = userGetUserInfoRpcService.findUserInfo( userInfoReqData );
            BeanUtils.copyProperties( userInfo1, userInfo );
        }else {
            userInfo.setMpID( mpID );
            userInfo.setOpenid( userAuth.getOpenID() );
        }
        userAuthorizationRes.setUserInfo( userInfo );
        return userAuthorizationRes;
    }
}
