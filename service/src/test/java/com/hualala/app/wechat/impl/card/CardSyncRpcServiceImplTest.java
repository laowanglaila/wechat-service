package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardStatusEnum;
import com.hualala.app.wechat.CardSyncRpcService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Test
    public void testSyncCouponInfo() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardSyncReqData cardSyncReqData = new CardSyncRpcService.CardSyncReqData();
        cardSyncReqData.setCardKey("sffgaergfdgadsg123");
        CardSyncRpcService.CardSyncResData cardSyncResData = rpcClient.syncCouponInfo(cardSyncReqData);
        System.out.println(cardSyncResData.getMessage());
    }

    @Test
    public void testSyncMemberInfo() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardSyncReqData cardSyncReqData = new CardSyncRpcService.CardSyncReqData();
        cardSyncReqData.setCardKey("ren5671243fdsgsvg25");
        CardSyncRpcService.CardSyncResData cardSyncResData = rpcClient.syncMemberInfo(cardSyncReqData);
        System.out.println(cardSyncResData.getMessage());
    }

    //根据cardID mpID 同步卡券数据  需要指定cardKey
    @Test
    public void testDonwloadCard() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardDownloadReqData cardDownloadReqData = new CardSyncRpcService.CardDownloadReqData();
        cardDownloadReqData.setCardKey("sffg7896aerzcvgadsg123");
        cardDownloadReqData.setCardID("p7FjEuOpIEX6v_IU8-nAC_8SadM4");
        cardDownloadReqData.setMpID("doulaofangceshi");
        CardSyncRpcService.CardDownloadResData cardDownloadResData = rpcClient.downloadCardInfo(cardDownloadReqData);
        System.out.println(cardDownloadResData.getMessage());
    }

    @Test
    public void testDonwloadCardList() {
        CardSyncRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardListReqData cardListReqData = new CardSyncRpcService.CardListReqData();
        cardListReqData.setCount(10);
        cardListReqData.setOffset(30);
        cardListReqData.setMpID("doulaofangceshi");
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
        UUID uuid = UUID.randomUUID();
        cardDownloadReqData.setMpID("doulaofangceshi");
        int i = 1;
        for (String cardID : cardIdList) {
            cardDownloadReqData.setCardKey(uuid.toString()+i++);
            cardDownloadReqData.setCardID(cardID);
            CardSyncRpcService.CardDownloadResData cardDownloadResData = rpcClient.downloadCardInfo(cardDownloadReqData);
            System.out.println("---------------------------------" + cardDownloadResData.getMessage());
        }
    }


}
