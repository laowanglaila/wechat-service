package com.hualala.app.wechat.service.user;

import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.mapper.WechatUserMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信用户
 * Created by xkia on 2017/4/10.
 */
@Slf4j
@Service
public class WechatUserService {

    @Autowired
    WechatUserMapper wechatUserMapper;

    public String queryOpenID(Map<String,Object> param){

        String mpID = String.valueOf(param.get("mpID"));
        long userID = (long) param.get("userID");
        Integer isSubscribe = (Integer) param.get("isSubscribe");
        String openID = String.valueOf(param.get("openID"));

//        if(!StringUtils.isEmpty(openID)) {
//            int row  = wechatUserMapper.queryCheckOpenID(mpID,openID,isSubscribe);
//            if(row > 0) {
//                return openID;
//            }
//            log.info("用户未关注！");
//            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"用户未关注！");
//        }
        if (userID > 2){
            openID = wechatUserMapper.queryOpenID(mpID,userID,null);
        }
        if (StringUtils.isBlank( openID )){
            log.info("用户唯一标识为空，请指定正确的userID或者openID！");
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"用户唯一标识为空，请指定正确的userID或者openID！");
        }
        return openID;
    }
}
