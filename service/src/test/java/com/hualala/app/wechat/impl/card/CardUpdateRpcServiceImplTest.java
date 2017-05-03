package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardUpdateRpcService;
import org.junit.Test;

/**
 * Created by renjianfei on 2017/5/2.
 */
public class CardUpdateRpcServiceImplTest extends BaseRpcTest {

    @Override
    public void test() {
        CardUpdateRpcService rpcClient = super.baseRpcClient.getRpcClient(CardUpdateRpcService.class);
        CardUpdateRpcService.MemberUpdateReqData memberUpdateReqData = new CardUpdateRpcService.MemberUpdateReqData();
        memberUpdateReqData.setCardKey("5678aerzc12adssg123");
        memberUpdateReqData.setTitle("哗啦啦钻石会员");
        memberUpdateReqData.setBalanceRules("adsfasdf");
        CardUpdateRpcService.CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData = memberUpdateReqData.buildBaseInfo();
        cardBaseInfoUpdateReqData.setCanGiveFriend(true);
        cardBaseInfoUpdateReqData.setDescription("asdfasdf");
        CardUpdateRpcService.CardUpdateResData cardUpdateResData = rpcClient.updateMemberInfo(memberUpdateReqData);
        System.out.println(cardUpdateResData.isSendCheck());
    }
    @Test
    public void testSku() {
        CardUpdateRpcService rpcClient = super.baseRpcClient.getRpcClient(CardUpdateRpcService.class);
        CardUpdateRpcService.CardSkuUpdateReqData cardSkuUpdateReqData = new CardUpdateRpcService.CardSkuUpdateReqData();
        cardSkuUpdateReqData.setCardKey("5678aerzc12adssg123");
        cardSkuUpdateReqData.setIncreaseStockValue(100);
        cardSkuUpdateReqData.setReduceStockValue(0);
        CardUpdateRpcService.CardUpdateResData cardUpdateResData = rpcClient.updateMemberSku(cardSkuUpdateReqData);

        System.out.println(cardUpdateResData.getMessage());
    }

}
