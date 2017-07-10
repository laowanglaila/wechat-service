package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/6/28.
 */
@RpcService(description = "获取jsapi签名相关")
public interface JsApiSignRpcService {

    /**
     * 获取h5投放卡券签名和必传参数
     *
     */
    @RpcMethod(description = "获取h5jsapi签名")
    JsApiSignResData getSign(JsApiSignReqData jsApiSignReqData);


    @Data
    class JsApiSignReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "当前网页的URL，不包含#及其后面部分")
        private String url;
        @Protocol(fieldType = FieldType.LONG, order = 3, description = "微信公众号ID")
        private String mpID;

    }
    @Data
    class JsApiSignResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "时间戳，商户生成从1970年1月1日00:00:00至今的秒数,即当前的时间,且最终需要转换为字符串形式;由商户生成后传入,不同添加请求的时间戳须动态生成，若重复将会导致领取失败！")
        private String timeStamp;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "随机字符串，由开发者设置传入，加强安全性（若不填写可能被重放请求）。随机字符串，不长于32位。推荐使用大小写字母和数字，不同添加请求的nonce须动态生成，若重复将会导致领取失败。")
        private String nonceStr;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "签名，商户将接口列表中的参数按照指定方式进行签名,签名方式使用SHA1,具体签名方案参见下文;由商户按照规范签名后传入")
        private String signature;
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "必填，公众号的唯一标识")
        private String appId;

    }


}
