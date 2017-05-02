package com.hualala.app.wechat.model;

import com.hualala.core.base.BaseItem;
import lombok.Data;

@Data
public class WechatTemplateModel extends BaseItem {

    private long itemID;
    private String mpID;
    private String modelID;
    private String modelTitle;
    private String templateType;
    private String templateID;
    private int action;
    private long actionTime;
    private long createTime;

}