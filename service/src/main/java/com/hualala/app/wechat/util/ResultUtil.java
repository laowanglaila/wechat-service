package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.core.base.ResultInfo;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * Created by xkia on 2017/3/22.
 */
public class ResultUtil {

    public static JSONObject toResultJson(JSONObject resultJson, boolean isSuccess, String code, String message){
        if(resultJson == null){
            resultJson = new JSONObject();
        }
        resultJson.put(WechatMessageType.IS_SUCCESS, isSuccess);
        resultJson.put(WechatMessageType.MESSAGE, message);
        if(StringUtils.isEmpty(code)){
            resultJson.put(WechatMessageType.CODE, ErrorCodes.WECHAT_MP_ERROR);
        } else {
            resultJson.put(WechatMessageType.CODE, code);
        }
        return resultJson;
    }


    /**
     *与toResultJson配合使用，使用toResultJson后再使用该方法获取标准返回值
     * @param jsonObject 必须是经过判断是否成功 ，使用toResultJson包装过对象
     * @param clazz  grpc期望返回参数的class
     * @param <T> grpc期望返回参数的class
     * @return 一个包含成功，失败以及返回参数的完整对象
     */
    public static <T extends ResultInfo> T getResultInfoBean(JSONObject jsonObject, Class<T> clazz) {
        Map<String, Object> stringObjectMap = WechatNameConverterUtil.convertToJavaStyle(jsonObject);
        T object = JSONObject.parseObject(JSONObject.toJSONString(stringObjectMap), clazz);
        try {
            Class<ResultInfo> resultInfoClass = ResultInfo.class;
            Method setCode = resultInfoClass.getDeclaredMethod("setCode", String.class);
            Method setMessage = resultInfoClass.getDeclaredMethod("setMessage", String.class);

            setCode.invoke(object,jsonObject.getString(WechatMessageType.CODE));
            setMessage.invoke(object,jsonObject.getString(WechatMessageType.MESSAGE));
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return object;
    }
}
