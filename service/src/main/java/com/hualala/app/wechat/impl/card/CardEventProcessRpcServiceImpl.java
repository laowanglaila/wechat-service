package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.cardChannel.CardChannelReq;
import com.hualala.app.crm.bean.cardChannel.CardChannelRes;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelReq;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelRes;
import com.hualala.app.crm.service.CardChannelService;
import com.hualala.app.crm.service.GiftDetailChannelService;
import com.hualala.app.wechat.CardEventProcessRpcService;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import com.hualala.core.app.Logger;
import com.hualala.core.client.BaseRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 识别事件类型，根据事件类型作出处理
 * Created by renjianfei on 2017/6/1.
 */
@Service
@Slf4j
public class CardEventProcessRpcServiceImpl implements CardEventProcessRpcService {
//    private Logger logger = Logger.of(this.getClass());
    @Autowired
    private BaseRpcClient baseRpcClient;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;

    @Override
    public EventProcessRes process(EventProcessReq json) {
        String jsonStr = json.getJson();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (log.isInfoEnabled()){
            log.info(jsonObject.toJSONString());
        }
        String event = jsonObject.getString("Event");
        if (log.isInfoEnabled()){
            log.info("CardEventProcessRpcService.process()\r\nEvent:{}\r\n$params:{}",event,jsonObject);
        }
        EventProcessRes eventProcessRes = new EventProcessRes();

        if (WechatMessageType.EVENT_CARD_MEMBER_ACTIVE.equals(event)) {
            //激活会员卡

        } else if (WechatMessageType.EVENT_CARD_NOT_PASS_CHECK.equals(event)) {
            //未通过审核

        } else if (WechatMessageType.EVENT_CARD_PASS_CHECK.equals(event)) {
            //通过审核

        } else if (WechatMessageType.EVENT_CARD_PAY_ORDER.equals(event)) {
            //券点流水详情事件

        } else if (WechatMessageType.EVENT_CARD_SKU_REMIND.equals(event)) {
            //库存报警事件

        } else if (WechatMessageType.EVENT_CARD_UPDATE_MEMBER.equals(event)) {
            //会员卡内容更新事件

        } else if (WechatMessageType.EVENT_CARD_USER_CONSUME.equals(event)) {
            //用户核销事件

        } else if (WechatMessageType.EVENT_CARD_USER_DEL.equals(event)) {
            //用户删除事件

        } else if (WechatMessageType.EVENT_CARD_USER_ENTER.equals(event)) {
            //用户从卡券进入公众号会话事件

        } else if (WechatMessageType.EVENT_CARD_USER_GET.equals(event)) {
            //用户领取事件
            this.eventUserGetHandler(jsonObject);
        } else if (WechatMessageType.EVENT_CARD_USER_GIFTING.equals(event)) {
            //用户转赠事件

        } else if (WechatMessageType.EVENT_CARD_USER_PAY_FROM_PAY_CELL.equals(event)) {
            //用户买单事件

        } else if (WechatMessageType.EVENT_CARD_USER_VIEW.equals(event)) {
            //用户进入会员卡事件（暂不接受压力大）

        }


        eventProcessRes.setJson(jsonStr);
        return eventProcessRes;
    }

