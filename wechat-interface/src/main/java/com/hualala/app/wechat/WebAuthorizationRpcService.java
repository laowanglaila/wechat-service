package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/8/11.
 */
@RpcService
public interface WebAuthorizationRpcService {

    @RpcMethod(description = "高级授权，需要用户点击确定同意授权，可以获取accessTocken和openID")
    AuthorizationRes snsAuthorization(AuthorizationReq authorizationReq);
    @RpcMethod(description = "需要高级授权的accessTocken和openID来获取用户信息")
    WechatUserInfoRes getWechatUserInfo(WechatUserInfoReq wechatUserInfoReq);

//    appid	是	公众号的appid
//    code	是	填写第一步获取的code参数
//    grant_type	是	填authorization_code
//    component_appid	是	服务开发方的appid
//    component_access_token	是	服务开发方的access_token

    @Data
    class AuthorizationReq extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "集团ID")
        private Long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "品牌ID")
        private Long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 5, description = "店铺ID")
        private Long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 6,description = "填写网页授权获取的code参数")
        private String code;
    }
//    access_token	接口调用凭证
//    expires_in	access_token接口调用凭证超时时间，单位（秒）
//    refresh_token	用户刷新access_token
//    openid	授权用户唯一标识
//    scope	用户授权的作用域，使用逗号（,）分隔
    @Data
    class AuthorizationRes extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "接口调用凭证")
        private String access_token;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "access_token接口调用凭证超时时间，单位（秒）")
        private String expires_in;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "用户刷新access_token")
        private String refresh_token;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "授权用户唯一标识")
        private String openid;
        @Protocol(fieldType = FieldType.STRING, order = 6,description = "用户授权的作用域，使用逗号（,）分隔")
        private String scope;
    }

    @Data
    class WechatUserInfoReq extends RequestInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "集团ID")
        private Long groupID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同")
        private String accessToken;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "用户的唯一标识")
        private String openid;
        @Protocol(fieldType = FieldType.ENUM, order = 5, description = "返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语")
        private LangTypeEnum lang;
    }
    @Data
    class WechatUserInfoRes extends ResultInfo {

//        openid	        用户的唯一标识
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "用户的唯一标识")
        private String openID;
//        nickname	        用户昵称
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "用户昵称")
        private String nickName;
//        sex	            用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
        private String sex;
        //        province	        用户个人资料填写的省份
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "用户个人资料填写的省份")
        private String province;
        //        city	            普通用户个人资料填写的城市
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "普通用户个人资料填写的城市")
        private String city;
        //        country	        国家，如中国为CN
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "国家，如中国为CN")
        private String country;
        //        headimgurl	    用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。")
        private String headImgUrl;
        //        privilege	        用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）")
        private String privilege;
        //        unionid	        只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）")
        private String unionID;

    }
}
