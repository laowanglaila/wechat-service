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
 * Created by renjianfei on 2017/10/16.
 */
@RpcService(description = "永久二维码相关操作")
public interface WechatPermanentQrCodeRpcService {

    @RpcMethod(description = "桌台二维码删除(标记删除)")
    DelQrCodeRes delWechatQrcodeByParams(DelQrCodeReq delQrCodeReq);

    @RpcMethod(description = "桌台更新相应二维码更新")
    UpdateQrCodeRes updateQrcode(UpdateQrCodeReq updateQrCodeReq);
    @Data
    class DelQrCodeReq extends RequestInfo {
        @NotBlank(message = "mpID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号mpID")
        private String mpID;
        @NotBlank(message = "shopID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "店铺ID不能为空")
        String shopID;
        @NotBlank(message = "qrcodeType不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "二维码类型   0:图文或文本消息，1:桌台")
        String qrcodeType;

    }

    @Data
    class UpdateQrCodeReq extends RequestInfo {
        @NotBlank(message = "mpID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号mpID")
        private String mpID;
        @NotBlank(message = "shopID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "店铺ID不能为空")
        String shopID;
        @NotBlank(message = "param3不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "QRCodeID")
        String param3;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "tableName")
        String tableName;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "actionType")
        String actionType;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "isActive")
        Integer isActive;
    }

    @Data
    class DelQrCodeRes extends ResultInfo{}

    @Data
    class UpdateQrCodeRes extends ResultInfo{}


}
