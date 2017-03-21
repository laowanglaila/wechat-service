package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.app.wechat.util.WechatCacheUtil;
import org.apache.commons.lang.StringUtils;
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
    private MpInfoService mpInfoService;

    private static MpInfoService mpInfoServiceStatic;

    private void init(){
        mpInfoServiceStatic = this.mpInfoService;
    }

    public static Map<String, Object> getAccessToken(String mpID) {

        JSONObject result = new JSONObject();
        if (mpID == null || mpID.trim().isEmpty()) {
            result.put("isSuccess", WechatMessageType.FALSE);
            result.put("errmsg", "mpID is null。");
            return result;
        }

        String appID = null;
        String authorize = null;
        // TODO 获取公众号信息
        JSONObject mpInfoJson = null;
        if (mpInfoJson == null) {
            result.put("isSuccess", WechatMessageType.FALSE);
            result.put("errmsg", "缓存中获取accessToken is null。");
            return result;
        }
        appID = mpInfoJson.getString("appID");
        authorize = mpInfoJson.getString("authorize");
        if (logger.isInfoEnabled())
            logger.info("get appID and authorize! appID:" + appID + " authorize:" + authorize);
        if (authorize != null && "1".equals(authorize.trim())) {
            return getAuthorizerAcTokenFromCache(appID);
        } else if ("2".equals(authorize.trim())) {
            result.put("isSuccess", WechatMessageType.FALSE);
            result.put("errmsg", "该公众号已经取消第三方平台授权");
        }
        String accessToken = WechatCacheUtil.getCacheAccessToken(mpID);

        if (accessToken == null) {

            // TODO 获取accessToken到redis中
            Map<String,Object> param = new HashMap<String,Object>();

            initAccessToken(param);
          //  accessToken = WechatCacheUtil.getCacheAccessToken(mpID);

            if (accessToken == null) {
                result.put("isSuccess", WechatMessageType.FALSE);
                result.put("errmsg", "缓存中获取accessToken is null。");
                return result;
            }
        }
        result.put("isSuccess", WechatMessageType.TRUE);
        result.put("accessToken", accessToken);
        return result;
    }

    private static Map<String, Object> initAccessToken(Map<String,Object> param){
        Map<String,Object> mpInfoLst = mpInfoServiceStatic.queryMpInfo(param);
        if(mpInfoLst == null){
            return null;
        }
        String appID = String.valueOf(mpInfoLst.get("appID"));
        String mpID = String.valueOf(mpInfoLst.get("mpID"));
        String appSecret = String.valueOf(mpInfoLst.get("appSecret"));
        String authorize = String.valueOf(mpInfoLst.get("authorize"));
        String authorizerRefreshToken = String.valueOf(mpInfoLst.get("authorizerRefreshToken"));
        String url = null;
        if(WechatBaseApi.AUTHORIZE_1.equals(authorize)){
            String refreshToken = WechatCacheUtil.getData(appID, "authorizerReToken");

           // WechatCacheUtil.setData(componentAppID, "componentAcToken", value, Long.parseLong(result.getString("expires_in")));
        } else {
            url = WechatBaseApi.GET_ACCESS_TOKEN + "&appid=" + appID + "&secret=" + appSecret;
            JSONObject resultJson = HttpApiUtil.httpGet(url);
            if(resultJson == null){
                return null;
            }

            if (resultJson == null || resultJson.getString("access_token") == null) {
                String errcode = resultJson.getString("errcode");
                String errmsg   = WechatErrorCode.wechatError.get(errcode);
                if(errmsg == null){
                    errmsg = resultJson.getString("errmsg");
                }
                logger.error("get access token failed for mpID=" + appID + " , errcode : " + errcode + " , errmsg : "+errmsg);
                return null;
            }
            String value = resultJson.getString("access_token");
            WechatCacheUtil.setCacheAccessToken(mpID, value, Long.parseLong(resultJson.getString("expires_in")));
            param.put("accessToken",value);
            return param;
        }
        return null;
    }

    public static JSONObject getAuthorizerAcTokenFromCache (String appID){
        return null;
    }

    /**
     * 开放平台Post请求
     * @param url
     * @param componentAccessToken
     * @param parm
     * @param appID
     * @return
     */
    public static JSONObject componentPost(String url,String componentAccessToken,String parm,String appID){
        JSONObject resultJson = null;
        String errmsg = null;
        if(url==null || url.trim().isEmpty()
                || componentAccessToken==null || componentAccessToken.trim().isEmpty()
                || appID==null || appID.trim().isEmpty()){
            resultJson = new JSONObject();
            resultJson.put("isSuccess", WechatMessageType.FALSE);
            resultJson.put("errmsg", "缺少参数。");
            return resultJson;
        }
        resultJson = HttpApiUtil.httpPost(url+"?component_access_token=" + componentAccessToken, parm);
        logger.info(">>>>  invoke url=" + url + ", return=" + resultJson);
        if(resultJson == null){
            resultJson = new JSONObject();
            resultJson.put("isSuccess", WechatMessageType.FALSE);
            resultJson.put("errmsg", "httpClient请求出错。");
            return resultJson;
        }
        String errcode = resultJson.getString("errcode");
        if(errcode!=null){
            if(WechatMessageType.WECHAT_INVALID.equals(errcode) || "42001".equals(errcode)){
//                Dataset dataset=DatasetFactory.buildDataset();
//                dataset.putProperty("componentAppID", appID);
//                RequestModel  requestServiceModel=BaseModelFactory.buildRequestModel("wechat_apiComponentAccessToken",dataset);
//                ResponseModel responseServiceModel=ServiceClientFactory.getNativeClient().doRequestResponse(requestServiceModel);
//
//                if(responseServiceModel == null || !responseServiceModel.isSuccess()){
//                    result = new JSONObject();
//                    result.put("isSuccess", WechatMessageType.FALSE);
//                    result.put("errmsg", responseServiceModel.isSuccess()==false ? responseServiceModel.getReturnMessage() : "获取accessToken服务失败。");
//                    return result;
//                }
                componentAccessToken = WechatCacheUtil.getData(appID,"componentAcToken");

                logger.debug(">>>> appID="+appID+" , componentAccessToken=" + componentAccessToken);
                if(componentAccessToken==null){
                    resultJson = new JSONObject();
                    resultJson.put("isSuccess", WechatMessageType.FALSE);
                    resultJson.put("errmsg", "缓存中获取componentAccessToken is null。");
                    return resultJson;
                }
                resultJson = HttpApiUtil.httpPost(url+"?component_access_token=" + componentAccessToken, parm);
                logger.debug(">>>>  invoke url=" + url + ", return=" + resultJson);
                if(resultJson == null){
                    resultJson = new JSONObject();
                    resultJson.put("isSuccess", WechatMessageType.FALSE);
                    resultJson.put("errmsg", "httpClient请求出错。");
                    return resultJson;
                }
                errcode=resultJson.getString("errcode");
                if(errcode!=null && !"0".equals(errcode)){
                    errmsg  = WechatErrorCode.wechatError.get(errcode);
                    if(errmsg != null){
                        resultJson.put("errmsg", errmsg);
                    }
                    resultJson.put("isSuccess", WechatMessageType.FALSE);
                    return resultJson;
                }
            }else if(!"0".equals(errcode)){
                errmsg  = WechatErrorCode.wechatError.get(errcode);
                if(errmsg != null){
                    resultJson.put("errmsg", errmsg);
                }
                resultJson.put("isSuccess", WechatMessageType.FALSE);
                return resultJson;
            }
        }
        resultJson.put("isSuccess", WechatMessageType.TRUE);
        return resultJson;
    }
}
