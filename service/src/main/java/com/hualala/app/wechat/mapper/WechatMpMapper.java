package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatMpModel;
import com.hualala.app.wechat.model.mp.MpInfoCache;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface WechatMpMapper {

	int insert(WechatMpModel model);

	List<WechatMpModel> queryList(Map<String, Object> params);

	List<Map<String,Object >> queryByParams(Map<String,Object> params);
	MpInfoCache queryByMpID(@Param( "mpID" )String mpID);

	int queryCount(Map<String, Object> params);

	/**
	 * 获取巨头api权限的公众号 shopID暂时保留，
	 * @param groupID
	 * @param brandID
	 * @param shopID
	 * @return
	 */
	String queryMpIDAuth(@Param("groupID") long groupID, @Param("brandID") long brandID, @Param("shopID") long shopID);
}
