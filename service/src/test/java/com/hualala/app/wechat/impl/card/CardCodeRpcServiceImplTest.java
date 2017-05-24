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
    public void testDecodingCode() {
        CardCodeRpcService rpcClient = super.baseRpcClient.getRpcClient(CardCodeRpcService.class);

        CardCodeRpcService.CardCodeDecodingReqData cardCodeDecodingReqData = new CardCodeRpcService.CardCodeDecodingReqData();
        cardCodeDecodingReqData.setMpID("hualala_com");
        cardCodeDecodingReqData.setEncryptCode("XXIzTtMqCxwOaawoE91+VJdsFmv7b8g0VZIZkqf4GWA60Fzpc8ksZ/5ZZ0DVkXdE");
        CardCodeRpcService.CardCodeDecodingResData cardCodeDecodingResData = rpcClient.decodingCardCode(cardCodeDecodingReqData);
        System.out.println(cardCodeDecodingResData.getMessage());
    }
}
