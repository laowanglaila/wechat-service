package com.hualala.app.wechat.model.mp;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
@Data
public class MpInfoModel extends BaseItem implements Serializable {
    /**
     * 记录ID
     */
    private Long itemID;

    /**
     * 公众平台微信号
     */
    private String mpID;

    /**
     * 微信号原始ID
     */
    private String ghID;

    /**
     * 公众平台账户名称
     */
    private String mpName;

    /**
     * 微信账号类型 0：未设置，10：订阅号，11：订阅号（已认证），20：服务号，21：服务号（已认证）
     */
    private Integer mpType;

    /**
     * 接口token
     */
    private String token;

    /**
     * 服务号AppId
     */
    private String appID;

    /**
     * 服务号AppSecret
     */
    private String appSecret;

    /**
     * 服务号EncodingAESKey,消息体加密密钥
     */
    private String encodingAESKey;

    /**
     * 关注URL地址
     */
    private String weixinURL;

    /**
     * 授权方头像
     */
    private String headImg;

    /**
     * 生成关注二维码URL地址
     */
    private String qrCodeURL;

    /**
     * 自定义菜单JSON
     */
    private String menuJson;

    /**
     * 关联集团ID
     */
    private Integer groupID;

    /**
     * 品牌ID
     */
    private Long brandID;

    /**
     * 关联店铺ID
     */
    private Long shopID;

    /**
     * 成为会员免手机绑定
     */
    private Integer customerWithoutBindMobile;

    /**
     * 关注即为会员
     */
    private Integer subscribeToCcustomer;

    /**
     * 桌台码图文消
息模版
     */
    private String tableMsgTemplate;

    /**
     * 公众号授权给开发者的权限集列表
     */
    private String funcInfo;

    /**
     * 授权方公众号所设置的微信号
     */
    private String alias;

    /**
     * 否授权给微信开放平台 0:未知，1:是，2:取消授权
     */
    private Integer authorize;

    /**
     * 公众号刷新令牌
     */
    private String authorizerRefreshToken;

    /**
     * 是否有微信网页授权权限
     */
    private Integer oauth;

    /**
     * 微信认证日期
     */
    private Long wechatEndDate;

    /**
     * 是否启用 0：未启用，1：启用
     */
    private Integer isActiveUse;


    private static final long serialVersionUID = 1L;

}