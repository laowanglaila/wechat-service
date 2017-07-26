package com.hualala.app.wechat.util.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 微信模板常量类
 *
 */
public class WechatTemplateConstants {
	
	public static final String ORDER_TYPE = "order";
	
	public static final String CRM_TYPE = "crm";
	
	public static final String GIFT_TYPE = "gift";
	
	public static final String QUEUE_TYPE = "queue";
	// 顾客评价回复
	public static final String ASSESSMENT_TYPE = "assessment";

	public static final String INVOICE_TYPE = "invoice";

	public static final String SUBMIT_SUBTYPE = "submit";
	
	public static final String CHECK_SUBTYPE = "check";
	
	public static final String RETURN_SUBTYPE = "return";
	
	public static final String REFUND_SUBTYPE = "refund";
	
	public static final String ALARM_SUBTYPE = "alarm";
	
	public static final String TRANS_SUBTYPE = "trans";
	
	// 积分领取通知
	public static final String GIFT_ALARM_CRMPOINT = "crmPoint";
	// 会员储值领取通知
	public static final String GIFT_ALARM_CRMMONEY = "crmMoney";
	// 代金券领取通知
	public static final String GIFT_ALARM_GIFT = "giftAlarm";
	// 会员权益包领取通知
	public static final String GIFT_ALARM_CRMCUSTOMER = "crmCustomer";
	// 会员推荐奖励提醒
	public static final String GIFT_ALARM_CRMBRANCH = "crmBranch";
	// 会员权益包变动提醒
	public static final String GIFT_CRMCUSTOMER_CHANGE = "crmCustomerChange";
	// 会员积分清零
	public static final String GIFT_POINT_INIT = "crmPointInit";
	// 顾客评价回复
	public static final String ASSESSMENT_ALARM_REPLY = "asReply";
	// 微信模板编号
	// 会员状态变动通知
	public static final String WECHAT_MODELID = "OPENTM204805905";
	// 会员注册成功通知
	public static final String CRM_REG_MODELID = "OPENTM201565167";
	// 会员更换手机号通知
	public static final String CRM_REPLACE_MODELID = "OPENTM201500859";
	// 交易提醒
	public static final String CRM_TRANS_MODELID = "OPENTM203199409";
	// 代金券到期提醒
	public static final String GIFT_ALARM_MODELID = "OPENTM204739104";
	// 代金券领取提醒
	public static final String GIFT_GET_ALARM_MODELID = "OPENTM202425363";
	// 排队到号提醒
	public static final String QUEUE_ALARM_MODELID = "OPENTM200568893";
	// 礼品领取通知
	public static final String GIFT_ALARM_GIFT_MODELID = "OPENTM203067265";
	// 会员权益通知
	public static final String GIFT_ALARM_CRMCUSTOMER_MODELID = "OPENTM207508788";
	// 顾客评价回复提醒
	public static final String ASSESSMENT_ALARM_REPLY_MODELID = "TM405716624";
	// 电子发票开票结果提醒
	public static final String INVOICE_ALARM_MODELID = "OPENTM408389786";
    // 订单状态提醒
	public static final String ORDER_MODELID = "OPENTM200833782";
	// 预订结果通知
	public static final String ORDER_BOOK_MODELID = "OPENTM411367320";

	public static String DLD_PLACEHOLDER_PREFIX = "[";
	public static String DLD_PLACEHOLDER_SUFFIX = "]";
	public static String DLD_DEFAULT = ":";
	// 微信模板ID和对应模板title
	public static Map<String, String> MODELID_TITLE_MAP = new HashMap<>();
	public static Map<String, String> MODEL_TYPE_MODELID_MAP = new HashMap<>();

	static {

		MODELID_TITLE_MAP.put("OPENTM200568893","排队到号提醒");
		MODELID_TITLE_MAP.put("OPENTM200833782","订单状态提醒");
		MODELID_TITLE_MAP.put("OPENTM411367320","预订结果通知");

		MODEL_TYPE_MODELID_MAP.put("queue_alarm",QUEUE_ALARM_MODELID);
		MODEL_TYPE_MODELID_MAP.put("order_default",ORDER_MODELID);
		MODEL_TYPE_MODELID_MAP.put("order_book",ORDER_BOOK_MODELID);

	}
}
