package com.hualala.app.wechat.model.mq;

import com.hualala.app.wechat.WechatQRTypeEnum;
import lombok.Data;

/**
 * Created by renjianfei on 2017/5/15.
 */
@Data
public class QrcodeInfoModel {
    private WechatQRTypeEnum qrcodeType;
    private int cacheNo;
    private int expireSeconds;
    private String mpID;
}
