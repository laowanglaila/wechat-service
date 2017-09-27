package com.hualala.app.wechat.mapper.user;

import com.hualala.app.wechat.model.user.UserRelationModel;
import com.hualala.app.wechat.model.user.UserRelationModelQuery;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
@Mapper
public interface UserRelationModelMapper {
    @SelectProvider(type=UserRelationModelSqlProvider.class, method="countByExample")
    int countByExample(UserRelationModelQuery example);

    @DeleteProvider(type=UserRelationModelSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserRelationModelQuery example);

    @Delete({
        "delete from tbl_shop_wechat_user_relation",
        "where itemID = #{itemID,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long itemID);

    @Insert({
        "insert into tbl_shop_wechat_user_relation (itemID, userID, ",
        "mpID, openid, subscribe)",
        "values (#{itemID,jdbcType=BIGINT}, #{userID,jdbcType=BIGINT}, ",
        "#{mpID,jdbcType=VARCHAR}, #{openid,jdbcType=VARCHAR}, #{subscribe,jdbcType=TINYINT})"
    })
    int insert(UserRelationModel record);

    @InsertProvider(type=UserRelationModelSqlProvider.class, method="insertSelective")
    int insertSelective(UserRelationModel record);

    @SelectProvider(type=UserRelationModelSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="itemID", property="itemID", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="userID", property="userID", jdbcType=JdbcType.BIGINT),
        @Result(column="mpID", property="mpID", jdbcType=JdbcType.VARCHAR),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="subscribe", property="subscribe", jdbcType=JdbcType.TINYINT)
    })
    List<UserRelationModel> selectByExample(UserRelationModelQuery example);

    @Select({
        "select",
        "itemID, userID, mpID, openid, subscribe",
        "from tbl_shop_wechat_user_relation",
        "where itemID = #{itemID,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="itemID", property="itemID", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="userID", property="userID", jdbcType=JdbcType.BIGINT),
        @Result(column="mpID", property="mpID", jdbcType=JdbcType.VARCHAR),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="subscribe", property="subscribe", jdbcType=JdbcType.TINYINT)
    })
    UserRelationModel selectByPrimaryKey(Long itemID);

    @UpdateProvider(type=UserRelationModelSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserRelationModel record, @Param("example") UserRelationModelQuery example);

    @UpdateProvider(type=UserRelationModelSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserRelationModel record, @Param("example") UserRelationModelQuery example);

    @UpdateProvider(type=UserRelationModelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRelationModel record);

    @Update({
        "update tbl_shop_wechat_user_relation",
        "set userID = #{userID,jdbcType=BIGINT},",
          "mpID = #{mpID,jdbcType=VARCHAR},",
          "openid = #{openid,jdbcType=VARCHAR},",
          "subscribe = #{subscribe,jdbcType=TINYINT}",
        "where itemID = #{itemID,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserRelationModel record);
}