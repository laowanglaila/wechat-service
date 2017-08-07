package com.hualala.app.wechat.exception;

import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.core.base.ServiceException;
import lombok.Data;

/**
 * Created by renjianfei on 2017/8/4.
 */
@Data
public class WechatException extends ServiceException {

    public WechatException(String code,String message){
        super(code,message);
    }
    public WechatException(WechatExceptionTypeEnum typeEnum){
        super(typeEnum.getCode(),typeEnum.getMessage());
    }

}
