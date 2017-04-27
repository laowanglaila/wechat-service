package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.PrePareQueryCardRpcService;
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
public class PrePareQueryCardRpcServiceImplTest extends BaseRpcTest {
//    @Autowired
//    private PrePareQueryCardRpcService prePareQueryCardRpcService;


    @Test
    public void testQueryByKey(){
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setCardKey("sffg7896aerzcvgadsg123");
        PrePareQueryCardRpcService.CouponResData couponResData = rpcClient.queryCouponByCardKey(cardQuery);
        System.out.println(couponResData.toString());


    }


    @Override
    public void test() {
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setCardKey("123aerzcvgadsg123");
        PrePareQueryCardRpcService.MemberResData memberResData = rpcClient.queryMemberByCardKey(cardQuery);
        System.out.println(memberResData.toString());
    }
    @Test
    public void testQueryByWhere(){
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setCardKey("sffg7896aerzcvgadsg123");
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("豆捞坊%");
        PrePareQueryCardRpcService.CouponResDataList couponResDataList = rpcClient.queryCouponList(cardQuery);
        List<PrePareQueryCardRpcService.CouponResData> couponResDataList1 = couponResDataList.getCouponResDataList();
        System.out.println(couponResDataList1);


    }
    @Test
    public void testQueryMemberByWhere(){
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setCardKey("asdfa123123dfgasd");
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("豆捞坊%");
        cardQuery.setPageNO(1);
        cardQuery.setPageSize(2);
        PrePareQueryCardRpcService.MemberResDataList memberResDataList = rpcClient.queryMemberList(cardQuery);
        List<PrePareQueryCardRpcService.MemberResData> memberResDataList1 = memberResDataList.getMemberResData();
        System.out.println(memberResDataList1.size());
    }
    @Test
    public void testQueryCouponByWhere(){
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setMpID("doulaofangceshi");
        cardQuery.setTitle("%哗啦啦%");
        cardQuery.setPageNO(1);
        cardQuery.setPageSize(2);
        PrePareQueryCardRpcService.CouponResDataList couponResDataList = rpcClient.queryCouponList(cardQuery);
        List<PrePareQueryCardRpcService.CouponResData> couponResDataList1 = couponResDataList.getCouponResDataList();
        System.out.println(couponResDataList1.size());
    }

    @Test
    public void testQueryBaseInfoByKey(){
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setCardKey("1203128074192cfsdfsdf");
        PrePareQueryCardRpcService.CardBaseInfoResData baseInfo = rpcClient.queryBaseInfoByCardKey(cardQuery);
        System.out.println(baseInfo.toString());
    }
    @Test
    public void testQueryAdvanceInfoByKey(){
        PrePareQueryCardRpcService rpcClient = super.baseRpcClient.getRpcClient(PrePareQueryCardRpcService.class);
        PrePareQueryCardRpcService.CardQuery cardQuery = new PrePareQueryCardRpcService.CardQuery();
        cardQuery.setCardKey("1203128074192cfsdfsdf");
        PrePareQueryCardRpcService.CardAdvancedInfoResData advancedInfoResData = rpcClient.queryAdvancedInfoByCardKey(cardQuery);
        System.out.println(advancedInfoResData.toString());
    }
}
