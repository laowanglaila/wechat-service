package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by renjianfei on 2017/8/23.
 */
@Slf4j
public class OkHttpUtil {
    public static OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final Integer MAXIDLE_CONNECTION = 20;
    public static final Long KEEP_ALIVE_DURATION = 10L;

    static {
        ConnectionPool connectionPool = new ConnectionPool(MAXIDLE_CONNECTION, KEEP_ALIVE_DURATION, TimeUnit.MINUTES);
        client = new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .build();
    }



    public static JSONObject get(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        return doHttpRequest(request);
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param callback
     */
    public static void get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * post请求
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static JSONObject post(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return doHttpRequest(request);
    }



    /**
     * 异步post请求
     *
     * @param url
     * @param json
     * @param callback
     * @throws IOException
     */
    public static void post(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    /**
     * 发送http请求
     * @param request
     * @return
     */
    private static JSONObject doHttpRequest(Request request) {
        Response response;
        JSONObject jsonObject;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("http error message:[{}]", response.message());
                throw new WechatException(WechatExceptionTypeEnum.WECHAT_HTTP_FAILED,response.code()+ " - " +response.message());
            }
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("http IOException:[{}]", e.getMessage());
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_HTTP_FAILED, e.getMessage());
        }
        return jsonObject;
    }
}
