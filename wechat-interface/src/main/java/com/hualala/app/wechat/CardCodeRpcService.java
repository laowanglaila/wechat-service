package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
    @RpcMethod(description = "导入code方法")
    CardCodeImportResData importCode(CardCodeImportReqData cardCodeImportReqData);

    /**
     * 核销code方法
     *
     * @param
     * @return
     */
    @RpcMethod(description = "核销code方法")
    CardCodeDestroyResData destoryCode(CardCodeDestroyReqData cardCodeDestroyReqData);

    @RpcMethod(description = "核销code方法")
    CardCodeDestroyResData destoryThirdpartyCode(DestoryThirdpartyCodeReqData cardCodeDestroyReqData);

//    @RpcMethod
//    CardCodeDestroyResData destoryCouponCode(CardCodeDestroyReqData cardCodeDestroyReqData);

    /**
     * code解码
     */
    @RpcMethod(description = "code解码")
    CardCodeDecodingResData decodingCardCode(CardCodeDecodingReqData cardCodeDecodingReqData);

    /**
     * 查询优惠券code状态
     */
    @RpcMethod(description = "查询优惠券code状态")
    CardCodeQueryResData queryCouponStatus(CardCodeQueryReqData cardCodeQueryReqData);

    /**
     * 修改会员积分余额等信息
     */
    @RpcMethod(description = "修改会员积分余额等信息")
    MemberItemUpdateRes updateMemberItem(MemberItemUpdateReq memberItemUpdateReq);


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
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "集团ID")
        private String groupID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "微信卡券模板唯一ID")
        private String cardID;
        @NotBlank(message = "公众号唯一标识不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "公众号ID")
        private String mpID;
    }
    @Data
    class DestoryThirdpartyCodeReqData extends RequestInfo {
        //    card_id	否	string(32) 卡券ID。创建卡券时use_custom_code填写true时必填。非自定义Code不必填写。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "微信卡券模板唯一ID")
        private String cardID;
        //    code	是	string(20)	1231231	需核销的Code码。
        @NotBlank(message = "微信code码不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "需导入微信卡券后台的自定义code，上限为100个。")
        private String code;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "集团ID")
        private String groupID;
        @NotBlank(message = "公众号唯一标识不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "公众号ID")
        private String mpID;
    }

    @Data
    class CardCodeDestroyResData extends ResultInfo {
        //    openid	用户在该公众号内的唯一身份标识。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String openId;
        //    card_id	卡券ID。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "卡券模板ID")
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
    class CardCodeQueryReqData extends RequestInfo {
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

    /**
     * 同步会员余额积分
     */
    @Data
    class MemberItemUpdateReq extends RequestInfo {
        //        code            	        是            	string(20)            	1231123            	                        卡券Code码。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "解密后获取的真实Code码")
        private String cardCode;
        //    card_id	        否	string(32)      pFS7Fjg8kV1I    dDz01r4SQwMkuCKc 卡券ID代表一类卡券。自定义code卡券必填。
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "卡券ID代表一类卡券。自定义code卡券必填")
        private Long cardKey;
        //        background_pic_url        否            	string（128）           https://mmbiz.qlogo.cn/                     支持商家激活时针对单个会员卡分配自定义的会员卡背景。
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "支持商家激活时针对单个会员卡分配自定义的会员卡背景")
        private String backgroundPicUrl;
        //        bonus                     否              int            	        100            	                            需要设置的积分全量值，传入的数值会直接显示
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "需要设置的积分全量值，传入的数值会直接显示")
        private String bonus;
        //        add_bonus   	            否	            int     	            100	                                        本次积分变动值，传负数代表减少
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "本次积分变动值，传负数代表减少")
        private String addBonus;
        //        record_bonus            	否 	            string(42)            	消费30元，获得3积分            	            商家自定义积分消耗记录，不超过14个汉字
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "商家自定义积分消耗记录，不超过14个汉字")
        private String recordBonus;
        //        balance                   否              int            	        100            	                            需要设置的余额全量值，传入的数值会直接显示在卡面
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "需要设置的余额全量值，传入的数值会直接显示在卡面")
        private String balance;
        //        add_balance	            否	            int     	            100	                                        本次余额变动值，传负数代表减少
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "本次余额变动值，传负数代表减少")
        private String addBalance;
        //        record_balance            否            	string(42)              购买焦糖玛琪朵一杯，扣除金额30元。          商家自定义金额消耗记录，不超过14个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "商家自定义金额消耗记录，不超过14个汉字。")
        private String recordBalance;
        //        custom_field_value1       否            	string（12）            白金            	                        创建时字段custom_field1定义类型的最新数值，限制为4个汉字，12字节。
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "创建时字段custom_field1定义类型的最新数值，限制为4个汉字，12字节。")
        private String customFieldValue1;
        //        custom_field_value2       否            	string（12）            8折            	                            创建时字段custom_field2定义类型的最新数值，限制为4个汉字，12字节。
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "创建时字段custom_field2定义类型的最新数值，限制为4个汉字，12字节。")
        private String customFieldValue2;
        //        custom_field_value3       否            	string（12）            500            	                            创建时字段custom_field3定义类型的最新数值，限制为4个汉字，12字节。
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "创建时字段custom_field3定义类型的最新数值，限制为4个汉字，12字节。")
        private String customFieldValue3;
        //        is_notify_bonus           否	            bool	                true	                                    积分变动时是否触发系统模板消息，默认为true
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "积分变动时是否触发系统模板消息，默认为true")
        private String isNotifyBonus;
        //        is_notify_balance	        否	            bool	                true	                                    余额变动时是否触发系统模板消息，默认为true
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "余额变动时是否触发系统模板消息，默认为true")
        private String isNotifyBalance;
        //        is_notify_custom_field1	否	            bool	                false                                   	自定义group1变动时是否触发系统模板消息，默认为false。（2、3同理）
        @Protocol(fieldType = FieldType.BOOL, order = 16, description = "自定义custom_field1变动时是否触发系统模板消息，默认为false。（2、3同理）")
        private boolean isNotifyCustomField1;
        @Protocol(fieldType = FieldType.BOOL, order = 17, description = "自定义custom_field1变动时是否触发系统模板消息，默认为false。（2、3同理）")
        private boolean isNotifyCustomField2;
        @Protocol(fieldType = FieldType.BOOL, order = 18, description = "自定义custom_field1变动时是否触发系统模板消息，默认为false。（2、3同理）")
        private boolean isNotifyCustomField3;
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "集团ID")
        private String groupID;
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "发生余额、积分变动的时间")
        private Long msgCreateTime;
    }

    @Data
    class MemberItemUpdateRes extends ResultInfo {
        //        openid            	用户openid
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "用户openid")
        private String openid;
        //        result_bonus            	当前用户积分总额
        @Protocol(fieldType = FieldType.INT, order = 3, description = "当前用户积分总额")
        private int resultBonus;
        //        result_balance            	当前用户预存总金额
        @Protocol(fieldType = FieldType.INT, order = 4, description = "当前用户预存总金额")
        private int resultBalance;

    }

}
