package com.hualala.app.wechat.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.RequestUtil;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/8/17.
 */
@Service
public class UserGetUserInfoRpcServiceImpl implements UserGetUserInfoRpcService{
    @Autowired
    private BaseHttpService baseHttpService;
    @Override
    public UserInfoResData getUserInfoByOpenID(UserInfoReqData userInfoReqData) {
        String mpID = RequestUtil.getMpID(userInfoReqData);
        String openID = userInfoReqData.getOpenID();
        LangTypeEnum langType = userInfoReqData.getLangType();
        if (StringUtils.isBlank(openID)){
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"openID不能为空");
        }
        if (langType == null){
            langType = LangTypeEnum.zh_CN;
        }
        String param = "openid="+openID+"&lang=" + langType.name();
        JSONObject wechatAPIUserInfo = baseHttpService.getWechatAPIUserInfo(param, mpID);
        UserInfoResData resultInfoBean = ResultUtil.getResultInfoBean(wechatAPIUserInfo, UserInfoResData.class);
        resultInfoBean.setWechatGroupID(wechatAPIUserInfo.getString("groupid"));
        return resultInfoBean;
    }
}
