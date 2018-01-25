package com.hualala.app.wechat.model.invoice;

import com.hualala.core.base.BaseItem;
import lombok.Data;

@Data
public class InvoiceTemplateModel extends BaseItem{
    /**
     * 公众号唯一id
     */
    private String mpID;

    /**
     * 提交模板申请后，返回的发票卡券模板编号，用于后续该商户发票生成后，作为必填参数在调用插卡接口时传入
     */
    private String cardID;

    /**
     * 收款方（开票方）全称，显示在发票详情内。故建议一个收款方对应一个发票卡券模板
     */
    private String payee;

    /**
     *发票类型
     */
    private String invoiceType;

    /**
     * 发票商家 LOGO ，请参考 上传图片接口
     */
    private String logoUrl;

    /**
     * 收款方（显示在列表），上限为 9 个汉字，建议填入商户简称
     */
    private String title;

}
