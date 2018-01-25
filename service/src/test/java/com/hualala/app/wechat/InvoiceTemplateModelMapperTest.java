package com.hualala.app.wechat;

import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.mapper.invoice.InvoiceTemplateModelMapper;
import com.hualala.app.wechat.model.invoice.InvoiceTemplateModel;
import com.hualala.app.wechat.service.ComponentTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceTemplateModelMapperTest {

    @Autowired
    private InvoiceTemplateModelMapper invoiceTemplateModelMapper;

    @Test
    public void insertTest() {
        InvoiceTemplateModel invoiceTemplateModel =new InvoiceTemplateModel();
        invoiceTemplateModel.setCardID("mpID13412342134");
        invoiceTemplateModel.setInvoiceType("cardID32143124");
        invoiceTemplateModel.setLogoUrl("hualalala");
        invoiceTemplateModel.setMpID("1234134123412");
        invoiceTemplateModel.setPayee("pyee31434124");
        invoiceTemplateModel.setTitle("http://;alsdjfasjdflkdjf;lsa");
        invoiceTemplateModelMapper.insert(invoiceTemplateModel);
    }
}