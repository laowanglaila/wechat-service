package com.hualala.app.wechat.event.builder;

import com.hualala.app.wechat.sdk.mp.api.WxMpService;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlMessage;
import com.hualala.app.wechat.sdk.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renjianfei
 */
public abstract class AbstractBuilder {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract WxMpXmlOutMessage build(String content,
                                            WxMpXmlMessage wxMessage, WxMpService service);
}
