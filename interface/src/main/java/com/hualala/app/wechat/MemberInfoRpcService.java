package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/4/10.
 */
@RpcService
public interface MemberInfoRpcService {

    @RpcMethod(description = "获取单条会员信息")
    MemberInfoRpcService.MemberInfoResData queryMemberInfo(MemberInfoRpcService.MemberInfoQueryReqData reqData) throws Exception;

    @Data
    class MemberInfoQueryReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "itemID")
        private long itemID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "公众号名称")
        private String mpName;
        @Protocol(fieldType = FieldType.ENUM, order = 5, description = "公众号类型")
        private MpTypeEnum mpType;
        @Protocol(fieldType = FieldType.LONG, order = 6, description = "集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 7, description = "品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 8, description = "店铺ID")
        private long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "appID")
        private String appID;
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "appSecret")
        private String appSecret;
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "cardId")
        private String cardId;
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "code")
        private String code;
    }

    @Data
    class MemberInfoResData extends ResultInfo {
        //        errcode            	错误码，0为正常
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "errcode")
        private String errcode;
        //        errmsg            	错误信息
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "errmsg")
        private String errmsg;
        //        openid            	用户在本公众号内唯一识别码
        @Protocol(fieldType = FieldType.STRING, order = 4, description = "openid")
        private String openid;

        //        nickname            	用户昵称
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "nickname")
        private String nickname;

        //        bonus            	积分信息
        @Protocol(fieldType = FieldType.STRING, order = 6, description = "bonus")
        private String bonus;

        //        balance            	余额信息
        @Protocol(fieldType = FieldType.STRING, order = 7, description = "balance")
        private String balance;

        //        sex            	用户性别
        @Protocol(fieldType = FieldType.STRING, order = 8, description = "sex")
        private String sex;

        //        user_info            	会员信息
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "userInfo")
        private String userInfo;

        //        custom_field_list            	开发者设置的会员卡会员信息类目，如等级。
        @Protocol(fieldType = FieldType.STRING, order = 10, description = "customFieldList")
        private String customFieldList;

        //        name            	会员信息类目名称
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "name")
        private String name;

        //        value            	会员卡信息类目值，比如等级值等
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "value")
        private String value;

        //        user_card_status            	当前用户会员卡状态，NORMAL 正常 EXPIRE 已过期 GIFTING 转赠中 GIFT_SUCC 转赠成功 GIFT_TIMEOUT 转赠超时 DELETE 已删除，UNAVAILABLE 已失效
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "userCardStatus")
        private String userCardStatus;

    }

}
