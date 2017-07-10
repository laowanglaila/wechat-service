package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardEventRpcService;

/**
 * Created by renjianfei on 2017/5/5.
 */
public class CardEventRpcServiceImplTest extends BaseRpcTest {


    /**
     * 测试激活会员卡
     */
    @Override
    public void test() {
        CardEventRpcService rpcClient = super.baseRpcClient.getRpcClient(CardEventRpcService.class);
        CardEventRpcService.ActivateMemberCardReqData activateMemberCardReqData = new CardEventRpcService.ActivateMemberCardReqData();
        activateMemberCardReqData.setCardKey(12312L);
        activateMemberCardReqData.setCode("");
        activateMemberCardReqData.setMembershipNumber("");
        activateMemberCardReqData.setBackgroundPicUrl("");
        activateMemberCardReqData.setActivateBeginTime(0);
        activateMemberCardReqData.setActivateEndTime(0);
        activateMemberCardReqData.setInitBonus(100);
        activateMemberCardReqData.setInitBonusRecord("");
        activateMemberCardReqData.setInitBalance(0);
        activateMemberCardReqData.setInitCustomFieldValue1("");
        activateMemberCardReqData.setInitCustomFieldValue2("");
        activateMemberCardReqData.setInitCustomFieldValue3("");
        CardEventRpcService.ActivateMemberCardResData activateMemberCardResData = rpcClient.activateMemberCard(activateMemberCardReqData);
        System.out.println(activateMemberCardResData.getMessage());
    }

}
