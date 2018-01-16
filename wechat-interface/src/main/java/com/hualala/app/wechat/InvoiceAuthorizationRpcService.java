package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by renjianfei on 2018/1/16.
 */
@RpcService
public interface InvoiceAuthorizationRpcService {


    @RpcMethod(description = "获取授权url")
    InvoiceAuthorizationRes getAuthorizUrl(InvoiceAuthorizationReq invoiceAuthorizationReq);

    @Data
    class InvoiceAuthorizationReq extends RequestInfo {
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号唯一ID")
        String mpID;
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "开票平台在微信的标识号")
        private String spAppID;
        @NotNull
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "授权类型，0：开票授权，1：填写字段开票授权，2：领票授权")
        private Integer type;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "订单id，在商户内单笔开票请求的唯一识别号")
        private String orederID;
        @NotNull
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "订单金额，以分为单位")
        private Integer money;
        @NotNull
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "下单时间，当前时间秒数")
        private Integer timeStamp;
        @NotBlank
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "开票来源，app：app开票，web：微信h5开票")
        private String source;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "授权成功后跳转页面。本字段只有在source为H5的时候需要填写，引导用户在微信中进行下一步流程")
        private String redirectUrl;
    }

    @Data
    class InvoiceAuthorizationRes extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "授权url")
        private String authoricatedUrl;
    }
}
