package com.hualala.app.wechat.sdk.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.Data;

import java.util.List;

/**
 * Created by renjianfei on 2018/1/14.
 */
@Data
public class InvoiceAuthInfo {
//    errcode	Int	是	错误码
    private Integer errcode;
//    errmsg	String	是	错误信息
    private String errmsg;
//    invoice_status	String	否	订单授权状态，当errcode为0时会出现
    @SerializedName( "invoice_status" )
    private String invoiceStatus;
//    auth_time	Int	否	授权时间，为十位时间戳（utc+8），当errcode为0时会出现
    @SerializedName( "auth_time" )
    private Integer authTime;
//    user_auth_info	Object	否	用户授权信息结构体，仅在授权页为type=1时出现
    @SerializedName( "user_auth_info" )
    private UserAuthInfo userAuthInfo;

    public boolean isBizTitle(){
        return userAuthInfo.getBizField() != null;
    }
    public static InvoiceAuthInfo fromJson(String json){
        InvoiceAuthInfo invoiceAuthInfo = WxMpGsonBuilder.create().fromJson( json, InvoiceAuthInfo.class );
        return invoiceAuthInfo;
    }
    @Data
    class UserAuthInfo{
        @SerializedName( "user_field" )
        private Field userField;
        @SerializedName( "biz_field" )
        private Field bizField;
        @SerializedName( "customer_field" )
        private List<CustomerField> customerFields;
    }
    @Data
    class CustomerField {
        private String key;
        private String value;
    }
    @Data
    class Field {
//             "title": "xx公司",
        private String title;
//                     "phone": "1557548768",
        private String phone;
//        "email": "dhxhxhhx@qq.cind"
        private String email;
//                     "tax_no": "6464646766",
        @SerializedName( "tax_no" )
        private String taxNo;
//                     "addr": "xx大厦",
        private String addr;
//                     "bank_type": "xx银行",
        @SerializedName( "bank_type" )
        private String bankType;
//                     "bank_no": "545454646",
        @SerializedName( "bank_no" )
        private String bankNo;
    }

}
