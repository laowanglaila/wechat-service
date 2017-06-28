package com.hualala.app.wechat.mapper.card;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseInfoModelMapperEXT {

    List<String> giveOutCardId(Map<String, Object> map);

}