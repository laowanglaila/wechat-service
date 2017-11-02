package com.hualala.app.wechat.impl.card;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.googlecode.protobuf.format.JsonFormat;
import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardPrePareQueryRpcService;
import com.hualala.app.wechat.grpc.CardPrePareQueryRpcData;
import com.hualala.app.wechat.grpc.CardPrePareQueryRpcServiceGrpc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by renjianfei on 2017/4/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CardPrePareQueryRpcServiceImplTest extends BaseRpcTest {
//    @Autowired
//    private CardPrePareQueryRpcService prePareQueryCardRpcService;
//@Resource
//private GrpcClient wechatGrpcClient;

    @Test
    public void testQueryByKey(){
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(6426933076835705861L);

        CardPrePareQueryRpcService.CardBaseInfoResData cardBaseInfoResData = rpcClient.queryBaseInfoByCardKey(cardQuery);
        System.out.println(cardBaseInfoResData.getMessage());


    }
    @Autowired
    private CardPrePareQueryRpcServiceGrpc.CardPrePareQueryRpcServiceBlockingStub stub;
    @Test
    public void testQueryByKeyStub(){
//        CardPrePareQueryRpcServiceGrpc stub = (CardPrePareQueryRpcServiceGrpc)wechatClient.getBlockingStub(CardPrePareQueryRpcServiceGrpc.class);
//        wechatClient.
        CardPrePareQueryRpcData.CardQuery.Builder builder = CardPrePareQueryRpcData.CardQuery.newBuilder();
        builder.setCardKey(6426933076835705861L);
        CardPrePareQueryRpcData.CardQuery build = builder.build();
        CardPrePareQueryRpcData.CardBaseInfoResData cardBaseInfoResData = stub.queryBaseInfoByCardKey(build);
        System.out.println(cardBaseInfoResData.getResult().getMessage());


    }
    //grpc 异步写法
    @Autowired
    private CardPrePareQueryRpcServiceGrpc.CardPrePareQueryRpcServiceFutureStub cardPrePareQueryRpcServiceFutureStub;
    @Test
    public void test7() {
        CardPrePareQueryRpcData.CardQuery.Builder builder = CardPrePareQueryRpcData.CardQuery.newBuilder();
        builder.setCardKey(6426933076835705861L);
        CardPrePareQueryRpcData.CardQuery build = builder.build();
        ListenableFuture <CardPrePareQueryRpcData.CardBaseInfoResData> cardBaseInfoResDataListenableFuture = cardPrePareQueryRpcServiceFutureStub.queryBaseInfoByCardKey( build );
        cardBaseInfoResDataListenableFuture.addListener( () -> {
            CardPrePareQueryRpcData.CardBaseInfoResData cardBaseInfoResData = null;
            try {
                cardBaseInfoResData = cardBaseInfoResDataListenableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(new JsonFormat().printToString( cardBaseInfoResData ));
        } , Executors.newSingleThreadExecutor());
        System.out.println("end");

        Futures.addCallback( cardBaseInfoResDataListenableFuture, new FutureCallback <CardPrePareQueryRpcData.CardBaseInfoResData>() {
            @Override
            public void onSuccess(@Nullable CardPrePareQueryRpcData.CardBaseInfoResData cardBaseInfoResData) {
                System.out.println(new JsonFormat().printToString( cardBaseInfoResData ));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        } );
        System.out.println("end1");
    }

    @Override
    public void test() {
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setCardKey(6426933076835705861L);
        CardPrePareQueryRpcService.MemberResData memberResData = rpcClient.queryMemberByCardKey(cardQuery);
        System.out.println(memberResData.getMessage());
    }

//    @Test
//    public void testQueryByWhere(){
//        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
//        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
//        cardQuery.setCardKey(1231231L);
//        cardQuery.setMpID("doulaofangceshi");
//        cardQuery.setTitle("豆捞坊%");
//        CardPrePareQueryRpcService.CouponResDataList couponResDataList = rpcClient.queryCouponList(cardQuery);
//        List<CardPrePareQueryRpcService.CouponResData> couponResDataList1 = couponResDataList.getCouponResDataList();
//        System.out.println(couponResDataList1);
//
//
//    }
//    @Test
//    public void testQueryMemberByWhere(){
//        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
//        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
//        cardQuery.setCardKey(123123123L);
//        cardQuery.setMpID("doulaofangceshi");
//        cardQuery.setTitle("豆捞坊%");
//        cardQuery.setPageNO(1);
//        cardQuery.setPageSize(2);
//        CardPrePareQueryRpcService.MemberResDataList memberResDataList = rpcClient.queryMemberList(cardQuery);
//        List<CardPrePareQueryRpcService.MemberResData> memberResDataList1 = memberResDataList.getMemberResData();
//        System.out.println(memberResDataList1.size());
//    }
//    @Test
//    public void testQueryCouponByWhere(){
//        long l = System.currentTimeMillis();
//        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
//        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
//        cardQuery.setMpID("doulaofangceshi");
//        cardQuery.setTitle("%哗啦啦%");
//        cardQuery.setPageNO(1);
//        cardQuery.setPageSize(2);
//        CardPrePareQueryRpcService.CouponResDataList couponResDataList = rpcClient.queryCouponList(cardQuery);
//        List<CardPrePareQueryRpcService.CouponResData> couponResDataList1 = couponResDataList.getCouponResDataList();
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1-l+"ms");
//        System.out.println(couponResDataList1.size());
//    }

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
    @Test
    public void testBaseInfoList(){

        long l = System.currentTimeMillis();
        CardPrePareQueryRpcService rpcClient = super.baseRpcClient.getRpcClient(CardPrePareQueryRpcService.class);
        CardPrePareQueryRpcService.CardQuery cardQuery = new CardPrePareQueryRpcService.CardQuery();
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("%哗啦啦%");
        cardQuery.setPageNO(1);
        cardQuery.setPageSize(10);
        CardPrePareQueryRpcService.CardBaseInfoResDataList cardBaseInfoResDataList = rpcClient.queryBaseInfoList(cardQuery);
        List<CardPrePareQueryRpcService.CardBaseInfoResData> baseInfoList = cardBaseInfoResDataList.getBaseInfoList();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l+"ms");
        System.out.println(baseInfoList.size());
    }

}
