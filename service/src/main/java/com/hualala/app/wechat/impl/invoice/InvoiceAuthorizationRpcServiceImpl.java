package com.hualala.app.wechat.impl.invoice;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.InvoiceAuthorizationRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.bean.invoice.InvoiceUrlRequest;
import com.hualala.app.wechat.service.ApiTicketService;
import com.hualala.app.wechat.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2018/1/16.
 */
@Slf4j
@Service
public class InvoiceAuthorizationRpcServiceImpl implements InvoiceAuthorizationRpcService {
    @Autowired
    private WxGroupMpService wxGroupMpService;
    @Autowired
    private ApiTicketService apiTicketService;
    @Override
    public InvoiceAuthorizationRes getAuthorizUrl(InvoiceAuthorizationReq invoiceAuthorizationReq) {
        String mpID = invoiceAuthorizationReq.getMpID();
        InvoiceUrlRequest invoiceUrlRequest = new InvoiceUrlRequest();
        invoiceUrlRequest.setMoney( invoiceAuthorizationReq.getMoney() );
        invoiceUrlRequest.setOrederID( invoiceAuthorizationReq.getOrederID() );
        invoiceUrlRequest.setRedirectUrl( invoiceUrlRequest.getRedirectUrl() );
        invoiceUrlRequest.setSource( invoiceAuthorizationReq.getSource() );
        invoiceUrlRequest.setSpAppID( invoiceAuthorizationReq.getSpAppID() );
        invoiceUrlRequest.setTimeStamp( invoiceAuthorizationReq.getTimeStamp() );
        invoiceUrlRequest.setType( invoiceAuthorizationReq.getType() );
        JSONObject wxCardApiTicket = apiTicketService.getWxCardApiTicket( mpID );
        if (!wxCardApiTicket.getBoolean( WechatMessageType.IS_SUCCESS )){
            return ResultUtil.getResultInfoBean( wxCardApiTicket,InvoiceAuthorizationRes.class );
        }
        String ticket = wxCardApiTicket.getString( "ticket" );
        invoiceUrlRequest.setTicket( ticket );
        String authUrl = null;
        try {
            authUrl = wxGroupMpService.getWxMpInvoiceService( mpID ).getAuthUrl( invoiceUrlRequest );
        } catch (WxErrorException e) {
            log.error( "获取电子发票授权链接失败",e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_ERROR,"获取电子发票授权链接失败:" + e.getMessage() );
        }
        InvoiceAuthorizationRes invoiceAuthorizationRes = new InvoiceAuthorizationRes();
        invoiceAuthorizationRes.setAuthoricatedUrl( authUrl );
        return invoiceAuthorizationRes;
    }
}
