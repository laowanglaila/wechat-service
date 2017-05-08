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
    // 获取微信模板失败
    String WECHAT_TEMPLATE_ERROR = "00112119";
    //微信请求参数错误！
    String WECHAT_ARGS_ERROR = "00112120";
    //创建卡券时没有指定类型
    String WECHAT_CARD_TYPE_NULL = "00112121";
    //创建卡券时没有指定类型
    String WECHAT_CARD_LEAST_COST_NULL = "00112122";
    //没有设置团购券交易详情
    String WECHAT_CARD_QUANTITY_NULL = "00112123";
    //没有设置团购券交易详情
    String WECHAT_CARD_DATE_TYPE_NULL = "00112124";
    //没有logoUrl
    String WECHAT_CARD_LOGO_NULL = "00112125";
    //没有设置code显示类型
    String WECHAT_CARD_CODE_TYPE_NULL = "00112126";
    //没有设置code显示类型
    String WECHAT_CARD_COLOR_NULL = "00112127";
    //没有设置code显示类型
    String WECHAT_CARD_NOTICE_NULL = "00112128";
    //没有设置description显示类型
    String WECHAT_CARD_DESCRIPTION_NULL = "00112129";
    //公众号groupID不允许为空
    String WECHAT_GROUP_ID_NULL = "00112130";
    //公众号title不允许为空
    String WECHAT_CARD_TITLE_NULL = "00112131";
    //公众号cardKey不允许为空
    String WECHAT_CARD_KEY_NULL = "00112132";
    //卡券数据重复提交
    String WECHAT_CARD_SUBMIT_REPEATED = "00112133";
    //会员卡特权说明为空
    String WECHAT_CARD_PREROGATIVE_EMPTY = "00112134";
    //会员卡特权说明为空
    String WECHAT_CARD_RESULT_NULL = "00112135";
    //不存在指定的Key
    String WECHAT_CARD_KEY_NONE = "00112136";
    //cardID不能为空
    String WECHAT_CARD_ID_NULL = "00112137";
 //membershipID不能为空
    String WECHAT_CARD_MEMBER_NUM_NULL = "00112138";

}
