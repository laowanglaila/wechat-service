package com.hualala.app.wechat.sdk.mp.api;

import com.hualala.app.wechat.sdk.mp.bean.invoice.*;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 电子发票相关接口
 *
 * Created by renjianfei on 2018/1/13.
 */
public interface WxMpInvoiceService {
    //创建电子发票模板
    String INVOICE_CREATE = "https://api.weixin.qq.com/card/invoice/platform/createcard";
    String INVOICE_SET_PDF = "https://api.weixin.qq.com/card/invoice/platform/setpdf";
    String INVOICE_GET_AUTH_URL= "https://api.weixin.qq.com/card/invoice/getauthurl";
    String INVOICE_GET_AUTH_DATA= "https://api.weixin.qq.com/card/invoice/getauthdata";
    String INVOICE_INSERT= "https://api.weixin.qq.com/card/invoice/insert";
    String INVOICE_REJECT_INSERT= "https://api.weixin.qq.com/card/invoice/rejectinsert";

    /**
     * 创建电子发票模板
     */
    InvoiceResult createInvoiceTemplate(InvoiceInfo invoiceInfo) throws WxErrorException;

    /**
     * 上传电子发票pdf 返回mediaID
     * @param sourceUrl
     * @return
     */
    String uploadPdf(String sourceUrl);
    /**
     * 获取授权URL
     * @param request
     * @return
     */
    String getAuthUrl(InvoiceUrlRequest request) throws WxErrorException;
    /**
     * 获取订单授权状态
     * @param
     * @return
     */
    InvoiceAuthInfo getAuthData(String orderID, String spAppID) throws WxErrorException;
    /**
     * 插入卡券包
     * @param
     * @return
     */
    InvoiceInsertResponse insert(InvoiceInsertRequest invoiceInsertRequest) throws WxErrorException;
    /**
     * 拒绝开票
     * @param
     * @return
     */
    void reject(InvoiceRejectRequest request) throws WxErrorException;
}
