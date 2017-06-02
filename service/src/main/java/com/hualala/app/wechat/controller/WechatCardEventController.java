package com.hualala.app.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardEventProcessRpcService;
import com.hualala.app.wechat.impl.card.CardEventProcessRpcServiceImpl;
import com.hualala.core.app.Logger;
import com.hualala.core.client.BaseRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by renjianfei on 2017/5/22.
 */
@RestController
public class WechatCardEventController {
    private static Logger logger = Logger.of(WechatCardEventController.class);

    @Autowired
    public BaseRpcClient baseRpcClient;

    @RequestMapping("/card/event")
    public JSONObject event(HttpServletRequest request) {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        System.out.println(JSONObject.toJSONString(parameterMap));
        ServletInputStream is = null;
        StringBuilder sb = null;
        try {
            is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error(() -> "HttpRequest: ", () -> e);
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String json = sb.toString();
        logger.info(() -> "HttpRequest: " + json);
        CardEventProcessRpcService rpcClient = new CardEventProcessRpcServiceImpl();
        CardEventProcessRpcService.EventProcessReq eventProcessReq = new CardEventProcessRpcService.EventProcessReq();
        eventProcessReq.setJson(json);
        CardEventProcessRpcService.EventProcessRes process = rpcClient.process(eventProcessReq);
        String resJson = process.getJson();
        JSONObject jsonObject = JSONObject.parseObject(resJson);
        return jsonObject;
    }

}
