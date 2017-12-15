package com.hualala.app.wechat.common;

/**
 * Created by renjianfei on 2017/8/4.
 */

public enum WechatExceptionTypeEnum {


    WECHAT_SUCCESS_CODE("000","请求成功"),
    WECHAT_PARPAS_EMPTY("00112101","参数错误"),
    WECHAT_MPID_EMPTY("00112102","公众号mpID不允许为空"),
    WECHAT_COMMON_ERROR("00112103","微信服务内部错误"),
    WECHAT_MPINFO_INCOMPLETE("00112104","公众号信息不完整"),
    WECHAT_MP_ERROR("00112110","微信公众号公共错误"),
    WECHAT_MP_NULL("00112111","未找到对应的公众号"),
    WECHAT_MP_ACCESSTOKEN_ERROR("00112112","公众号收授权令牌错误"),
    WECHAT_MP_ACCESSTOKEN_AUTH_ERROR("00112113","公众号未授权"),
    WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR("00112114","公众号未认证"),
    WECHAT_MP_REFRESHTOKEN_ERROR("00112115","刷新令牌错误"),
    WECHAT_MP_IMAGEURL_ERROR("00112116","图片地址有误"),
    WECHAT_MP_URL_NULL("00112117","url不可以为空"),
    WECHAT_HTTP_FAILED("00112118","HTTP请求失败"),
    WECHAT_TEMPLATE_ERROR("00112119","获取微信模板失败"),
    WECHAT_ARGS_ERROR("00112120","微信请求参数错误！"),
    WECHAT_CARD_TYPE_NULL("00112121","创建卡券时没有指定类型"),
    WECHAT_CARD_LEAST_COST_NULL("00112122","创建卡券时没有指定类型"),
    WECHAT_CARD_QUANTITY_NULL("00112123","没有设置团购券交易详情"),
    WECHAT_CARD_DATE_TYPE_NULL("00112124","没有设置团购券交易详情"),
    WECHAT_CARD_LOGO_NULL("00112125","没有logoUrl"),
    WECHAT_CARD_CODE_TYPE_NULL("00112126","没有设置code显示类型"),
    WECHAT_CARD_COLOR_NULL("00112127","没有设置code显示类型"),
    WECHAT_CARD_NOTICE_NULL("00112128","没有设置code显示类型"),
    WECHAT_CARD_DESCRIPTION_NULL("00112129","没有设置description显示类型"),
    WECHAT_GROUP_ID_NULL("00112130","公众号groupID不允许为空"),
    WECHAT_CARD_TITLE_NULL("00112131","公众号title不允许为空"),
    WECHAT_CARD_KEY_NULL("00112132","公众号cardKey不允许为空"),
    WECHAT_CARD_SUBMIT_REPEATED("00112133","卡券数据重复提交"),
    WECHAT_CARD_PREROGATIVE_EMPTY("00112134","会员卡特权说明为空"),
    WECHAT_CARD_RESULT_NULL("00112135","会员卡特权说明为空"),
    WECHAT_CARD_KEY_NONE("00112136","不存在指定的Key"),
    WECHAT_CARD_ID_NULL("00112137","cardID不能为空"),
    WECHAT_CARD_MEMBER_NUM_NULL("00112138","membershipID不能为空"),
    WECHAT_CARD_ENCRYPT_CODE_NULL("00112139","encryptCode不能为空"),
    WECHAT_CARD_CODE_NULL("00112140","encryptCode不能为空"),
    WECHAT_ARGUMENTS_ILLEGALITY("00112141","encryptCode不能为空"),
    WECHAT_ARGUMENTS_NULL("00112142","encryptCode不能为空"),
    WECHAT_QRCODE_TYPE_NULL("00112143","QrcodeType不能为空"),
    WECHAT_QRCODE_EXPIRESECONDS_OVERSTEP("00112144","二维码有效时间超出范围"),
    WECHAT_QRCODE_NOT_ENOUGH("00112145","二维码缓存数量不足"),
    WECHAT_MESSAGE_PUBLISH_WRONG_SIZE("00112145","根据openid群发消息 , 2<数量<10000"),
    WECHAT_SIGN_URL_EMPTY("00112146","签名服务的url不允许为空"),
    WECHAT_MPID_NOT_FOUND("00112147","公众号mpID不允许为空"),
    WECHAT_ILLEGAL_ARGUMENTS("00112148","非法参数"),
    WECHAT_CARD_MISMATCH("00112149","卡券信息无法匹配"),
    WAIT_LOCK_TIMEOUT("00112150","等待同步锁超时"),
    WECHAT_APPSECRET_MISSED("00112151","等待同步锁超时"),
    WECHAT_TIME_RANGE_WRONG("00112152","时间范围有误"),
    WECHAT_AMOUNT_RANGE_WRONG("00112153","金额范围有误"),
    WECHAT_CARD_ID_MISSED("00112154","没有对应的微信会员卡"),
    WECHAT_CARD_LOCK_ERROR("00112155","会员卡更新消息时间小于上一次更新时间"),
    WECHAT_CARD_MSGCREATETIME_EMPTY("00112156","msgCreateTime不允许为空"),
    WECHAT_MP_NOT_EXIST("00112157","msgCreateTime不允许为空"),
    WECHAT_MP_PERMISSION_DENIED("00112158","接口功能未授权，请确认公众号已获得该权限"),
    WECHAT_MESSAGE_CUSTOM_SEMD("00112159","客服接口错误"),
    WECHAT_GET_AUTHORIZER_INFO_FIELD("00112160","客服接口错误"),
    WECHAT_GET_PREAUTHCODE_FIELD("00112161","获取预授权码失败"),
    WECHAT_QUERY_AUTH_FIELD("00112162","公众号授权失败"),
    WECHAT_GET_ACCESSTOKEN_FIELD("00112163","获取accessToken失败"),
    WECHAT_GET_AUTHORIZER_ACCESSTOKEN_FIELD("00112164","获取authorizerAccessToken失败"),
    WECHAT_OAUTH_ERROR("00112165","公众号OAUTH不具备授权权限"),
    WECHAT_USER_SERVICE_ERROR("00112166","获取用户服务错误"),
    WECHAT_USER_AUTHORIZATION_FIELD("00112167","获取用户授权失败"),
    WECHAT_AUTHORIZATION_MISS("00112168","接口校验失败"),
    WECHAT_NONE_CARD_RELATION_MAPPING("00112169","卡券关系映射异常");


    private String code;
    private String message;
    WechatExceptionTypeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public static com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum parseEnum(String code) {
        for (com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum each : com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum.class.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
