package com.hualala.app.wechat.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.hualala.app.wechat.common.WechatMessageType.*;

/**
 * OAuth2.0
 * 微信网页授权
 */
@Service
public class WechatUserOAuthService {

    private static String wechatOAuthRequestURL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    private static String componentAppID = "";

    public static String getWechatOAuthAPIUrl(String mpid, int oauth, String state, String redirectMpid, String callbackUrl, HttpServletRequest request) {

        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        String oauthUrl = null;
        String redirect_uri = null;

        String appid = "";
        String appsecret = "";
        if (OAUTH_BASE_TYPE == oauth) {
            try {
                redirect_uri = URLEncoder.encode(callbackUrl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }

            oauthUrl = wechatOAuthRequestURL + "?appid=" + appid
                    + "&redirect_uri=" + redirect_uri
                    + "&response_type=code"
                    + "&scope=" + OAUTH_BASE
                    + "&state=" + state + "#wechat_redirect";

        } else if (OAUTH_USERINFO_TYPE == oauth) {

            oauthUrl = wechatOAuthRequestURL + "?appid=" + appid
                    + "&redirect_uri=" + redirect_uri
                    + "&response_type=code"
                    + "&scope=" + OAUTH_USERINFO
                    + "&state=" + state + "#wechat_redirect";
        } else if (COMPONENT_OAUTH_USERINFO_TYPE == oauth) {

            try {
                redirect_uri = URLEncoder.encode(callbackUrl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            oauthUrl = wechatOAuthRequestURL + "?appid=" + appid
                    + "&redirect_uri=" + redirect_uri
                    + "&response_type=code"
                    + "&scope=" + OAUTH_BASE
                    + "&state=" + state
                    + "&component_appid=" + componentAppID
                    + "#wechat_redirect";
        }

        return oauthUrl;
    }

    public String OAuthApIUrl() {


        return "";
    }
}
