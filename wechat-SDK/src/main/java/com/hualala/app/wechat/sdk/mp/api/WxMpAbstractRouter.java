package com.hualala.app.wechat.sdk.mp.api;

/**
 * Created by renjianfei on 2018/1/7.
 */
public abstract class WxMpAbstractRouter {
    protected Boolean isAllowMsgDuplicate = true;
    public void allowedMsgDuplicate(Boolean isAllowMsgDuplicate) {
        this.isAllowMsgDuplicate = isAllowMsgDuplicate;
    }
}
