package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatTemplateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface WechatTemplateMapper {

	int insert(WechatTemplateModel model);

	List<WechatTemplateModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

	WechatTemplateModel queryTemplate(@Param("mpID") String mpID, @Param("modelID") String modelID);
}
