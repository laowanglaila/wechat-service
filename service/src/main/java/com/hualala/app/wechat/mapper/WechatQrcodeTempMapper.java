package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatQrcodeTempModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface WechatQrcodeTempMapper {

	int insert(WechatQrcodeTempModel model);

	List<WechatQrcodeTempModel> queryList(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

	int queryMaxSenceID(String mpID);

	List<WechatQrcodeTempModel> queryCacheQrcode(Map<String, Object> params);

	int updateByPrimaryKey(WechatQrcodeTempModel wechatQrcodeTempModel);
}
