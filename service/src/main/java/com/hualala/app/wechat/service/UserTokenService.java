package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.model.mp.MpInfoCache;
import com.hualala.app.wechat.util.OkHttpUtil;
import com.hualala.app.wechat.vo.UserAuthVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/9/22.
 * 用户网页授权，获取openID，AccessToken，refreshToken
 * 需要区分公众号是否授权
 */
@Slf4j
@Service
public class UserTokenService {

    @Value("${wechat.data.componentAppID}")
    private String componentAppID;

    @Autowired
    private MpInfoService mpInfoService;
    @Autowired
    private ComponentTokenService componentTokenService;

    /**
     * 获取用户授权
     */
    public UserAuthVO getUserAuth(String mpID,String code) throws WechatInnerException {
        MpInfoCache mpInfoByMpID = mpInfoService.getMpInfoByMpID( mpID );
        String appID = mpInfoByMpID.getAppID();
        String appSecret = mpInfoByMpID.getAppSecret();
        String authorize = mpInfoByMpID.getAuthorize();
        if ("1".equals(authorize)) {
            return this.componentUserAuthorization(appID,code);
        } else if ("2".equals(authorize)) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR ,"该公众号已经取消第三方平台授权");
        }
        if (StringUtils.isBlank( appSecret )){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MPINFO_INCOMPLETE , "缺少AppSecret");
        }
        return this.userAuthorization( appID,appSecret,code );
    }



    /**
     * 公众号未授权，通过appID和APPSecret获取用户授权
     */
    private UserAuthVO userAuthorization(String appID,String secret,String code) throws WechatInnerException {
        String params = "appid="+appID+
                "&secret="+ secret +
                "&code=" + code +
                "&grant_type=authorization_code";
        String url = WechatBaseApi.MP_USER_AUTHORIZATION + "?" + params;
        return this.getUserAuth( url );

    }


    /**
     * 公众号已授权采用公众号授权码获取用户授权
     */
    private UserAuthVO componentUserAuthorization(String appID,String code) throws WechatInnerException {

        String componentToken = componentTokenService.initComponentToken();
        String params = "appid="+appID+
                "&code=" + code +
                "&grant_type=authorization_code" +
                "&component_appid="+ componentAppID +
                "&component_access_token="+ componentToken;
        String url = WechatBaseApi.OPEN_USER_AUTHORIZATION + "?" + params;
        return this.getUserAuth( url );

    }


    /**
     * 获取用户信息
     * @param
     * @return
     */


    private UserAuthVO getUserAuth(String url) throws WechatInnerException {
        JSONObject responseJson = this.get( url );
        String accessToken = responseJson.getString( "access_token" );
        Integer expiresIn = responseJson.getInteger( "expires_in" );
        String refreshToken = responseJson.getString( "refresh_token" );
        String openID = responseJson.getString( "openid" );
        String scope = responseJson.getString( "scope" );
        UserAuthVO userAuthVO = new UserAuthVO();
        userAuthVO.setAccessToken( accessToken );
        userAuthVO.setExpiresIn( expiresIn );
        userAuthVO.setOpenID( openID );
        userAuthVO.setRefreshToken( refreshToken );
        userAuthVO.setScope( scope );
        return userAuthVO;
    }

    private JSONObject get(String url) throws WechatInnerException {
        JSONObject responseJson = OkHttpUtil.get( url );
        log.debug("微信响应参数 ：" + responseJson.toJSONString());
        String errcode = responseJson.getString( WechatMessageType.WECHAT_ERR_CODE);
        if (StringUtils.isNotBlank(errcode) && !"0".equals(errcode)) {
            String errmassage = WechatErrorCode.wechatError.get(errcode);
            String errorcode = errmassage == null ? "[ errcode:"+errcode+" ]" : errmassage;
            throw new WechatInnerException( errorcode+":[ "+responseJson.getString(WechatMessageType.WECHAT_ERR_MESSAGE)+" ]");
        }
        return responseJson;
    }

}
