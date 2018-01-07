package com.hualala.app.wechat.impl.EventHandler.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.impl.EventHandler.AbstractCardEventHandler;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.api.WxMpMemberCardService;
import com.hualala.app.wechat.sdk.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 激活卡券事件，开卡，告知会员用户信息
 *
 * Created by renjianfei on 2017/12/15.
 */
@Slf4j
@Component("activeMembercardHandler")
public class ActiveMembercardUserInfoEventHandler extends AbstractCardEventHandler {
    @Autowired
    private WxGroupMpService wxGroupMpService;

    @Override
    public Object handler(JSONObject jsonObject) {
        Long cardKey = null;
        String cardId = null;
        String userCardCode = null;
        BaseInfoModel baseInfoModel = null;
        if (jsonObject.containsKey( "CardId" )) {
            cardId = jsonObject.getString( "CardId" );
            baseInfoModel = getBaseInfoModel( cardId );
        }
        if(jsonObject.containsKey( "UserCardCode" )){
            userCardCode = jsonObject.getString( "UserCardCode" );
        }
        String fromUserName = jsonObject.getString( "FromUserName" );
        String mpID = baseInfoModel.getMpID();
        WxMpMemberCardService memberCardService = wxGroupMpService.getMemberCardService( mpID );
        WxMpMemberCardUserInfoResult userInfo = null;
        try {
            userInfo = memberCardService.getUserInfo( cardId, userCardCode );
        } catch (WxErrorException e) {
            log.error( "获取会员激活信息异常："+e.getError().toString(),e );
            throw new WechatException();
        }
        VoucherCardRes voucherCardRes = super.processCardNotBeenExist(userInfo, fromUserName, userCardCode, baseInfoModel );
        return voucherCardRes;
    }


}
