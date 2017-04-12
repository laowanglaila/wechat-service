package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/4/12.
 */
@RpcService
public interface WechatQRCodeRpcSerivce {
    @RpcMethod
    public WechatQRCodeReq getQRCode(WechatQRCodeRes qrCodeReq);

    @Data
    public class WechatQRCodeReq extends RequestInfo {
        //    expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "该二维码有效时间")
        private long expireSeconds;
        //    action_info	二维码描述
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "二维码描述")
        private String actionInfo;
        //二维码类型决定： 永久、临时；有效时间？
        @Protocol(fieldType = FieldType.ENUM, order = 4, description = "二维码类型")
        private WechatQRTypeEnum qrcodeType;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "param1冗余")
        private String param1;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "param2冗余")
        private String param2;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "param3冗余")
        private String param3;
        //        String shopName = dataset.getProperty("shopName");
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "locationName二维码使用场景地址")
        private String shopName;
        //        String groupID = dataset.getProperty("groupID");
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "集团ID")
        private String groupID;
        //        String shopID = dataset.getProperty("shopID"); // 店铺ID
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "店铺ID")
        private String shopID;
        //        String brandID = dataset.getProperty("brandID"); // 品牌ID
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "品牌ID")
        private String brandID;

    }

    @Data
    public class WechatQRCodeRes extends RequestInfo {
        //        ticket	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "获取的二维码ticket")
        private String ticket;
        //        expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "该二维码有效时间")
        private long expireSeconds;
        //        url	二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "二维码图片解析后的地址")
        private String url;

    }
}
