package com.hualala.app.wechat.event.handler;

import com.hualala.app.wechat.sdk.mp.api.WxMpService;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author renjianfei
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        String msg = String.format("type:%s, event:%s, key:%s",
                wxMessage.getMsgType(), wxMessage.getEvent(),
                wxMessage.getEventKey());
        if (WxConsts.BUTTON_VIEW.equals(wxMessage.getEvent())) {
            return null;
        }

        return WxMpXmlOutMessage.TEXT().content(msg)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }

}
