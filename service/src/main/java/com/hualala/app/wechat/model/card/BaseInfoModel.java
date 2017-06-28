package com.hualala.app.wechat.model.card;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
@Data
public class BaseInfoModel extends BaseItem implements Serializable {
    /**
     * 会员端产生的唯一主键
     */
    private Long cardKey;

    /**
     * 卡券基本信息表自增主键
     */
    private Integer itemID;

    /**
     * 微信返回的卡券ID
     */
    private String cardID;

    /**
     * 哗啦啦cardID
     */
    private Long hualalaCardID;

    /**
     * 店铺ID
     */
    private Long shopID;

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
     * 会员卡创建的进度，1：创建中，2：已提交，审核中，3：审核失败，4：审合通过，5：已投放， 6：失效
     */
    private Integer cardStatus;

    /**
     * 卡券的商户logo，建议像素为300*300
     */
    private String logoUrl;

    /**
     * Code展示类型
     */
    private String codeType;

    /**
     * 券颜色。按色彩规范标注填写Color010-Color100
     */
    private String color;

    /**
     * 商户名字,字数上限为12个汉字
     */
    private String brandName;

    /**
     * 卡券使用提醒，字数上限为16个汉字
     */
    private String notice;

    /**
     * 卡券使用说明，字数上限为1024个汉字
     */
    private String description;

    /**
     * 卡券库存的数量，不支持填写0，上限为100000000。
     */
    private Integer sku;

    /**
     * 卡券时间信息:Json结构
     */
    private String dateInfo;

    /**
     * 是否自定义Code码.填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
     */
    private Boolean useCustomCode;

    /**
     * 是否指定用户领取，填写true或false。默认为false。
     */
    private Boolean bindOpenid;

    /**
     * 客服电话
     */
    private String servicePhone;

    /**
     * 门店位置ID集合。调用POI门店管理接口获取门店位置ID。多个使用( ','+'空格' )分割
     */
    private String locationIdList;

    /**
     * 第三方来源名，例如同程旅游、大众点评。
     */
    private String source;

    /**
     * 自定义跳转外链的入口名字。
     */
    private String customUrlName;

    /**
     * 自定义跳转的URL。
     */
    private String customUrl;

    /**
     * 显示在入口右侧的提示语。
     */
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口名称。
     */
    private String promotionUrlName;

    /**
     * 入口跳转外链的地址链接。
     */
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语。
     */
    private String promotionUrlSubTitle;

    /**
     * 每人可领券的数量限制。默认值为50。
     */
    private Integer getLimit;

    /**
     * 卡券领取页面是否可分享。
     */
    private Boolean canShare;

    /**
     * 卡券是否可转赠。
     */
    private Boolean canGiveFriend;

    /**
     * 填写true为用户点击进入会员卡时推送事件，默认为false。会员卡专用
     */
    private Boolean needPushOnView;

    /**
     * 填入 GET_CUSTOM_CODE_MODE_DEPOSIT 表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，quantity字段须为0,须导入code后再增加库存
     */
    private String customCodeMode;

    /**
     * 设置本卡券支持全部门店，与location_id_list互斥
     */
    private Boolean useAllLocations;

    /**
     * 立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
     */
    private String centerTitle;

    /**
     * 立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
     */
    private String centerSubTitle;

    /**
     * 顶部居中的url，仅在卡券状态正常(可以核销)时显示。
     */
    private String centerUrl;

    /**
     * 每人可核销的数量限制,不填写默认为50。
     */
    private Integer useLimit;

    /**
     * 是否设置该会员卡支持拉出微信支付刷卡界面
     */
    private Boolean isSwipeCard;

    /**
     * 是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码
     */
    private Boolean isPayAndQrcode;

    private static final long serialVersionUID = 1L;

}