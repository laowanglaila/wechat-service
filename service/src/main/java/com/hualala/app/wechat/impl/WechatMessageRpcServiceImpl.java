package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatMessageRpcService;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.WechatCustomService;
import com.hualala.app.wechat.service.user.WechatUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WechatMessageRpcServiceImpl implements WechatMessageRpcService {

    @Autowired
    private WechatCustomService wechatCustomService;
    @Autowired
    private MpInfoService mpInfoService;
    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public WechatMessageResData wechatMessageSend(WechatMessageReqData reqData) {

        String mpID = reqData.getMpID();
        String openID = reqData.getOpenID();
        if(StringUtils.isEmpty(mpID)) {
            mpID = mpInfoService.queryMpIDAuth(reqData.getGroupID(), reqData.getBrandID(), reqData.getShopID());
        }

        if(mpID == null){
            throw new WechatException( ErrorCodes.WECHAT_MP_ERROR,"未找到对应公众号");
        }

        if(StringUtils.isEmpty(openID)){
            Map<String,Object> param = new HashMap<>();
            param.put("isSubscribe",1);
            param.put("mpID",mpID);
            param.put("userID", reqData.getUserID());
            param.put("openID", "");

            openID = wechatUserService.queryOpenID(param);
        }
        if(StringUtils.isEmpty(openID)) {
            throw new WechatException(ErrorCodes.WECHAT_MP_ERROR,"未找到对应用户，无法推送消息");
        }

        wechatCustomService.messageSendText(mpID,openID,reqData.getContent());

        return new WechatMessageResData();
    }
}
