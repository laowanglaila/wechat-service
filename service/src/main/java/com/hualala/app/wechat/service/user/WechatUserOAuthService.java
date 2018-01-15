package com.hualala.app.wechat.service.user;

import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.service.MpInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.hualala.app.wechat.common.WechatMessageType.OAUTH_BASE;
import static com.hualala.app.wechat.common.WechatMessageType.OAUTH_USERINFO;

/**
 * OAuth2.0
 * 微信网页授权
 */
@Service
public class WechatUserOAuthService {

    private Logger logger = LoggerFactory.getLogger(WechatUserOAuthService.class);

    private static String wechatOAuthRequestURL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    @Value("${wechat.data.componentAppID}")
    private String componentAppID;

    @Autowired
    private MpInfoService mpInfoService;


    public String OAuthApIUrl(String mpID, String state, String callBackUrl) {

        Map<String,Object> param = new HashMap<>();
        param.put("mpID",mpID);
        Map<String, Object> mpInfo = mpInfoService.queryMpInfo(param);
        if(mpInfo == null) {
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_OAUTH_ERROR,"mpInfo不存在");
        }

        String appID = (String) mpInfo.get("appID");
        Integer authorize = (Integer) mpInfo.get("authorize");
        int oauth = 1;
        // if(!WechatMessageType.HUALALA_COM.equals(mpID) && 1 == authorize){
        if( 1 == authorize){
            oauth = 3;
        }

        return getOAuthAPIUrl(oauth,appID,state,callBackUrl);
    }


    private String getOAuthAPIUrl(int oauth, String appID, String state, String callBackUrl) {

        String oauthUrl = null;
        String redirect_uri = null;
        try {
            redirect_uri = URLEncoder.encode(callBackUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_OAUTH_ERROR);
        }
        switch (oauth) {
            case 1:
                oauthUrl = wechatOAuthRequestURL + "?appid=" + appID
                        + "&redirect_uri=" + redirect_uri
                        + "&response_type=code"
                        + "&scope=" + OAUTH_BASE
                        + "&state=" + state + "#wechat_redirect";
                break;
            case 2:
                oauthUrl = wechatOAuthRequestURL + "?appid=" + appID
                        + "&redirect_uri=" + redirect_uri
                        + "&response_type=code"
                        + "&scope=" + OAUTH_USERINFO
                        + "&state=" + state + "#wechat_redirect";
                break;
            case 3:
                oauthUrl = wechatOAuthRequestURL + "?appid=" + appID
                        + "&redirect_uri=" + redirect_uri
                        + "&response_type=code"
                        + "&scope=" + OAUTH_BASE
                        + "&state=" + state
                        + "&component_appid=" + componentAppID
                        + "#wechat_redirect";
                break;
            default:
                oauthUrl = wechatOAuthRequestURL + "?appid=" + appID
                        + "&redirect_uri=" + redirect_uri
                        + "&response_type=code"
                        + "&scope=" + OAUTH_BASE
                        + "&state=" + state + "#wechat_redirect";
                break;
        }

        logger.info("oauth url : {}",oauthUrl);

        return oauthUrl;
    }

}
