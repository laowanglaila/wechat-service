package com.hualala.app.wechat.sdk.mp.api.group.mp;

import lombok.Data;

/**
 * Created by renjianfei on 2018/1/5.
 */
@Data
public class MpInfoRelation {
    private String mpID;
    private String appID;
    private Long groupID;
    private String secret;
    private String token;
    private String aesKey;
    private String authorize;
    private Long createTime = System.currentTimeMillis();
}
