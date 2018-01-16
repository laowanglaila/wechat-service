package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by renjianfei on 2018/1/15.
 */
@RpcService
public interface InvoiceTemplateRpcService {
    @RpcMethod(description = "创建微信电子发票模板")
    InvoiceInfoRes create(InvoiceInfoReq invoiceInfoReq);

    @Data
    class InvoiceInfoReq extends RequestInfo {
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "收款方（开票方）全称，显示在发票详情内。故建议一个收款方对应一个发票卡券模板")
        String payee;
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "发票类型")
        String type;
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "发票商家 LOGO ，请参考 上传图片接口")
        String logoUrl;
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "收款方（显示在列表），上限为 9 个汉字，建议填入商户简称")
        String title;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "发票使用说明。可以介绍电子发票的背景、报销使用流程等")
        String descriprion;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "开票平台自定义入口名称，与 custom_url 字段共同使用，长度限制在 5 个汉字内")
        String customUrlName;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "开票平台自定义入口跳转外链的地址链接 , 发票外跳的链接会带有发票参数，用于标识是从哪张发票跳出的链接")
        String customUrl;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "显示在入口右侧的 tips ，长度限制在 6 个汉字内")
        String customUrlSubTitle;
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "营销场景的自定义入口")
        String promotionUrlName;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "入口跳转外链的地址链接，发票外跳的链接会带有发票参数，用于标识是从那张发票跳出的链接")
        String promotionUrl;
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "显示在入口右侧的 tips ，长度限制在 6 个汉字内")
        String promotionUrlSubTitle;
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "公众号唯一ID")
        String mpID;
    }
    @Data
    class InvoiceInfoRes extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "电子发票模板ID")
        String cardID;
    }

}
