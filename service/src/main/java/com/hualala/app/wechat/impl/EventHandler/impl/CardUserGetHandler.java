package com.hualala.app.wechat.impl.EventHandler.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.card.VoucherCardReq;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.crm.bean.cardChannel.CardChannelReq;
import com.hualala.app.crm.bean.gift.CrmGiftRequest;
import com.hualala.app.crm.bean.gift.CrmGiftResponse;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelReq;
import com.hualala.app.crm.service.CardChannelService;
import com.hualala.app.crm.service.CardInfoService;
import com.hualala.app.crm.service.CrmGiftService;
import com.hualala.app.crm.service.GiftDetailChannelService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.impl.EventHandler.AbstractCardEventHandler;
import com.hualala.app.wechat.impl.EventHandler.bean.OuterStr;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.client.BaseRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 领取卡券事件，判断 是会员卡，是优惠券，判断是否需要激活，判断是否正向投放回调
 * 作出不同动作
 *
 * Created by renjianfei on 2017/9/8.
 *
 */
@Slf4j
@Component("cardUserGetHandler")
public class CardUserGetHandler extends AbstractCardEventHandler {
    @Autowired
    private MemberModelMapper memberModelMapper;



    /**
     * 领取卡券事件处理器
     *
     * @param jsonObject
     */
    @Override
    public Object handler(JSONObject jsonObject) {
        Long cardKey = null;
        String cardId = null;
        String cardType = null;
        String fromUserName = jsonObject.getString( "FromUserName" );
        String isGiveByFriend = jsonObject.getString( "IsGiveByFriend" );
        BaseInfoModel baseInfoModel = null;
        if (jsonObject.containsKey( "CardId" )) {
            cardId = jsonObject.getString( "CardId" );
            baseInfoModel = getBaseInfoModel( cardId );
        }
        String userCardCode = null;
        if (jsonObject.containsKey( "UserCardCode" )) {
            userCardCode = jsonObject.getString( "UserCardCode" );
        }
        if (baseInfoModel == null || baseInfoModel.getHualalaCardID() == 0L) {
            log.info( "卡券信息不存在，或者还没有绑定到哗啦啦卡券！" );
            return null;
        }
        cardKey = baseInfoModel.getCardKey();
        cardType = baseInfoModel.getCardType();
        ResultInfo resultInfo;
        if ("MEMBER_CARD".equals( cardType )) {
            MemberModel memberModel = memberModelMapper.selectByPrimaryKey( cardKey );
            if (memberModel != null && !memberModel.getAutoActivate()){
                log.info( "卡券需要激活，领取事件不做处理" );
                return null;
            }
            if (!jsonObject.containsKey( "OuterStr" )) {
                if (log.isInfoEnabled())
                    log.info( "微信渠道-会员卡领取事件：{}", jsonObject );
                return super.processCardNotBeenExist(fromUserName,userCardCode,baseInfoModel);
            }
            OuterStr outStr = getOutStr( jsonObject);
            checkArguments( userCardCode, cardKey, cardId, cardType, outStr );
            CardChannelService rpcClient = baseRpcClient.getRpcClient( CardChannelService.class );
            CardChannelReq cardChannelReq = new CardChannelReq();
            cardChannelReq.setWechatCardKey( cardKey );
            cardChannelReq.setWechatCardCode( userCardCode );
            cardChannelReq.setGroupID( outStr.getGroupID() );
            cardChannelReq.setCardID( outStr.getHualalaCardCode() );
            resultInfo = rpcClient.addCardChannel( cardChannelReq );
        } else {
            if (!jsonObject.containsKey( "OuterStr" )) {
                if (log.isInfoEnabled())
                    log.info( "微信渠道-优惠券领取事件：{}", jsonObject );
                CrmGiftResponse giftFromTrd;
                if ("1".equals( isGiveByFriend )){
                    giftFromTrd = giveToFriend(jsonObject,baseInfoModel);
                }else {
                    giftFromTrd = sendCoupon( fromUserName, baseInfoModel, userCardCode );
                }
                return giftFromTrd;
            }
            OuterStr outStr = getOutStr( jsonObject);
            checkArguments( userCardCode, cardKey, cardId, cardType, outStr );
            GiftDetailChannelService rpcClient = baseRpcClient.getRpcClient( GiftDetailChannelService.class );
            GiftDetailChannelReq giftDetailChannelReq = new GiftDetailChannelReq();
            giftDetailChannelReq.setCustomerGiftDetailID( outStr.getHualalaCardCode() );
            giftDetailChannelReq.setGroupID( outStr.getGroupID() );
            giftDetailChannelReq.setCustomerID( outStr.getCustomerID() );
            giftDetailChannelReq.setWechatCardCode( userCardCode );
            giftDetailChannelReq.setWechatCardKey( cardKey.toString() );
            resultInfo = rpcClient.addGiftDetailChannel( giftDetailChannelReq );
        }
        return resultInfo;
    }

