package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BaseInfoModelMapper {
    int countByExample(BaseInfoModelQuery example);

    int deleteByExample(BaseInfoModelQuery example);

    int deleteByPrimaryKey(Long cardKey);

    int insert(BaseInfoModel record);

    int insertSelective(BaseInfoModel record);

    List<BaseInfoModel> selectByExample(BaseInfoModelQuery example);

    BaseInfoModel selectByPrimaryKey(Long cardKey);

    int updateByExampleSelective(@Param("record") BaseInfoModel record, @Param("example") BaseInfoModelQuery example);

    int updateByExample(@Param("record") BaseInfoModel record, @Param("example") BaseInfoModelQuery example);

    int updateByPrimaryKeySelective(BaseInfoModel record);

    int updateByPrimaryKey(BaseInfoModel record);
}