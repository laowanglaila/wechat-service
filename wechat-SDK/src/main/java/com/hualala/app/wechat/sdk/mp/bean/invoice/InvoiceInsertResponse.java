package com.hualala.app.wechat.sdk.mp.bean.invoice;

import lombok.Data;

/**
 * Created by renjianfei on 2018/1/14.
 */
@Data
public class InvoiceInsertResponse {
//    errcode	Int	是	错误码
    private Integer errcode;
//    errmsg	String	是	错误信息
    private String errmsg;
//    code	String	是	发票code
    private String code;
//    openid	String	是	获得发票用户的openid
    private String openid;
//    unionid	String	否	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
    private String unionid;
}
