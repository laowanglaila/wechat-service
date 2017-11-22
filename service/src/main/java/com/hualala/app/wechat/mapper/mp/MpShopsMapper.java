package com.hualala.app.wechat.mapper.mp;

import com.hualala.app.wechat.model.mp.MpShopsModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by renjianfei on 2017/9/19.
 */
@Mapper
public interface MpShopsMapper {

    List<String> select(@Param( "groupID" ) long groupID,@Param( "shopID" ) long shopID);
    int delete(MpShopsModel model);
    int insert(MpShopsModel model);
    int count(MpShopsModel model);

}
