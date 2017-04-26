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
 * Created by renjianfei on 2017/4/18.
 */
@RpcService(description = "卡券创建接口")
public interface CreateCardCouponRpcService {

    @RpcMethod(description = "卡券创建方法")
    public CardCouponResData createCoupon(CouponReqData couponReqData);

    /**
     * 优惠券，券
     */
    @Data
    class CouponReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.OBJECT, order = 2, description = "公众号信息")
        private HeadData headData = new HeadData();
        //        card_type	            是	string(24)	    GENERAL_COUPON	优惠券类型。
        @Protocol(fieldType = FieldType.ENUM, order = 3, description = "优惠券类型")
        private CouponTypeEnum couponType;
        @Protocol(fieldType = FieldType.OBJECT, order = 4, description = "卡券基本信息")
        private BaseInfo baseInfo = new BaseInfo();
        @Protocol(fieldType = FieldType.OBJECT, order = 5, description = "卡券高级信息")
        private AdvancedInfo advancedInfo = new AdvancedInfo();
        //        default_detail	    是	string(3072)	双人套餐\n -进口红酒一支。\n孜然牛肉一份。。	优惠券专用，填写优惠详情。
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "优惠券专用，填写优惠详情。")
        private String defaultDetail;
        //       deal_detail	        是	string(3072)	双人套餐\n -进口红酒一支。\n孜然牛肉一份。	团购券专用，团购详情。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "团购券专用，填写团购详情。")
        private String dealDetail;
        //        least_cost	        是	int	10000	    代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。")
        private Integer leastCost;
        //        reduce_cost	        是	int	10000	    代金券专用，表示减免金额。（单位为分）
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "代金券专用，表示减免金额。（单位为分）")
        private Integer reduceCost;
        //        discount	            是	int	30	        折扣券专用，表示打折额度（百分比）。填30就是七折。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "折扣券专用，表示打折额度（百分比）。填30就是七折。")
        private Integer discount;
        //        gift	                是	string(3072)	可兑换音乐木盒一个。	兑换券专用，填写兑换内容的名称。
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "可兑换音乐木盒一个。兑换券专用，填写兑换内容的名称。")
        private String gift;

    }

    @Data
    class CardCouponResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "错误码，0为正常")
        private String errcode;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "错误消息")
        private String errmsg;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "cardID")
        private String cardId;
    }

    @Data
    public class HeadData {
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "集团ID")
        private String groupID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "品牌ID")
        private String brandID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "店铺ID")
        private String shopID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "微信用户唯一标识")
        private String openID;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "微信账号")
        private String userName;
    }

    /**
     * 卡券基本信息
     */
    @Data
    class BaseInfo {

        //        logo_url 	             是 	string(128) 	http://mmbiz.qpic.cn/ 	卡券的商户logo，建议像素为300*300。
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "卡券的商户logo，建议像素为300*300")
        private String logoUrl;
        //        code_type 	         是 	string(16) 	    CODE_TYPE_TEXT 	Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；
        @Protocol(fieldType = FieldType.ENUM, order = 2, description = "Code展示类型")
        private CodeTypeEnum codeType;
        //        color 	             是 	string（16） 	Color010 	券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口")
        private String color;
        //        brand_name             是 	string（36） 	海底捞 	商户名字,字数上限为12个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "商户名字,字数上限为12个汉字")
        private String brandName;

        //        title 	             是 	string（27） 	双人套餐100元兑换券 	卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)")
        private String title;

        //        notice 	             是 	string（48） 	请出示二维码核销卡券 	卡券使用提醒，字数上限为16个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "卡券使用提醒，字数上限为16个汉字")
        private String notice;
        //        description            是 	string（3072） 	不可与其他优惠同享/n如需团购券发票，请向店员提出要求。 	卡券使用说明，字数上限为1024个汉字。
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "卡券使用说明，字数上限为1024个汉字")
        private String description;
        //--------------------------------------------------------------------------
