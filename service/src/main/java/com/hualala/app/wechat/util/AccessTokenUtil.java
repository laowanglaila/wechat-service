package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取accessToke工具类
 * Created by xkia on 2017/2/17.
 */
public class AccessTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenUtil.class);

    public static JSONObject getAccessToken(String mpID){
        JSONObject result = new JSONObject();
        if(mpID==null || mpID.trim().isEmpty()){
            result.put("isSuccess", WechatMessageType.FALSE);
            result.put("errmsg", "mpID is null。");
            return result;
        }

        String appID = null;
        String authorize = null;
        // TODO 获取公众号信息
        JSONObject mpInfoJson = null;
        if(mpInfoJson == null) {
            result.put("isSuccess", WechatMessageType.FALSE);
            result.put("errmsg", "缓存中获取accessToken is null。");
            return result;
        }
        appID = mpInfoJson.getString("appID");
        authorize = mpInfoJson.getString("authorize");
        if(logger.isInfoEnabled())
            logger.info("get appID and authorize! appID:"+appID+ " authorize:"+authorize);
        if(authorize != null && "1".equals(authorize.trim())){
            return getAuthorizerAcTokenFromCache(appID);
        } else if("2".equals(authorize.trim())){
            result.put("isSuccess", WechatMessageType.FALSE);
            result.put("errmsg", "该公众号已经取消第三方平台授权");
        }
        String accessToken = WechatCacheUtil.getCacheAccessToken(mpID);

        if(accessToken==null){

            // TODO 获取accessToken到redis中
//            Dataset dataset=DatasetFactory.buildDataset();
//            dataset.putProperty("mpID", mpID);
//            RequestModel requestServiceModel=BaseModelFactory.buildRequestModel("wechat_accessToken",dataset);
//            ResponseModel responseServiceModel=ServiceClientFactory.getNativeClient().doRequestResponse(requestServiceModel);
//            if(responseServiceModel == null || !responseServiceModel.isSuccess()){
//                result.put("isSuccess", WechatMessageType.FALSE);
//                result.put("errmsg", responseServiceModel.isSuccess()==false ? responseServiceModel.getReturnMessage() : "获取accessToken服务失败。");
//                return result;
//            }
            accessToken = WechatCacheUtil.getCacheAccessToken(mpID);

            if(accessToken==null){
                result.put("isSuccess", WechatMessageType.FALSE);
                result.put("errmsg", "缓存中获取accessToken is null。");
                return result;
            }
        }
        result.put("isSuccess", WechatMessageType.TRUE);
        result.put("accessToken", accessToken);
        return result;
    }

    public static JSONObject getAuthorizerAcTokenFromCache (String appID){
        return null;
    }
}
