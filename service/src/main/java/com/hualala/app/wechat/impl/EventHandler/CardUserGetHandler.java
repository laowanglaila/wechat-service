package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.card.VoucherCardReq;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.crm.bean.cardChannel.CardChannelReq;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelReq;
import com.hualala.app.crm.service.CardChannelService;
import com.hualala.app.crm.service.CardInfoService;
import com.hualala.app.crm.service.GiftDetailChannelService;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.client.BaseRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by renjianfei on 2017/9/8.
 */
@Slf4j
@Component("cardUserGetHandler")
public class CardUserGetHandler extends BaseEventCardEventHandler {
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Autowired
    private UserModelMapper userModelMapper;
    @Autowired
    private BaseRpcClient baseRpcClient;

    /**
     * 领取卡券事件处理器
     *
     * @param jsonObject
     */
    @Override
    public Object handler(JSONObject jsonObject) {
        if (!jsonObject.containsKey( "OuterStr" )) {
            if (log.isInfoEnabled())
                log.info( "微信渠道投放卡券：{}", jsonObject );
            this.processCardNotBeenExist(jsonObject);
            return null;
        }
        String outerStr = jsonObject.getString( "OuterStr" );
        JSONObject jsonObj = JSONObject.parseObject( new String( Base64.decodeBase64( outerStr ) ) );
        if (log.isDebugEnabled()) {
            log.debug( "outStr:" + jsonObj.toJSONString() );
        }
        Long groupID = null;
        if (jsonObj.containsKey( "groupID" )) {
            groupID = jsonObj.getLong( "groupID" );
        }
        Long hualalaCardID = null;
        if (jsonObj.containsKey( "hualalaCardID" )) {
            hualalaCardID = jsonObj.getLong( "hualalaCardID" );
        }
        Long hualalaCardCode = null;
        if (jsonObj.containsKey( "hualalaCardCode" )) {
            hualalaCardCode = jsonObj.getLong( "hualalaCardCode" );
        }
        Long customerID = null;
        if (jsonObj.containsKey( "customerID" )) {
            customerID = jsonObj.getLong( "customerID" );
        }
        String userCardCode = null;
        if (jsonObject.containsKey( "UserCardCode" )) {
            userCardCode = jsonObject.getString( "UserCardCode" );
        }
        Long cardKey = null;
        String cardId = null;
        String cardType = null;
        BaseInfoModel baseInfoModel = null;
        if (jsonObject.containsKey( "CardId" )) {
            cardId = jsonObject.getString( "CardId" );
            baseInfoModel = getBaseInfoModel( cardId );
        }
        if (baseInfoModel != null) {
            cardKey = baseInfoModel.getCardKey();
            cardType = baseInfoModel.getCardType();
        }
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
            return null;
        }
        ResultInfo resultInfo;
        if ("MEMBER_CARD".equals( cardType )) {
            CardChannelService rpcClient = baseRpcClient.getRpcClient( CardChannelService.class );
            CardChannelReq cardChannelReq = new CardChannelReq();
            cardChannelReq.setWechatCardKey( cardKey );
            cardChannelReq.setWechatCardCode( userCardCode );
            cardChannelReq.setGroupID( groupID );
            cardChannelReq.setCardID( hualalaCardCode );
            resultInfo = rpcClient.addCardChannel( cardChannelReq );
        } else {
            GiftDetailChannelService rpcClient = baseRpcClient.getRpcClient( GiftDetailChannelService.class );
            GiftDetailChannelReq giftDetailChannelReq = new GiftDetailChannelReq();
            giftDetailChannelReq.setCustomerGiftDetailID( hualalaCardCode );
            giftDetailChannelReq.setGroupID( groupID );
            giftDetailChannelReq.setCustomerID( customerID );
            giftDetailChannelReq.setWechatCardCode( userCardCode );
            giftDetailChannelReq.setWechatCardKey( cardKey );
            resultInfo = rpcClient.addGiftDetailChannel( giftDetailChannelReq );
        }
        return resultInfo;
    }

    private BaseInfoModel getBaseInfoModel(String cardId) {
        BaseInfoModelQuery baseInfoModelQuery = new BaseInfoModelQuery();
        baseInfoModelQuery.createCriteria().andCardIDEqualTo( cardId );
        List<BaseInfoModel> baseInfoModels = baseInfoModelMapper.selectByExample( baseInfoModelQuery );
        BaseInfoModel baseInfoModel = null;
        if (baseInfoModels != null && baseInfoModels.size() == 1) {
            baseInfoModel = baseInfoModels.get( 0 );
        }
        return baseInfoModel;
    }

    private VoucherCardRes processCardNotBeenExist(JSONObject jsonObject) {
        String userCardCode = jsonObject.getString( "UserCardCode" );
        String fromUserName = jsonObject.getString( "FromUserName" );
        String cardId = null;
        BaseInfoModel baseInfoModel = null;
        if (jsonObject.containsKey( "CardId" )) {
            cardId = jsonObject.getString( "CardId" );
            baseInfoModel = getBaseInfoModel( cardId );
        }
        if (baseInfoModel != null) {
            log.info( "未找到卡券关系，不处理" );
            return null;
        }
        CardInfoService rpcClient = baseRpcClient.getRpcClient( CardInfoService.class );
        VoucherCardReq voucherCardReq = new VoucherCardReq();
        voucherCardReq.setMpID( baseInfoModel.getMpID() );
        voucherCardReq.setGroupID( baseInfoModel.getGroupID() );
        voucherCardReq.setWechatCardKey( baseInfoModel.getCardKey() );
        voucherCardReq.setCardTypeID( baseInfoModel.getHualalaCardID() );
        voucherCardReq.setWechatCardCode( userCardCode );
        voucherCardReq.setActiveStatus( 1 );
        voucherCardReq.setSourceWay( true );
        voucherCardReq.setSourceType( 30 );
        voucherCardReq.setShopWeixinID( fromUserName );
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria().andOpenidEqualTo( fromUserName );
        List <UserModel> userModels = userModelMapper.selectByExample( userModelQuery );
        if (userModels != null && userModels.size() > 0) {
            UserModel userModel = userModels.get( 0 );
            Long userID = userModel.getUserID();
            if(userID != null && -1 != userID){
                UserModelQuery userModelQuery1 = new UserModelQuery();
                userModelQuery1.createCriteria().andUserIDEqualTo( userID ).andMpIDEqualTo( "hualala_com" );
                List <UserModel> userModels1 = userModelMapper.selectByExample( userModelQuery1 );
                if (userModels1 != null && userModels1.size() > 0) {
                    UserModel userModel1 = userModels1.get( 0 );
                    String openid = userModel1.getOpenid();
                    voucherCardReq.setWeixinID( openid );
                }
            }
        }
        return rpcClient.reverseVoucherCardAssociation( voucherCardReq );


    }


}
