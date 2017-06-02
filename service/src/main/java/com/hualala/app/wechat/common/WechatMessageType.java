package com.hualala.app.wechat.common;

public class WechatMessageType {
	
	public static final String TEXT = "text";
	
	public static final String IMAGE = "image";
	
	public static final String LOCATION = "location";
	
	public static final String LINK = "link";
	
	public static final String MUSIC = "music";
	
	public static final String NEWS = "news";
	
	public static final String EVENT = "event";
	
	public static final String EVENT_SUBSCRIBE = "subscribe";
	
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	
	public static final String EVENT_CLICK = "CLICK";
	
	public static final String EVENT_SCAN = "SCAN";
	
	public static final String QR_SCENE = "QR_SCENE";

	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	
	public static final String WECHAT_INVALID = "40001";
	
	public static final String SUCCESS = "success";
	
	public static final String ERROR = "error";
	
	public static final Boolean TRUE = true;
	
	public static final Boolean FALSE = false;
	
	public static final String HUALALA_COM = "hualala_com";

	public static final String CODE = "code4service";

	public static final String MESSAGE = "message4service";
	public static final String WECHAT_ERR_CODE = "errcode";
	public static final String WECHAT_ERR_MESSAGE = "errmsg";

	public static final String IS_SUCCESS = "isSuccess4service";
	public static final String CARD_TYPE_GROUPON = "GROUPON";
	public static final String CARD_TYPE_CASH = "CASH";
	public static final String CARD_TYPE_DISCOUNT = "DISCOUNT";
	public static final String CARD_TYPE_GIFT = "GIFT";
	public static final String CARD_TYPE_GENERAL_COUPON = "GENERAL_COUPON";
	public static final String CARD_TYPE_MEMBER_CARD = "MEMBER_CARD";

	//卡券通过审核
	public static final String EVENT_CARD_PASS_CHECK = "card_pass_check";
	//卡券未通过审核
	public static final String EVENT_CARD_NOT_PASS_CHECK = "card_not_pass_check";
	//用户领取事件
	public static final String EVENT_CARD_USER_GET = "user_get_card";
	//用户转赠事件
	public static final String EVENT_CARD_USER_GIFTING = "user_gifting_card";
	//用户删除事件
	public static final String EVENT_CARD_USER_DEL = "user_del_card";
	//用户核销事件
	public static final String EVENT_CARD_USER_CONSUME = "user_consume_card";
	//用户买单事件
	public static final String EVENT_CARD_USER_PAY_FROM_PAY_CELL = "user_pay_from_pay_cell";
	//用户进入会员卡事件（暂不接受压力大）
	public static final String EVENT_CARD_USER_VIEW = "user_view_card";
	//用户从卡券进入公众号会话事件推送
	public static final String EVENT_CARD_USER_ENTER = "user_enter_session_from_card";
	//会员卡内容更新事件
	public static final String EVENT_CARD_UPDATE_MEMBER = "update_member_card";
	//库存报警事件
	public static final String EVENT_CARD_SKU_REMIND = "card_sku_remind";
	//券点流水详情事件
	public static final String EVENT_CARD_PAY_ORDER = "card_pay_order";
	//会员卡激活事件推送
	public static final String EVENT_CARD_MEMBER_ACTIVE = "submit_membercard_user_info";

}
