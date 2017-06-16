package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.impl.WechatTemplateRpcServiceImpl;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatMediaUtil;
import com.hualala.core.app.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by renjianfei on 2017/3/29.
 */
@Service
public class BaseMediaService {

    private Logger logger = com.hualala.core.app.Logger.of(WechatTemplateRpcServiceImpl.class);

    @Autowired
    private AccessTokenService accessTokenService;



    /**
     * 微信服务器素材上传
     *
     * @param url  微信公众号上传接口url
     * @param type 只支持四种类型素材(video/image/voice/thumb)
     * @param file 表单名称media
     * @param mpID 公众号id
     * @return errcode media_id
     */
    public JSONObject upload(String url, String type, File file, String mpID) {
        String token = null;
        JSONObject result = accessTokenService.getAccessToken(mpID);
        if (WechatMessageType.FALSE.equals(result.getBoolean(WechatMessageType.IS_SUCCESS))) {
            return result;
        } else {
            token = result.getString("accessToken");
        }
        logger.debug(() -> "上传微信文件 ：\r\n" + "FileName:" + file.getName() + "\r\ntype:" + type + "\r\nurl:" + url + "\r\nmpID:" + mpID);
        JSONObject responseJson = WechatMediaUtil.uploadMedia(file, token, type, url);
        //首先判断 null ：200    然后判断创建是否成功
        if (null == responseJson) {
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, "http请求失败！");
        }
        logger.debug(() -> "微信响应参数 ：" + responseJson.toJSONString());
        String errcode = responseJson.getString(WechatMessageType.WECHAT_ERR_CODE);
        if (StringUtils.isNotBlank(errcode) && !"0".equals(errcode)){
            String errmassage = WechatErrorCode.wechatError.get(errcode);
            String errorcode = errmassage == null ? "[ errcode:" + errcode + " ]" : errmassage;
            logger.error(errorcode + ":[ " + responseJson.getString(WechatMessageType.WECHAT_ERR_MESSAGE) + " ]");
            return ResultUtil.toResultJson(responseJson, false, null, errorcode + ":[ " + responseJson.getString(WechatMessageType.WECHAT_ERR_MESSAGE) + " ]");
        }
        return ResultUtil.toResultJson(responseJson, true, ErrorCodes.WECHAT_SUCCESS_CODE, "执行成功！");
    }


    /**
     * 微信服务器Content图片上传
     *
     * @param file 表单名称media
     * @param mpID 公众号id
     * @return url
     */
    public JSONObject uploadImage(File file, String mpID) {
        String url = WechatBaseApi.API_UPLOAD_MEDIA;
        String type = WechatMessageType.IMAGE;
        return upload(url, type, file, mpID);
    }


    /**
     * 微信服务器临时素材上传
     *
     * @param file 表单名称media
     * @param mpID 公众号id
     * @param type 只支持四种类型素材(video/image/voice/thumb)
     * @return errcode media_id
     */
    public JSONObject uploadTempMedia(String type, File file, String mpID) {
        String url = WechatBaseApi.API_UPLOAD_TEMP_MEDIA;
        return upload(url, type, file, mpID);
    }
}
