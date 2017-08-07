package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.model.card.MemberMsgModel;
import com.hualala.app.wechat.model.card.MemberMsgModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberMsgModelMapper {
    int countByExample(MemberMsgModelQuery example);

    int deleteByExample(MemberMsgModelQuery example);

    int deleteByPrimaryKey(Long itemID);

    int insert(MemberMsgModel record);

    int insertSelective(MemberMsgModel record);

    List<MemberMsgModel> selectByExample(MemberMsgModelQuery example);

    MemberMsgModel selectByPrimaryKey(Long itemID);

    int updateByExampleSelective(@Param("record") MemberMsgModel record, @Param("example") MemberMsgModelQuery example);
    int updateByCondition(MemberMsgModel record);

    int updateByExample(@Param("record") MemberMsgModel record, @Param("example") MemberMsgModelQuery example);

    int updateByPrimaryKeySelective(MemberMsgModel record);

    int updateByPrimaryKey(MemberMsgModel record);
}