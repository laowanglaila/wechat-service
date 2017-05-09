package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardPrePareQueryRpcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by renjianfei on 2017/4/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CardPrePareQueryRpcServiceImplTest extends BaseRpcTest {
//    @Autowired
//    private CardPrePareQueryRpcService prePareQueryCardRpcService;


    @Test
    public void testQueryByKey(){
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(12535216799220737L);
        CardPrePareQueryRpcService.CouponResData couponResData = rpcClient.queryCouponByCardKey(cardQuery);
        System.out.println(couponResData.getMessage());


    }


    @Override
    public void test() {
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(12535216799220737L);
        CardPrePareQueryRpcService.MemberResData memberResData = rpcClient.queryMemberByCardKey(cardQuery);
        System.out.println(memberResData.getMessage());
    }
    @Test
    public void testQueryByWhere(){
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(1231231L);
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("豆捞坊%");
        CardPrePareQueryRpcService.CouponResDataList couponResDataList = rpcClient.queryCouponList(cardQuery);
        List<CardPrePareQueryRpcService.CouponResData> couponResDataList1 = couponResDataList.getCouponResDataList();
        System.out.println(couponResDataList1);


    }
    @Test
    public void testQueryMemberByWhere(){
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(123123123L);
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("豆捞坊%");
        cardQuery.setPageNO(1);
        cardQuery.setPageSize(2);
        CardPrePareQueryRpcService.MemberResDataList memberResDataList = rpcClient.queryMemberList(cardQuery);
        List<CardPrePareQueryRpcService.MemberResData> memberResDataList1 = memberResDataList.getMemberResData();
        System.out.println(memberResDataList1.size());
    }
    @Test
    public void testQueryCouponByWhere(){
        long l = System.currentTimeMillis();
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("%哗啦啦%");
        cardQuery.setPageNO(1);
        cardQuery.setPageSize(2);
        CardPrePareQueryRpcService.CouponResDataList couponResDataList = rpcClient.queryCouponList(cardQuery);
        List<CardPrePareQueryRpcService.CouponResData> couponResDataList1 = couponResDataList.getCouponResDataList();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l+"ms");
        System.out.println(couponResDataList1.size());
    }

    @Test
    public void testQueryBaseInfoByKey(){
        long l = System.currentTimeMillis();
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(12312312L);
        CardPrePareQueryRpcService.CardBaseInfoResData baseInfo = rpcClient.queryBaseInfoByCardKey(cardQuery);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l+"ms");
        System.out.println(baseInfo.toString());
    }
    @Test
    public void testQueryAdvanceInfoByKey(){
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(12312L);
        CardPrePareQueryRpcService.CardAdvancedInfoResData advancedInfoResData = rpcClient.queryAdvancedInfoByCardKey(cardQuery);
        System.out.println(advancedInfoResData.toString());
    }
//    @Test
//    public void testSyncCouponInfo(){
//        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
//        CardPrePareQueryRpcService.CardSyncReqData cardSyncReqData = new CardPrePareQueryRpcService.CardSyncReqData();
//        cardSyncReqData.setCardKey("sffgaergfdgadsg123");
//        rpcClient.syncCouponInfo(cardSyncReqData);
//    }
//    @Test
//    public void testSyncMemberInfo(){
//        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
//        CardPrePareQueryRpcService.CardSyncReqData cardSyncReqData = new CardPrePareQueryRpcService.CardSyncReqData();
//        cardSyncReqData.setCardKey("ren5671243fdsgsvg25");
//        rpcClient.syncMemberInfo(cardSyncReqData);
//    }
}
