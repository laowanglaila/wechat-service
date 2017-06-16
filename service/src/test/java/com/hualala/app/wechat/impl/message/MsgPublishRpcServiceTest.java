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
        String text = "群发测试Hellow word!\r\n<a url=\"http://www.hualala.com/\">hualala<a/>";
        String mpID = "doulaofangceshi";
//        String mpID = "hualala_com";

        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
//        String params = "{ " +
//                "    \"towxname\":\"renfeifei_1314\"," +
//                "    \"text\":{\"content\":\""+text+"\"}," +
//                "    \"msgtype\":\"text\"" +
//                "}";
        String params = "{\n" +
//                        "  \"touser\":\"o7FjEuBvkjdVaJBkrQt8GNqMuSIQ\", \n" +
                      "    \"towxname\":\"renfeifei_1314\"," +
                        "  \"msgtype\":\"wxcard\",\n" +
//                        "  \"wxcard\":{\"card_id\":\"pACwGs9SukuPsEQ42U4DYdJJxOjw\"},\n" +
                        "  \"wxcard\":{\"card_id\":\"p7FjEuKXEJY0ktzCj_R7J5xY0KAI\"},\n" +
                        "}";
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, params, mpID);
        System.out.println(jsonObject);
    }

    /**
     * 图文消息预览
     */
    @Test
    public void test4(){
        String text = "群发测试Hellow word!\r\n<a url=\"http://www.hualala.com/\">hualala<a/>";
//        String mpID = "doulaofangceshi";
        String mpID = "hualala_com";

        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
//        String params = "{ " +
//                "    \"towxname\":\"renfeifei_1314\"," +
//                "    \"text\":{\"content\":\""+text+"\"}," +
//                "    \"msgtype\":\"text\"" +
//                "}";
        String params = "{\n" +
//                        "  \"touser\":\"o7FjEuBvkjdVaJBkrQt8GNqMuSIQ\", \n" +
                      "    \"towxname\":\"renfeifei_1314\"," +
                        "  \"msgtype\":\"mpnews\",\n" +
//                        "  \"wxcard\":{\"card_id\":\"pACwGs9SukuPsEQ42U4DYdJJxOjw\"},\n" +
                        "  \"mpnews\":{\"media_id\":\"oEevPYTm4Q0qmOl7f8kK_m5O30AQG-uSQW00hAhnf1JGonEhMgmUgfU2tuhKa7U_\"},\n" +
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
        MsgPublishRpcService.TextMsgRes textMsgRes = rpcClient.publishTextByUserList(textMsgReq);


        System.out.println(textMsgRes.getMessage());
    }
}
