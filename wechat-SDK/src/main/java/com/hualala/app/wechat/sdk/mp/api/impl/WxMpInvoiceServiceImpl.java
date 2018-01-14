package com.hualala.app.wechat.sdk.mp.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.api.WxMpInvoiceService;
import com.hualala.app.wechat.sdk.mp.api.WxMpService;
import com.hualala.app.wechat.sdk.mp.bean.invoice.*;
import com.hualala.app.wechat.sdk.mp.bean.material.WxMpMaterialUploadResult;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.exception.WechatException;
import com.hualala.app.wechat.sdk.mp.util.FileUtil;
import com.hualala.app.wechat.sdk.mp.util.http.okhttp.OkhttpInvoiceUploadExecutor;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by renjianfei on 2018/1/13.
 */
@Slf4j
public class WxMpInvoiceServiceImpl implements WxMpInvoiceService {

    private WxMpService wxMpService;
    public WxMpInvoiceServiceImpl( WxMpService wxMpService){
        this.wxMpService = wxMpService;
    }

    @Override
    public InvoiceResult createInvoiceTemplate(InvoiceInfo invoiceInfo) throws WxErrorException {
        String result = wxMpService.post( INVOICE_CREATE, invoiceInfo.toJson() );
        InvoiceResult invoiceResult = InvoiceResult.fromJson( result );
        return invoiceResult;
    }

    public String uploadPdf(String sourceUrl){
        String suffix = ".pdf";
        File file = null;
        String mediaID = null;
        try {
            file = File.createTempFile( UUID.randomUUID().toString(), suffix);
            FileUtil.downloadFile(sourceUrl, file.getPath(), 1000, 1000);
            WxMpMaterialUploadResult execute = wxMpService.execute( OkhttpInvoiceUploadExecutor.create( this.wxMpService.getRequestHttp() ), INVOICE_SET_PDF, file );
            mediaID = execute.getMediaId();
        } catch (IOException e) {
            log.error(">>> update user photoImage is error",e);
        } catch (WxErrorException e){
            log.error( ">>> update user photoImage is error",e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_ERROR,e.getMessage());
        } finally{
            if (file != null) {
                file.delete();
            }
        }
        return mediaID;
    }

    @Override
    public String getAuthUrl(InvoiceUrlRequest request) throws WxErrorException {
        String result = wxMpService.post( INVOICE_GET_AUTH_URL, request.toJson() );
        return JSONObject.parseObject( result ).getString( "auth_url" );
    }
    @Override
    public InvoiceAuthInfo getAuthData(String orderID, String spAppID) throws WxErrorException {
        String json = "{\"order_id\":\"" + orderID + "\"s_pappid\":\"" + spAppID + "\"}";
        String result = wxMpService.post( INVOICE_GET_AUTH_DATA, json );
        return InvoiceAuthInfo.fromJson( result );
    }

    @Override
    public InvoiceInsertResponse insert(InvoiceInsertRequest request) throws WxErrorException {
        String result = wxMpService.post( INVOICE_INSERT, request.toJson() );
        return WxMpGsonBuilder.create().fromJson( result,InvoiceInsertResponse.class );
    }

    @Override
    public void reject(InvoiceRejectRequest request) throws WxErrorException {
        wxMpService.post( INVOICE_REJECT_INSERT, request.toJson() );
    }
}
