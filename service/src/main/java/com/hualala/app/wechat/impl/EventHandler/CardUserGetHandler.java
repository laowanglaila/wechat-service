package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.cardChannel.CardChannelReq;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelReq;
import com.hualala.app.crm.service.CardChannelService;
import com.hualala.app.crm.service.GiftDetailChannelService;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
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
    private BaseRpcClient baseRpcClient;

    /**
     * 领取卡券事件处理器
     *
     * @param jsonObject
     */
    @Override
    public Object handler(JSONObject jsonObject) {
        if (!jsonObject.containsKey( "OuterStr" )) {
            //todo 或者做默认记录处理，如果openid匹配可以则做线下核销，否则记录无效
            if (log.isInfoEnabled())
                log.info( "给卡券不是哗啦啦投放渠道领取，不做同步处理：{}", jsonObject );
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
        if (jsonObject.containsKey( "CardId" )) {
            cardId = jsonObject.getString( "CardId" );
            BaseInfoModelQuery baseInfoModelQuery = new BaseInfoModelQuery();
            baseInfoModelQuery.createCriteria().andCardIDEqualTo( cardId );
            List<BaseInfoModel> baseInfoModels = baseInfoModelMapper.selectByExample( baseInfoModelQuery );
            if (baseInfoModels != null && baseInfoModels.size() == 1) {
                BaseInfoModel baseInfoModel1 = baseInfoModels.get( 0 );
                cardKey = baseInfoModel1.getCardKey();
                cardType = baseInfoModel1.getCardType();
            }
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


}
