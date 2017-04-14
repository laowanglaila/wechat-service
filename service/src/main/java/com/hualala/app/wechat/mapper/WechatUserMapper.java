package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface WechatUserMapper {

	int insert(WechatUserModel model);

	List<WechatUserModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

	String queryOpenID(@Param("mpID") String mpID, @Param("userID") long userID, @Param("isSubscribe") Integer isSubscribe);

	int queryCheckOpenID(@Param("mpID") String mpID, @Param("openID") String openID, @Param("isSubscribe") Integer isSubscribe);
}