//        sku 	                 是 	Json结构 	    见上述示例。 	商品信息。
//        -quantity              是 	int 	        100000   	卡券库存的数量，不支持填写0，上限为100000000。
        @Protocol(fieldType = FieldType.OBJECT, order = 8, description = "商品信息")
        private Sku sku = new Sku();
        //--------------------------------------------------------------------------
//        date_info              是 	Json结构 	    见上述示例。 	使用日期，有效期的信息。
        @Protocol(fieldType = FieldType.OBJECT, order = 9, description = "使用日期，有效期的信息")
        private DateInfo dateInfo = new DateInfo();
//--------------------------------------------------------------------------

        //        use_custom_code        否 	bool 	        true 	是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
        @Protocol(fieldType = FieldType.BOOL, order = 10, description = "是否自定义Code码.填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。")
        private Boolean useCustomCode;
        //        bind_openid 	         否 	bool 	        true 	是否指定用户领取，填写true或false。默认为false。
        @Protocol(fieldType = FieldType.BOOL, order = 11, description = "是否指定用户领取，填写true或false。默认为false。")
        private Boolean bindOpenid;
        //        service_phone 	     否 	string（24） 	40012234 	客服电话。
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "客服电话。")
        private String servicePhone;
        //-----------------------------------------------------------------------
//        location_id_list 	     否 	array 	        1234，2312 	门店位置ID。调用POI门店管理接口获取门店位置ID。
        @Protocol(fieldType = FieldType.INT, order = 13, description = "门店位置ID集合。调用POI门店管理接口获取门店位置ID。多个使用( ','+'空格' )分割")
        private List<Integer> locationIdList;
        //-----------------------------------------------------------------------
