package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by renjianfei on 2017/9/8.
 */

public interface BaseEventHanlder {
   void handler(JSONObject object);
}
