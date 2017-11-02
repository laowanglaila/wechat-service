package com.hualala.app.wechat.service.checkpermission;

import com.hualala.app.wechat.WechatFuctionEnum;
import com.hualala.app.wechat.sdk.mp.util.WechatBeanFactory;

/**
 * Created by renjianfei on 2017/10/13.
 */
public abstract class AuthorizationCheck {
    public abstract Boolean handle(String mpID);
    public static AuthorizationCheck create(WechatFuctionEnum interfaceType){
        switch (interfaceType){
            case TEMPORARY_QR_CODE:
                return WechatBeanFactory.getBean( interfaceType.name() ,AuthorizationCheck.class);
            default:
                return null;
        }
    }
}
