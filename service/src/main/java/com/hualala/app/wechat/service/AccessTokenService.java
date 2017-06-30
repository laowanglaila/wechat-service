package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by xkia on 2017/3/21.
 */
@Service
public class AccessTokenService {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    @Autowired
    private ComponentTokenService componentTokenService;

    /**
     * 获取公众号accessToken
     * @param mpID
     * @return
     */
    public JSONObject getAccessToken(String mpID) {

        if (mpID == null || mpID.trim().isEmpty()) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MPID_EMPTY,"mpID is empty");
        }

        String appID = null;
        String authorize = null;
        Map<String,Object> param = new HashMap<>();
        param.put("mpID",mpID);
        // TODO 待优化
        JSONObject mpInfoJson = WechatCacheUtil.getMpInfo(mpID, null);

        if (mpInfoJson == null) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MPID_EMPTY,"mpID is empty");
        }

        appID = mpInfoJson.getString("appID");
        authorize = mpInfoJson.getString("authorize");
        if ("1".equals(authorize)) {
            return componentTokenService.getAuthorizerAcToken(appID);
        } else if ("2".equals(authorize)) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR,"该公众号已经取消第三方平台授权");
        }
        String accessToken = WechatCacheUtil.getCacheAccessToken(mpID);
        JSONObject result = new JSONObject();
        if (accessToken == null) {

            param.put("mpID",mpID);
            param.put("appID",appID);
            result = initAccessToken(param);
            if(!result.getBoolean(WechatMessageType.IS_SUCCESS)){
                return ResultUtil.toResultJson(result,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_ERROR,"缓存中获取accessToken is null");
            }
            //accessToken = WechatCacheUtil.getCacheAccessToken(mpID);
            accessToken = result.getString("accessToken");
        }
        result.put(WechatMessageType.IS_SUCCESS, WechatMessageType.TRUE);
        result.put("accessToken", accessToken);
        return ResultUtil.toResultJson(result,WechatMessageType.TRUE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR,"");
    }

    /**
     *
     * 初始化accessToken
     * @param param
     * @return
     */
    private JSONObject initAccessToken(Map<String,Object> param){
        String mpID = param.containsKey("mpID")?String.valueOf(param.get("mpID")):null;
        String appID = param.containsKey("appID")?String.valueOf(param.get("appID")):null;
        JSONObject mpInfoJson = WechatCacheUtil.getMpInfo(mpID, appID);

        //Map<String,Object> mpInfoLst = mpInfoService.queryMpInfo(param);
        if(mpInfoJson == null){
            ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_NULL,"未找到对应的公众号");
        }
        appID = mpInfoJson.getString("appID");
        mpID = mpInfoJson.getString("mpID");
        String appSecret = mpInfoJson.getString("appSecret");
        String authorize = mpInfoJson.getString("authorize");
        String url = null;
        if(WechatBaseApi.AUTHORIZE_1.equals(authorize)){
           return componentTokenService.getAuthorizerAcToken(appID);
        } else {
            url = WechatBaseApi.GET_ACCESS_TOKEN + "&appid=" + appID + "&secret=" + appSecret;
            JSONObject resultJson = HttpApiUtil.httpGet(url);
            if(resultJson == null){
                return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR,"http请求出错了");
            }

            if (resultJson.getString("access_token") == null) {
                String errcode = resultJson.getString("errcode");
                String errmsg   = WechatErrorCode.wechatError.get(errcode);
                if(errmsg == null){
                    errmsg = resultJson.getString(WechatMessageType.MESSAGE);
                }
                return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR,errmsg);
            }
            String value = resultJson.getString("access_token");
            WechatCacheUtil.setCacheAccessToken(mpID, value, Long.parseLong(resultJson.getString("expires_in")));

            resultJson.put("accessToken",value);
            return ResultUtil.toResultJson(resultJson,WechatMessageType.TRUE, ErrorCodes.WECHAT_SUCCESS_CODE,"");
        }
    }
}
