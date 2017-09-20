package com.hualala.app.wechat.model.mp;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by renjianfei on 2017/9/19.
 */
@Data
public class MpShopsModel extends BaseItem implements Serializable{
    private String mpID;
    private Long shopID;
    private Long groupID;
}
