package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatUserModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface WechatUserMapper {

	int insert(WechatUserModel model);

	List<WechatUserModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

}
