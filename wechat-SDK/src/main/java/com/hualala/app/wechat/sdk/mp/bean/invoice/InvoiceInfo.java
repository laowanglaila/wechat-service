package com.hualala.app.wechat.sdk.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by renjianfei on 2018/1/13.
 */
@Data
public class InvoiceInfo {
    private InvoiceInfo() {
    }

    //    base_info	object	是	发票卡券模板基础信息
    @SerializedName("base_info")
    private BaseInfo baseInfo;
    //    payee	string	是	收款方（开票方）全称，显示在发票详情内。故建议一个收款方对应一个发票卡券模板
    private String payee;
    //    type	string	是	发票类型
    private String type;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        BaseInfo baseInfo = new BaseInfo();
        String payee;
        String type;

        private Builder() {

        }

        public Builder setPayee(String payee) {
            this.payee = payee;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setLogoUrl(String logoUrl) {
            this.baseInfo.logoUrl = logoUrl;
            return this;
        }

        public Builder setTitle(String title) {
            this.baseInfo.title = title;
            return this;
        }

        public Builder setDescriprion(String descriprion) {
            this.baseInfo.descriprion = descriprion;
            return this;
        }

        public Builder setCustomUrlName(String customUrlName) {
            this.baseInfo.customUrlName = customUrlName;
            return this;
        }

        public Builder setCustomUtl(String customUtl) {
            this.baseInfo.customUtl = customUtl;
            return this;
        }

        public Builder setCustomUrlSubTitle(String customUrlSubTitle) {
            this.baseInfo.customUrlSubTitle = customUrlSubTitle;
            return this;
        }

        public Builder setPromotionUrlName(String promotionUrlName) {
            this.baseInfo.promotionUrlName = promotionUrlName;
            return this;
        }

        public Builder setPromotionUrl(String promotionUrl) {
            this.baseInfo.promotionUrl = promotionUrl;
            return this;
        }

        public Builder setPromotionUrlSubTitle(String promotionUrlSubTitle) {
            this.baseInfo.promotionUrlSubTitle = promotionUrlSubTitle;
            return this;
        }

        public InvoiceInfo build() {
            InvoiceInfo invoiceInfo = new InvoiceInfo();
            invoiceInfo.setBaseInfo( this.baseInfo );
            invoiceInfo.setPayee( this.payee );
            invoiceInfo.setType( this.type );
            return invoiceInfo;
        }
    }

    public static class BaseInfo {


        //        logo_url	string	是	发票商家 LOGO ，请参考 上传图片接口
        @NotBlank
        @SerializedName( "logo_url" )
        String logoUrl;
        //        title	string	是	收款方（显示在列表），上限为 9 个汉字，建议填入商户简称
        @NotBlank
        String title;
        //        description	string	否	发票使用说明。可以介绍电子发票的背景、报销使用流程等
        String descriprion;
        //        custom_url_name	string	否	开票平台自定义入口名称，与 custom_url 字段共同使用，长度限制在 5 个汉字内
        @SerializedName( "custom_url_name" )
        String customUrlName;
        //        custom_url	string	否	开票平台自定义入口跳转外链的地址链接 , 发票外跳的链接会带有发票参数，用于标识是从哪张发票跳出的链接，详情请见 备注6.2 。
        @SerializedName( "custom_url" )
        String customUtl;
        //        custom_url_sub_title	string	否	显示在入口右侧的 tips ，长度限制在 6 个汉字内
        @NotBlank
        @SerializedName( "custom_url_sub_title" )
        String customUrlSubTitle;
        //        promotion_url_name	string	否	营销场景的自定义入口
        @SerializedName( "promotion_url_name" )
        String promotionUrlName;
        //        promotion_url	string	否	入口跳转外链的地址链接，发票外跳的链接会带有发票参数，用于标识是从那张发票跳出的链接，详情请见 备注6.2 。
        @SerializedName( "promotion_url" )
        String promotionUrl;
        //        promotion_url_sub_title	string	否	显示在入口右侧的 tips ，长度限制在 6 个汉字内
        @SerializedName( "promotion_url_sub_title" )
        String promotionUrlSubTitle;

        private BaseInfo() {
        }
    }
    public String toJson(){
        return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

}
