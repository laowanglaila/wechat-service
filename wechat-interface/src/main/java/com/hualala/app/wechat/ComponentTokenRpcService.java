package com.hualala.app.wechat;

import com.hualala.app.wechat.DefaultClass.DefaultRequestInfo;
import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/8/31.
 */
@RpcService
public interface ComponentTokenRpcService {

    @RpcMethod(description = "获取预授权码")
    PreAuthCodeResData createPreAuthCode(DefaultRequestInfo defaultRequestInfo);
    @RpcMethod(description = "获取开放平台授权authorizerAccessToken ，authorizerRefreshToken，同时缓存到redis")
    QueryAuthResData queryAuth(QueryAuthReqData queryAuthReqData);
//    @Data
//    class PreAuthCodeReqData extends RequestInfo {
//    }
    @Data
    class PreAuthCodeResData extends ResultInfo {

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "开放平台预授权码")
        private String preAuthCode;
    }
    @Data
    class QueryAuthReqData extends RequestInfo {

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "开放平台预授权码")
        private String preAuthCode;
    }

    @Data
    class QueryAuthResData extends ResultInfo {

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "获取开放平台授权accessToken")
        private String authorizerAccessToken;
    }

}
