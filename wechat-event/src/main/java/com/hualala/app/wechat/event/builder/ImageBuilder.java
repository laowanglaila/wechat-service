package com.hualala.app.wechat.event.builder;

import com.hualala.app.wechat.sdk.mp.api.WxMpService;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlOutImageMessage;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author renjianfei
 */
public class ImageBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
                                   WxMpService service) {

        WxMpXmlOutImageMessage m = WxMpXmlOutMessage.IMAGE().mediaId(content)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();

        return m;
    }

}
