package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/4/25.
 */
@RpcService(description = "预创建卡券接口")
public interface PrePareCreateCardRpcService {

    @RpcMethod(description = "卡券创建方法")
    public CardCouponResData createCoupon(CouponReqData couponReqData);

    /**
     * 优惠券，券
     */
    @Data
    class CouponReqData extends RequestInfo {

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "集团ID")
        private Long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "品牌ID")
        private Long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 5, description = "店铺ID")
        private Long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "卡券名称")
        private String title;
        //        card_type	            是	string(24)	    GENERAL_COUPON	优惠券类型。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "优惠券类型")
        private String cardType;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "唯一ID")
        private String cardKey;
        //        default_detail	    是	string(3072)	双人套餐\n -进口红酒一支。\n孜然牛肉一份。。	优惠券专用，填写优惠详情。
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "优惠券专用，填写优惠详情。")
        private String defaultDetail;
        //       deal_detail	        是	string(3072)	双人套餐\n -进口红酒一支。\n孜然牛肉一份。	团购券专用，团购详情。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "团购券专用，填写团购详情。")
        private String dealDetail;
        //        least_cost	        是	int	10000	    代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。")
        private Integer leastCost;
        //        reduce_cost	        是	int	10000	    代金券专用，表示减免金额。（单位为分）
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "代金券专用，表示减免金额。（单位为分）")
        private Integer reduceCost;
        //        discount	            是	int	30	        折扣券专用，表示打折额度（百分比）。填30就是七折。
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "折扣券专用，表示打折额度（百分比）。填30就是七折。")
        private Integer discount;
        //        gift	                是	string(3072)	可兑换音乐木盒一个。	兑换券专用，填写兑换内容的名称。
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "可兑换音乐木盒一个。兑换券专用，填写兑换内容的名称。")
        private String gift;

    }

    @Data
    class CardCouponResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String cardKey;
    }

}
