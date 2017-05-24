package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardDeleteRpcService;
import com.hualala.core.base.ResultInfo;
import org.junit.Test;

/**
 * Created by renjianfei on 2017/5/2.
 */
public class CardDeleteRpcServiceImplTest extends BaseRpcTest {
    @Override
    public void test() {
        long l = System.currentTimeMillis();
        CardDeleteRpcService rpcClient = super.baseRpcClient.getRpcClient(CardDeleteRpcService.class);
        CardDeleteRpcService.CardDeleteReqData cardDeleteReqData = new CardDeleteRpcService.CardDeleteReqData();
        cardDeleteReqData.setCardKey(6423578836226565125L);
        ResultInfo resultInfo = rpcClient.deleteCard(cardDeleteReqData);
        long l1 = System.currentTimeMillis();
        System.out.println("[ "+(l1-l)+" ]ms");
        System.out.println(resultInfo.getMessage());
    }
    @Test
    public void test1() {
//        long l = System.currentTimeMillis();
//        CardDeleteRpcService rpcClient = super.baseRpcClient.getRpcClient(CardDeleteRpcService.class);
//        CardDeleteRpcService.CardDeleteReqData cardDeleteReqData = new CardDeleteRpcService.CardDeleteReqData();
//        cardDeleteReqData.setCardKey(6423579463291790449L);
//        ResultInfo resultInfo = rpcClient.unAvailableCard(cardDeleteReqData);
//        long l1 = System.currentTimeMillis();
//        System.out.println("[ "+(l1-l)+" ]ms");
//        System.out.println(resultInfo.getMessage());
    }
}
