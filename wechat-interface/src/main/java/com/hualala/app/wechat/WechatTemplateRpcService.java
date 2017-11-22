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
 * Created by xkia on 2017/4/13.
 */
@RpcService
public interface WechatTemplateRpcService {

    @RpcMethod
    WechatTemplateRpcResData sentWechatTemplate(WechatTemplateRpcReqData reqData);
    @RpcMethod
    WechatSendTemplateRes sentWechatTemplateByMQ(WechatSendTemplateReq reqData);


    @Data
    class WechatTemplateRpcReqData extends RequestInfo{
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "店铺ID")
        private long shopID;
        @Protocol(fieldType = FieldType.ENUM, order = 5, description = "模板消息类型枚举")
        private WechatTemplateTypeEnum templateType = WechatTemplateTypeEnum.TEMPLATE_ENUM_DEFAULT;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "mpID")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "openID")
        private String openID;
        @Protocol(fieldType = FieldType.LONG, order = 8, description = "用户ID")
        private long userID;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "模板消息title")
        private String first;
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "keynote1")
        private String keynote1;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "keynote2")
        private String keynote2;
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "keynote3")
        private String keynote3;
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "keynote4")
        private String keynote4;
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "keynote5")
        private String keynote5;
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "keynote6")
        private String keynote6;
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "remark")
        private String remark;
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "param1")
        private String param1;
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "key")
        private String orderKey;

//        @Protocol(fieldType = FieldType.STRING, order = 5, description = "模板类型")
//        private String ModelType;
//        @Protocol(fieldType = FieldType.STRING, order = 6, description = "模板子类型")
//        private String modelSubType;
//        @Protocol(fieldType = FieldType.STRING, order = 7, description = "类型")
//        private String subType;
    }

    @Data
    class WechatTemplateRpcResData extends ResultInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2)
        private long itemID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "发送消息类型 WECHAT SMS")
        private String msgType;
    }


    @Data
    class WechatTemplateItem{
        @Protocol(fieldType = FieldType.ENUM, order = 1,description = "模板name")
        private TemplateItemTypeEnum type;
        @Protocol(fieldType = FieldType.STRING, order = 2,description = "模板value")
        private String value;
        @Protocol(fieldType = FieldType.STRING, order = 3,description = "字体颜色，默认黑色")
        private String color;
    }
    @Data
    class WechatSendTemplateRes extends ResultInfo {}
    @Data
    class WechatSendTemplateReq extends RequestInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "店铺ID")
        private long shopID;
        @Protocol(fieldType = FieldType.ENUM, order = 5, description = "模板消息类型枚举")
        private WechatTemplateTypeEnum templateType = WechatTemplateTypeEnum.TEMPLATE_ENUM_DEFAULT;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "mpID")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "openID")
        private String openID;
        @Protocol(fieldType = FieldType.LONG, order = 8, description = "用户ID")
        private long userID;
        @Protocol(fieldType = FieldType.OBJECT, order = 9, description = "模板消息item")
        private List<WechatTemplateItem> templateItem;
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "param1")
        private String url;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "param1")
        private String param1;
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "key")
        private String orderKey;
    }

}
