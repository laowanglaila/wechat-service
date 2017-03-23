package com.hualala.app.wechat.common;

/**
 * Created by xkia on 2017/3/21.
 */
public class WechatBaseApi {
    public static final String AUTHORIZE_1 = "1";


    public static final String ROOT = "https://api.weixin.qq.com/cgi-bin";

    public static final String GET_ACCESS_TOKEN = ROOT + "/token?grant_type=client_credential";

    public static final String API_COMPONENT_TOKEN = ROOT + "/component/api_component_token";

    public static final String API_AUTHORIZER_TOKEN = ROOT + "/component/api_authorizer_token";
}
