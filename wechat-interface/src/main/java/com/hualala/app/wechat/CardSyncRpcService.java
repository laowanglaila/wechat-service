package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

import java.util.List;

/**
 * Created by renjianfei on 2017/5/4.
 */
@RpcService
public interface CardSyncRpcService {


    /**
     * 获取当前公众号下的所有卡券id
     */

    @RpcMethod
    CardListResData getCardList(CardListReqData cardListReqData);

    @Data
    class CardListReqData extends RequestInfo{

        @Protocol(fieldType = FieldType.STRING,order = 2,description = "公众号mpid")
        private String mpID;
        @Protocol(fieldType = FieldType.INT,order = 3,description = "开始位置")
        private int offset;
        @Protocol(fieldType = FieldType.INT,order = 4,description = "数量，最大50")
        private int count;
        @Protocol(fieldType = FieldType.STRING,order = 5,description = "期望获取卡券状态列表")
        private List<String> statusList;
// {
//            "offset": 0,
//                    "count": 10,
//                    "status_list": ["CARD_STATUS_VERIFY_OK", "CARD_STATUS_DISPATCH"]
//        }

    }
 @Data
    class CardListResData extends ResultInfo{
        @Protocol(fieldType = FieldType.STRING,order = 2,description = "卡券ID列表")
        private List<String> cardIdList;
        @Protocol(fieldType = FieldType.INT,order = 3,description = "该商户名下卡券ID总数")
        private int totalNum;

//card_id_list	卡券ID列表。
//        total_num	该商户名下卡券ID总数。
    }

    /**
     * 根据CardKey和card_id导入数据到本地
     */
    @RpcMethod
    CardDownloadResData downloadCardInfo(CardDownloadReqData cardSyncReqData);
    /**
     * 与微信同步会员数据
     */
    @RpcMethod
    CardSyncResData syncMemberInfo(CardSyncReqData cardSyncReqData);

    /**
     * 与微信同步卡券数据
     */
    @RpcMethod
    CardSyncResData syncCouponInfo(CardSyncReqData cardSyncReqData);

    /**
     * 同步数据返回对象
     */
    @Data
    class CardSyncResData extends ResultInfo{
    }

    @Data
    class CardSyncReqData extends RequestInfo{
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "唯一ID")
        private Long cardKey;

    }

    @Data
    class CardDownloadReqData extends RequestInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "微信卡券唯一ID")
        private String cardID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "微信公众号唯一标识")
        private String mpID;
    }
    @Data
    class CardDownloadResData extends ResultInfo{
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "唯一ID")
        private Long cardKey;
    }


}
