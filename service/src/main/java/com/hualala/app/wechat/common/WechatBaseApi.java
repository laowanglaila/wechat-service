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

    //微信卡券上传背景图片
    public static final String API_UPLOAD_MEDIA = ROOT + "/media/uploadimg";

    //微信公众号错误码字段名
    public static final String MP_ERRCODE = "errcode";

    //微信卡券创建卡券
    public static final String CREATE_CARD_URL = "https://api.weixin.qq.com/card/create";

}
