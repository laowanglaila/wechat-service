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
 * Created by renjianfei on 2017/6/5.
 */
@RpcService(description = "群发图文消息")
public interface MsgPublishRpcService {

    /**
     *  根据openID群发文本消息
     * @param textMsgReq
     * @return
     */
    @RpcMethod
    TextMsgRes publishTextByUserList(TextMsgReq textMsgReq);

    /**
     * 群发文本消息请求参数
     */
    @Data
    class TextMsgReq extends RequestInfo{
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "集团ID")
        private Long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "品牌ID")
        private Long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 5, description = "店铺ID")
        private Long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 6,description = "群发用户openid列表")
        private List<String> openIds;
        @Protocol(fieldType = FieldType.LONG, order = 7,description = "群发用户userid列表")
        private List<Long> userIds;
        @Protocol(fieldType = FieldType.STRING, order = 8,description = "文字消息内容")
        private String text;
    }

    /**
     * 群发文本消息响应参数
     */
    @Data
    class TextMsgRes extends ResultInfo{
//        @Protocol(fieldType = FieldType.STRING, order = 2,description = "群发用户列表")
//        private List<String> openIds;
//        @Protocol(fieldType = FieldType.STRING, order = 3,description = "文字消息内容")
//        private String text;
    }
}
