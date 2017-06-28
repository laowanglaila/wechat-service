package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardCodeRpcService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renjianfei on 2017/5/3.
 */
public class CardCodeRpcServiceImplTest extends BaseRpcTest {
    @Override
    public void test() {
        CardCodeRpcService rpcClient = super.baseRpcClient.getRpcClient(CardCodeRpcService.class);
        CardCodeRpcService.CardCodeImportReqData cardCodeImportReqData = new CardCodeRpcService.CardCodeImportReqData();
        List<String> codes = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++){
            codes.add(""+i);
        }
        cardCodeImportReqData.setCardKey(123123L);
        cardCodeImportReqData.setCode(codes);
        CardCodeRpcService.CardCodeImportResData cardCodeImportResData = rpcClient.importCode(cardCodeImportReqData);
        System.out.println(cardCodeImportResData.getMessage());
    }

    @Test
    public void testCodeDestory() {
        CardCodeRpcService rpcClient = super.baseRpcClient.getRpcClient(CardCodeRpcService.class);

        CardCodeRpcService.CardCodeDestroyReqData cardCodeDestroyReqData = new CardCodeRpcService.CardCodeDestroyReqData();
        cardCodeDestroyReqData.setCode("410947662432");
        cardCodeDestroyReqData.setCardKey(123123L);
        CardCodeRpcService.CardCodeDestroyResData cardCodeDestroyResData = rpcClient.destoryCode(cardCodeDestroyReqData);
        System.out.println(cardCodeDestroyResData.getMessage());
    }
    @Test
    public void testUpdateMemberItem() {
        CardCodeRpcService rpcClient = super.baseRpcClient.getRpcClient(CardCodeRpcService.class);
        CardCodeRpcService.MemberItemUpdateReq memberItemUpdateReq = new CardCodeRpcService.MemberItemUpdateReq();
//        memberItemUpdateReq.setCardCode("154096919594");
        memberItemUpdateReq.setCardCode("177839940248");
        memberItemUpdateReq.setCardKey(6435763117279446021L);
        memberItemUpdateReq.setBonus("400");
        memberItemUpdateReq.setAddBonus("6");
        memberItemUpdateReq.setBalance("100");
        memberItemUpdateReq.setAddBalance("100");
        memberItemUpdateReq.setRecordBonus("哗啦啦测试消费积分");
        memberItemUpdateReq.setRecordBalance("哗啦啦测试储值");

        CardCodeRpcService.MemberItemUpdateRes memberItemUpdateRes = rpcClient.updateMemberItem(memberItemUpdateReq);
        System.out.println(memberItemUpdateRes.toString());
        System.out.println(memberItemUpdateRes.getMessage());
    }

}
