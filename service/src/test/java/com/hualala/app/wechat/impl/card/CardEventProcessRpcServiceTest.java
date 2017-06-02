package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardEventProcessRpcService;
import org.junit.Test;

/**
 * Created by renjianfei on 2017/6/1.
 */
public class CardEventProcessRpcServiceTest extends BaseRpcTest {
    @Override
    public void test() {
        CardEventProcessRpcService rpcClient = super.baseRpcClient.getRpcClient(CardEventProcessRpcService.class);
        String json = "{\"event\":\"active\",\"json\":\"json\"}";
        CardEventProcessRpcService.EventProcessReq eventProcessReq = new CardEventProcessRpcService.EventProcessReq();
        eventProcessReq.setJson(json);
        CardEventProcessRpcService.EventProcessRes process = rpcClient.process(eventProcessReq);
        System.out.println(process);
    }
    @Test
    public void testImpl() {
        CardEventProcessRpcService rpcClient = new CardEventProcessRpcServiceImpl();
        String json = "{\"event\":\"active\",\"json\":\"json\"}";
        CardEventProcessRpcService.EventProcessReq eventProcessReq = new CardEventProcessRpcService.EventProcessReq();
        eventProcessReq.setJson(json);
        CardEventProcessRpcService.EventProcessRes process = rpcClient.process(eventProcessReq);
        System.out.println(process);
    }


}