//        source 	             否 	string（36） 	大众点评 	第三方来源名，例如同程旅游、大众点评。
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "第三方来源名，例如同程旅游、大众点评。")
        private String source;
        //        custom_url_name 	     否 	string（15） 	立即使用 	自定义跳转外链的入口名字。
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "自定义跳转外链的入口名字。")
        private String customUrlName;
        //        custom_url 	         否 	string（128） 	"xxxx.com" 	自定义跳转的URL。
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "自定义跳转的URL。")
        private String customUrl;
        //        custom_url_sub_title 	 否 	string（18） 	更多惊喜 	显示在入口右侧的提示语。
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "显示在入口右侧的提示语。")
        private String customUrlSubTitle;
        //        promotion_url_name 	 否 	string（15） 	产品介绍 	营销场景的自定义入口名称。
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "营销场景的自定义入口名称。")
        private String promotionUrlName;
        //        promotion_url 	     否 	string（128） 	XXXX.com 	入口跳转外链的地址链接。
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "入口跳转外链的地址链接。")
        private String promotionUrl;
        //        promotion_url_sub_title否 	string（18） 	卖场大优惠。 	显示在营销入口右侧的提示语。
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "显示在营销入口右侧的提示语。")
        private String promotionUrlSubTitle;
        //        get_limit 	         否 	int 	        1 	每人可领券的数量限制。默认值为50。
        @Protocol(fieldType = FieldType.STRING, order = 21, description = "每人可领券的数量限制。默认值为50。")
        private Integer getLimit;
        //        can_share 	         否 	bool 	        false 	卡券领取页面是否可分享。
        @Protocol(fieldType = FieldType.BOOL, order = 22, description = "卡券领取页面是否可分享。")
        private Boolean canShare;
        //        can_give_friend 	     否 	bool 	        false 	卡券是否可转赠。
        @Protocol(fieldType = FieldType.BOOL, order = 23, description = "卡券是否可转赠。")
        private Boolean canGiveFriend;
        //        need_push_on_view 	 否 	bool 	        false 	填写true为用户点击进入会员卡时推送事件，默认为false。详情见进入会员卡事件推送
        @Protocol(fieldType = FieldType.BOOL, order = 24, description = "填写true为用户点击进入会员卡时推送事件，默认为false。会员卡专用")
        private Boolean needPushOnView;

        //        get_custom_code_mode	否	string(32)      GET_CUSTOM_COD E_MODE_DEPOSIT 填入 GET_CUSTOM_CODE_MODE_DEPOSIT
        //                                                  表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，
        //                                                  quantity字段须为0,须导入code后再增加库存
        @Protocol(fieldType = FieldType.ENUM, order = 25, description = "填入 GET_CUSTOM_CODE_MODE_DEPOSIT 表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，quantity字段须为0,须导入code后再增加库存")
        private CustomCodeModeEnum CustomCodeMode;
        //        use_all_locations     否	bool	        true	设置本卡券支持全部门店，与location_id_list互斥
        @Protocol(fieldType = FieldType.BOOL, order = 26, description = "设置本卡券支持全部门店，与location_id_list互斥")
        private Boolean useAllLocations;
        //        center_title	        否	string（18）	立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
        @Protocol(fieldType = FieldType.STRING, order = 27, description = "立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示")
        private String centerTitle;

        //        center_sub_title	    否	string（24）	立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
        @Protocol(fieldType = FieldType.STRING, order = 28, description = "立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。")
        private String centerSubTitle;
        //        center_url	        否	string（128）	www.qq.com顶部居中的url，仅在卡券状态正常(可以核销)时显示。
            @Protocol(fieldType = FieldType.STRING, order = 29, description = "顶部居中的url，仅在卡券状态正常(可以核销)时显示。")
        private String centerUrl;
        //        use_limit	            否	int	            100	每人可核销的数量限制,不填写默认为50。
        @Protocol(fieldType = FieldType.STRING, order = 30, description = "每人可核销的数量限制,不填写默认为50。")
        private Integer useLimit;

    }

    /**
     * 卡券商品信息
     */
    @Data
    class Sku {
        //        -quantity              是 	int 	        100000 	卡券库存的数量，不支持填写0，上限为100000000。
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "卡券库存的数量，不支持填写0，上限为100000000")
        private Integer quantity;
    }

    /**
     * 卡券时间信息
     */
    @Data
    class DateInfo {

        //          -type 	             是 	string 	        DATE_TYPE_FIX_TIME_RANGE 表示固定日期区间，DATE_TYPE_FIX_TERM表示固定时长（自领取后按天算），DATE_TYPE_PERMANENT 表示永久有效。 	使用时间的类型，旧文档采用的1和2依然生效。
        @Protocol(fieldType = FieldType.ENUM, order = 1, description = "DATE_TYPE_FIX_TIME_RANGE 表示固定日期区间，DATE_TYPE_FIX_TERM表示固定时长（自领取后按天算），DATE_TYPE_PERMANENT 表示永久有效。")
        private DateInfoTypeEnum type;
        //        -begin_timestamp       否 	unsigned int 	14300000 	type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间，单位为秒）
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间，单位为秒）")
        private Integer beginTimestamp;
        //        -end_timestamp 	     否 	unsigned int 	15300000 	type为DATE_TYPE_FIX_TERM_RANGE时专用，表示结束时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "type为DATE_TYPE_FIX_TERM_RANGE时专用，表示结束时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）")
        private Integer endTimestamp;
        //        -fixed_term 	         否 	int 	        0 	type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，领取后当天有效填写0。（单位为天）
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，领取后当天有效填写0")
        private Integer fixedTerm;
        //        -fixed_begin_term 	 否 	int 	        15 	type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效。（单位为天）
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效。（单位为天）")
        private Integer fixedBeginTerm;
    }

    // 卡券高级属性封装形式待定
    @Data
    class AdvancedInfo {
        //        abstract	                    否	JSON结构	        封面摘要结构体名称
        @Protocol(fieldType = FieldType.OBJECT, order = 1, description = "封面摘要结构体名称")
        private Abstract abstractObj = new Abstract();
        //          business_service	            否	arry	            商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选")
        private List<String> businessServiceList;
        //          text_image_list	            否	JSON结构            图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表
        @Protocol(fieldType = FieldType.OBJECT, order = 3, description = "图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表")
        private List<TextImage> textImageList;
        //          time_limit	                否	JSON结构	        使用时段限制，包含以下字段
        @Protocol(fieldType = FieldType.OBJECT, order = 4, description = "使用时段限制，包含以下字段")
        private List<TimeLimit> timeLimitList;
        //          use_condition	            否	JSON结构            使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享
        @Protocol(fieldType = FieldType.OBJECT, order = 5, description = "使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享")
        private UseCodition useCodition = new UseCodition();

    }

    //        abstract	                    否	JSON结构	        封面摘要结构体名称
    @Data
    class Abstract {
        //              abstract	                    否	string（24）	    封面摘要简介。
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "封面摘要简介。")
        private String coverAbstract;
        //              icon_url_list	                否	string（128）       封面图片列表，仅支持填入一个封面图片链接，上传图片接口上传获取图片获得链接，填写非CDN链接会报错，并在此填入。建议图片尺寸像素850*350
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "封面图片列表，仅支持填入一个封面图片链接，上传图片接口上传获取图片获得链接，填写非CDN链接会报错，并在此填入。建议图片尺寸像素850*350")
        private List<String> iconUrlList;
    }

    @Data
    class TextImage {
        //              image_url	                    否	string（128）       图片链接，必须调用上传图片接口上传图片获得链接，并在此填入，否则报错
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "图片链接，必须调用上传图片接口上传图片获得链接，并在此填入，否则报错。")
        private String imageUrl;
        //              text	                        否	string（512）	    图文描述
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "图文描述。")
        private String text;

    }


    @Data
    class TimeLimit {
        //              type	                        否	string（24）        限制类型枚举值：支持填入 MONDAY 周一,TUESDAY 周二,WEDNESDAY 周三,THURSDAY 周四,FRIDAY 周五,SATURDAY 周六, SUNDAY 周日,此处只控制显示，不控制实际使用逻辑，不填默认不显示
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "限制类型枚举值：支持填入 MONDAY 周一,TUESDAY 周二,WEDNESDAY 周三,THURSDAY 周四,FRIDAY 周五,SATURDAY 周六, SUNDAY 周日,此处只控制显示，不控制实际使用逻辑，不填默认不显示")
        private String type;
        //              begin_hour	                    否	int                 当前type类型下的起始时间（小时），如当前结构体内填写了MONDAY，此处填写了10，则此处表示周一 10:00可用
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "当前type类型下的起始时间（小时），如当前结构体内填写了MONDAY，此处填写了10，则此处表示周一 10:00可用")
        private Integer beginHour;
        //              begin_minute	                否	int                 当前type类型下的起始时间（分钟），如当前结构体内填写了MONDAY，begin_hour填写10，此处填写了59，则此处表示周一 10:59可用
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "当前type类型下的起始时间（分钟），如当前结构体内填写了MONDAY，begin_hour填写10，此处填写了59，则此处表示周一 10:59可用")
        private Integer beginMinute;
        //              end_hour	                    否	int                 当前type类型下的结束时间（小时），如当前结构体内填写了MONDAY，此处填写了20，则此处表示周一 10:00-20:00可用
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "当前type类型下的结束时间（小时），如当前结构体内填写了MONDAY，此处填写了20，则此处表示周一 10:00-20:00可用")
        private Integer endHour;
        //              end_minute	                    否	int                 当前type类型下的结束时间（分钟），如当前结构体内填写了MONDAY，begin_hour填写10，此处填写了59，则此处表示周一 10:59-00:59可用
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "当前type类型下的结束时间（分钟），如当前结构体内填写了MONDAY，begin_hour填写10，此处填写了59，则此处表示周一 10:59-00:59可用")
        private Integer endMinute;

    }

    @Data
    class UseCodition {
        //              accept_category	                否	string（512）       指定可用的商品类目，仅用于代金券类型，填入后将在券面拼写适用于xxx
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "指定可用的商品类目，仅用于代金券类型，填入后将在券面拼写适用于xxx")
        private String acceptCategory;
        //              reject_category	                否	string（512）       指定不可用的商品类目，仅用于代金券类型，填入后将在券面拼写不适用于xxxx
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "指定不可用的商品类目，仅用于代金券类型，填入后将在券面拼写不适用于xxxx")
        private String rejectCategory;
        //              least_cost	                    否	int                 满减门槛字段，可用于兑换券和代金券，填入后将在全面拼写消费满xx元可用。
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "满减门槛字段，可用于兑换券和代金券，填入后将在全面拼写消费满xx元可用。")
        private String leastCost;
        //              object_use_for	                否	string（512）       购买xx可用类型门槛，仅用于兑换，填入后自动拼写购买xxx可用。
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "购买xx可用类型门槛，仅用于兑换，填入后自动拼写购买xxx可用。")
        private String objectUseFor;
        //              can_use_with_other_discount	    否	bool                不可以与其他类型共享门槛，填写false时系统将在使用须知里 拼写“不可与其他优惠共享”，填写true时系统将在使用须知里 拼写“可与其他优惠共享”，默认为true
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "不可以与其他类型共享门槛，填写false时系统将在使用须知里 拼写“不可与其他优惠共享”，填写true时系统将在使用须知里 拼写“可与其他优惠共享”，默认为true")
        private Boolean canUseWithOtherDiscount;

    }

    /**
     * 会员卡请求参数
     */
    @Data
    class MemberCardReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.OBJECT, order = 2, description = "公众号信息")
        private HeadData headData;
        @Protocol(fieldType = FieldType.OBJECT, order = 3, description = "卡券基本信息")
        private BaseInfo baseInfo;
        @Protocol(fieldType = FieldType.OBJECT, order = 4, description = "卡券高级信息")
        private AdvancedInfo advancedInfo;

