package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatMediaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by renjianfei on 2017/3/29.
 */
@Service
public class MediaUploadService {

    private static Logger logger = LoggerFactory.getLogger(MediaUploadService.class);

    @Autowired
    private AccessTokenService accessTokenService;
    /**
     * 微信服务器素材上传
     * @param file  表单名称media
     * @param mpID  公众号id
     * type只支持四种类型素材(video/image/voice/thumb)
     */
    public JSONObject uploadMemberShipBackground(File file, String mpID) {
        String url = WechatBaseApi.API_UPLOAD_MEDIA;
        String type = WechatMessageType.IMAGE;
        String token = null;

        JSONObject result = accessTokenService.getAccessToken(mpID);
        if(WechatMessageType.FALSE.equals(result.getString(WechatMessageType.IS_SUCCESS))){
            return result;
        }else {
            token = result.getString("accessToken");
        }

        JSONObject jsonObject = WechatMediaUtil.uploadMedia(file, token, type, url);
        if(null == jsonObject){
            return ResultUtil.toResultJson(jsonObject,WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_IMAGEURL_ERROR,"图片地址有误");
        }else if (jsonObject.containsKey(WechatMessageType.WECHAT_ERR_CODE)){
            String errcode = jsonObject.getString(WechatMessageType.WECHAT_ERR_CODE);
            return ResultUtil.toResultJson(jsonObject,WechatMessageType.FALSE,null, WechatErrorCode.wechatError.get(errcode));
        }

        return ResultUtil.toResultJson(jsonObject,WechatMessageType.FALSE,ErrorCodes.WECHAT_SUCCESS_CODE,null);
    }
}
