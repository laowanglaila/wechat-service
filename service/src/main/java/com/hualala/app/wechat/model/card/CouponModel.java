package com.hualala.app.wechat.model.card;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
@Data
public class CouponModel extends BaseItem implements Serializable {
    /**
     * 会员端产生的唯一主键
     */
    private String cardKey;

    /**
     * 微信返回的卡券ID
     */
    private String cardID;

    /**
     * 集团ID
     */
    private Long groupID;

    /**
     * 公众平台微信号
     */
    private String mpID;

    /**
     * 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)
     */
    private String title;

    /**
     * 卡券类型
     */
    private String cardType;

    /**
     * 优惠券专用，填写优惠详情。
     */
    private String defaultDetail;

    /**
     * 团购券专用，团购详情。
     */
    private String dealDetail;

    /**
     * 代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。
     */
    private Integer leastCost;

    /**
     * 代金券专用，表示减免金额。（单位为分）
     */
    private Integer reduceCost;

    /**
     * 折扣券专用，表示打折额度（百分比）。填30就是七折。
     */
    private Integer discount;

    /**
     * 可兑换音乐木盒一个。兑换券专用，填写兑换内容的名称。
     */
    private String gift;

    /**
     * 会员卡创建的进度，1：创建中，2：已提交，审核中，3：审合通过，4：审核失败，5：已投放
     */
    private Integer cardStatus;


    private static final long serialVersionUID = 1L;


}