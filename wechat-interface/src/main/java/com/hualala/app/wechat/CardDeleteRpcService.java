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
public interface CardDeleteRpcService {

    @RpcMethod
    CardDeleteAndUnAbailableResData deleteCard(CardDeleteReqData cardDeleteReqData);

    @RpcMethod
    CardDeleteAndUnAbailableResData unAvailableCard(CardUnAvailableReqData cardUnAvailableReqData);
//    @RpcMethod
//    CardDeleteAndUnAbailableResData deleteCouponInfo(CardDeleteReqData cardDeleteReqData);
//
//    @RpcMethod
//    CardDeleteAndUnAbailableResData unAvailableCouponInfo(CardUnAvailableReqData cardUnAvailableReqData);

    @Data
    class CardDeleteReqData extends RequestInfo{
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "唯一ID")
        private Long cardKey;
    }
    @Data
    class CardUnAvailableReqData extends RequestInfo{
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "唯一ID")
        private Long cardKey;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "code码")
        private String code;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "失效原因")
        private String reason;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "集团ID")
        private String groupID;
    }

    @Data
    class CardDeleteAndUnAbailableResData extends ResultInfo{

    }

}
