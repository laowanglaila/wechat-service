package com.hualala.app.wechat.model.card;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
@Data
public class MemberModel extends BaseItem implements Serializable {
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
     * 卡券类型,会员卡类型
     */
    private String cardType;

    /**
     * 折扣，该会员卡享受的折扣优惠,填10就是九折。
     */
    private Integer discount;

    /**
     * 商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下
     */
    private String backgroundPicUrl;

    /**
     * 会员卡特权说明。
     */
    private String prerogative;

    /**
     *  设置为true时用户领取会员卡后系统自动将其激活，无需调用激活接口，详情见自动激活。
     */
    private Boolean autoActivate;

    /**
     * 设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。
     */
    private Boolean wxActivate;

    /**
     * 显示积分，填写true或false，如填写true，积分相关字段均为必填。
     */
    private Boolean supplyBonus;

    /**
     * 设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段。
     */
    private String bonusUrl;

    /**
     * 是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
     */
    private Boolean supplyBalance;

    /**
     * 设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。
     */
    private String balanceUrl;

    /**
     * 自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构
     */
    private String customField1;

    /**
     * 自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构
     */
    private String customField2;

    /**
     * 自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构
     */
    private String customField3;

    /**
     * 自定义会员信息类目，会员卡激活后显示。name/tips/url。JSON结构
     */
    private String customCell1;

    /**
     * 积分规则。JSON结构
     */
    private String bonusRule;

    /**
     * 会员卡创建的进度，1：创建中，2：已提交，审核中，3：审合通过，4：审核失败，5：已投放
     */
    private Integer cardStatus;

    private static final long serialVersionUID = 1L;

}