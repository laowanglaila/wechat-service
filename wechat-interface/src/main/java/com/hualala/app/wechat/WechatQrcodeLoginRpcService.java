package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by renjianfei on 2017/12/18.
 */
@RpcService(description = "微信二维码登录服务")
public interface WechatQrcodeLoginRpcService  {


    @RpcMethod(description = "获取oauth授权地址")
    OauthUrlRes getOauthUrl(OauthUrlReq reqData);

    @RpcMethod(description = "获取用户网页授权，获取用户基本信息")
    QrcodeLoginRes qrcodeLogin(QrcodeLoginReq userAuthorizationReq);



    @Data
    class OauthUrlReq extends RequestInfo{

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号appID")
        private String appID;
        @NotBlank(message = "回调地址不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "回调地址")
        private String callBackUrl;

    }

    @Data
    class OauthUrlRes extends ResultInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "oauth获取授权地址")
        private String oauthUrl;
    }


    @Data
    class QrcodeLoginReq extends RequestInfo{
        @NotBlank(message = "appID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号appID")
        private String appID;
        @NotBlank(message = "code不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "网页授权获取的code")
        private String code;
    }
    @Data
    class QrcodeLoginRes extends ResultInfo {

        //        openid	        用户的唯一标识
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "用户的唯一标识")
        private String openID;
        //        nickname	        用户昵称
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "用户昵称")
        private String nickName;
        //        sex	            用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
        private String sex;
        //        province	        用户个人资料填写的省份
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "用户个人资料填写的省份")
        private String province;
        //        city	            普通用户个人资料填写的城市
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "普通用户个人资料填写的城市")
        private String city;
        //        country	        国家，如中国为CN
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "国家，如中国为CN")
        private String country;
        //        headimgurl	    用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。")
        private String headImgUrl;
        //        privilege	        用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）")
        private String privilege;
        //        unionid	        只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）")
        private String unionID;
    }

}
