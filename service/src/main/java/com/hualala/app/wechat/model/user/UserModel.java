package com.hualala.app.wechat.model.user;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserModel extends BaseItem implements Serializable {
    /**
     * 记录自增ID
     */
    private Long itemID;

    /**
     * 公众平台微信号
     */
    private String mpID;

    /**
     * 用户ID
     */
    private Long userID;

    /**
     * 用户登录名，GozapID
     */
    private String userLoginName;

    /**
     * 微信用户的唯一标识
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户昵称（未过滤表\r\n情字符）
     */
    private String userNickName;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;

    /**
     * 用户个人资料填写的省份Beijing
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市Chaoyang
     */
    private String city;

    /**
     * 国家CN
     */
    private String country;

    /**
     * 用户头像
     */
    private String headimgurl;

    /**
     * 用户头像(本地服务器地址)
     */
    private String photoImage;

    /**
     * 0: 未知，1: 关注，2: 取消关注
     */
    private Integer isSubscribe;

    private static final long serialVersionUID = 1L;

}