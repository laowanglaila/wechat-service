package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/4/14.
 * 通过openid或者username将微信账号加入测试白名单
 */
@RpcService
public interface WhiteListRpcService {

    @RpcMethod
    public ResData addToWhiteList(ReqData reqData);


    @Data
    public class ReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "集团ID")
        private String groupID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "品牌ID")
        private String brandID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "店铺ID")
        private String shopID;
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "微信用户唯一标识")
        private String[] openID;
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "微信账号")
        private String[] userName;
    }
    @Data
    public class ResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "错误码，0为正常")
        private String errcode;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "错误消息")
        private String errmsg;
    }

}
