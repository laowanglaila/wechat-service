package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.model.card.CouponModel;
import com.hualala.app.wechat.model.card.CouponModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CouponModelMapper {
    int countByExample(CouponModelQuery example);

    int deleteByExample(CouponModelQuery example);

    int deleteByPrimaryKey(String cardKey);

    int insert(CouponModel record);

    int insertSelective(CouponModel record);

    List<CouponModel> selectByExample(CouponModelQuery example);

    CouponModel selectByPrimaryKey(String cardKey);

    int updateByExampleSelective(@Param("record") CouponModel record, @Param("example") CouponModelQuery example);

    int updateByExample(@Param("record") CouponModel record, @Param("example") CouponModelQuery example);

    int updateByPrimaryKeySelective(CouponModel record);

    int updateByPrimaryKey(CouponModel record);
}