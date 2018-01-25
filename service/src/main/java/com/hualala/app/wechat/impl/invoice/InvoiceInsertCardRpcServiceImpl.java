package com.hualala.app.wechat.impl.invoice;

import com.hualala.app.wechat.InvoiceInsertCardRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.mapper.invoice.InvoiceTemplateModelMapper;
import com.hualala.app.wechat.model.invoice.InvoiceTemplateModel;
import com.hualala.app.wechat.model.mp.MpInfoCache;
import com.hualala.app.wechat.sdk.mp.api.WxMpInvoiceService;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.bean.invoice.InvoiceInsertRequest;
import com.hualala.app.wechat.service.MpInfoService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Houguanglin
 * @date 2018.1.18
 */
@Slf4j
@Service
public class InvoiceInsertCardRpcServiceImpl implements InvoiceInsertCardRpcService {

    @Autowired
    WxGroupMpService wxGroupMpService;
    @Autowired
    MpInfoService mpInfoService;
    @Autowired
    InvoiceTemplateModelMapper invoiceTemplateModelMapper;

    @Override
    public InvoiceInsertCardRpcService.InvoiceInsertResData insert(InvoiceInsertCardRpcService.InvoiceInsertReqData invoiceInsertReqData) {

        WxMpInvoiceService wxMpInvoiceService = wxGroupMpService.getWxMpInvoiceService(invoiceInsertReqData.getMpID());
        String mediaID = wxMpInvoiceService.uploadPdf(invoiceInsertReqData.getInvoiceUrl());
        String tripMediaID = wxMpInvoiceService.uploadPdf(invoiceInsertReqData.getInvoiceAccessoryUrl());
        InvoiceTemplateModel invoiceTemplateModel = invoiceTemplateModelMapper.selectByMpID(invoiceInsertReqData.getMpID());

        MpInfoCache mpInfoByMpID = null;
        try {
            mpInfoByMpID = mpInfoService.getMpInfoByMpID(invoiceInsertReqData.getMpID());
        } catch (WechatInnerException e) {
            log.error("MpID错误或不存在，无对应平台信息",e);
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_MPINFO_INCOMPLETE);
        }
        InvoiceInsertRequest invoiceInsertRequest = InvoiceInsertRequest.newBuilder()
                                                                        .setBuyerAddressAndPhone(invoiceInsertReqData.getBuyerAddressAndPhone())
                                                                        .setBillingTime(invoiceInsertReqData.getBillingTime())
                                                                        .setBillingNo(invoiceInsertReqData.getBillingNo())
                                                                        .setBillingCode(invoiceInsertReqData.getBillingCode())
                                                                        .setAppid(mpInfoByMpID.getAppID())
                                                                        .setBuyerBankAccount(invoiceInsertReqData.getBuyerBankAccount())
                                                                        .setBuyerNumber(invoiceInsertReqData.getBuyerNumber())
                                                                        .setCardID(invoiceTemplateModel.getCardID())
                                                                        .setCashier(invoiceInsertReqData.getCashier())
                                                                        .setCheckCode(invoiceInsertReqData.getCheckCode())
                                                                        .setFee(invoiceInsertReqData.getFee())
                                                                        .setFeeWithoutTax(invoiceInsertReqData.getFeeWithoutTax())
                                                                        .setMaker(invoiceInsertReqData.getMaker())
                                                                        .setOrderID(invoiceInsertReqData.getOrderID())
                                                                        .setRemarks(invoiceInsertReqData.getRemarks())
                                                                        .setSellerAddressAndPhone(invoiceInsertReqData.getSellerAddressAndPhone())
                                                                        .setSellerBankAccount(invoiceInsertReqData.getSellerBankAccount())
                                                                        .setSellerNumber(invoiceInsertReqData.getSellerNumber())
                                                                        .setsPdfMediaID(mediaID)
                                                                        .setsTripPdfMediaID(tripMediaID)
                                                                        .setTax(invoiceInsertReqData.getTax())
                                                                        .setTitle(invoiceInsertReqData.getTitle()).build();


        try {
            wxMpInvoiceService.insert(invoiceInsertRequest);

        } catch (WxErrorException e) {
            log.error("发票插入微信卡包失败",e);
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_USER_SERVICE_ERROR,"获取用户服务失败");
        }

        return new InvoiceInsertCardRpcService.InvoiceInsertResData() ;

    }


}
