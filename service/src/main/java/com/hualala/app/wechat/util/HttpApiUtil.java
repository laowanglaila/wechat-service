package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xkia on 2017/2/17.
 */
public class HttpApiUtil {

    private static final int SOCKET_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 5000;
    private static Logger logger = LoggerFactory.getLogger(HttpApiUtil.class);
    private static String ENCODE = "UTF-8";


    public static JSONObject httpPost(String url, Map<String, String> params) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        //公共请求参数
        List<NameValuePair> nameValuePairs = params.keySet().stream().map(key ->
                new BasicNameValuePair(key, params.get(key))
        ).collect(Collectors.toList());
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, ENCODE));
            HttpResponse httpResponse = null;

            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                logger.error("url [" + url + "] statusCode [" + httpResponse.getStatusLine().getStatusCode() + "]");
                // TODO
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String result = result = EntityUtils.toString(httpEntity, ENCODE);
                if (logger.isInfoEnabled()) {
                    logger.info("result data：[" + result + "]");
                }
                return JSON.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static JSONObject httpPost(String url, String params) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        //设置请求头，服务器端关闭连接
        httpPost.setHeader("Connection", "close");

        try {
            httpPost.setEntity(new StringEntity(params, ENCODE));
            HttpResponse httpResponse = null;

            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                logger.error("url [" + url + "] statusCode [" + httpResponse.getStatusLine().getStatusCode() + "]");
                // TODO
            }
            HttpEntity httpEntity  = httpResponse.getEntity();
            if (httpEntity != null) {
                String result = result = EntityUtils.toString(httpEntity, ENCODE);

                if (logger.isInfoEnabled()) {
                    logger.info("result data：[" + result + "]");
                }
                return JSON.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
            return null;
    }


    public static JSONObject httpGet(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                logger.error("");
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String result = result = EntityUtils.toString(httpEntity, ENCODE);
                logger.info(result);
                return JSON.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static JSONObject httpPost(String url,String params,String mpID){
        String[] split = url.split("[?]");
        if(split.length > 1 && split[1].startsWith("access_token")){

        }

        JSONObject jsonObject = httpPost(url, params);

        String errCode = jsonObject.getString("errcode");

        if("42001".equals(errCode)){



        }

        return null;
    }

}
