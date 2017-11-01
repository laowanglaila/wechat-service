package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by renjianfei on 2017/8/23.
 */
@Slf4j
public class OkHttpUtil {
    public static OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final Integer MAXIDLE_CONNECTION = 20;
    public static final Long KEEP_ALIVE_DURATION = 10L;
    public static final Long RESPONSE_TIMOUT = 5L;
    public static final Long READ_WRITE_TIMOUT = 10L;

    static {
        ConnectionPool connectionPool = new ConnectionPool(MAXIDLE_CONNECTION, KEEP_ALIVE_DURATION, TimeUnit.MINUTES);
        client = new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .connectTimeout( RESPONSE_TIMOUT,TimeUnit.SECONDS )
                .readTimeout( READ_WRITE_TIMOUT ,TimeUnit.SECONDS)
                .writeTimeout( READ_WRITE_TIMOUT ,TimeUnit.SECONDS)
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
     * 异步get请求
     *
     * @param url
     * @param onResponse
     */
    public static void get(String url, Consumer<Response> onResponse) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new DefaultCallback( onResponse ));
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
     * 异步post请求,实现方法onResponse，提供默认的onFailure方法
     *
     * @param url
     * @param json
     * @param onResponse
     * @throws IOException
     */
    public static void post(String url, String json, Consumer<Response> onResponse) {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new DefaultCallback(onResponse));
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
    static class DefaultCallback implements Callback{

        private int maxRetryTimes = 3;
        String RETRY_NAME = "retryTimes";
        private Consumer<Response> onResponse;
        DefaultCallback(Consumer<Response> onResponse){
            this.onResponse = onResponse;
        }
        @Override
        public void onFailure(Call call, IOException e) {
            Request request = call.request();
            String header = request.header( RETRY_NAME );
            Integer retryTimes;
            if (StringUtils.isBlank( request.header( RETRY_NAME ) )) {
                retryTimes = 0;
            }else {
                retryTimes = Integer.parseInt( header );
            }
            if(e.getCause().equals(SocketTimeoutException.class) && retryTimes < maxRetryTimes)//如果超时并未超过指定次数，则重新连接
            {
                retryTimes = retryTimes + 1;
                request.newBuilder().addHeader( RETRY_NAME,retryTimes.toString() );
                client.newCall(call.request()).enqueue(this);
            }else {
                log.error( "OkHttp异步请求发生异常-Request:"+request.toString()+"" );
                log.error( "OkHttp异步请求发生异常-RequestBody:{}",JSONObject.toJSONString( request.body()));
                log.error( "OkHttp异步请求发生异常-Exception:",e);
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException{
            onResponse.accept( response );
        }

    }
}
