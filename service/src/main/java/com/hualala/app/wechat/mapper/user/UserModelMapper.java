package com.hualala.app.wechat.mapper.user;

import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserModelMapper {
    int countByExample(UserModelQuery example);

    int deleteByExample(UserModelQuery example);

    int deleteByPrimaryKey(Long itemID);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    List<UserModel> selectByExample(UserModelQuery example);
    List<String> selectByUserList(UserModelQuery example);

    UserModel selectByPrimaryKey(Long itemID);

    int updateByExampleSelective(@Param("record") UserModel record, @Param("example") UserModelQuery example);

    int updateByExample(@Param("record") UserModel record, @Param("example") UserModelQuery example);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}