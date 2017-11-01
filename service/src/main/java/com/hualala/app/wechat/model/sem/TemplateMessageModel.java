package com.hualala.app.wechat.model.sem;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class TemplateMessageModel extends BaseItem implements Serializable {
    /**
     * 发送记录唯一ID
     */
    private Long itemID;

    /**
     * 公众号唯一ID
     */
    private String mpID;

    /**
     * 公众号用户唯一ID
     */
    private String openID;

    /**
     * 微信模板消息，消息体
     */
    private String message;

    /**
     * 加入队列失败：0，队列中：1，已发送：2，发送失败：3
     */
    private Integer status;

    /**
     * 成功：‘success’；失败：‘{微信失败原因}’
     */
    private String result;

    private Timestamp lastSendTime;

    private static final long serialVersionUID = 1L;

}