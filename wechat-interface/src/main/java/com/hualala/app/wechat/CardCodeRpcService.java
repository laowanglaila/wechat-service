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
     * @param
     * @return
     */
    @RpcMethod
    CardCodeDestroyResData destoryMemberCode(CardCodeDestroyReqData cardCodeDestroyReqData);
    @RpcMethod
    CardCodeDestroyResData destoryCouponCode(CardCodeDestroyReqData cardCodeDestroyReqData);

    /**
     * 导入code
     */

    @Data
    class CardCodeImportReqData extends RequestInfo {
        //    card_id	需要进行导入code的卡券ID。	是
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String cardKey;
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
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String cardKey;
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
//encrypt_code	是	string(128)  经过加密的Code码。
//code	解密后获取的真实Code码、


/**
 * 查询code接口
 */


}
