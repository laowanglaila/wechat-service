package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.model.card.AdvancedModel;
import com.hualala.app.wechat.model.card.AdvancedModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AdvancedModelMapper {
    int countByExample(AdvancedModelQuery example);

    int deleteByExample(AdvancedModelQuery example);

    int deleteByPrimaryKey(Long cardKey);

    int insert(AdvancedModel record);

    int insertSelective(AdvancedModel record);

    List<AdvancedModel> selectByExample(AdvancedModelQuery example);

    AdvancedModel selectByPrimaryKey(Long cardKey);

    int updateByExampleSelective(@Param("record") AdvancedModel record, @Param("example") AdvancedModelQuery example);

    int updateByExample(@Param("record") AdvancedModel record, @Param("example") AdvancedModelQuery example);

    int updateByPrimaryKeySelective(AdvancedModel record);

    int updateByPrimaryKey(AdvancedModel record);
}