//        card_type            	    是            	string(24)            	会员卡类型。

//        background_pic_url        否            	string(128)             商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下
//        prerogative            	是              string（3072）          会员卡特权说明。
//        auto_activate            	否            	bool            	    设置为true时用户领取会员卡后系统自动将其激活，无需调用激活接口，详情见自动激活。
//        wx_activate            	否            	bool            	    设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。
//        supply_bonus            	是            	bool            	    显示积分，填写true或false，如填写true，积分相关字段均为必填。
//        bonus_url            	    否            	string(128)            	设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段。
//        supply_balance            是            	bool            	    是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
//        balance_url            	否            	string(128)            	设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。
//        discount            	        否            	int            	折扣，该会员卡享受的折扣优惠,填10就是九折。

//        custom_field1            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示,包含name_type(name)和url字段
//        custom_field2            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示，包含name_type(name)和url字段
//        custom_field3            	否            	JSON结构            	自定义会员信息类目，会员卡激活后显示，包含name_type(name)和url字段

//        bonus_cleared            	    否            	string（128）            	积分清零规则。
//        bonus_rules            	    否            	string（128）            	积分规则。
//        balance_rules            	    否            	string（128）            	储值说明。
//        activate_url            	    是            	string（128）            	激活会员卡的url。

//        custom_cell1            	    否            	JSON结构            	自定义会员信息类目，会员卡激活后显示。           	            是            	string（128）            	入口跳转链接。

//        bonus_rule               	    否            	JSON结构      	积分规则。
//        cost_money_unit            	否            	int            	消费金额。以分为单位。
//        increase_bonus            	否            	int            	对应增加的积分。
//        max_increase_bonus            否            	int            	用户单次可获取的积分上限。
//        init_increase_bonus           否            	int            	初始设置积分。
//        cost_bonus_unit            	否            	int            	每使用5积分。
//        reduce_money            	    否            	int            	抵扣xx元，（这里以分为单位）
//        least_money_to_use_bonus      否            	int            	抵扣条件，满xx元（这里以分为单位）可用。
//        max_reduce_bonus              否            	int            	抵扣条件，单笔最多使用xx积分。






    }
}
