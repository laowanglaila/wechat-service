package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardStatusEnum;
import com.hualala.app.wechat.CardSyncRpcService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renjianfei on 2017/5/4.
 */
public class CardSyncRpcServiceImplTest extends BaseRpcTest {
    @Override
    public void test() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardListReqData cardListReqData = new CardSyncRpcService.CardListReqData();
        cardListReqData.setCount(10);
        cardListReqData.setOffset(0);
        cardListReqData.setMpID("doulaofangceshi");
        List<String> cardStatusEnums = new ArrayList<>();
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_NOT_VERIFY.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_VERIFY_FAIL.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_VERIFY_OK.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_DISPATCH.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_DELETE.name());
        cardListReqData.setStatusList(cardStatusEnums);
        CardSyncRpcService.CardListResData cardList = rpcClient.getCardList(cardListReqData);
        System.out.println(cardList.getMessage());
    }
}
