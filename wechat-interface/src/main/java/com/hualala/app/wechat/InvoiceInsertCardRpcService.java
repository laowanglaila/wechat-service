package com.hualala.app.wechat;

import com.google.gson.annotations.SerializedName;
import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *接受电子发票信息，插入卡包
 */
@RpcService
public interface InvoiceInsertCardRpcService {


    @RpcMethod(description = "插入卡券包")
    InvoiceInsertResData insert(InvoiceInsertCardRpcService.InvoiceInsertReqData invoiceInsertReqData) ;

    @Data
    class InvoiceInsertResData extends ResultInfo {

    }

    @Data
    class InvoiceInsertReqData extends RequestInfo {
        //        morder_id	string	是	发票order_id
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "发票order_id")
        private String orderID;
        //        maker	String	否	开票人，发票下方处
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "微信公众号id")
        private String mpID;
        //        maker	String	否	开票人，发票下方处
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "开票人，发票下方处")
        private String maker;
        //    card_id	String	是	发票url
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "发票url")
        private String invoiceUrl;
        //    card_id	String	是	发票附件url
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "发票附件url")
        private String invoiceAccessoryUrl;

        //        fee	Int	是	发票的金额，以分为单位
        @Protocol(fieldType = FieldType.INT, order = 8, description = "发票的金额，以分为单位")
        private Integer fee;
        //        title	String	是	发票的抬头
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "发票的抬头")
        private String title;
        //        billing_time	Int	是	发票的开票时间，为10位时间戳（utc+8）
        @Protocol(fieldType = FieldType.INT, order = 10, description = "发票的开票时间，为10位时间戳（utc+8）")
        private Integer billingTime;
        //        billing_no	String	是	发票的发票代码
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "发票的发票代码")
        private String billingNo;
        //        billing_code	String	是	发票的发票号码
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "发票的发票号码")
        private String billingCode;

        //        info	List	否	商品详情结构，见下方
        @Protocol(fieldType = FieldType.OBJECT, order = 13, description = "商品详情结构，见下方")
        private List<Info> infoList;

        //        fee_without_tax	Int	是	不含税金额，以分为单位
        @Protocol(fieldType = FieldType.INT, order = 14, description = "不含税金额，以分为单位")
        private Integer feeWithoutTax;
        //        tax	Int	是	税额，以分为单位
        @Protocol(fieldType = FieldType.INT, order = 15, description = "税额金额，以分为单位")
        private Integer tax;

        //        check_code	String	是	校验码
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "校验码")
        private String checkCode;
        //        buyer_number	String	否	购买方纳税人识别号
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "购买方纳税人识别号")
        private String buyerNumber;
        //        buyer_address_and_phone	String	否	购买方地址、电话
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "购买方地址、电话")
        private String buyerAddressAndPhone;
        //        buyer_bank_account	String	否	购买方开户行及账号
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "购买方开户行及账号")
        private String buyerBankAccount;
        //        seller_number	String	否	销售方纳税人识别号
        @Protocol(fieldType = FieldType.STRING, order = 20, description = "销售方纳税人识别号")
        private String sellerNumber;
        //        seller_address_and_phone	String	否	销售方地址、电话
        @Protocol(fieldType = FieldType.STRING, order = 21, description = "销售方地址、电话")
        private String sellerAddressAndPhone;
        //        seller_bank_account	String	否	销售方开户行及账号
        @Protocol(fieldType = FieldType.STRING, order = 22, description = "销售方开户行及账号")
        private String sellerBankAccount;
        //        remarks	String	否	备注，发票右下角初
        @Protocol(fieldType = FieldType.STRING, order = 23, description = "备注，发票右下角初")
        private String remarks;
        //        cashier	String	否	收款人，发票左下角处
        @Protocol(fieldType = FieldType.STRING, order = 24, description = "收款人，发票左下角处")
        private String cashier;

    }

    @Data
    class Info{
        //        name	String	是	项目的名称
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "项目的名称")
        private String name;
        //        num	Int	是	项目的数量
        @Protocol(fieldType = FieldType.INT, order = 2, description = "项目的数量")
        private Integer num;
        //        unit	String	是	项目的单位，如个
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "项目的单位，如个")
        private String unit;
        //        price	Int	是	项目的单价
        @Protocol(fieldType = FieldType.INT, order = 4, description = "项目的单价")
        private Integer price;
    }
}
