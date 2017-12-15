package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.sdk.mp.api.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.api.WxMpMemberCardService;
import com.hualala.app.wechat.sdk.mp.bean.membercard.NameValues;
import com.hualala.app.wechat.sdk.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
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
        NameValues nameValues = Stream.of( userInfo.getUserInfo().getCommonFieldList() )
                                      .filter( item -> "USER_FORM_INFO_FLAG_MOBILE".equals( item.getName() ) )
                                      .findAny()
                                      .get();
        String mobile = nameValues.getValue();
        VoucherCardRes voucherCardRes = super.processCardNotBeenExist(mobile, fromUserName, userCardCode, baseInfoModel );
        return voucherCardRes;
    }


}
