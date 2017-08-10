package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
@RpcService(description = "微信消息接口")
public interface WechatMessageRpcService {

    @RpcMethod(description = "推送客服消息")
    WechatMessageResData wechatMessageSend(WechatMessageReqData reqData);

    @Data
    class WechatMessageReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "店铺ID")
        private long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "mpID")
        private String mpID;
        @Protocol(fieldType = FieldType.ENUM, order = 6, description = "消息类型枚举")
        private WechatMessageEnum wechatMessageEnum;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "用户openID")
        private String openID;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "文本内容")
        private String content;
        @Protocol(fieldType = FieldType.LONG, order = 9, description = "用户ID")
        private long userID;
    }

    @Data
    class WechatMessageResData extends ResultInfo {

    }
}
