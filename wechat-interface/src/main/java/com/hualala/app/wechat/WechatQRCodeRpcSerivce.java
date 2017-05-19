package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

import java.util.List;

/**
 * Created by renjianfei on 2017/4/12.
 */
@RpcService
public interface WechatQRCodeRpcSerivce {


    @RpcMethod
    WechatQRCodeRes createQRCode(WechatQRCodeReq qrCodeReq);
    WechatQRCodeListRes createQRCodeList(WechatQRCodeListReq qrCodeReqList);

    @Data
    public class WechatQRCodeListReq extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "集团ID")
        private String groupID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "品牌ID")
        private String brandID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "店铺ID")
        private String shopID;
        @Protocol(fieldType = FieldType.ENUM, order = 6, description = "二维码类型")
        private WechatQRTypeEnum qrcodeType;
        @Protocol(fieldType = FieldType.INT, order = 7, description = "缓存数量，集合内容为空时必须填写，")
        private Integer size;
        //    expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
        @Protocol(fieldType = FieldType.INT, order = 8, description = "该二维码有效时间/秒")
        private int expireSeconds;
        @Protocol(fieldType = FieldType.OBJECT, order = 9, description = "微信二维码请求参数集合")
        private List<WechatQRCodeData> wechatQRCodeDataList;
    }
    @Data
    public class WechatQRCodeData {
        @Protocol(fieldType = FieldType.STRING, order = 1, description = "二维码描述")
        private String description;
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "二维码名称")
        private String qrcodeName;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "param1冗余")
        private String param1;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "param2冗余")
        private String param2;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "param3冗余")
        private String param3;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "locationName二维码使用场景地址")
        private String locationName;
        @Protocol(fieldType = FieldType.STRING, order= 7, description = "店铺名称")
        private String shopName;


    }
    @Data
    public class WechatQRCodeListRes extends ResultInfo {
        @Protocol(fieldType = FieldType.OBJECT, order = 2, description = "微信二维码返回参数集合")
        private List<WechatQRCodeRes> wechatQRCodeResList;
    }
    @Data
    public class WechatQRCodeReq extends RequestInfo {

        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "集团ID")
        private String groupID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "品牌ID")
        private String brandID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "店铺ID")
        private String shopID;

        @Protocol(fieldType = FieldType.STRING, order = 6, description = "二维码描述")
        private String description;

        // 如果要区分二维码永久或者临时，再增加一个字段，不然就暴露两个方法
        // 二维码类型决定： 永久、临时；有效时间？
        @Protocol(fieldType = FieldType.ENUM, order = 7, description = "二维码类型")
        private WechatQRTypeEnum qrcodeType;
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "param1冗余")
        private String param1;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "param2冗余")
        private String param2;
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "param3冗余")
        private String param3;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "locationName二维码使用场景地址")
        private String locationName;
        @Protocol(fieldType = FieldType.STRING, order= 12, description = "店铺名称")
        private String shopName;
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "二维码名称")
        private String qrcodeName;
        //    expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
        @Protocol(fieldType = FieldType.INT, order = 14, description = "该二维码有效时间/秒")
        private int expireSeconds;

    }

    @Data
    public class WechatQRCodeRes extends ResultInfo {
        //        ticket	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "获取的二维码ticket")
        private String ticket;
        //        expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
        @Protocol(fieldType = FieldType.INT, order = 3, description = "该二维码有效时间")
        private int expireSeconds;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "二维码图片解析后的地址")
        private String wxUrl;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "param1冗余")
        private String param1;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "param2冗余")
        private String param2;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "param3冗余")
        private String param3;
        @Protocol(fieldType = FieldType.LONG, order = 8, description = "二维码唯一ID")
        private Long itemID;
    }
}
