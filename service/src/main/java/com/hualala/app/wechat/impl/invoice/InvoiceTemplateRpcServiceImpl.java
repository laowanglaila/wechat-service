package com.hualala.app.wechat.impl.invoice;

import com.hualala.app.wechat.InvoiceTemplateRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.mapper.invoice.InvoiceTemplateModelMapper;
import com.hualala.app.wechat.model.invoice.InvoiceTemplateModel;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.bean.invoice.InvoiceInfo;
import com.hualala.app.wechat.sdk.mp.bean.invoice.InvoiceResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2018/1/15.
 */
@Slf4j
@Service
public class InvoiceTemplateRpcServiceImpl implements InvoiceTemplateRpcService {
    @Autowired
    private WxGroupMpService wxGroupMpService;

    @Autowired
    InvoiceTemplateModelMapper invoiceTemplateModelMapper;

    @Override
    public InvoiceInfoRes create(InvoiceInfoReq invoiceInfoReq) {
        InvoiceInfo build = InvoiceInfo.newBuilder()
                                       .setCustomUrlName( invoiceInfoReq.getCustomUrlName() )
                                       .setCustomUrlSubTitle( invoiceInfoReq.getCustomUrlSubTitle() )
                                       .setCustomUtl( invoiceInfoReq.getCustomUrl() )
                                       .setDescriprion( invoiceInfoReq.getDescriprion() )
                                       .setLogoUrl( invoiceInfoReq.getLogoUrl() )
                                       .setPayee( invoiceInfoReq.getPayee() )
                                       .setPromotionUrl( invoiceInfoReq.getPromotionUrl() )
                                       .setPromotionUrlName( invoiceInfoReq.getPromotionUrlName() )
                                       .setPromotionUrlSubTitle( invoiceInfoReq.getPromotionUrlSubTitle() )
                                       .setTitle( invoiceInfoReq.getTitle() )
                                       .setType( invoiceInfoReq.getType() )
                                       .build();
        InvoiceResult invoiceTemplate = null;
        try {
            invoiceTemplate = wxGroupMpService.getWxMpInvoiceService( invoiceInfoReq.getMpID() ).createInvoiceTemplate( build );
            
        } catch (WxErrorException e) {
            log.error( "创建电子发票模板失败！",e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_ERROR ,e.getMessage());
        }

        //将发票信息落库
        InvoiceTemplateModel invoiceTemplateModel = new InvoiceTemplateModel();
        invoiceTemplateModel.setCardID(invoiceTemplate.getCardID());
        invoiceTemplateModel.setInvoiceType(invoiceInfoReq.getType());
        invoiceTemplateModel.setLogoUrl(invoiceInfoReq.getLogoUrl());
        invoiceTemplateModel.setMpID(invoiceInfoReq.getMpID());
        invoiceTemplateModel.setPayee(invoiceInfoReq.getPayee());
        invoiceTemplateModel.setTitle(invoiceInfoReq.getTitle());
        invoiceTemplateModelMapper.insert(invoiceTemplateModel);

        InvoiceInfoRes invoiceInfoRes = new InvoiceInfoRes();
        invoiceInfoRes.setCardID( invoiceTemplate.getCardID() );
        return invoiceInfoRes;
    }
}
