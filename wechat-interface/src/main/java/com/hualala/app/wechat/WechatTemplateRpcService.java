package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by xkia on 2017/4/13.
 */
@RpcService(description = "微信模板消息接口")
public interface WechatTemplateRpcService {

    @RpcMethod(description = "创建模板消息")
    WechatTemplateRpcResData sentWechatTemplate(WechatTemplateRpcReqData reqData);


    @Data
    class WechatTemplateRpcReqData extends RequestInfo{
        @Protocol(fieldType = FieldType.LONG, order = 2, description = "集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "店铺ID")
        private long shopID;

        @Protocol(fieldType = FieldType.STRING, order = 5, description = "模板类型")
        private String modelType;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "模板子类型")
        private String modelSubType;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "类型")
        private String subType;

        @Protocol(fieldType = FieldType.STRING, order = 8, description = "mpID")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "openID")
        private String openID;
        @Protocol(fieldType = FieldType.LONG, order = 10, description = "用户ID")
        private long userID;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "模板消息title")
        private String first;
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "keynote1")
        private String keynote1;
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "keynote2")
        private String keynote2;
        @Protocol(fieldType = FieldType.STRING, order = 14, description = "keynote3")
        private String keynote3;
        @Protocol(fieldType = FieldType.STRING, order = 15, description = "keynote4")
        private String keynote4;
        @Protocol(fieldType = FieldType.STRING, order = 16, description = "keynote5")
        private String keynote5;
        @Protocol(fieldType = FieldType.STRING, order = 17, description = "keynote6")
        private String keynote6;
        @Protocol(fieldType = FieldType.STRING, order = 18, description = "remark")
        private String remark;
        @Protocol(fieldType = FieldType.STRING, order = 19, description = "param1")
        private String param1;
    }

    @Data
    class WechatTemplateRpcResData extends ResultInfo {
        @Protocol(fieldType = FieldType.LONG, order = 2)
        private long itemID;
    }
}
