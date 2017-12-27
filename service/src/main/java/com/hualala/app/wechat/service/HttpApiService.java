package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.ErrorCodes;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.sdk.mp.exception.WechatException;
import com.hualala.app.wechat.sdk.mp.exception.WechatInnerException;
import com.hualala.app.wechat.sdk.mp.util.OkHttpUtil;
import com.hualala.app.wechat.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/3/29.
 */
@Service
public class HttpApiService {
    private static Logger logger = LoggerFactory.getLogger(HttpApiService.class);
    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 对httpApiUtil包装
     * 如果url中没有accessToken，判断mpID，获取accessToken
     * 如果accessToken超时，删除原有accessToken自动获取一次，并重新请求
     * @param url
     * @param params
     * @param mpID
     * @return
     */
    public JSONObject httpPost(String url, String params, String mpID){
        if(url == null){
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_URL_NULL,"http请求url不能为空！");
        }
        url = checkAccessTocken(url, mpID);
//        JSONObject jsonObject = HttpApiUtil.httpPost(url, params);
        JSONObject jsonObject = OkHttpUtil.post(url, params);
        String errCode = jsonObject.getString("errcode");

        if("42001".equals(errCode)){
            //删除原有的accessToken
            String[] sp = url.trim().split("[?]");
            url = sp[0];
            jsonObject = httpPost(url, params, mpID);
        }else if ("40001".equals( errCode )){
            String newUrl = checkAccessTocken( url, mpID, true );
            jsonObject = OkHttpUtil.post(newUrl, params);
        }
        return jsonObject;
    }

    public JSONObject httpGet(String url, String mpID){
        if(url == null){
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_URL_NULL,"http请求url不能为空！");
        }
        url = checkAccessTocken(url, mpID);
//        JSONObject jsonObject = HttpApiUtil.httpGet(url);
        JSONObject jsonObject = OkHttpUtil.get(url);
        String errCode = jsonObject.getString("errcode");

        if (errCode != null && !errCode.equals("0")){

        }else if (errCode != null && errCode.equals("-1")) {

        }
//        if("42001".equals(errCode)){
//            //删除原有的accessToken
//            String[] sp = url.trim().split("[?]");
//            url = sp[0];
//            jsonObject = httpGet(url, mpID);
//        }
        return jsonObject;
    }

    private String checkAccessTocken(String url, String mpID) {
        return checkAccessTocken( url, mpID ,false);
    }

    private String checkAccessTocken(String url, String mpID, boolean isForceRefresh) {
        String[] split = url.trim().split("[?]");
        if(split.length ==1 && !url.contains("access_token")){

            JSONObject result = null;
            try {
                result = accessTokenService.getAccessToken(mpID);
            } catch (WechatInnerException e) {
                logger.error( e.getMessage(), e);
                throw new WechatException( WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD);
            }
            if(!result.getBoolean( WechatMessageType.IS_SUCCESS)){
                String code = result.getString(WechatMessageType.CODE);
                String message = result.getString(WechatMessageType.MESSAGE);
                throw new WechatException(code,message) ;
            }else {
                String accessToken = result.getString("accessToken");
                StringBuilder suilder = new StringBuilder(url);
                url = suilder.append("?access_token="+accessToken).toString();
            }
        } else if(split.length > 1 && !url.contains("access_token")){
            JSONObject result = null;
            try {
                result = accessTokenService.getAccessToken(mpID);
            } catch (WechatInnerException e) {
                logger.error( e.getMessage(), e);
                throw new WechatException(WechatExceptionTypeEnum.WECHAT_GET_ACCESSTOKEN_FIELD);
            }
            if(!result.getBoolean(WechatMessageType.IS_SUCCESS)){
                String code = result.getString(WechatMessageType.CODE);
                String message = result.getString(WechatMessageType.MESSAGE);
                throw new WechatException(code,message) ;
            }else {
                String accessToken = result.getString("accessToken");
                StringBuilder suilder = new StringBuilder(url);
                url = suilder.append("&access_token="+accessToken).toString();
            }
        }
        return url;
    }

}
