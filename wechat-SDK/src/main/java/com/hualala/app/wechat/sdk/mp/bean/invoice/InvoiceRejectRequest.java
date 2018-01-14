package com.hualala.app.wechat.sdk.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.Data;

/**
 * Created by renjianfei on 2018/1/14.
 */
@Data
public class InvoiceRejectRequest {
//    s_pappid	string	是	开票平台在微信上的标识，由开票平台告知商户
    @SerializedName( "s_pappid" )
    private String apAppID;
//    order_id	string	是	订单 id
    @SerializedName( "order_id" )
    private String orderID;
//    reason	string	是	商家解释拒绝开票的原因，如重复开票，抬头无效、已退货无法开票等
    private String reason;
//    url	string	否	跳转链接，引导用户进行下一步处理，如重新发起开票、重新填写抬头、展示订单情况等
    private String url;
    public String toJson(){
        return WxMpGsonBuilder.create().toJson( this );
    }
}
