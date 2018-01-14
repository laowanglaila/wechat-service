package com.hualala.app.wechat.sdk.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.Data;

import java.util.*;

/**
 * Created by renjianfei on 2018/1/14.
 */
public class InvoiceInsertRequest {

    //    order_id	string	是	发票order_id
    @SerializedName("order_id")
    private String orderID;
    //    card_id	String	是	发票card_id
    @SerializedName("card_id")
    private String cardID;
    //    appid	String	是	该订单号授权时使用的appid，一般为商户appid
    private String appid;
    //    card_ext	Object	是	发票具体内容
    @SerializedName("card_ext")
    private CardExt cardExt;

    public static Builder newBuilder() {
        return new Builder();
    }

    @Data
    static class CardExt {
        //        nonce_str	String	是	随机字符串，防止重复
        @SerializedName("nonce_str")
        private String nonceStr = UUID.randomUUID().toString();
        //        user_card	Object	是	用户信息结构体
        @SerializedName("user_card")
        private Map <String, Object> userCard;
    }

    @Data
    static class InvoiceUserData {
        //        fee	Int	是	发票的金额，以分为单位
        Integer fee;
        //        title	String	是	发票的抬头
        String title;
        //        billing_time	Int	是	发票的开票时间，为10位时间戳（utc+8）
        @SerializedName("billing_time")
        Integer billingTime;
        //        billing_no	String	是	发票的发票代码
        @SerializedName("billing_no")
        String billingNo;
//        billing_code	String	是	发票的发票号码
        @SerializedName("billing_code")
        String billingCode;
        //        info	List	否	商品详情结构，见下方
        @SerializedName("info")
        List <Info> infoList;
        //        fee_without_tax	Int	是	不含税金额，以分为单位
        @SerializedName("fee_without_tax")
        Integer feeWithoutTax;
        //        tax	Int	是	税额，以分为单位
        Integer tax;
        //        s_pdf_media_id	String	是	发票pdf文件上传到微信发票平台后，会生成一个发票s_media_id，该s_media_id可以直接用于关联发票PDF和发票卡券。发票上传参考“ 上传PDF ”一节
        @SerializedName("s_pdf_media_id")
        String sPdfMediaID;
        //        s_trip_pdf_media_id	String	否	其它消费附件的PDF，如行程单、水单等，PDF上传方式参考“ 上传PDF ”一节
        @SerializedName("s_trip_pdf_media_id")
        String sTripPdfMediaID;
        //        check_code	String	是	校验码
        @SerializedName("check_code")
        String checkCode;
        //        buyer_number	String	否	购买方纳税人识别号
        @SerializedName("buyer_number")
        String buyerNumber;
        //        buyer_address_and_phone	String	否	购买方地址、电话
        @SerializedName("buyer_address_and_phone")
        String buyerAddressAndPhone;
        //        buyer_bank_account	String	否	购买方开户行及账号
        @SerializedName("buyer_bank_account")
        String buyerBankAccount;
        //        seller_number	String	否	销售方纳税人识别号
        @SerializedName("seller_number")
        String sellerNumber;
        //        seller_address_and_phone	String	否	销售方地址、电话
        @SerializedName("seller_address_and_phone")
        String sellerAddressAndPhone;
        //        seller_bank_account	String	否	销售方开户行及账号
        @SerializedName("seller_bank_account")
        String sellerBankAccount;
        //        remarks	String	否	备注，发票右下角初
        String remarks;
        //        cashier	String	否	收款人，发票左下角处
        String cashier;
        //        maker	String	否	开票人，发票下方处
        String maker;

    }

    @Data
    static class Info {
        //        name	String	是	项目的名称
        private String name;
        //        num	Int	是	项目的数量
        private Integer num;
        //        unit	String	是	项目的单位，如个
        private String unit;
        //        price	Int	是	项目的单价
        private Integer price;
    }

    public static class Builder {
        private String orderID;
        private String cardID;
        private String appid;
        private CardExt cardExt;
        private InvoiceUserData invoiceUserData;

