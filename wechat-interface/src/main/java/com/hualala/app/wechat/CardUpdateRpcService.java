package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/4/28.
 */
@RpcService
public interface CardUpdateRpcService {

    /**
     * 更新部分会员信息
     *
     * @param memberUpdateReqData
     * @return
     */
    @RpcMethod
    CardUpdateResData updateMemberInfo(MemberUpdateReqData memberUpdateReqData);

    /**
     * 更新基本信息（优惠券只支持更新基本信息）
     *
     * @param cardBaseInfoUpdateReqData
     * @return
     */
    @RpcMethod
    CardUpdateResData updateBaseInfo(CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData);




    @Data
    class MemberUpdateReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "唯一ID")
        private String cardKey;
        //        supply_bonus	                    是	            bool   	是否支持积分，仅支持从false变为true，默认为false
        @Protocol(fieldType = FieldType.BOOL, order = 3, description = "显示积分，填写true或false，如填写true，积分相关字段均为必填。")
        private Boolean supplyBonus;
        //        supply_balance	                是              bool   	是否支持储值，仅支持从false变为true，默认为false
        @Protocol(fieldType = FieldType.BOOL, order = 4, description = "是否支持储值，填写true或false。如填写true，储值相关字段均为必填。")
        private Boolean supplyBalance;
        //        discount            	            是          	int            	折扣，该会员卡享受的折扣优惠
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "折扣券专用，表示打折额度（百分比）。填30就是七折。")
        private Integer discount;
        //        background_pic_url            	否            	string(128)            	会员卡自定义卡面背景图
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下")
        private String backgroundPicUrl;
        //        bonus_cleared            	        否           	string(3072)            	积分清零规则。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "积分清零规则。")
        private String bonusCleared;

        //        bonus_url	                        否	            string(128) 	积分信息类目跳转的url。
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段。")
        private String bonusUrl;
        //        balance_url	                    否	            string(128) 	余额信息类目跳转的url
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。")
        private String balanceUrl;
        //        balance_rules            	        否           	string(3072)            	储值说明。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "储值说明。")
        private String balanceRules;
        //        prerogative            	        否            	string(3072)            	特权说明。
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "会员卡特权说明。")
        private String prerogative;
        //        wx_activate            	        否            	bool  是否开通一键开卡设置为true时，该卡将支持一键开卡详情见一键开卡。 该选项与activate_url互斥。
        @Protocol(fieldType = FieldType.BOOL, order = 12, description = "设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。")
        private Boolean wxActivate;
        //        auto_activate            	        否            	bool  是否开通自动激活，设置为true时用户领取会员卡自动设置为激活，详情见自动激活。
        @Protocol(fieldType = FieldType.BOOL, order = 13, description = "设置为true时用户领取会员卡后系统自动将其激活，无需调用激活接口，详情见自动激活。")
        private Boolean autoActivate;
        //        activate_url	                    否 	            string(128) 激活链接
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "激活会员卡的url。")
        private String activateUrl;
        //        custom_field1            	        否            	Json结构            	自定义会员信息类目，会员卡激活后显示。
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构")
        private String customField1;
        //        custom_field2            	        否            	Json结构            	自定义会员信息类目，会员卡激活后显示。
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构")
        private String customField2;
        //        custom_field3            	        否            	Json结构            	自定义会员信息类目，会员卡激活后显示。
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段。JSON结构")
        private String customField3;
        //        custom_cell1            	        否            	JSON结构	自定义会员信息类目，会员卡激活后显示
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "自定义会员信息类目，会员卡激活后显示。name/tips/url。JSON结构")
        private String customCell1;
        //        bonus_rule            	        否            	JSON结构 	积分规则结构体，用于微信买单功能
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "积分规则。JSON结构")
        private String bonusRule;
        //        bonus_rules            	        否           	string(3072)            	积分规则。
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "积分规则")
        private String bonusRules;
    }

    @Data
    class CardBaseInfoUpdateReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "唯一ID")
        private String cardKey;
        //        title	                            是	            string(27)	            微信会员卡	会员卡标题，字数上限为9个汉字
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "卡券名称")
        private String title;
        //        description            	        是            	string（3072）          不可与其他优惠同享使用说明。
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "卡券使用说明，字数上限为1024个汉字")
        private String description;
        //        notice            	            是（会员卡不提审）         	    string（48）            请出示二维码核销卡券。使用提醒，字数上限为16个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "卡券使用提醒，字数上限为16个汉字")
        private String notice;
        //        logo_url            	            否          	string(128)             http://mmbiz.qpic.cn/卡券的商户logo，建议像素为300*300。
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "卡券的商户logo，建议像素为300*300")
        private String logoUrl;
        //        service_phone            	        否            	string（24）            	40012234            	客服电话。
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "客服电话。")
        private String servicePhone;
        //        color            	                否          	string（3072）            	Color010            	卡券颜色。
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口")
        private String color;
        //        location_id_list            	    否            	string（3072）            	1234,2314            	支持更新适用门店列表。
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "门店位置ID集合。调用POI门店管理接口获取门店位置ID。多个使用( ','+'空格' )分割")
        private String locationIdList;
        //        use_all_locations	                否 	            bool	true	        支持全部门店，填入后卡券门店跟随商户门店更新而更新
        @Protocol(fieldType = FieldType.BOOL, order = 15, description = "设置本卡券支持全部门店，与location_id_list互斥")
        private Boolean useAllLocations;
        //        center_title            	        否            	string(18)            	立即使用会员卡中部的跳转按钮名称 ，建议用作使用用途
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示")
        private String centerTitle;
        //        center_sub_title            	    否            	string(24)            	到店后使用            	会员卡中部按钮解释wording
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。")
        private String centerSubTitle;
        //        center_url            	        否            	string(128)            	www.qq.com            	会员卡中部按钮对应跳转的url
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "顶部居中的url，仅在卡券状态正常(可以核销)时显示。")
        private String centerUrl;
        //        custom_url_name            	    否            	string（16）            	立即使用            	自定义跳转入口的名字。
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "自定义跳转外链的入口名字。")
        private String customUrlName;
        //        custom_url            	        否            	string（128）            	www.qq.com            	自定义跳转的URL。
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "自定义跳转的URL。")
        private String customUrl;
        //        custom_url_sub_title            	否            	string（18）            	更多惊喜            	显示在入口右侧的提示语。
        @Protocol(fieldType = FieldType.STRING, order = 21, description = "显示在入口右侧的提示语。")
        private String customUrlSubTitle;
        //        promotion_url_name            	否            	string（16）            	产品介绍。            	营销场景的自定义入口名称。
        @Protocol(fieldType = FieldType.STRING, order = 22, description = "营销场景的自定义入口名称。")
        private String promotionUrlName;
        //        promotion_url            	        否            	string（128）            	www.qq.com；            	入口跳转外链的地址链接。
        @Protocol(fieldType = FieldType.STRING, order = 23, description = "入口跳转外链的地址链接。")
        private String promotionUrl;
        //        promotion_url_sub_title           否            	string（18）            	卖场大优惠。            	显示在营销入口右侧的提示语。
        @Protocol(fieldType = FieldType.STRING, order = 24, description = "显示在营销入口右侧的提示语。")
        private String promotionUrlSubTitle;
        //        code_type            	            否            	string（16）            CODE_TYPE_TEXT。Code码展示类型，"CODE_TYPE_TEXT"  文本           "CODE_TYPE_BARCODE"一维码 "CODE_TYPE_QRCODE 二维码   "CODE_TYPE_ONLY_QRCODE"仅显示二维码  "CODE_TYPE_ONLY_BARCODE"仅显示一维码  "CODE_TYPE_NONE"不显示任何码型
        @Protocol(fieldType = FieldType.STRING, order = 25, description = "Code展示类型")
        private String codeType;
        //        is_swipe_card	                    否	            bool	                true	    是否设置该会员卡支持拉出微信支付刷卡界面
        @Protocol(fieldType = FieldType.BOOL, order = 26, description = "是否设置该会员卡支持拉出微信支付刷卡界面")
        private Boolean isSwipeCard;
        //        is_pay_and_qrcode	                否	            bool	                true	是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码
        @Protocol(fieldType = FieldType.BOOL, order = 27, description = "是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码")
        private Boolean isPayAndQrcode;
        //        get_limit            	            否            	int            	        1            	每人可领券的数量限制
        @Protocol(fieldType = FieldType.STRING, order = 28, description = "每人可领券的数量限制。默认值为50。")
        private Integer getLimit;
        //        can_share            	            否            	bool            	    false            	卡券原生领取页面是否可分享
        @Protocol(fieldType = FieldType.BOOL, order = 29, description = "卡券领取页面是否可分享。")
        private Boolean canShare;
        //        can_give_friend            	    否            	bool            	    false            	卡券是否可转赠
        @Protocol(fieldType = FieldType.BOOL, order = 30, description = "卡券是否可转赠。")
        private Boolean canGiveFriend;
        //        date_info            	            否            	Json结构            	见上述示例            	使用日期，有效期的信息，有效期时间修改仅支持有效区间的扩大
        @Protocol(fieldType = FieldType.STRING, order = 31, description = "使用日期，有效期的信息")
        private String dateInfo;
    }

    @Data
    class CardUpdateResData extends ResultInfo {
        //        send_check            	此次更新是否需要提审，true为需要，false为不需要。
        @Protocol(fieldType = FieldType.BOOL, order = 2, description = "是否需要提审")
        private boolean sendCheck;
    }
}
