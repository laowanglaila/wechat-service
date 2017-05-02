package com.hualala.app.wechat.service;

import com.hualala.app.wechat.mapper.WechatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 微信用户
 * Created by xkia on 2017/4/10.
 */
@Service
public class WechatUserService {

    @Autowired
    WechatUserMapper wechatUserMapper;

    public String queryOpenID(Map<String,Object> param){

        String mpID = String.valueOf(param.get("mpID"));
        long userID = (long) param.get("userID");
        Integer isSubscribe = (Integer) param.get("isSubscribe");
        String openID = String.valueOf(param.get("openID"));

        if(!StringUtils.isEmpty(openID)) {
            int row  = wechatUserMapper.queryCheckOpenID(mpID,openID,isSubscribe);
            if(row > 0) {
                return openID;
            }
        }
        openID = wechatUserMapper.queryOpenID(mpID,userID,isSubscribe);
        return openID;
    }
}
