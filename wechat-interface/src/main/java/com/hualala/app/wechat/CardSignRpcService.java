package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by renjianfei on 2017/6/27.
 */
@RpcService(description = "获取api签名相关")
public interface CardSignRpcService {

    /**
     * 获取h5投放卡券签名和必传参数
     *
     */
    @RpcMethod(description = "获取h5投放卡券签名和必传参数")
    CardSignResData getSign(CardSignReqData cardSignReqData);
    @RpcMethod(description = "批量获取h5投放卡券签名和必传参数")
    CardBachSignResData getSignList(CardBachSignReqData cardSignReqData);


    @Data
    class CardSignReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "集团ID")
        private Long groupID;
//        @Protocol(fieldType = FieldType.LONG, order = 4, description = "店铺ID")
//        private Long shopID;
        @Protocol(fieldType = FieldType.LONG, order = 5, description = "哗啦啦会员卡TypeID")
        private Long hualalaCardID;
        //        code		            否	是	指定的卡券code码，只能被领一次。自定义code模式的卡券必须填写，非自定义code和预存code模式的卡券不必填写。详情见：是否自定义code码
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "指定的卡券code码，只能被领一次。自定义code模式的卡券必须填写，非自定义code和预存code模式的卡券不必填写")
        private String code;
        //        openid	            否	是	指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，bind_openid字段为false不必填写。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，bind_openid字段为false不必填写")
        private String openid;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "哗啦啦用户的会员卡id")
        private String hualalaCardCode;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "哗啦啦用户id")
        private String customerID;
    }
    @Data
    class CardSignResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "时间戳，商户生成从1970年1月1日00:00:00至今的秒数,即当前的时间,且最终需要转换为字符串形式;由商户生成后传入,不同添加请求的时间戳须动态生成，若重复将会导致领取失败！")
        private String timeStamp;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "随机字符串，由开发者设置传入，加强安全性（若不填写可能被重放请求）。随机字符串，不长于32位。推荐使用大小写字母和数字，不同添加请求的nonce须动态生成，若重复将会导致领取失败。")
        private String nonceStr;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "签名，商户将接口列表中的参数按照指定方式进行签名,签名方式使用SHA1,具体签名方案参见下文;由商户按照规范签名后传入")
        private String signature;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "微信cardID")
        private String cardID;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "领取渠道参数，用于标识本次领取的渠道值")
        private String outerStr;
    }

    @Data
    class CardBachSignReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "集团ID")
        private Long groupID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，bind_openid字段为false不必填写")
        private String openid;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "哗啦啦用户id")
        private String customerID;
        @Size(min = 1,message = "签名卡券实例参数集合不能为空")
        @Protocol(fieldType = FieldType.OBJECT, order = 6, description = "批量签名卡券实例参数集合")
        private List<CardBachSignReqItem> items;


    }
    @Data
    class CardBachSignReqItem {
        @Protocol(fieldType = FieldType.LONG, order = 1, description = "哗啦啦会员卡TypeID")
        private Long hualalaCardID;
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "指定的卡券code码，只能被领一次。自定义code模式的卡券必须填写，非自定义code和预存code模式的卡券不必填写")
        private String code;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "哗啦啦用户的会员卡id")
        private String hualalaCardCode;
    }
    @Data
    class CardBachSignResData extends ResultInfo {
        @Protocol(fieldType = FieldType.OBJECT, order = 2, description = "卡券签名集合")
        private List<CardSignResData> signs;
    }
}
