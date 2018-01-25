package com.hualala.app.wechat.mapper.invoice;

import com.hualala.app.wechat.model.invoice.InvoiceTemplateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InvoiceTemplateModelMapper {
    int insert(InvoiceTemplateModel invoiceTemplateModel);

    InvoiceTemplateModel selectByMpID(@Param("mpID") String mpID);
}
