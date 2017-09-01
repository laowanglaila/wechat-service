package com.hualala.app.wechat.mapper.mp;

import com.hualala.app.wechat.model.mp.MpInfoModel;
import com.hualala.app.wechat.model.mp.MpInfoModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MpInfoModelMapper {
    int countByExample(MpInfoModelQuery example);

    int deleteByExample(MpInfoModelQuery example);

    int deleteByPrimaryKey(Long itemID);

    int insert(MpInfoModel record);

    int insertSelective(MpInfoModel record);

    List<MpInfoModel> selectByExample(MpInfoModelQuery example);

    MpInfoModel selectByPrimaryKey(Long itemID);

    int updateByExampleSelective(@Param("record") MpInfoModel record, @Param("example") MpInfoModelQuery example);

    int updateByExample(@Param("record") MpInfoModel record, @Param("example") MpInfoModelQuery example);

    int updateByPrimaryKeySelective(MpInfoModel record);

    int updateByPrimaryKey(MpInfoModel record);
}