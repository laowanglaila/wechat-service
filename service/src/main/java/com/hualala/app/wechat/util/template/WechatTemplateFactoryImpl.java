package com.hualala.app.wechat.util.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * 微信接口工厂模式实现类
 *
 */
public class WechatTemplateFactoryImpl implements WechatTemplateFatory{
	
	private JSONObject defaultJson;
	private String defaultJsonStr;
	
	public String getDefaultJsonStr() {
		return defaultJsonStr;
	}

	public void setDefaultJson(String defaultJsonStr) {
		this.defaultJsonStr = defaultJsonStr;
		this.defaultJson = StringUtils.isEmpty(defaultJsonStr) ? null : JSON.parseObject(defaultJsonStr);
	}

	@Override
	public WechatTemplate newWechatTemplate(String template, String modelType,String modelSubType,String subType) {
		
		WechatTemplateImpl wechatTemplateImpl = new WechatTemplateImpl();
		wechatTemplateImpl.setModelType(modelType);
		wechatTemplateImpl.setModelSubType(modelSubType);
		wechatTemplateImpl.setSubType(subType);

		if(StringUtils.isNotBlank(subType)){
			wechatTemplateImpl.setDefaultJson(defaultJson.getJSONObject(modelType).getJSONObject(modelSubType).getJSONObject(subType));
		}else{
			wechatTemplateImpl.setDefaultJson(defaultJson.getJSONObject(modelType).getJSONObject(modelSubType));
		}
		wechatTemplateImpl.setTemplateJson(StringUtils.isEmpty(template)? null : JSON.parseObject(template));
		return wechatTemplateImpl;
	}

}
