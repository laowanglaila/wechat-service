package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.model.card.MemberModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberModelMapper {
    int countByExample(MemberModelQuery example);

    int deleteByExample(MemberModelQuery example);

    int deleteByPrimaryKey(String cardKey);

    int insert(MemberModel record);

    int insertSelective(MemberModel record);

    List<MemberModel> selectByExample(MemberModelQuery example);

    MemberModel selectByPrimaryKey(String cardKey);

    int updateByExampleSelective(@Param("record") MemberModel record, @Param("example") MemberModelQuery example);

    int updateByExample(@Param("record") MemberModel record, @Param("example") MemberModelQuery example);

    int updateByPrimaryKeySelective(MemberModel record);

    int updateByPrimaryKey(MemberModel record);
}