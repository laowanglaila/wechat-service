package com.hualala.app.wechat.common;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/5/2.
 */
public enum CardStatus implements ValueEnum {
    CARD_STATUS_NOT_VERIFY (2),
    CARD_STATUS_VERIFY_FAIL (3),
    CARD_STATUS_VERIFY_OK (4),
    CARD_STATUS_DISPATCH (5),
    CARD_STATUS_DELETE (6);

    private int value;
    CardStatus(int value){
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