    private CrmGiftResponse giveToFriend(JSONObject jsonObject, BaseInfoModel baseInfoModel) {
        String targetOpenID = jsonObject.getString( "FromUserName" );
        String sourceOpenID = jsonObject.getString( "FriendUserName" );
        String sourceCode = jsonObject.getString( "OldUserCardCode" );
        String targetCode = jsonObject.getString( "UserCardCode" );
        //转赠发券
        CrmGiftService rpcClient = baseRpcClient.getRpcClient( CrmGiftService.class );
        CrmGiftRequest crmGiftRequest = new CrmGiftRequest();
        crmGiftRequest.setGroupID( baseInfoModel.getGroupID() );
        crmGiftRequest.setMpID( baseInfoModel.getMpID() );
        crmGiftRequest.setSourceType( 30 );
        crmGiftRequest.setSourceWay( true );
        crmGiftRequest.setSourceOpenID( sourceOpenID );
        crmGiftRequest.setTargetOpenID( targetOpenID );

        ArrayList <CrmGiftRequest.TransferCouponModel> models = new ArrayList <>();
        CrmGiftRequest.TransferCouponModel transferCouponModel = new CrmGiftRequest.TransferCouponModel();
        transferCouponModel.setGiftItemID( baseInfoModel.getHualalaCardID() );
        transferCouponModel.setSourceWechatCardCode( sourceCode );
        transferCouponModel.setSourceWechatCardKey(  baseInfoModel.getCardKey().toString() );
        transferCouponModel.setWechatCardCode( targetCode );
        transferCouponModel.setWechatCardKey( baseInfoModel.getCardKey().toString() );
        models.add( transferCouponModel );
        crmGiftRequest.setTransferCouponModelList( models );
        return rpcClient.trdTransferGift( crmGiftRequest );
    }

    private CrmGiftResponse sendCoupon(String fromUserName, BaseInfoModel baseInfoModel, String userCardCode) {
        CrmGiftService rpcClient = baseRpcClient.getRpcClient( CrmGiftService.class );
        CrmGiftRequest crmGiftRequest = new CrmGiftRequest();
//        crmGiftRequest.setWechatCardCode( userCardCode);
        crmGiftRequest.setGroupID( baseInfoModel.getGroupID() );
        crmGiftRequest.setSourceType( 30 );
        crmGiftRequest.setSourceWay( true );
        crmGiftRequest.setSourceOpenID( fromUserName );
        crmGiftRequest.setMpID( baseInfoModel.getMpID() );
        CrmGiftRequest.GiftDetailListModel giftDetailListModel = new CrmGiftRequest.GiftDetailListModel();
        giftDetailListModel.setWechatGiftCardID( baseInfoModel.getCardKey().toString() );
        giftDetailListModel.setWechatGiftCode( userCardCode );
        giftDetailListModel.setGiftItemID( baseInfoModel.getHualalaCardID() );
        ArrayList<CrmGiftRequest.GiftDetailListModel> list = new ArrayList <>();
        list.add( giftDetailListModel );
        crmGiftRequest.setGiftDetailList( list );
        return rpcClient.getGiftFromTrd( crmGiftRequest );
    }

    private OuterStr getOutStr(JSONObject jsonObject) {
        String outerStr = jsonObject.getString( "OuterStr" );
        OuterStr outerObj = JSONObject.parseObject( new String( Base64.decodeBase64( outerStr ) ) ,OuterStr.class);
        if (log.isDebugEnabled()) {
            log.debug( "outStr:" + JSONObject.toJSONString( outerObj ) );
        }

        return outerObj;
    }

    private void checkArguments(String userCardCode, Long cardKey, String cardId, String cardType, OuterStr outerObj) {
        Long groupID = outerObj.getGroupID();
        Long hualalaCardID = outerObj.getHualalaCardID();
        Long hualalaCardCode = outerObj.getHualalaCardCode();
        Long customerID = outerObj.getCustomerID();
        if (cardKey == null || userCardCode == null || hualalaCardID == null || groupID == null
                || hualalaCardCode == null || cardType == null) {
            if (log.isErrorEnabled()) {
                log.error( "缺少必须的参数：\n" +
                        "groupID:[" + groupID + "]\n" +
                        "hualalaCardID:[" + hualalaCardID + "]\n" +
                        "userCardCode:[" + userCardCode + "]\n" +
                        "cardKey:[" + cardKey + "]\n" +
                        "cardId:[" + cardId + "]\n" +
                        "hualalaCardCode:[" + hualalaCardCode + "]\n" +
                        "customerID:[" + customerID + "]\n" +
                        "cardType:[" + cardType + "]" );
            }
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS);
        }
    }




}
