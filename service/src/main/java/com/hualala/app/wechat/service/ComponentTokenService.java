package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatCacheUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 开放平台相关服务类
 * Created by xkia on 2017/3/22.
 */
@Service
public class ComponentTokenService {

    private Logger logger = LoggerFactory.getLogger(ComponentTokenService.class);

    @Value("${wechat.data.componentAppID}")
    private String componentAppID;
    @Value("${wechat.data.componentAppSecret}")
    private String componentAppSecret;

    @Autowired
    private MpInfoService mpInfoService;

    @Autowired
    private WechatCacheUtil wechatCacheUtil;
    /**
     *  获取公共平台token
     *  @return
     */
    public JSONObject initComponentToken() {
        String params = getComponentModel(componentAppID, componentAppSecret);
        JSONObject resultJson = HttpApiUtil.httpPost(WechatBaseApi.API_COMPONENT_TOKEN, params);

        if (resultJson == null || resultJson.getString("component_access_token") == null) {
            String errcode = resultJson.getString("errcode");
            String errmsg = WechatErrorCode.wechatError.get(errcode);
            if (errmsg == null) {
                errmsg = resultJson.getString("errmsg");
            }
            return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE,errcode,errmsg);
        }
        String value = resultJson.getString("component_access_token");
        //缓存
        wechatCacheUtil.setData(componentAppID, "componentAcToken", value, Long.parseLong(resultJson.getString("expires_in")));
        resultJson.put("componentAccessToken", value);
        resultJson.put("isSuccess", WechatMessageType.TRUE);
        return resultJson;
    }


    /**
     * 获取公众号调用凭证(accessToken)授权方式
     *
     * @return
     */
    public JSONObject apiAuthorizerToken(String authorizerAppID) {

        String authorizerRefreshToken = wechatCacheUtil.getData(authorizerAppID, "authorizerReToken");

        if (authorizerRefreshToken == null) {
            Map<String, Object> param = new HashMap<>();
            param.put("appID", authorizerAppID);
            Map<String, Object> mpInfo = mpInfoService.queryMpInfo(param);
            if (mpInfo == null) {
                return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_NULL, "未找到对应的公众号");
            }
            Integer authorize = (Integer) mpInfo.get("authorize");
            Integer mpType = (Integer) mpInfo.get("mpType");
            String authorizerReToken = String.valueOf(mpInfo.get("authorizerRefreshToken"));

            if (authorize != 1) {
                return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_ERROR, "公众号未授权");
            }
            if (mpType != 11 && mpType != 21) {
                return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR, "公众号未认证");
            }
            if (!StringUtils.isEmpty(authorizerReToken)) {
                authorizerRefreshToken = authorizerReToken;
            }
        }

        if (authorizerRefreshToken == null) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_REFRESHTOKEN_ERROR, "刷新令牌丢失，请重新授权公众号");
        }
        Map<String, Object> resultMap = initComponentToken();

        String componentAccessToken = String.valueOf(resultMap.get("componentAccessToken"));

        JSONObject resultJson = componentPost(WechatBaseApi.API_AUTHORIZER_TOKEN, componentAccessToken, getComponentModel(componentAppID, authorizerAppID, authorizerRefreshToken), componentAppID);

        if (WechatMessageType.FALSE == resultJson.getBoolean("isSuccess")) {
            return ResultUtil.toResultJson(null,WechatMessageType.FALSE,null, resultJson.getString("errmsg"));
        }

        String value = resultJson.getString("authorizer_access_token");
        // 缓存
        wechatCacheUtil.setData(authorizerAppID, "authorizerAcToken", value, Long.parseLong(resultJson.getString("expires_in")));
        // 刷新令牌
        authorizerRefreshToken = resultJson.getString("authorizer_refresh_token");

        wechatCacheUtil.setData(authorizerAppID, "authorizerReToken", authorizerRefreshToken);
        resultJson.put("authorizerAccessToken",value);
        resultJson.put("accessToken",value);
        return ResultUtil.toResultJson(resultJson,WechatMessageType.TRUE,ErrorCodes.WECHAT_SUCCESS_CODE,"");
    }

    /**
     * 开放平台post请求
     * @param url
     * @param componentAccessToken
     * @param parm
     * @param appID
     * @return
     */
    public JSONObject componentPost(String url,String componentAccessToken,String parm,String appID){
        JSONObject resultJson = null;
        String errmsg = null;
        if(org.apache.commons.lang3.StringUtils.isEmpty(url) || org.apache.commons.lang3.StringUtils.isEmpty(componentAccessToken) || org.apache.commons.lang3.StringUtils.isEmpty(appID)){
            return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE,ErrorCodes.WECHAT_PARPAS_EMPTY,"缺少参数");
        }
        resultJson = HttpApiUtil.httpPost(url+"?component_access_token=" + componentAccessToken, parm);

        if(resultJson == null){
            return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR,"httpClient请求出错");
        }
        String errcode = resultJson.getString("errcode");
        if(errcode!=null){
            if(WechatMessageType.WECHAT_INVALID.equals(errcode) || "42001".equals(errcode)){
                Map<String, Object> resultMap = initComponentToken();
                if(resultMap == null || !(Boolean) resultMap.get("isSuccess")) {
                    return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_ERROR,"获取accessToken服务失败");
                }
                componentAccessToken = String.valueOf(resultMap.get("componentAccessToken"));

                if(componentAccessToken==null){
                    return ResultUtil.toResultJson(null,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, "缓存中获取componentAccessToken is null");
                }
                resultJson = HttpApiUtil.httpPost(url+"?component_access_token=" + componentAccessToken, parm);
                if(resultJson == null){
                    return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR,"httpClient请求出错");
                }
                errcode=resultJson.getString("errcode");
                if(errcode!=null && !"0".equals(errcode)){
                    errmsg  = WechatErrorCode.wechatError.get(errcode);
                    return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ERROR,errmsg);
                }
            }else if(!"0".equals(errcode)){
                errmsg  = WechatErrorCode.wechatError.get(errcode);
                return ResultUtil.toResultJson(resultJson,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ERROR,errmsg);
            }
        }
        resultJson.put("isSuccess", WechatMessageType.TRUE);
        return resultJson;
    }

    /**
     * 获取授权令牌
     * @param appID
     * @return
     */
    public JSONObject getAuthorizerAcToken (String appID){
        JSONObject result = new JSONObject();
        String authorizerAcToken = wechatCacheUtil.getData(appID,"authorizerAcToken");

        if(authorizerAcToken==null){
            apiAuthorizerToken(appID);
            authorizerAcToken = wechatCacheUtil.getData(appID,"authorizerAcToken");
            if(authorizerAcToken==null){
                return ResultUtil.toResultJson(result,WechatMessageType.FALSE,ErrorCodes.WECHAT_MP_ERROR,"缓存中获取preAuthCode is null");
            }
        }
        result.put("isSuccess", WechatMessageType.TRUE);
        result.put("authorizerAcToken", authorizerAcToken);
        result.put("accessToken", authorizerAcToken);
        return result;
    }

    /**
     * 拼接获取componentToken请求json
     * * @param component_appid
     *
     * @param component_appsecret
     * @return
     */
    private String getComponentModel(String component_appid, String component_appsecret) {

        String ticket = wechatCacheUtil.getData(component_appid, "ticket");
        if (ticket == null) {
            logger.error("component_verify_ticket is null ");
            return null;
        }
        StringBuffer sb = new StringBuffer("{");
        sb.append("\"component_appid\":\"").append(component_appid).append("\",")
                .append("\"component_appsecret\":\"").append(component_appsecret).append("\",")
                .append("\"component_verify_ticket\":\"").append(ticket).append("\"")
                .append("}");

        return sb.toString();
    }

    /**
     * 拼接获取componentToken请求json
     * @param component_appid
     * @param authorizer_appid
     * @param authorizer_refresh_token
     * @return
     */
    private String getComponentModel(String component_appid, String authorizer_appid, String authorizer_refresh_token) {
        StringBuffer sb = new StringBuffer("{");
        sb.append("\"component_appid\":\"").append(component_appid).append("\",")
                .append("\"authorizer_appid\":\"").append(authorizer_appid).append("\",")
                .append("\"authorizer_refresh_token\":\"").append(authorizer_refresh_token).append("\"")
                .append("}");

        return sb.toString();
    }
}
