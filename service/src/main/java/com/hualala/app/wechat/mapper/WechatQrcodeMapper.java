package com.hualala.app.wechat.mapper;

import com.hualala.app.wechat.model.WechatQrcodeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface WechatQrcodeMapper {

	int insert(WechatQrcodeModel model);

	List<WechatQrcodeModel> queryList(Map<String, Object> params);

	List<WechatQrcodeModel> queryQrcode(Map<String, Object> params);

	int queryCount(Map<String, Object> params);

	/**
	 * 删除二维码 根据shopID,qrcodeType 和 shopID
	 *
	 * @param map
	 * @return
	 */
	int delTableQrcoed(Map<String, Object> map);
	/**
	 * 逻辑删除二维码信息
	 *
	 * @param itemID
	 * @return
	 */
	int updateDelQrCode( @Param( "itemID" ) Long itemID);
	/**
	 * 更新二维码信息
	 *
	 * @param map
	 * @return
	 */
	int updateQrCode(WechatQrcodeModel map);

}
