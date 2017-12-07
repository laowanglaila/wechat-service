package com.hualala.app.wechat.event.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import com.hualala.app.wechat.sdk.mp.api.WxMpService;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author renjianfei
 */
@Component
public class NullHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        return null;
    }

}
