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
 * 公众号基本信息
 * Created by xkia on 2017/3/20.
 */
@RpcService
public interface MpInfoService {

    @RpcMethod(description = "获取公众信息")
    MpInfoQueryResData queryMpInfo(MpInfoQueryReqData reqData);

    @Data
    class MpInfoQueryReqData extends RequestInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description="itemID")
        private long itemID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description="公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description="公众号名称")
        private String mpName;
        @Protocol(fieldType = FieldType.ENUM, order = 5, description="公众号类型")
        private MpTypeEnum mpType;
        @Protocol(fieldType = FieldType.LONG, order = 6, description="集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 7, description="品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 8, description="店铺ID")
        private long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 9, description="appID")
        private String appID;
        @Protocol(fieldType = FieldType.INT,order = 10)
        private int pageNo;
        @Protocol(fieldType = FieldType.INT,order = 11)
        private int pageSize;

    }
    @Data
    class MpInfoResData extends ResultInfo {
        @Protocol(fieldType = FieldType.STRING, order = 2, description="itemID")
        private long itemID;
        @Protocol(fieldType = FieldType.STRING, order = 3, description="公众号编码")
        private String mpID;
        @Protocol(fieldType = FieldType.STRING, order = 4, description="公众号原始ID")
        private String ghID;
        @Protocol(fieldType = FieldType.STRING, order = 5, description="公众号名称")
        private String mpName;
        @Protocol(fieldType = FieldType.ENUM, order = 6, description="公众号类型")
        private MpTypeEnum mpType;
        @Protocol(fieldType = FieldType.STRING, order = 7, description="token")
        private String token;
        @Protocol(fieldType = FieldType.STRING, order = 8, description="公众号APPID")
        private String appID;
        @Protocol(fieldType = FieldType.STRING, order = 9, description="appSecret")
        private String appSecret;
        @Protocol(fieldType = FieldType.STRING, order = 10, description="encodingAESKey")
        private String encodingAESKey;
        @Protocol(fieldType = FieldType.STRING, order = 11, description="微信关注url")
        private String weixinURL;
        @Protocol(fieldType = FieldType.STRING, order = 12, description="图片地址")
        private String headImg;
        @Protocol(fieldType = FieldType.STRING, order = 13, description="关注url")
        private String qrCodeURL;
        @Protocol(fieldType = FieldType.STRING, order = 14, description="3+5菜单")
        private String menuJson;
        @Protocol(fieldType = FieldType.LONG, order = 15, description="集团ID")
        private long groupID;
        @Protocol(fieldType = FieldType.LONG, order = 16, description="品牌ID")
        private long brandID;
        @Protocol(fieldType = FieldType.LONG, order = 17, description="店铺ID")
        private long shopID;
        @Protocol(fieldType = FieldType.STRING, order = 18, description="成为会员是否需要手机号")
        private String customerWithoutBindMobile;
        @Protocol(fieldType = FieldType.STRING, order = 19, description="关注即会员")
        private String subscribeToCcustomer;
        @Protocol(fieldType = FieldType.STRING, order = 20, description="二码合一模板")
        private String tableMsgTemplate;
        @Protocol(fieldType = FieldType.STRING, order = 21, description="公众号")
        private String alias;
        @Protocol(fieldType = FieldType.STRING, order = 22, description="授权方式")
        private String authorize;
        @Protocol(fieldType = FieldType.STRING, order = 23, description="公众号刷新令牌")
        private String authorizerRefreshToken;
        @Protocol(fieldType = FieldType.STRING, order = 24, description="是否有微信网页授权权限")
        private String oauth;
        @Protocol(fieldType = FieldType.LONG, order = 25, description="微信认证日期")
        private long wechatEndDate;
        @Protocol(fieldType = FieldType.STRING, order = 26, description="是否启用 0：未启用，1：启用")
        private String isActiveUse;

    }

    @Data
    class MpInfoQueryResData extends ResultInfo {
        @Protocol(fieldType = FieldType.OBJECT, order = 2, description = "公众号信息集合")
        private List<MpInfoResData> mpInfoResDataList;
    }

}
