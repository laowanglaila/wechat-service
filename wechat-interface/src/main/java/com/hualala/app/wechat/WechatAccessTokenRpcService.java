package com.hualala.app.wechat;

import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/10/12.
 */
@RpcService
public interface WechatAccessTokenRpcService {
    @RpcMethod
    AccessTokenRes getAccessToken(AccessTokenReq accessTokenReq);

    @Data
    class AccessTokenReq extends WechatRequestInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "集团ID")
        private Long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "品牌ID")
        private Long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 5, description = "店铺ID")
        private Long shopID;
        @Protocol(fieldType = FieldType.BOOL, order = 6, description = "是否强制刷新")
        private boolean isForceRefresh;

    }

    @Data
    class AccessTokenRes extends ResultInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "微信接口调用凭证")
        private String accessToken;
    }
}
