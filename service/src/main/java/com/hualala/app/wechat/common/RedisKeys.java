package com.hualala.app.wechat.common;

/**
 * Created by renjianfei on 2017/8/10.
 */
public interface RedisKeys {
    String PREFIX = "wechat";
    String COLON = ":";
    String WECHAT_QRCODE_ERRO_CODE = PREFIX+ COLON +"error_code"+ COLON + "QrcodeCacheService" + COLON ;
    String WECHAT_USER_RELATION_LOCK = PREFIX+ COLON  +  "tbl_shop_wechat_user_relation" + COLON ;
    String WECHAT_USER_INFO_LOCK = PREFIX+ COLON  +  "tbl_shop_wechat_user" + COLON ;
    String WEHCHAT_MPINFO_KEY = PREFIX + COLON + "mpInfo" + COLON;
}
