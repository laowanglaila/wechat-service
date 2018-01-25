package com.hualala.app.wechat.impl.EventHandler.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.user.UserInvoiceCardService;
import com.hualala.app.wechat.impl.EventHandler.BaseEventCardEventHandler;
import com.hualala.core.client.BaseRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by renjianfei on 2018/1/15.
 */
@Component
public class InvoiceUserAuthorizeEventHandler extends BaseEventCardEventHandler {
    @Autowired
    private BaseRpcClient baseRpcClient;

    @Override
    public Object handler(JSONObject jsonObject) {
//        ToUserName	String	是	公众号标识
        String toUserName = jsonObject.getString( "ToUserName" );
//        FromUserName	String	是	用户openid
        String fromUserName = jsonObject.getString( "FromUserName" );
//        SuccOrderId	String	否	授权成功的订单号，与失败订单号两者必显示其一
        String succOrderId = jsonObject.getString( "SuccOrderId" );
//        FailOrderId	String	否	授权失败的订单号，与成功订单号两者必显示其一
        String failOrderId = jsonObject.getString( "FailOrderId" );
//        AuthorizeAppId	String	是	用于接收事件推送的公众号的AppId
//        Source	String	是	授权来源，web表示来自微信内H5，app标识来自app


        UserInvoiceCardService client = baseRpcClient.getRpcClient(UserInvoiceCardService.class);

        UserInvoiceCardService.WeChatInsertCardReqData reqData = new UserInvoiceCardService.WeChatInsertCardReqData();
        reqData.setTaxOrderNo(succOrderId);

        UserInvoiceCardService.WeChatInsertCardResData weChatInsertCardResData = client.weChatInsertCard(reqData);
//      weChatInsertCardResData.success();   如果请求发票信息失败怎么办?????

        return null;
    }
}
