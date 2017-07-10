package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/6/1.
 */
@RpcService
public interface CardEventProcessRpcService {
    @RpcMethod
    EventProcessRes process(EventProcessReq json);
    @Data
    class EventProcessReq extends RequestInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "事件推送json")
        private String json;
    }
    @Data
    class EventProcessRes extends ResultInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "json")
        private String json;
    }
}
