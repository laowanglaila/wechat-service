package com.hualala.app.wechat.model.card;

import lombok.Data;

import java.io.Serializable;
@Data
public class MemberMsgModel implements Serializable {
    /**
     * memberMsgID
     */
    private Long itemID;

    /**
     * 集团ID
     */
    private Long groupID;

    /**
     * 会员卡唯一标识
     */
    private String code;

    /**
     * 会员卡类型标识
     */
    private String cardID;

    /**
     * 消息创建时间
     */
    private Long msgCreateTime;

    private static final long serialVersionUID = 1L;

}