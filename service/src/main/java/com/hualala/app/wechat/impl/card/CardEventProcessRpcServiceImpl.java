package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardEventProcessRpcService;
import com.hualala.app.wechat.impl.EventHandler.BaseEventCardEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 识别事件类型，根据事件类型作出处理
 * Created by renjianfei on 2017/6/1.
 */
@Service
@Slf4j
public class CardEventProcessRpcServiceImpl implements CardEventProcessRpcService {

    @Override
    public EventProcessRes process(EventProcessReq json) {
        String jsonStr = json.getJson();
        JSONObject jsonObject = JSONObject.parseObject( jsonStr );
        String event = jsonObject.getString( "Event" );
        if (log.isInfoEnabled()) {
            log.info( "CardEventProcessRpcService.process()\r\nEvent:{}\r\n$params:{}", event, jsonObject );
        }
        EventProcessRes eventProcessRes = new EventProcessRes();

        BaseEventCardEventHandler baseEventCardEventHandler = BaseEventCardEventHandler
                .create( event );
        if (baseEventCardEventHandler != null){
            baseEventCardEventHandler.handler( jsonObject );
        }
        eventProcessRes.setJson( jsonStr );
        return eventProcessRes;
    }

}
