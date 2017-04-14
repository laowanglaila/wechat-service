package com.hualala.app.wechat;

public interface ErrorCodes {

    String WECHAT_SUCCESS_CODE = "000";

    String WECHAT_PARPAS_EMPTY = "00112101";
    //公众号mpID不允许为空
    String WECHAT_MPID_EMPTY = "00112102";

    String WECHAT_MP_ERROR = "00112110";
    // 未找到对应的公众号
    String WECHAT_MP_NULL = "00112111";
    // 公众号收授权令牌错误
    String WECHAT_MP_ACCESSTOKEN_ERROR = "00112112";
    // 公众号未授权
    String WECHAT_MP_ACCESSTOKEN_AUTH_ERROR = "00112113";
    // 公众号未认证
    String WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR = "00112114";
    // 刷新令牌错误
    String WECHAT_MP_REFRESHTOKEN_ERROR = "00112115";
    // 图片地址有误
    String WECHAT_MP_IMAGEURL_ERROR = "00112116";
    //url不可以为空
    String WECHAT_MP_URL_NULL = "00112117";
    //HTTP请求失败
    String WECHAT_HTTP_FAILED = "00112118";
    //微信请求参数错误！
    String WECHAT_ARGS_ERROR = "00112119";
}