    /**
     * 领取卡券事件处理器
     *
     * @param jsonObject
     */
    private void eventUserGetHandler(JSONObject jsonObject) {
        String outerStr = jsonObject.getString("OuterStr");
        JSONObject jsonObj = JSONObject.parseObject(new String(Base64.decodeBase64(outerStr)));
        if (log.isDebugEnabled()){
            log.debug("outStr:" + jsonObj.toJSONString());
        }
        Long groupID = null;
        if (jsonObj.containsKey("groupID")) {
            groupID = jsonObj.getLong("groupID");
        }
        Long hualalaCardID = null;
        if (jsonObj.containsKey("hualalaCardID")) {
            hualalaCardID = jsonObj.getLong("hualalaCardID");
        }
        Long hualalaCardCode = null;
        if (jsonObj.containsKey("hualalaCardCode")) {
            hualalaCardCode = jsonObj.getLong("hualalaCardCode");
        }
        Long customerID = null;
        if (jsonObj.containsKey("customerID")) {
            customerID = jsonObj.getLong("customerID");
        }
        String userCardCode = null;
        if (jsonObject.containsKey("UserCardCode")) {
            userCardCode = jsonObject.getString("UserCardCode");
        }
        Long cardKey = null;
        String cardId = null;
        String cardType = null;
        if (jsonObject.containsKey("CardId")) {
            cardId = jsonObject.getString("CardId");
            BaseInfoModelQuery baseInfoModelQuery = new BaseInfoModelQuery();
            baseInfoModelQuery.createCriteria().andCardIDEqualTo(cardId);
            List<BaseInfoModel> baseInfoModels = baseInfoModelMapper.selectByExample(baseInfoModelQuery);
            if (baseInfoModels != null && baseInfoModels.size() == 1) {
                BaseInfoModel baseInfoModel1 = baseInfoModels.get(0);
                cardKey = baseInfoModel1.getCardKey();
                cardType = baseInfoModel1.getCardType();
            }
        }
        if (cardKey == null || userCardCode == null || hualalaCardID == null || groupID == null
                            || hualalaCardCode == null || cardType == null ) {
            if (log.isErrorEnabled()){
                log.error("缺少必须的参数：\n" +
                        "groupID:[" + groupID + "]\n" +
                        "hualalaCardID:[" + hualalaCardID + "]\n" +
                        "userCardCode:[" + userCardCode + "]\n" +
                        "cardKey:[" + cardKey + "]\n" +
                        "cardId:[" + cardId + "]\n" +
                        "hualalaCardCode:[" + hualalaCardCode + "]\n" +
                        "customerID:["+customerID+"]\n" +
                        "cardType:["+cardType+"]");
            }
            return;
        }
        if ("MEMBER_CARD".equals(cardType)){
            CardChannelService rpcClient = baseRpcClient.getRpcClient(CardChannelService.class);
            CardChannelReq cardChannelReq = new CardChannelReq();
            cardChannelReq.setWechatCardKey(cardKey);
            cardChannelReq.setWechatCardCode(userCardCode);
            cardChannelReq.setGroupID(groupID);
            cardChannelReq.setCardID(hualalaCardCode);
            CardChannelRes cardChannelRes = rpcClient.addCardChannel(cardChannelReq);
            if (log.isDebugEnabled()){
                log.debug(JSONObject.toJSONString(cardChannelRes.getMessageParams()));
            }
            if (!"000".equals(cardChannelRes.getCode())){
                Object[] messageParams = cardChannelRes.getMessageParams();
                String s = JSONObject.toJSONString(messageParams);
                if (log.isErrorEnabled()){
                    log.error(s);
                }
            }

        }else {
            GiftDetailChannelService rpcClient = baseRpcClient.getRpcClient(GiftDetailChannelService.class);
            GiftDetailChannelReq giftDetailChannelReq = new GiftDetailChannelReq();
            giftDetailChannelReq.setCustomerGiftDetailID(hualalaCardCode);
            giftDetailChannelReq.setGroupID(groupID);
            giftDetailChannelReq.setCustomerID(customerID);
            giftDetailChannelReq.setWechatCardCode(userCardCode);
            giftDetailChannelReq.setWechatCardKey(cardKey);
            GiftDetailChannelRes giftDetailChannelRes = rpcClient.addGiftDetailChannel(giftDetailChannelReq);
            if (log.isDebugEnabled()){
                log.debug(JSONObject.toJSONString(giftDetailChannelRes.getMessageParams()));
            }
            if (!"000".equals(giftDetailChannelRes.getCode())){
                Object[] messageParams = giftDetailChannelRes.getMessageParams();
                String s = JSONObject.toJSONString(messageParams);
                if (log.isErrorEnabled()){
                    log.error(s);
                }
            }

        }

    }
}
