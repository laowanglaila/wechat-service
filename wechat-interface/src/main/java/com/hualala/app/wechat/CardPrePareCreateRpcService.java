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
public interface CardPrePareCreateRpcService {
    @RpcMethod(description = "卡券创建必要信息方法")
    public PreCardResData createCoupon(PreCouponReqData preCouponReqData);

    @RpcMethod(description = "会员卡创建必要信息方法")
    public PreCardResData createMemberCard(PreMemberReqData preCardReqData);

    @RpcMethod(description = "基本信息")
    public PreCardResData createBaseInfo(PreCardBaseInfoData preCardBaseInfoData);

    @RpcMethod(description = "高级信息")
    public PreCardResData createAdvancedInfo(PreAdvancedInfoData preAdvancedInfoData);

    @RpcMethod(description = "提交优惠券")
    public PreCardResData submitCouponInfo(CardPrimaryKey cardPrimaryKey);

    @RpcMethod(description = "提交会员卡信息")
    public PreCardResData submitMemberInfo(CardPrimaryKey cardPrimaryKey);

    /**
     * 主键
     */
    @Data
    class CardPrimaryKey extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "卡券主键")
        private String cardKey;
    }

    /**
     * 优惠券，券
     */
    @Data
    class PreCouponReqData extends RequestInfo {

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

    /**
     * 会员卡
     */
    @Data
    class PreMemberReqData extends RequestInfo {

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
        //        card_type            	是            	string(24)            	会员卡类型。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "优惠券类型")
        private String cardType;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "会员端产生的唯一主键ID")
        private String cardKey;
        //        discount            	否            	int            	折扣，该会员卡享受的折扣优惠,填10就是九折。
        @Protocol(fieldType = FieldType.INT, order = 9, description = "折扣，该会员卡享受的折扣优惠,填10就是九折。")
        private Integer discount;
        //        background_pic_url        否            	string(128)         商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下")
        private String backgroundPicUrl;
        //        prerogative            	是
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "会员卡特权说明。")
        private String prerogative;
        //        auto_activate            	否            	bool            	设置为true时用户领取会员卡后系统自动将其激活，无需调用激活接口，详情见自动激活。
        @Protocol(fieldType = FieldType.BOOL, order = 12, description = "设置为true时用户领取会员卡后系统自动将其激活，无需调用激活接口，详情见自动激活。")
        private Boolean autoActivate;
        //        wx_activate            	否            	bool            	设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。
        @Protocol(fieldType = FieldType.BOOL, order = 13, description = "设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。")
        private Boolean wxActivate;
        //        supply_bonus            	是            	bool            	显示积分，填写true或false，如填写true，积分相关字段均为必填。
        @Protocol(fieldType = FieldType.BOOL, order = 14, description = "显示积分，填写true或false，如填写true，积分相关字段均为必填。")
        private Boolean supplyBonus;
        //        bonus_url            	否            	string(128)            	设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段。
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段。")
        private String bonusUrl;
        //        supply_balance            	是            	bool            	是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
        @Protocol(fieldType = FieldType.BOOL, order = 16, description = "是否支持储值，填写true或false。如填写true，储值相关字段均为必填。")
        private Boolean supplyBalance;
        //        balance_url            	否            	string(128)            	设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。")
        private String balanceUrl;
        //        custom_field1            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构")
        private String customField1;
        //        custom_field2            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示，包含name_type(name)和url字段
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构")
        private String customField2;
        //        custom_field3            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示，包含name_type(name)和url字段
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构")
        private String customField3;
