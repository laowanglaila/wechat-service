package com.hualala.app.wechat.exception;

import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.core.base.ServiceException;
import lombok.Data;

/**
 * Created by renjianfei on 2017/8/4.
 */
@Data
public class WechatException extends ServiceException {
    public WechatException(){
        super( WechatExceptionTypeEnum.WECHAT_COMMON_ERROR.getCode(),WechatExceptionTypeEnum.WECHAT_COMMON_ERROR.getMessage());
    }
    public WechatException(String code,String message){
        super(code,message);
    }
    public WechatException(WechatExceptionTypeEnum typeEnum){
        super(typeEnum.getCode(),typeEnum.getMessage());
    }
    public WechatException(WechatExceptionTypeEnum typeEnum,String message){
        super(typeEnum.getCode(),typeEnum.getMessage()+":"+message);
    }
}
