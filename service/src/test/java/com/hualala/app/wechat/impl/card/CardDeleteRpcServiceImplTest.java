package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardDeleteRpcService;
import com.hualala.core.base.ResultInfo;

/**
 * Created by renjianfei on 2017/5/2.
 */
public class CardDeleteRpcServiceImplTest extends BaseRpcTest {
    @Override
    public void test() {
        long l = System.currentTimeMillis();
        CardDeleteRpcService rpcClient = super.baseRpcClient.getRpcClient(CardDeleteRpcService.class);
        CardDeleteRpcService.CardDeleteReqData cardDeleteReqData = new CardDeleteRpcService.CardDeleteReqData();
        cardDeleteReqData.setCardKey(12312312L);
        ResultInfo resultInfo = rpcClient.deleteMemberInfo(cardDeleteReqData);
        long l1 = System.currentTimeMillis();
        System.out.println("[ "+(l1-l)+" ]ms");
    }
}
