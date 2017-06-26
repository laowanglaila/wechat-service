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
        cardDeleteReqData.setCardKey(6429086015092172805l);
        ResultInfo resultInfo = rpcClient.deleteCard(cardDeleteReqData);
        long l1 = System.currentTimeMillis();
        System.out.println("[ "+(l1-l)+" ]ms");
        System.out.println(resultInfo.getMessage());
    }
    @Test
    public void test1() {
        long l = System.currentTimeMillis();
        CardDeleteRpcService rpcClient = super.baseRpcClient.getRpcClient(CardDeleteRpcService.class);
        CardDeleteRpcService.CardUnAvailableReqData cardUnAvailableReqData = new CardDeleteRpcService.CardUnAvailableReqData();
        cardUnAvailableReqData.setCardKey(6434399349198825477L);
        cardUnAvailableReqData.setCode("489746517381");
        cardUnAvailableReqData.setReason("测试设置失效");
        ResultInfo resultInfo = rpcClient.unAvailableCard(cardUnAvailableReqData);
        long l1 = System.currentTimeMillis();
        System.out.println("[ "+(l1-l)+" ]ms");
        System.out.println(resultInfo.getMessage());
    }
}
