package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/5/2.
 */
@RpcService
public interface CheckHealthRpcService {
    @RpcMethod
    CheckHealthResData check(CheckHealthReqData checkHealthReqData);

    @Data
    class CheckHealthReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "接收到请求的时间")
        private Long responseTime;
    }

    @Data
    class CheckHealthResData extends ResultInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "接收到请求的时间")
        private Long responseTime;
    }

}
