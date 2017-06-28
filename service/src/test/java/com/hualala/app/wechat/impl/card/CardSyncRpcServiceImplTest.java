package com.hualala.app.wechat.impl.card;

import com.hualala.app.crm.bean.cardChannel.CardChannelReq;
import com.hualala.app.crm.bean.cardChannel.CardChannelRes;
import com.hualala.app.crm.service.CardChannelService;
import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardStatusEnum;
import com.hualala.app.wechat.CardSyncRpcService;
import com.hualala.app.wechat.service.card.CreateCardKeyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        cardListReqData.setMpID("hualala_com");
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

    @Test
    public void testSyncCouponInfo() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardSyncReqData cardSyncReqData = new CardSyncRpcService.CardSyncReqData();
        cardSyncReqData.setCardKey(6423578788981908485L);
        CardSyncRpcService.CardSyncResData cardSyncResData = rpcClient.syncCouponInfo(cardSyncReqData);
        System.out.println(cardSyncResData.getMessage());
    }

    @Test
    public void testSyncMemberInfo() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardSyncReqData cardSyncReqData = new CardSyncRpcService.CardSyncReqData();
        cardSyncReqData.setCardKey(6434342685695289349L);
        CardSyncRpcService.CardSyncResData cardSyncResData = rpcClient.syncMemberInfo(cardSyncReqData);
        System.out.println(cardSyncResData.getMessage());
    }

    //根据cardID mpID 同步卡券数据  需要指定cardKey
    @Test
    public void testDonwloadCard() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardDownloadReqData cardDownloadReqData = new CardSyncRpcService.CardDownloadReqData();
        cardDownloadReqData.setCardID("pACwGs3tatjGfZLGj5dOlrTu7ups");
        cardDownloadReqData.setMpID("hualala_com");
        CardSyncRpcService.CardDownloadResData cardDownloadResData = rpcClient.downloadCardInfo(cardDownloadReqData);
        System.out.println(cardDownloadResData.getMessage());
    }
@Autowired
private CreateCardKeyService createCardKeyService;
    @Test
    public void testDonwloadCardList() throws ExecutionException {
        String mpID = "hualala_com";
//        String mpID = "doulaofangceshi";
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardListReqData cardListReqData = new CardSyncRpcService.CardListReqData();
        cardListReqData.setCount(50);
        cardListReqData.setOffset(0);
        cardListReqData.setMpID(mpID);
        List<String> cardStatusEnums = new ArrayList<>();
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_NOT_VERIFY.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_VERIFY_FAIL.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_VERIFY_OK.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_DISPATCH.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_DELETE.name());
        cardListReqData.setStatusList(cardStatusEnums);
        CardSyncRpcService.CardListResData cardList = rpcClient.getCardList(cardListReqData);
        List<String> cardIdList = cardList.getCardIdList();
        CardSyncRpcService.CardDownloadReqData cardDownloadReqData = new CardSyncRpcService.CardDownloadReqData();
        cardDownloadReqData.setMpID(mpID);
        for (String cardID : cardIdList) {
            cardDownloadReqData.setCardID(cardID);
            CardSyncRpcService.CardDownloadResData cardDownloadResData = rpcClient.downloadCardInfo(cardDownloadReqData);
            System.out.println("---------------------------------" + cardDownloadResData.getMessage());
        }
    }

    @Test
    public void testaddCardChannel(){
        CardChannelService rpcClient = super.baseRpcClient.getRpcClient(CardChannelService.class);
        CardChannelReq cardChannelReq = new CardChannelReq();
//        cardChannelReq.set
        CardChannelRes cardChannelRes = rpcClient.addCardChannel(cardChannelReq);
        System.out.println(cardChannelRes.getMessage());
    }

}
