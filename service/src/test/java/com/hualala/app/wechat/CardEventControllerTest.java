package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.util.HttpApiUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by renjianfei on 2017/5/23.
 */
@SpringBootTest
public class CardEventControllerTest {

    @Test
    public void test(){
        String json = "{\"CardId\":\"pACwGs1o30yZFhsTqysXImX5QHDA\",\"pushEvent\":\"user_view_card\",\"Event\":\"user_view_card\",\"pushMsgType\":\"event\",\"ToUserName\":\"gh_d15620755937\",\"MsgType\":\"event\",\"FromUserName\":\"oACwGswdcsCnQgJlarC9xIqQL2YI\",\"groupID\":\"5\",\"CreateTime\":\"1495518855\",\"mpID\":\"hllxmb\",\"userWechatID\":\"oACwGswdcsCnQgJlarC9xIqQL2YI\",\"OuterStr\":\"\",\"UserCardCode\":\"134361431665\"}";
//        JSONObject jsonObject = HttpApiUtil.httpPost("http://localhost:8881/card/event", json);
        JSONObject jsonObject = HttpApiUtil.httpPost("http://dohko.wechat.service.hualala.com:31558/card/event", json);
        System.out.println(jsonObject.toJSONString());
    }

}
