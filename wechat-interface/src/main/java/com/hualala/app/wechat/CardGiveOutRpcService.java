package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by renjianfei on 2017/7/12.
 */
@RpcService(description = "投放卡券接口")
public interface CardGiveOutRpcService {

    @RpcMethod(description = "支付即会员接口")
    PayToMemberRes payToMember(PayToMemberReq payToMemberReq);
    @RpcMethod(description = "支付赠券接口")
    PayToGiveCouponsRes payToGiveCoupon(PayToGiveCouponsReq payToGiveCouponsReq);

    /**
     * 支付即会员请求参数
     */
    @Data
    class PayToMemberReq extends RequestInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "必填参数，要赠送的会员卡cardKey")
        private Long cardKey;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "必填参数，规则开始时间,1970年1月1日 00:00:00开始的时间秒数")
        private Integer beginTime;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "必填参数，规则结束时间,1970年1月1日 00:00:00开始的时间秒数")
        private Integer endTime;
       @Protocol(fieldType = FieldType.STRING, order = 5, description = "必填参数，单次消费送会员卡的金额下限，以分为单位")
        private String leastCost;
       @Protocol(fieldType = FieldType.STRING, order = 6, description = "必填参数，单次消费送会员卡的金额上限，以分为单位")
        private String maxCost;
       @Protocol(fieldType = FieldType.STRING, order = 7, description = "选填参数，商户自定义领卡网页链接，填入后点击支付即会员消息会跳转至商户网页领卡")
        private String jumpUrl;
    }
    @Data
    class PayToMemberRes extends ResultInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "必填参数，要赠送的会员卡cardKey")
        private Long cardKey;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "必填参数，规则开始时间,1970年1月1日 00:00:00开始的时间秒数")
        private String beginTime;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "必填参数，规则结束时间,1970年1月1日 00:00:00开始的时间秒数")
        private String endTime;
       @Protocol(fieldType = FieldType.STRING, order = 5, description = "必填参数，单次消费送会员卡的金额下限，以分为单位")
        private String leastCost;
       @Protocol(fieldType = FieldType.STRING, order = 6, description = "必填参数，单次消费送会员卡的金额上限，以分为单位")
        private String maxCost;
       @Protocol(fieldType = FieldType.STRING, order = 7, description = "选填参数，商户自定义领卡网页链接，填入后点击支付即会员消息会跳转至商户网页领卡")
        private String jumpUrl;
    }

    /**
     * 支付赠券请求参数
     */
    @Data
    class PayToGiveCouponsReq extends RequestInfo {
        @NotBlank(message = "beginTime不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "必填参数，规则开始时间,1970年1月1日 00:00:00开始的时间秒数")
        private String beginTime;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "必填参数，规则结束时间,1970年1月1日 00:00:00开始的时间秒数")
        private String endTime;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "必填参数，赠送标题")
        private String title;
        @Valid
        @Protocol(fieldType = FieldType.OBJECT, order = 5, description = "必填参数，期望投放的优惠券集合")
        private List<CouponInfo> couponInfoList;

    }
    @Data
    class PayToGiveCouponsRes extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "必填参数，规则开始时间,1970年1月1日 00:00:00开始的时间秒数")
        private String beginTime;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "必填参数，规则结束时间,1970年1月1日 00:00:00开始的时间秒数")
        private String endTime;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "必填参数，赠送标题")
        private String title;

        @Protocol(fieldType = FieldType.OBJECT, order = 5, description = "必填参数，期望投放的优惠券集合")
        private List<CouponInfo> couponInfoList;

    }

    /**
     * 优惠券信息
     */
    @Data
    class CouponInfo{
        @NotNull(message = "cardKey不能为空")
        @Protocol(fieldType = FieldType.LONG, order = 1, description = "必填参数，要赠送的会员卡cardKey")
        private Long cardKey;
        @NotBlank(message = "amount不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "选填参数，起送金额条件，以分为单位，默认为0")
        private String amount;
    }

}
