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
 * Created by renjianfei on 2017/5/3.
 * 微信会员卡券code相关操作：
 * 导入、核销、更改、查询
 */
@RpcService
public interface CardCodeRpcService {


    /**
     * 导入code方法
     */
    @RpcMethod
    CardCodeImportResData importMemberCode(CardCodeImportReqData cardCodeImportReqData);

    @RpcMethod
    CardCodeImportResData importCouponCode(CardCodeImportReqData cardCodeImportReqData);

    /**
     * 核销code方法
     *
     * @param
     * @return
     */
    @RpcMethod
    CardCodeDestroyResData destoryMemberCode(CardCodeDestroyReqData cardCodeDestroyReqData);

    @RpcMethod
    CardCodeDestroyResData destoryCouponCode(CardCodeDestroyReqData cardCodeDestroyReqData);

    /**
     * code解码
     */
    @RpcMethod
    CardCodeDecodingResData decodingCardCode(CardCodeDecodingReqData cardCodeDecodingReqData);

    /**
     * 查询优惠券code状态
     */
    @RpcMethod
    CardCodeQueryResData queryCouponStatus(CardCodeQueryReqData cardCodeQueryReqData);

    /**
     * 导入code
     */

    @Data
    class CardCodeImportReqData extends RequestInfo {
        //    card_id	需要进行导入code的卡券ID。	是
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "唯一ID")
        private Long cardKey;
        //    code	需导入微信卡券后台的自定义code，上限为100个。	是
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "需导入微信卡券后台的自定义code，上限为100个。")
        private List<String> code;
    }

    @Data
    class CardCodeImportResData extends ResultInfo {
        //    exist_code	已经成功存入的code。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "已经成功存入的code")
        private List<String> existCode;
        //    not_exist_code	没有存入的code。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "没有存入的code")
        private List<String> notExistCode;
    }

    /**
     * 核销code
     */

    @Data
    class CardCodeDestroyReqData extends RequestInfo {
        //    card_id	否	string(32) 卡券ID。创建卡券时use_custom_code填写true时必填。非自定义Code不必填写。
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "唯一ID")
        private Long cardKey;
        //    code	是	string(20)	1231231	需核销的Code码。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "需导入微信卡券后台的自定义code，上限为100个。")
        private String code;
    }

    @Data
    class CardCodeDestroyResData extends ResultInfo {
        //    openid	用户在该公众号内的唯一身份标识。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String openId;
        //    card_id	卡券ID。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "需导入微信卡券后台的自定义code，上限为100个。")
        private String cardId;
    }


    /**
     * code解码
     */
    @Data
    class CardCodeDecodingReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号mpID")
        private String mpID;
        //encrypt_code	是	string(128)  经过加密的Code码。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "经过加密的Code码")
        private String encryptCode;

    }

    @Data
    class CardCodeDecodingResData extends ResultInfo {
        //code	解密后获取的真实Code码、
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "解密后获取的真实Code码")
        private String cardCode;
    }


    /**
     * 查询code接口
     */
    @Data
    class CardCodeQueryReqData extends RequestInfo  {
        //    code	            是	string(20)	    110201201245	单张卡券的唯一标准。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "解密后获取的真实Code码")
        private String cardCode;
        //    card_id	        否	string(32)      pFS7Fjg8kV1I    dDz01r4SQwMkuCKc 卡券ID代表一类卡券。自定义code卡券必填。
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "卡券ID代表一类卡券。自定义code卡券必填")
        private Long cardKey;
        //    check_consume	    否	bool	        true	        是否校验code核销状态，填入true和false时的code异常状态返回数据不同。
//        无论check_consume填写的是true还是false,当code未被添加或者code被转赠领取是统一报错：invalid serial code

    }
    @Data
    class CardCodeQueryResData extends ResultInfo {
        //    code	            是	string(20)	    110201201245	单张卡券的唯一标准。
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "解密后获取的真实Code码")
        private Long cardKey;
        //    card_id	        否	string(32)      pFS7Fjg8kV1I    dDz01r4SQwMkuCKc 卡券ID代表一类卡券。自定义code卡券必填。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "卡券ID代表一类卡券。自定义code卡券必填")
        private String openId;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "起始使用时间")
        private Long beginTime;
        @Protocol(fieldType = FieldType.LONG, order = 5, description = "结束时间")
        private Long endTime;
        @Protocol(fieldType = FieldType.BOOL, order = 6, description = "是否可以核销")
        private Boolean canConsume;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "当前code对应卡券的状态")
        private String userCardStatus;

    }

}
