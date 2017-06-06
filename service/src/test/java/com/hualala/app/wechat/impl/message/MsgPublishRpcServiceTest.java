package com.hualala.app.wechat.impl.message;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.MsgPublishRpcService;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renjianfei on 2017/6/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgPublishRpcServiceTest {
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private BaseRpcClient baseRpcClient;

    @Test
    public void test(){
        String text = "群发测试Hellow word!";
        String mpID = "doulaofangceshi";

        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
        String params = "{ " +
                "    \"towxname\":\"xukai80231314\"," +
                "    \"text\":{\"content\":\""+text+"\"}," +
                "    \"msgtype\":\"text\"" +
                "}";
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, params, mpID);
        System.out.println(jsonObject);
    }

    @Autowired
    private UserModelMapper userModelMapper;
    @Test
    public void test2(){
        ArrayList<Long> list = new ArrayList<>();
        list.add(3573647L);
        list.add(3573827L);
        list.add(3573610L);
        list.add(3573831L);
        ArrayList<Integer> actions = new ArrayList<>();
        actions.add(0);
        actions.add(1);
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria()
//                .andActionEqualTo(0)
                .andActionIn(actions)
                .andIsSubscribeEqualTo(1)
                .andUserIDIn(list)
        .andMpIDEqualTo("doulaofangceshi");

        List<String> userModels = userModelMapper.selectByUserList(userModelQuery);
        System.out.println(userModels);
    }
    @Test
    public void test3(){
        MsgPublishRpcService rpcClient = baseRpcClient.getRpcClient(MsgPublishRpcService.class);
        MsgPublishRpcService.TextMsgReq textMsgReq = new MsgPublishRpcService.TextMsgReq();
        ArrayList<Long> list = new ArrayList<>();
        list.add(3573647L);
        list.add(3573827L);
        list.add(3573610L);
        list.add(3573831L);
        list.add(3573900L);
        list.add(3573929L);
        list.add(3573966L);
        list.add(3574383L);
        textMsgReq.setUserIds(list);
        textMsgReq.setMpID("doulaofangceshi");
        textMsgReq.setText("群发消息");
        MsgPublishRpcService.TextMsgRes textMsgRes = rpcClient.publishTextByOpenID(textMsgReq);


        System.out.println(textMsgRes.getMessage());
    }
}
