package com.hualala.app.wechat.model.mp;

import lombok.Data;

/**
 * Created by renjianfei on 2017/9/22.
 */
@Data
public class MpInfoCache {
    String mpID;
    String appID;
    String mpType;
    String appSecret;
    String authorize;
    String authorizerRefreshToken;
    Integer groupID;
    String mpName;
}
