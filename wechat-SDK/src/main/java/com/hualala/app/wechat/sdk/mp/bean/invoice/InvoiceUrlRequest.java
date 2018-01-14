package com.hualala.app.wechat.sdk.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import com.hualala.app.wechat.sdk.mp.util.json.WxMpGsonBuilder;
import lombok.Data;

/**
 * Created by renjianfei on 2018/1/14.
 */
@Data
public class InvoiceUrlRequest {
    //    s_pappid	String	是	开票平台在微信的标识号，商户需要找开票平台提供
    @SerializedName("s_pappid")
    private String spAppID;
    //    order_id	String	是	订单id，在商户内单笔开票请求的唯一识别号，
    @SerializedName("order_id")
    private String orederID;
    //    money	Int	是	订单金额，以分为单位
    private Integer money;
    //    timestamp	Int	是 下单时间，当前时间秒数
    @SerializedName("timestamp")
    private Integer timeStamp;
    //    source	String	是	开票来源，app：app开票，web：微信h5开票
    private String source;
    //    redirect_url	String	否	授权成功后跳转页面。本字段只有在source为H5的时候需要填写，引导用户在微信中进行下一步流程。app开票因为从外部app拉起微信授权页，授权完成后自动回到原来的app，故无需填写。
    @SerializedName("redirect_url")
    private String redirectUrl;
    //    ticket	String	是	从上一环节中获取卡券ticket
    private String ticket;
    //    type	Int	是	授权类型，0：开票授权，1：填写字段开票授权，2：领票授权
    private Integer type;
    public String toJson(){
        return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }
}
