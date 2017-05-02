package com.hualala.app.wechat.util.template;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 微信模板参数接口
 *
 */
public interface WechatTemplate {
	
	/**
	 * 设置模板主类型
	 * @param modelType
	 */
	public void setModelType(String modelType);
	
	/**
	 * 设置模板子类型
	 * @param modelSubType
	 */
	public void setModelSubType(String modelSubType);
	
	/**
	 * 设置模板
	 * @param subType
	 */
	public void setSubType(String subType);
	
	/**
	 * 设置固定参数值
	 * @param key
	 * @param value
	 */
	public void setParam(String key, String value);
	
	/**
	 * 设置模板实例
	 * @param templateJson
	 */
	public void setTemplateJson(JSONObject templateJson);
	
	
	/**
	 * 获取模板主类型
	 * @return modelType
	 */
	public String getModelType();
	
	/**
	 * 获取模板子类型
	 * @return
	 */
	public String getModelSubType();
	
	/**
	 * 获取订单子类型
	 * @return
	 */
	public String getSubType();
	
	/**
	 * 获取模板实例内容
	 * @return
	 */
	public JSONObject getTemplateJson();
	
	/**
	 * 获取默认模板内容
	 * @return
	 */
	public JSONObject getDefaultJson();
	
	/**
	 * 获取模板参数值
	 * @return
	 */
	public String getParam(String key);
}
