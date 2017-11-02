package com.hualala.app.wechat.mapper.sem;

import com.hualala.app.wechat.model.sem.TemplateMessageModel;
import com.hualala.app.wechat.model.sem.TemplateMessageModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TemplateMessageModelMapper {
    int countByExample(TemplateMessageModelQuery example);

    int deleteByExample(TemplateMessageModelQuery example);

    int deleteByPrimaryKey(Long itemID);

    int insert(TemplateMessageModel record);

    int insertSelective(TemplateMessageModel record);

    List<TemplateMessageModel> selectByExample(TemplateMessageModelQuery example);

    TemplateMessageModel selectByPrimaryKey(Long itemID);

    int updateByExampleSelective(@Param("record") TemplateMessageModel record, @Param("example") TemplateMessageModelQuery example);

    int updateByExample(@Param("record") TemplateMessageModel record, @Param("example") TemplateMessageModelQuery example);

    int updateByPrimaryKeySelective(TemplateMessageModel record);

    int updateByPrimaryKey(TemplateMessageModel record);
}