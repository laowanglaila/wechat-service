package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.model.mp.MpInfoCache;
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

    @Autowired private MpInfoService mpInfoService;
    /**
     * 获取公众号accessToken
     * @param mpID
     * @return
     */
    public JSONObject getAccessToken(String mpID) throws WechatInnerException {
        return this.getAccessToken( mpID ,false );
    }
    public JSONObject getAccessToken(String mpID,boolean isForceRefresh) throws WechatInnerException {

        if (mpID == null || mpID.trim().isEmpty()) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MPID_EMPTY,"mpID is empty");
        }

        String appID = null;
        String authorize = null;
        Map<String,Object> param = new HashMap<>();
        param.put("mpID",mpID);
        MpInfoCache mpInfoByMpID = mpInfoService.getMpInfoByMpID( mpID );
        appID = mpInfoByMpID.getAppID();
        authorize = mpInfoByMpID.getAuthorize();
        if ("1".equals(authorize)) {
            return componentTokenService.getAuthorizerAcToken(appID,isForceRefresh);
        } else if ("2".equals(authorize)) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR,"该公众号已经取消第三方平台授权");
        }
        param.put("appID",appID);
        JSONObject result = findAccessToken(param ,isForceRefresh);
        return ResultUtil.toResultJson(result,WechatMessageType.TRUE,ErrorCodes.WECHAT_SUCCESS_CODE,"执行成功");
    }

    private JSONObject findAccessToken( Map <String, Object> param,boolean isForceRefresh) throws WechatInnerException {
        String mpID = (String)param.get( "mpID" );
        String accessToken = null;
        if (isForceRefresh){
            accessToken = WechatCacheUtil.getCacheAccessToken(mpID);
        }
        JSONObject result = new JSONObject();
        if (accessToken == null) {
            result = initAccessToken(param);
            if(!result.getBoolean( WechatMessageType.IS_SUCCESS)){
               throw new WechatInnerException("缓存中获取accessToken is null") ;
            }
            accessToken = result.getString("accessToken");
        }
        result.put(WechatMessageType.IS_SUCCESS, WechatMessageType.TRUE);
        result.put("accessToken", accessToken);
        return result;
    }

    /**
     *
     * 初始化accessToken
     * @param param
     * @return
     */
    private JSONObject initAccessToken(Map<String,Object> param) throws WechatInnerException {
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
        return ResultUtil.toResultJson(resultJson,WechatMessageType.TRUE, ErrorCodes.WECHAT_SUCCESS_CODE,"执行成功");

    }
}
