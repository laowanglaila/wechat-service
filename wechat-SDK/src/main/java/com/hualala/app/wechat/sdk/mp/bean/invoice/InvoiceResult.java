package com.hualala.app.wechat.sdk.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.Data;

/**
 * Created by renjianfei on 2018/1/13.
 */
@Data
public class InvoiceResult {
//    errcode	string	是	错误码
    private String errcode;
//    errmsg	string	是	错误信息
    private String errmsg;
//    card_id	string	否	当错误码为 0 时，返回发票卡券模板的编号，用于后续该商户发票生成后，作为必填参数在调用插卡接口时传入
    @SerializedName( "card_id" )
    private String cardID;

    public static InvoiceResult fromJson(String json){
        return WxMpGsonBuilder.INSTANCE.create().fromJson(json,InvoiceResult.class);
    }
}