//        custom_cell1            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示。

        @Protocol(fieldType = FieldType.STRING, order = 21, description = "自定义会员信息类目，会员卡激活后显示。name/tips/url。JSON结构")
        private String customCell1;

        //        bonus_rule            	        否            	JSON结构 	积分规则结构体，用于微信买单功能
        @Protocol(fieldType = FieldType.STRING, order = 22, description = "积分规则。JSON结构")
        private String bonusRule;
        //        activate_url            	是            	string（128）            	激活会员卡的url。
        @Protocol(fieldType = FieldType.STRING, order = 23, description = "激活会员卡的url。")
        private String activateUrl;
        //        bonus_cleared            	否            	string（128）            	积分清零规则。
        @Protocol(fieldType = FieldType.STRING, order = 24, description = "积分清零规则。")
        private String bonusCleared;
        //        balance_rules            	否            	string（128）            	储值说明。
        @Protocol(fieldType = FieldType.STRING, order = 25, description = "储值说明。")
        private String balanceRules;
        //        bonus_rules            	否            	string（512）            	积分规则。
        @Protocol(fieldType = FieldType.STRING, order = 26, description = "积分规则")
        private String bonusRules;

    }

    @Data
    class PreCardResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String cardKey;
    }

    /**
     * 卡券基本信息
     */
    @Data
    class PreCardBaseInfoData extends RequestInfo {

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "card唯一键")
        private String cardKey;
        //        logo_url 	             是 	string(128) 	http://mmbiz.qpic.cn/ 	卡券的商户logo，建议像素为300*300。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "卡券的商户logo，建议像素为300*300")
        private String logoUrl;
        //        code_type 	         是 	string(16) 	    CODE_TYPE_TEXT 	Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "Code展示类型")
        private String codeType;
        //        color 	             是 	string（16） 	Color010 	券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口")
        private String color;
        //        brand_name             是 	string（36） 	海底捞 	商户名字,字数上限为12个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "商户名字,字数上限为12个汉字")
        private String brandName;
        //        notice 	             是 	string（48） 	请出示二维码核销卡券 	卡券使用提醒，字数上限为16个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "卡券使用提醒，字数上限为16个汉字")
        private String notice;
        //        description            是 	string（3072） 	不可与其他优惠同享/n如需团购券发票，请向店员提出要求。 	卡券使用说明，字数上限为1024个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "卡券使用说明，字数上限为1024个汉字")
        private String description;
        //        -sku              是 	int 	        100000   	卡券库存的数量，不支持填写0，上限为100000000。
        @Protocol(fieldType = FieldType.INT, order = 9, description = "库存信息")
        private Integer sku;
        //        date_info              是 	Json结构 	    见上述示例。 	使用日期，有效期的信息。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "使用日期，有效期的信息")
        private String dateInfo;
        //        use_custom_code        否 	bool 	        true 	是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
        @Protocol(fieldType = FieldType.BOOL, order = 11, description = "是否自定义Code码.填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。")
        private Boolean useCustomCode;
        //        bind_openid 	         否 	bool 	        true 	是否指定用户领取，填写true或false。默认为false。
        @Protocol(fieldType = FieldType.BOOL, order = 12, description = "是否指定用户领取，填写true或false。默认为false。")
        private Boolean bindOpenid;
        //        service_phone 	     否 	string（24） 	40012234 	客服电话。
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "客服电话。")
        private String servicePhone;
        //-----------------------------------------------------------------------
//        location_id_list 	     否 	array 	        1234，2312 	门店位置ID。调用POI门店管理接口获取门店位置ID。
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "门店位置ID集合。调用POI门店管理接口获取门店位置ID。多个使用( ','+'空格' )分割")
        private String locationIdList;
        //-----------------------------------------------------------------------
