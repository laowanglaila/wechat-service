package com.hualala.app.wechat.sdk.mp.exception;

import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import lombok.Data;

/**
 * Created by renjianfei on 2017/8/4.
 */
@Data
public class WechatException extends RuntimeException {
    private String code;
    public WechatException(){
        super( WechatExceptionTypeEnum.WECHAT_COMMON_ERROR.getMessage());
        this.code = WechatExceptionTypeEnum.WECHAT_COMMON_ERROR.getCode();
    }
    public WechatException(String code,String message){
        super(message);
        this.code = code;
    }
    public WechatException(WechatExceptionTypeEnum typeEnum){
        super(typeEnum.getMessage());
        this.code = typeEnum.getCode();
    }
    public WechatException(WechatExceptionTypeEnum typeEnum,String message){
        super(typeEnum.getMessage()+":"+message);
        this.code = typeEnum.getCode();
    }
}
