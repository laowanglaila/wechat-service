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
        cardCodeDestroyReqData.setCode("724534781152");
        cardCodeDestroyReqData.setCardKey(6444364553793842307L);
        CardCodeRpcService.CardCodeDestroyResData cardCodeDestroyResData = rpcClient.destoryCode(cardCodeDestroyReqData);
        System.out.println(cardCodeDestroyResData.getMessage());
    }

    /**
     * 测试更新会员卡实例
     */
    @Test
    public void testCode () {
        CardCodeRpcService rpcClient = super.baseRpcClient.getRpcClient(CardCodeRpcService.class);
        CardCodeRpcService.MemberItemUpdateReq memberItemUpdateReq = new CardCodeRpcService.MemberItemUpdateReq();
        memberItemUpdateReq.setCardCode("724534781152");
        memberItemUpdateReq.setCardKey(6444364553793842307L);
        memberItemUpdateReq.setMsgCreateTime(124L);
        CardCodeRpcService.MemberItemUpdateRes memberItemUpdateRes = rpcClient.updateMemberItem(memberItemUpdateReq);
        System.out.println(memberItemUpdateRes.getCode() + " : " + memberItemUpdateRes.getMessage());
    }



}
