package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by renjianfei on 2017/9/8.
 */
@Slf4j
public abstract class BaseEventCardEventHandler implements BaseEventHanlder {
     void logResult(ResultInfo resultInfo, RequestInfo requestInfo) {
        if (log.isDebugEnabled()) {
            log.debug( JSONObject.toJSONString( resultInfo.getMessageParams() ) );
        }
        if (!"000".equals( resultInfo.getCode() )) {
            Object[] messageParams = resultInfo.getMessageParams();
            String s = JSONObject.toJSONString( messageParams );
            if (log.isErrorEnabled()) {
                log.error( "卡券绑定-服务端执行失败:" + JSON.toJSONString( resultInfo ) + ";\n" + JSON.toJSONString( requestInfo ) );
            }
        }
    }

     void logError(Throwable e, RequestInfo requestInfo) {
        if (log.isErrorEnabled()) {
            log.error( "卡券绑定-GRPC通信异常:" + JSON.toJSONString( requestInfo ), e );
        }
    }
}
