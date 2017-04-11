package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.MemberInfoRpcService;
import com.hualala.app.wechat.service.MemberInfoService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatNameConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/10.
 * 获取会员信息实现类
 */

@Service
public class MemberInfoRpcServiceImpl implements MemberInfoRpcService{
    @Autowired
    private MemberInfoService memberInfoService;

    @Override
    public MemberInfoResData queryMemberInfo(MemberInfoQueryReqData reqData) throws Exception {
        String appID = reqData.getAppID();
        String appSecret = reqData.getAppSecret();
        String cardId = reqData.getCardId();
        String code = reqData.getCode();
        HashMap<String, Object> params = new HashMap<>();
        params.put("cardId",cardId);
        params.put("code",code);
        Map<String, Object> map = WechatNameConverterUtil.convertToDBStyle(params);
        memberInfoService.setAppId(appID);
        memberInfoService.setAppSecret(appSecret);
        JSONObject jsonObject = memberInfoService.visitWeChat(map);
        return ResultUtil.getResultInfoBean(jsonObject,MemberInfoResData.class);
    }
}
