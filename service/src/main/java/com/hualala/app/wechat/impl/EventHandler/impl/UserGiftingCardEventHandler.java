package com.hualala.app.wechat.impl.EventHandler.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.gift.CrmGiftRequest;
import com.hualala.app.crm.bean.gift.CrmGiftResponse;
import com.hualala.app.crm.service.CrmGiftService;
import com.hualala.app.wechat.impl.EventHandler.AbstractCardEventHandler;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 转赠事件，告知会员修改状态
 *
 * Created by renjianfei on 2018/1/3.
 */
@Slf4j
@Component
public class UserGiftingCardEventHandler extends AbstractCardEventHandler {
    @Override
    public Object handler(JSONObject jsonObject) {
        String cardId = jsonObject.getString( "CardId" );
        String userCardCode = jsonObject.getString( "UserCardCode" );
        String FromUserName = jsonObject.getString( "FromUserName" );//接收卡券用户的openid
        String friendUserName = jsonObject.getString( "FriendUserName" );//接收卡券用户的openid
        Integer isReturnBack = jsonObject.getInteger( "IsReturnBack" );//是否转赠退回，0代表不是，1代表是。
        Integer isChatRoom = jsonObject.getInteger( "IsChatRoom" );//是否是群转赠，0代表不是，1代表是。
        BaseInfoModel baseInfoModel = this.getBaseInfoModel( cardId );
        String cardType = baseInfoModel.getCardType();
        if ("MEMBER_CARD".equals( cardType )){
            log.info( "会员卡转赠暂不支持！" );
            return null;
        }
        CrmGiftService rpcClient = super.baseRpcClient.getRpcClient( CrmGiftService.class );
        CrmGiftRequest crmGiftRequest = new CrmGiftRequest();
        crmGiftRequest.setGroupID( baseInfoModel.getGroupID() );
        crmGiftRequest.setMpID( baseInfoModel.getMpID() );
        crmGiftRequest.setWechatCardCode( userCardCode );
        crmGiftRequest.setSourceOpenID( FromUserName );
        crmGiftRequest.setTargetOpenID( friendUserName );
        crmGiftRequest.setSourceWay( true );
        crmGiftRequest.setSourceType( 30 );
        CrmGiftResponse crmGiftResponse;
        if (isReturnBack == 0){
            crmGiftRequest.setGiftStatus( 100 );
            crmGiftResponse = rpcClient.updateGiftStatusForTrd( crmGiftRequest );
        }else{
            crmGiftRequest.setGiftStatus( 1 );
            crmGiftResponse = rpcClient.updateGiftStatusForTrd( crmGiftRequest );
        }
        return crmGiftResponse;
    }
}
