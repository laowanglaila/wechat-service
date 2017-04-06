package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.util.HttpApiUtil;
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

        String[] split = url.trim().split("[?]");
        if(split.length ==1 || !url.contains("access_token")){

            JSONObject result = accessTokenService.getAccessToken(mpID);
            if(WechatMessageType.FALSE.equals(result.getString("isSuccess"))){
                return result;
            }else {
                String accessToken = result.getString("accessToken");
                StringBuilder suilder = new StringBuilder(url);
                url = suilder.append("?access_token="+accessToken).toString();
            }
        }
        JSONObject jsonObject = HttpApiUtil.httpPost(url, params);

        String errCode = jsonObject.getString("errcode");

        if("42001".equals(errCode)){
            //删除原有的accessToken
            String[] sp = url.trim().split("[?]");
            url = sp[0];
            jsonObject = HttpApiUtil.httpPost(url, params);
        }
        return jsonObject;
    }
}