//        source 	             否 	string（36） 	大众点评 	第三方来源名，例如同程旅游、大众点评。
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "第三方来源名，例如同程旅游、大众点评。")
        private String source;
        //        custom_url_name 	     否 	string（15） 	立即使用 	自定义跳转外链的入口名字。
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "自定义跳转外链的入口名字。")
        private String customUrlName;
        //        custom_url 	         否 	string（128） 	"xxxx.com" 	自定义跳转的URL。
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "自定义跳转的URL。")
        private String customUrl;
        //        custom_url_sub_title 	 否 	string（18） 	更多惊喜 	显示在入口右侧的提示语。
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "显示在入口右侧的提示语。")
        private String customUrlSubTitle;
        //        promotion_url_name 	 否 	string（15） 	产品介绍 	营销场景的自定义入口名称。
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "营销场景的自定义入口名称。")
        private String promotionUrlName;
        //        promotion_url 	     否 	string（128） 	XXXX.com 	入口跳转外链的地址链接。
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "入口跳转外链的地址链接。")
        private String promotionUrl;
        //        promotion_url_sub_title 否 	string（18） 	卖场大优惠。 	显示在营销入口右侧的提示语。
        @Protocol(fieldType = FieldType.STRING, order = 21, description = "显示在营销入口右侧的提示语。")
        private String promotionUrlSubTitle;
        //        get_limit 	         否 	int 	        1 	每人可领券的数量限制。默认值为50。
        @Protocol(fieldType = FieldType.STRING, order = 22, description = "每人可领券的数量限制。默认值为50。")
        private Integer getLimit;
        //        can_share 	         否 	bool 	        false 	卡券领取页面是否可分享。
        @Protocol(fieldType = FieldType.BOOL, order = 23, description = "卡券领取页面是否可分享。")
        private Boolean canShare;
        //        can_give_friend 	     否 	bool 	        false 	卡券是否可转赠。
        @Protocol(fieldType = FieldType.BOOL, order = 24, description = "卡券是否可转赠。")
        private Boolean canGiveFriend;
        //        need_push_on_view 	 否 	bool 	        false 	填写true为用户点击进入会员卡时推送事件，默认为false。详情见进入会员卡事件推送
        @Protocol(fieldType = FieldType.BOOL, order = 25, description = "填写true为用户点击进入会员卡时推送事件，默认为false。会员卡专用")
        private Boolean needPushOnView;
        //        get_custom_code_mode	否	string(32)      GET_CUSTOM_COD E_MODE_DEPOSIT 填入 GET_CUSTOM_CODE_MODE_DEPOSIT
        //                                                  表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，
        //                                                  quantity字段须为0,须导入code后再增加库存
        @Protocol(fieldType = FieldType.STRING, order = 26, description = "填入 GET_CUSTOM_CODE_MODE_DEPOSIT 表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，quantity字段须为0,须导入code后再增加库存")
        private String customCodeMode;
        //        use_all_locations     否	bool	        true	设置本卡券支持全部门店，与location_id_list互斥
        @Protocol(fieldType = FieldType.BOOL, order = 27, description = "设置本卡券支持全部门店，与location_id_list互斥")
        private Boolean useAllLocations;
        //        center_title	        否	string（18）	立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
        @Protocol(fieldType = FieldType.STRING, order = 28, description = "立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示")
        private String centerTitle;
        //        center_sub_title	    否	string（24）	立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
        @Protocol(fieldType = FieldType.STRING, order = 29, description = "立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。")
        private String centerSubTitle;
        //        center_url	        否	string（128）	www.qq.com顶部居中的url，仅在卡券状态正常(可以核销)时显示。
        @Protocol(fieldType = FieldType.STRING, order = 30, description = "顶部居中的url，仅在卡券状态正常(可以核销)时显示。")
        private String centerUrl;
        //        use_limit	            否	int	            100	每人可核销的数量限制,不填写默认为50。
        @Protocol(fieldType = FieldType.STRING, order = 31, description = "每人可核销的数量限制,不填写默认为50。")
        private Integer useLimit;
        @Protocol(fieldType = FieldType.BOOL, order = 32, description = "是否设置该会员卡支持拉出微信支付刷卡界面")
        private Boolean isSwipeCard;
        @Protocol(fieldType = FieldType.BOOL, order = 33, description = "是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码")
        private Boolean isPayAndQrcode;

    }

    /**
     * 卡券高级属性封装形式Json
     */

    @Data
    class PreAdvancedInfoData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "card唯一键")
        private String cardKey;
        //        abstract	                    否	JSON结构	        封面摘要结构体名称
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "封面摘要结构体名称")
        private String abstractInfo;
        //          business_service	            否	arry	            商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选,多个采用“，”+“空格”分割")
        private String businessService;
        //          text_image_list	            否	JSON结构            图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表")
        private String textImage;
        //          time_limit	                否	JSON结构	        使用时段限制，包含以下字段
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "使用时段限制，包含以下字段")
        private String timeLimit;
        //          use_condition	            否	JSON结构            使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享")
        private String useCodition;

    }

}
