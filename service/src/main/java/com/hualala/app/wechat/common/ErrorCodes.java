package com.hualala.app.wechat.common;

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
 //encryptCode不能为空
    String WECHAT_CARD_ENCRYPT_CODE_NULL = "00112139";
    //encryptCode不能为空
    String WECHAT_CARD_CODE_NULL = "00112140";
    //encryptCode不能为空
    String WECHAT_ARGUMENTS_ILLEGALITY = "00112141";
    //encryptCode不能为空
    String WECHAT_ARGUMENTS_NULL = "00112142";
    //QrcodeType不能为空
    String WECHAT_QRCODE_TYPE_NULL = "00112143";
    //二维码有效时间超出范围
    String WECHAT_QRCODE_EXPIRESECONDS_OVERSTEP = "00112144";
    //二维码缓存数量不足
    String WECHAT_QRCODE_NOT_ENOUGH = "00112145";
    //根据openid群发消息 , 2<数量<10000
    String WECHAT_MESSAGE_PUBLISH_WRONG_SIZE = "00112145";
    //签名服务的url不允许为空
    String WECHAT_SIGN_URL_EMPTY = "00112146";
    //公众号mpID不允许为空
    String WECHAT_MPID_NOT_FOUND = "00112147";
    //非法参数
    String WECHAT_ILLEGAL_ARGUMENTS = "00112148";
    //卡券信息无法匹配
    String WECHAT_CARD_MISMATCH = "00112149";
    //等待同步锁超时
    String WAIT_LOCK_TIMEOUT = "00112150";
    //等待同步锁超时
    String WECHAT_APPSECRET_MISSED = "00112151";
    //时间范围有误
    String WECHAT_TIME_RANGE_WRONG = "00112152";
    //金额范围有误
    String WECHAT_AMOUNT_RANGE_WRONG = "00112153";
    //没有对应的微信会员卡
    String WECHAT_CARD_ID_MISSED = "00112154";
    //会员卡更新消息时间小于上一次更新时间
    String WECHAT_CARD_LOCK_ERROR = "00112155";
    //msgCreateTime不允许为空
    String WECHAT_CARD_MSGCREATETIME_EMPTY = "00112156";
    //msgCreateTime不允许为空
    String WECHAT_MP_NOT_EXIST = "00112157";
    //msgCreateTime不允许为空
    String WECHAT_MP_PERMISSION_DENIED = "00112158";
    // 客服接口错误
    String WECHAT_MESSAGE_CUSTOM_SEMD = "00112159";
}
