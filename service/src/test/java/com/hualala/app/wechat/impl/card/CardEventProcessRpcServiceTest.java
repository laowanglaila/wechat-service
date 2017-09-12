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
        String json = "{\"CardId\":\"pD8-BwEomhnALJ-l_DjOxKsf4248\",\"CreateTime\":\"1504841943\",\"Event\":\"user_get_card\",\"FriendUserName\":\"\",\"FromUserName\":\"oD8-BwDLutkijpb1NwsKIxA6LQEg\",\"IsGiveByFriend\":\"0\",\"IsRecommendByFriend\":\"0\",\"IsRestoreMemberCard\":\"0\",\n" +
                "\"MsgType\":\"event\",\"OldUserCardCode\":\"\",\"OuterId\":\"0\",\"OuterStr\":\"eyJncm91cElEIjoiOCIsImh1YWxhbGFDYXJkSUQiOiI3NDQzOTU0MjgzMDQ0OTg2ODgiLCJodWFsYWxhQ2FyZENvZGUiOiI3NTMwOTcwNjY1MzU5NzY5NjAifQ\",\"SourceScene\":\"SOURCE_SCENE_UNKNOW\",\"ToUserName\":\"gh_2a08b061af9e\",\"UserCardCode\":\"324670832454\",\"groupID\":\"8\",\"mpID\":\"wangxiangyuanceshi\",\"pushEvent\":\"user_get_card\",\"pushMsgType\":\"event\",\"userWechatID\":\"oD8-BwDLutkijpb1NwsKIxA6LQEg\"}";
        CardEventProcessRpcService.EventProcessReq eventProcessReq = new CardEventProcessRpcService.EventProcessReq();
        eventProcessReq.setJson(json);
        CardEventProcessRpcService.EventProcessRes process = rpcClient.process(eventProcessReq);
        System.out.println(process.getJson());
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
