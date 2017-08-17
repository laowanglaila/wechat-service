package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;

/**
 * Created by renjianfei on 2017/8/17.
 */
public abstract class WechatRequestInfo extends RequestInfo {
    public abstract String getMpID();
    public abstract Long getGroupID();
    public abstract Long getBrandID();
    public abstract Long getShopID();
}
