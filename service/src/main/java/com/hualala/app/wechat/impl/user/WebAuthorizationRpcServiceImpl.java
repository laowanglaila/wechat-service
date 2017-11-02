package com.hualala.app.wechat.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.WebAuthorizationRpcService;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.exception.WechatException;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/8/11.
 */
@Service
public class WebAuthorizationRpcServiceImpl implements WebAuthorizationRpcService {

    @Autowired
    private BaseHttpService baseHttpService;

    @Override
    public AuthorizationRes snsAuthorization(AuthorizationReq authorizationReq) {
        return null;
    }

    @Override
    public WechatUserInfoRes getWechatUserInfo(WechatUserInfoReq wechatUserInfoReq) {
        Long groupID = wechatUserInfoReq.getGroupID();
        String accessToken = wechatUserInfoReq.getAccessToken();
        LangTypeEnum lang = wechatUserInfoReq.getLang();
        String openid = wechatUserInfoReq.getOpenid();
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openid) || lang == null ){
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"openID:["+openid+"],lang:["+lang+"],accessTocken:["+accessToken+"]");
        }
        JSONObject wechatUserInfo = baseHttpService.getWechatUserInfo(accessToken,openid,lang);
        //TODO 存入数据库 或者 返回结果
        WechatUserInfoRes resultInfoBean = ResultUtil.getResultInfoBean(wechatUserInfo, WechatUserInfoRes.class);
        if ("000".equals(resultInfoBean.getCode())) {
            resultInfoBean.setHeadImgUrl(wechatUserInfo.getString("headimgurl"));
            resultInfoBean.setNickName(wechatUserInfo.getString("nickname"));
            resultInfoBean.setOpenID(wechatUserInfo.getString("openid"));
            resultInfoBean.setUnionID(wechatUserInfo.getString("unionid"));
        }
        return resultInfoBean;
    }
}
