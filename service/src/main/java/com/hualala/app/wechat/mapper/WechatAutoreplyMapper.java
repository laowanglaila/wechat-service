package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatAutoreplyModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface WechatAutoreplyMapper {

	int insert(WechatAutoreplyModel model);

	List<WechatAutoreplyModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

}
