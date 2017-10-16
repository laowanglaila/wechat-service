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
 * Created by renjianfei on 2017/10/13.
 */
@RpcService(description = "权限检查接口")
public interface AuthorizationCheckRpcService {
    @RpcMethod(description = "检查微信接口权限")
    AuthorizationCheckRes check(AuthorizationCheckReq authorizationCheckReq);

    @Data
    class AuthorizationCheckReq extends RequestInfo{
        @NotBlank(message = "mpID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "需要校验公众号mpID")
        private String mpID;
        @NotNull(message = "请指定需要检查的接口")
        @Protocol(fieldType = FieldType.ENUM, order = 3, description = "需要校验的接口枚举")
        private WechatFuctionEnum interfaceType;
    }
    @Data
    class AuthorizationCheckRes extends ResultInfo {}
}
