package com.hualala.app.wechat.service.checkpermission.handler;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.checkpermission.AuthorizationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Created by renjianfei on 2017/10/13.
 */
@Component("TEMPORARY_QR_CODE")
public class QrCodeCheckHandler extends AuthorizationCheck {
    @Autowired
    private BaseHttpService baseHttpService;
    @Override
    public Boolean handle(String mpID) {
        String paramJson = "{\"action_name\": \"" + WechatMessageType.QR_LIMIT_SCENE
                + "\", \"action_info\": {\"scene\": {\"scene_id\": " + 0 + " }}}";
        JSONObject resultJson = baseHttpService.createQrCode( paramJson, mpID );
        if (WechatMessageType.FALSE == resultJson.getBoolean("isSuccess")) {
            return false;
        } else {
            return true;
        }
    }
}
