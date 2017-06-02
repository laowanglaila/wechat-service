package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardEventProcessRpcService;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.core.app.Logger;
import org.springframework.stereotype.Service;

/**
 * 识别事件类型，根据事件类型作出处理
 * Created by renjianfei on 2017/6/1.
 */
@Service
public class CardEventProcessRpcServiceImpl implements CardEventProcessRpcService {
    private Logger logger = Logger.of(CardEventProcessRpcServiceImpl.class);
    @Override
    public EventProcessRes process(EventProcessReq json) {
        String jsonStr = json.getJson();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        logger.info(() -> jsonObject.toJSONString());
        String event = jsonObject.getString("event");
        EventProcessRes eventProcessRes = new EventProcessRes();

        if (WechatMessageType.EVENT_CARD_MEMBER_ACTIVE.equals(event)){
            //激活会员卡

        }else if (WechatMessageType.EVENT_CARD_NOT_PASS_CHECK.equals(event)){
            //未通过审核

        }else if (WechatMessageType.EVENT_CARD_PASS_CHECK.equals(event)){
            //通过审核

        }else if (WechatMessageType.EVENT_CARD_PAY_ORDER.equals(event)){
            //券点流水详情事件

        }else if (WechatMessageType.EVENT_CARD_SKU_REMIND.equals(event)){
            //库存报警事件

        }else if (WechatMessageType.EVENT_CARD_UPDATE_MEMBER.equals(event)){
            //会员卡内容更新事件

        }else if (WechatMessageType.EVENT_CARD_USER_CONSUME.equals(event)){
            //用户核销事件

        }else if (WechatMessageType.EVENT_CARD_USER_DEL.equals(event)){
            //用户删除事件

        }else if (WechatMessageType.EVENT_CARD_USER_ENTER.equals(event)){
            //用户从卡券进入公众号会话事件

        }else if (WechatMessageType.EVENT_CARD_USER_GET.equals(event)){
            //用户领取事件

        }else if (WechatMessageType.EVENT_CARD_USER_GIFTING.equals(event)){
            //用户转赠事件

        }else if (WechatMessageType.EVENT_CARD_USER_PAY_FROM_PAY_CELL.equals(event)){
            //用户买单事件

        }else if (WechatMessageType.EVENT_CARD_USER_VIEW.equals(event)){
            //用户进入会员卡事件（暂不接受压力大）

        }


        eventProcessRes.setJson(jsonStr);
        return eventProcessRes;
    }
}
