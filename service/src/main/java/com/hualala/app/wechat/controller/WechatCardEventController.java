package com.hualala.app.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardEventProcessRpcService;
import com.hualala.app.wechat.CardStatusEnum;
import com.hualala.app.wechat.CardSyncRpcService;
import com.hualala.app.wechat.impl.card.CardEventProcessRpcServiceImpl;
import com.hualala.core.app.Logger;
import com.hualala.core.client.BaseRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/5/22.
 */
@Controller
@RequestMapping("/card")
public class WechatCardEventController {
    private static Logger logger = Logger.of(WechatCardEventController.class);

    @Autowired
    public BaseRpcClient baseRpcClient;

    @RequestMapping("/event")
    public JSONObject event(HttpServletRequest request) {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        System.out.println(JSONObject.toJSONString(parameterMap));
        InputStreamReader is = null;
        StringBuilder sb = null;
        try {
            is = new InputStreamReader(request.getInputStream());
            BufferedReader reader = new BufferedReader(is);
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
    @RequestMapping("/download")
    public @ResponseBody List<CardSyncRpcService.CardListResData> downLoad(@RequestParam String mpID, HttpServletRequest request, HttpServletResponse httpServletResponse) throws ExecutionException, InterruptedException {
        int count = 10;
        int offset = 0;
        List<CardSyncRpcService.CardListResData> list = new ArrayList<>();
        CardSyncRpcService.CardListResData cardList = get(mpID, count, offset);
        list.add(cardList);
        int totalNum = cardList.getTotalNum();
        if (totalNum > count){
            double multiple = 1.0 * totalNum / count;
            Double times = Math.ceil(multiple);
            for (int t = 1; t < times.intValue();t++ ) {
                offset = count*t;
                list.add(get(mpID, count, offset));
            }
        }
        return list;
    }

    @Async
    public CardSyncRpcService.CardListResData get(String mpID, int count, int offset){
        CardSyncRpcService rpcClient = baseRpcClient.getRpcClient(CardSyncRpcService.class);
        CardSyncRpcService.CardListReqData cardListReqData = new CardSyncRpcService.CardListReqData();
        cardListReqData.setCount(count);
        cardListReqData.setOffset(offset);
        cardListReqData.setMpID(mpID);
        List<String> cardStatusEnums = new ArrayList<>();
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_NOT_VERIFY.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_VERIFY_FAIL.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_VERIFY_OK.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_DISPATCH.name());
        cardStatusEnums.add(CardStatusEnum.CARD_STATUS_DELETE.name());
        cardListReqData.setStatusList(cardStatusEnums);
        CardSyncRpcService.CardListResData cardList = rpcClient.getCardList(cardListReqData);
        if ("000".equals(cardList.getCode())) {
            List<String> cardIdList = cardList.getCardIdList();
            CardSyncRpcService.CardDownloadReqData cardDownloadReqData = new CardSyncRpcService.CardDownloadReqData();
            cardDownloadReqData.setMpID(mpID);
            StringBuilder builder = new StringBuilder();
                for (String cardID : cardIdList) {
                cardDownloadReqData.setCardID(cardID);
                CardSyncRpcService.CardDownloadResData cardDownloadResData = rpcClient.downloadCardInfo(cardDownloadReqData);
                builder.append( JSONObject.toJSONString(cardDownloadResData) + ",");
            }
            String message = cardList.getMessage();
            cardList.setMessage("{\"message\":\"" + message + "\",\"dowloadResult\":[" + builder.toString().substring(0,builder.toString().length()-1) + "]}");
        }
        return cardList;
    }

}
