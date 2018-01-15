package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.util.template.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  客服消息接口
 */
@Service
public class WechatCustomService {

    /*
    mpID,groupID,brandID,shopID,openID,userID,content
     */
    private Logger logger = LoggerFactory.getLogger(WechatCustomService.class);

    @Autowired
    private BaseHttpService baseHttpService;

    /**
     * text 消息
     * @param mpID
     * @param openID
     * @param content
     * @return
     */
    public boolean messageSendText(String mpID, String openID, String content){

        JSONObject resultJson = baseHttpService.messageCustomSend(MessageUtil.JsonTest(openID,content),mpID);

        if( !resultJson.getBoolean(WechatMessageType.IS_SUCCESS)){
            logger.error("客服消息发送错误：",resultJson.getString(WechatMessageType.MESSAGE));
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_MESSAGE_CUSTOM_SEMD.getCode(), resultJson.getString(WechatMessageType.MESSAGE));
        }
        return true;
    }

}
