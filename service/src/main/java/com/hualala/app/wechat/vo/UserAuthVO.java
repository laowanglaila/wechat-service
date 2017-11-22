package com.hualala.app.wechat.vo;

import lombok.Data;

/**
 * Created by renjianfei on 2017/9/22.
 */
@Data
public class UserAuthVO {
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private String openID;
    private String scope;
}
