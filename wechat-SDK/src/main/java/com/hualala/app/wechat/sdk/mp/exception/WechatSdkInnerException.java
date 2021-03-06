package com.hualala.app.wechat.sdk.mp.exception;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.WechatErrorCode;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * Created by renjianfei on 2017/8/30.
 */
@Getter
public class WechatSdkInnerException extends Exception {

    public WechatSdkInnerException(String message){
        super(message);
    }

    public static WechatSdkInnerException of(JSONObject jsonObject){
        return of( "",jsonObject );
    }
    public static WechatSdkInnerException of(String message, JSONObject jsonObject){
        if (StringUtils.isNotBlank( message )){
            message = "->"+message;
        }
        String objMessage = jsonObject.getString( WechatMessageType.MESSAGE );
        String errmsg = jsonObject.getString( "errmsg" );
        String errcode = jsonObject.getString( "errcode" );
        String errmsgCH = WechatErrorCode.wechatError.get( errcode );
        if (StringUtils.isNotBlank( errmsgCH )){
            errmsgCH = "_" + errmsgCH;
        }
        return new WechatSdkInnerException( objMessage + message + ":" + errcode + "("+errmsg + errmsgCH +")" );
    }
}
