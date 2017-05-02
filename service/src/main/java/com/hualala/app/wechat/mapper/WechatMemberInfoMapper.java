package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatMemberInfoModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface WechatMemberInfoMapper {

	int insert(WechatMemberInfoModel model);

	List<WechatMemberInfoModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

}
