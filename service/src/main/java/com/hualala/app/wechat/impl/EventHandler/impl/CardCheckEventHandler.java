package com.hualala.app.wechat.impl.EventHandler.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardSyncRpcService;
import com.hualala.app.wechat.impl.EventHandler.AbstractCardEventHandler;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.core.client.BaseRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接收审核通过，和不通过事件，从微信端更新卡券模板到本地
 * Created by renjianfei on 2018/1/3.
 */
@Slf4j
@Component
public class CardCheckEventHandler extends AbstractCardEventHandler {

    @Autowired
    private BaseRpcClient rpcClient;

    @Override
    public Object handler(JSONObject jsonObject) {
        String mpID = jsonObject.getString( "mpID" );
        String event = jsonObject.getString( "Event" );
        String cardId = jsonObject.getString( "CardId" );
        String refuseReason = jsonObject.getString( "RefuseReason" );
        int status = 0;
        if (WechatMessageType.EVENT_CARD_PASS_CHECK.equals( event )){
            status = 4;
        }else if(WechatMessageType.EVENT_CARD_NOT_PASS_CHECK.equals( event )){
            status = 3;
        }
        //更新卡券
        CardSyncRpcService rpcClient = this.rpcClient.getRpcClient( CardSyncRpcService.class );
        CardSyncRpcService.CardDownloadReqData cardDownloadReqData = new CardSyncRpcService.CardDownloadReqData();
        cardDownloadReqData.setMpID(mpID);
        cardDownloadReqData.setCardID(cardId);
        CardSyncRpcService.CardDownloadResData cardDownloadResData = rpcClient.downloadCardInfo(cardDownloadReqData);
        return cardDownloadResData;
    }
}
