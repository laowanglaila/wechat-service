package com.hualala.app.wechat.util.template;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

public class WechatTemplateImpl implements WechatTemplate{
	
	private String modelType;
	private String modelSubType;
	private JSONObject defaultJson;
	private JSONObject templateJson;
	private String subType;

	@Override
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	@Override
	public void setModelSubType(String modelSubType) {
		this.modelSubType = modelSubType;
	}

	@Override
	public void setSubType(String subType) {
		this.subType = subType;
	}

	@Override
	public void setParam(String key, String value) {
		if(this.templateJson==null || templateJson.isEmpty()){
			if(defaultJson !=null && !defaultJson.isEmpty())
				templateJson = defaultJson;
			else
				this.templateJson = new JSONObject();
		}
		this.templateJson.put(key, value);
	}

	@Override
	public String getModelType() {
		return this.modelType;
	}

	@Override
	public String getModelSubType() {
		return this.modelSubType;
	}

	@Override
	public String getSubType() {
		return this.subType;
	}
	
	@Override
	public JSONObject getDefaultJson() {
		return defaultJson;
	}

	public void setDefaultJson(JSONObject defaultJson) {
		this.defaultJson = defaultJson;
	}
	
	@Override
	public JSONObject getTemplateJson() {
		if(templateJson==null || templateJson.isEmpty()){
			if(defaultJson!=null && !defaultJson.isEmpty()){
				return defaultJson;
			}else{
				return new JSONObject();
			}
		}
		return templateJson;
	}
	
	@Override
	public void setTemplateJson(JSONObject templateJson) {
		this.templateJson = templateJson;
	}

	@Override
	public String getParam(String key) {
		if(key == null || StringUtils.isEmpty(key)){
			return null;
		}
		if(templateJson == null || templateJson.isEmpty() ){
			if(defaultJson!=null && !defaultJson.isEmpty()){
				templateJson = defaultJson;
			}else{
				return null;
			}
		}
		return templateJson.getString(key);
	}
}
