package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import org.apache.commons.lang.StringUtils;

/**
 *
 * Created by xkia on 2017/3/22.
 */
public class ResultUtil {

    public static JSONObject toResultJson(JSONObject resultJson, boolean isSuccess, String code, String errmsg){
        if(resultJson == null){
            resultJson = new JSONObject();
        }
        resultJson.put("isSuccess", isSuccess);
        resultJson.put("errmsg", errmsg);
        if(StringUtils.isEmpty(code)){
            resultJson.put("code", ErrorCodes.WECHAT_MP_ERROR);
        } else {
            resultJson.put("code", code);
        }
        return resultJson;
    }
}
