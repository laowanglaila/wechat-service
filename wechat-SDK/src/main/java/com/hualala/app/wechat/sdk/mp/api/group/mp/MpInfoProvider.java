package com.hualala.app.wechat.sdk.mp.api.group.mp;

/**
 * Created by renjianfei on 2018/1/5.
 */
public interface MpInfoProvider {
   MpInfoRelation getMpInfoByAppID(String appID);
   MpInfoRelation getMpInfoByMpID(String mpID);
}
