package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by renjianfei on 2017/9/22.
 */
@RpcService
public interface UserAuthorizationRpcService {


    @RpcMethod(description = "获取用户网页授权，获取openID")
    UserAuthorizationRes userAuthorization(UserAuthorizationReq userAuthorizationReq);

    @Data
    class UserAuthorizationReq extends RequestInfo{
        @NotNull(message = "mpID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号ID")
        private String mpID;
        @NotNull(message = "code不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "网页授权获取的code")
        private String code;
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
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "用户openID")
        private String openID;
    }

}
