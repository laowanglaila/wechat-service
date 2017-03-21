package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatQrcodeModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface WechatQrcodeMapper {

	int insert(WechatQrcodeModel model);

	List<WechatQrcodeModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

}
