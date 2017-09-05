package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.*;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatCacheUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/6/27.
 */
@Service
public class ApiTicketService {

    private final Logger logger = LoggerFactory.getLogger(ApiTicketService.class);

    @Autowired
    private AccessTokenService accessTokenService;

    private static final String API_TICKET = "ticket";
    private static final Long TIME_OUT = 7000L;

    public JSONObject getWxCardApiTicket(String mpID){
        return this.getApiTicket(mpID,"wx_card");
    }
    public JSONObject getJsApiTicket(String mpID){
        return this.getApiTicket(mpID,"jsapi");
    }
    public JSONObject getApiTicket(String mpID,String type){


        if (mpID == null || mpID.trim().isEmpty()) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE, ErrorCodes.WECHAT_MPID_EMPTY,"mpID is empty");
        }

        String cacheApiTicket = WechatCacheUtil.getCacheApiTicket(mpID,type);

        if (StringUtils.isNotBlank(cacheApiTicket)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(API_TICKET,cacheApiTicket);
            return ResultUtil.toResultJson(jsonObject,WechatMessageType.TRUE, ErrorCodes.WECHAT_SUCCESS_CODE,"成功获取全局缓存"+type+"_ticket！");
        }

        JSONObject tockenObject = null;
        try {
            tockenObject = accessTokenService.getAccessToken(mpID);
        } catch (WechatInnerException e) {
            logger.error( e.getMessage(), e);
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD);
        }
        if (!tockenObject.getBoolean(WechatMessageType.IS_SUCCESS)){
            return tockenObject;
        }
        String accessTocken = tockenObject.getString("accessToken");
        JSONObject jsonObject = HttpApiUtil.httpGet(WechatBaseApi.GET_API_TICKET+"?access_token="+accessTocken+"&type="+type);
        if (null == jsonObject){
            return ResultUtil.toResultJson(null,false,"获取ticket失败");
        }
        if (logger.isInfoEnabled()){
            logger.info("缓存api_ticket过期，重新获取！",jsonObject);
        }
        String errCode = jsonObject.getString("errcode");

        if("42001".equals(errCode)){
            if (logger.isInfoEnabled()){
                logger.info("accessTocken过期，重新获取！",jsonObject);
            }
            jsonObject = getApiTicket(mpID,type);
        }
        //todo 加入缓存
        if ("0".equals(errCode)){
            WechatCacheUtil.setCacheApiTicket(mpID,jsonObject.getString(API_TICKET),jsonObject.getLong("expires_in")-200,type);
        }else {
            String errmassage = WechatErrorCode.wechatError.get(errCode);
            String errorcode = errmassage == null ? "[ errcode:"+errCode+" ]" : errmassage;
            if (logger.isErrorEnabled())
                logger.error(errorcode+":[ "+jsonObject.getString(WechatMessageType.WECHAT_ERR_MESSAGE)+" ]");
            return ResultUtil.toResultJson(jsonObject, false, null, errorcode+":[ "+jsonObject.getString(WechatMessageType.WECHAT_ERR_MESSAGE)+" ]");

        }
        return ResultUtil.toResultJson(jsonObject, true,ErrorCodes.WECHAT_SUCCESS_CODE);
    }

}
