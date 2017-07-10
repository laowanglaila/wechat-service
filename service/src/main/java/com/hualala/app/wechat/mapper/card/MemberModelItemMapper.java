package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.model.card.MemberModelItem;
import com.hualala.app.wechat.model.card.MemberModelItemQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberModelItemMapper {
    int countByExample(MemberModelItemQuery example);

    int deleteByExample(MemberModelItemQuery example);

    int deleteByPrimaryKey(Long itemID);

    int insert(MemberModelItem record);

    int insertSelective(MemberModelItem record);

    List<MemberModelItem> selectByExample(MemberModelItemQuery example);

    MemberModelItem selectByPrimaryKey(Long itemID);

    int updateByExampleSelective(@Param("record") MemberModelItem record, @Param("example") MemberModelItemQuery example);

    int updateByExample(@Param("record") MemberModelItem record, @Param("example") MemberModelItemQuery example);

    int updateByPrimaryKeySelective(MemberModelItem record);

    int updateByPrimaryKey(MemberModelItem record);
}