package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
@RpcService(description = "微信公众号用户oauth授权")
public interface WechatOAuthRpcService {

    @RpcMethod(description = "获取oauth授权地址")
    WechatOAuthResData wechatOAuthUrl(WechatOAuthReqData reqData);

    @Data
    class WechatOAuthReqData extends RequestInfo{

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号mpID")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "回调地址")
        private String callBackUrl;

    }

    @Data
    class WechatOAuthResData extends ResultInfo{

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号mpID")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "oauth获取授权地址")
        private String oauthUrl;
    }
}
