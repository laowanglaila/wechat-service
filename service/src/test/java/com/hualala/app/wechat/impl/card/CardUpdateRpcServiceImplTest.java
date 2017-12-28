package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardCodeRpcService;
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
        memberUpdateReqData.setCardKey(6439570631557130245L);
        memberUpdateReqData.setWxActivate( true );
        memberUpdateReqData.setWxActivateAfterSubmit( false );
        memberUpdateReqData.setWxActivateAfterSubmitUrl( "" );
//        CardUpdateRpcService.CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData = memberUpdateReqData.buildBaseInfo();
//        cardBaseInfoUpdateReqData.setTitle("哗啦啦钻石会员1");
//        cardBaseInfoUpdateReqData.setCanGiveFriend(true);
//        cardBaseInfoUpdateReqData.setDescription("asdfasdf");
//        cardBaseInfoUpdateReqData.setGetLimit( 10 );
        CardUpdateRpcService.CardUpdateResData cardUpdateResData = rpcClient.updateMemberInfo(memberUpdateReqData);
        System.out.println(cardUpdateResData.isSendCheck());
    }
    @Test
    public void testUpdateCoupon() {
        CardUpdateRpcService rpcClient = super.baseRpcClient.getRpcClient(CardUpdateRpcService.class);
        CardUpdateRpcService.CouponInfoUpdateReqData updateReqData = new CardUpdateRpcService.CouponInfoUpdateReqData();
        updateReqData.setCardKey(6423579463291790449L);
//        memberUpdateReqData.setTitle("哗啦啦钻石会员");
//        updateReqData.
        CardUpdateRpcService.CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData = updateReqData.buildBaseInfo();
        cardBaseInfoUpdateReqData.setTitle("哗啦啦超级优惠券");
        cardBaseInfoUpdateReqData.setCanGiveFriend(true);
        cardBaseInfoUpdateReqData.setDescription("asdfasdf");
        CardUpdateRpcService.CardUpdateResData cardUpdateResData = rpcClient.updateCouponInfo(updateReqData);
        System.out.println(cardUpdateResData.isSendCheck());
    }
    @Test
    public void testMemberSku() {
        CardUpdateRpcService rpcClient = super.baseRpcClient.getRpcClient(CardUpdateRpcService.class);
        CardUpdateRpcService.CardSkuUpdateReqData cardSkuUpdateReqData = new CardUpdateRpcService.CardSkuUpdateReqData();
        cardSkuUpdateReqData.setCardKey(6423579467586765937L);
        cardSkuUpdateReqData.setIncreaseStockValue(500);
        cardSkuUpdateReqData.setReduceStockValue(0);
        CardUpdateRpcService.CardUpdateResData cardUpdateResData = rpcClient.updateMemberSku(cardSkuUpdateReqData);

        System.out.println(cardUpdateResData.getMessage());
    }
    @Test
    public void testCouponSku() {
        CardUpdateRpcService rpcClient = super.baseRpcClient.getRpcClient(CardUpdateRpcService.class);
        CardUpdateRpcService.CardSkuUpdateReqData cardSkuUpdateReqData = new CardUpdateRpcService.CardSkuUpdateReqData();
        cardSkuUpdateReqData.setCardKey(6423579463291790449L);
        cardSkuUpdateReqData.setIncreaseStockValue(500);
        cardSkuUpdateReqData.setReduceStockValue(0);
        CardUpdateRpcService.CardUpdateResData cardUpdateResData = rpcClient.updateCouponSku(cardSkuUpdateReqData);

        System.out.println(cardUpdateResData.getMessage());
    }

    @Test
    public void testUpdateMemberItem() {
        CardCodeRpcService rpcClient = super.baseRpcClient.getRpcClient(CardCodeRpcService.class);
        CardCodeRpcService.MemberItemUpdateReq memberItemUpdateReq = new CardCodeRpcService.MemberItemUpdateReq();
//        memberItemUpdateReq.setCardCode("154096919594");
        memberItemUpdateReq.setCardCode("318648895989");
        memberItemUpdateReq.setCardKey(6439570631557130245L);
        memberItemUpdateReq.setBonus("400");
        memberItemUpdateReq.setAddBonus("6");
        memberItemUpdateReq.setBalance("100");
        memberItemUpdateReq.setAddBalance("100");
        memberItemUpdateReq.setRecordBonus("哗啦啦测试消费积分");
        memberItemUpdateReq.setRecordBalance("哗啦啦测试储值");
        memberItemUpdateReq.setMsgCreateTime( System.currentTimeMillis() );
        CardCodeRpcService.MemberItemUpdateRes memberItemUpdateRes = rpcClient.updateMemberItem(memberItemUpdateReq);
        System.out.println(memberItemUpdateRes.toString());
        System.out.println(memberItemUpdateRes.getMessage());
    }

}