        Builder() {
            this.cardExt = new CardExt();
            HashMap <String, Object> map = new HashMap <>();
            this.invoiceUserData = new InvoiceUserData();
            ArrayList <Info> infos = new ArrayList <>();
            invoiceUserData.setInfoList( infos );
            map.put( "", invoiceUserData );
            this.cardExt.setUserCard( map );
        }

        public Builder setOrderID(String orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setCardID(String cardID) {
            this.cardID = cardID;
            return this;
        }

        public Builder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public Builder setFee(Integer fee) {
            this.invoiceUserData.fee = fee;
            return this;
        }

        public Builder setTitle(String title) {
            this.invoiceUserData.title = title;
            return this;
        }

        public Builder setBillingTime(Integer billingTime) {
            this.invoiceUserData.billingTime = billingTime;
            return this;
        }

        public Builder setBillingNo(String billingNo) {
            this.invoiceUserData.billingNo = billingNo;
            return this;
        }

        public Builder setBillingCode(String billingCode) {
            this.invoiceUserData.billingCode = billingCode;
            return this;
        }

        public Builder addInfoItem(String name, Integer num, String unit, Integer price) {
            Info info = new Info();
            info.setName( name );
            info.setNum( num );
            info.setUnit( unit );
            info.setPrice( price );
            this.invoiceUserData.infoList.add( info );
            return this;
        }

        public Builder setFeeWithoutTax(Integer feeWithoutTax) {
            this.invoiceUserData.feeWithoutTax = feeWithoutTax;
            return this;
        }

        public Builder setTax(Integer tax) {
            this.invoiceUserData.tax = tax;
            return this;
        }

        public Builder setsPdfMediaID(String sPdfMediaID) {
            this.invoiceUserData.sPdfMediaID = sPdfMediaID;
            return this;
        }

        public Builder setsTripPdfMediaID(String sTripPdfMediaID) {
            this.invoiceUserData.sTripPdfMediaID = sTripPdfMediaID;
            return this;
        }

        public Builder setCheckCode(String checkCode) {
            this.invoiceUserData.checkCode = checkCode;
            return this;
        }

        public Builder setBuyerNumber(String buyerNumber) {
            this.invoiceUserData.buyerNumber = buyerNumber;
            return this;
        }

        public Builder setBuyerAddressAndPhone(String buyerAddressAndPhone) {
            this.invoiceUserData.buyerAddressAndPhone = buyerAddressAndPhone;
            return this;
        }

        public Builder setBuyerBankAccount(String buyerBankAccount) {
            this.invoiceUserData.buyerBankAccount = buyerBankAccount;
            return this;
        }

        public Builder setSellerNumber(String sellerNumber) {
            this.invoiceUserData.sellerNumber = sellerNumber;
            return this;
        }

        public Builder setSellerAddressAndPhone(String sellerAddressAndPhone) {
            this.invoiceUserData.sellerAddressAndPhone = sellerAddressAndPhone;
            return this;
        }

        public Builder setSellerBankAccount(String sellerBankAccount) {
            this.invoiceUserData.sellerBankAccount = sellerBankAccount;
            return this;
        }

        public Builder setRemarks(String remarks) {
            this.invoiceUserData.remarks = remarks;
            return this;
        }

        public Builder setCashier(String cashier) {
            this.invoiceUserData.cashier = cashier;
            return this;
        }

        public Builder setMaker(String maker) {
            this.invoiceUserData.maker = maker;
            return this;
        }

        public InvoiceInsertRequest build() {
            InvoiceInsertRequest invoiceInsertRequest = new InvoiceInsertRequest();
            invoiceInsertRequest.appid = this.appid;
            invoiceInsertRequest.cardID = this.cardID;
            invoiceInsertRequest.orderID = this.orderID;
            invoiceInsertRequest.cardExt = this.cardExt;
            return invoiceInsertRequest;
        }
    }

    public String toJson(){
        return WxMpGsonBuilder.create().toJson( this );
    }

}
