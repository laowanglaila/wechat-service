package com.hualala.app.wechat.util.template;

/**
 * 
 * 微信模板工厂接口
 *
 */
public interface WechatTemplateFatory {
	
	/**
	 * 根据模板主类型、子类型、订单类型创建微信模板
	 * @param template
	 * @param modelType
	 * @param modelSubType
	 * @param subType
	 * @return
	 */
	public WechatTemplate newWechatTemplate(String template, String modelType, String modelSubType, String subType);
	
}
