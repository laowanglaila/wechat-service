package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by renjianfei on 2017/9/22.
 */
@RpcService
public interface UserAuthorizationRpcService {


    @RpcMethod(description = "获取用户网页授权，获取用户基本信息")
    UserAuthorizationRes userAuthorization(UserAuthorizationReq userAuthorizationReq);

    @Data
    class UserAuthorizationReq extends RequestInfo{
        @NotBlank(message = "mpID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号ID")
        private String mpID;
        @NotBlank(message = "code不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "网页授权获取的code")
        private String code;
        @NotNull(message = "userID不能为空")
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "用户ID")
        private long userID;
    }
    @Data
    class UserAuthorizationRes extends ResultInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "网页授权tocken")
        private String accessToken;
        @Protocol(fieldType = FieldType.INT, order = 3, description = "有效时间")
        private Integer expiresIn;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "授权范围")
        private String scope;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "刷新tocken")
        private String refreshToken;
        @Protocol(fieldType = FieldType.OBJECT, order = 6, description = "用户openID")
        private UserInfo userInfo;
    }

    @Data
    class UserInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。")
        private Integer subscribe;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "用户的标识，对当前公众号唯一")
        private String openid;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "用户的昵称")
        private String nickname;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
        private Integer sex;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "用户所在城市")
        private String city;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "用户所在国家")
        private String country;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "用户的语言，简体中文为zh_CN")
        private String language;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。")
        private String headimgurl;
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间")
        private String subscribeTime;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。")
        private String unionid;
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注")
        private String remark;
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "用户所在的分组ID（兼容旧的用户分组接口）")
        private String wechatGroupID;
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "用户被打上的标签ID列表")
        private String tagidList;
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "用户所在省份")
        private String province;
        @Protocol(fieldType = FieldType.LONG, order = 16, description = "用户ID")
        private Long userID;
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "公众号ID")
        private String mpID;

    }

}
