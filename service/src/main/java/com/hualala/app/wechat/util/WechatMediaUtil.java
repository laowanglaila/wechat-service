package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Administrator on 2017/3/30.
 */
public class WechatMediaUtil {

    private static Logger logger = LoggerFactory.getLogger(WechatMediaUtil.class);

    /**
     * 微信服务器素材上传
     * @param file  表单名称media
     * @param token access_token
     * @param type  type只支持四种类型素材(video/image/voice/thumb)
     */
    public static JSONObject uploadMedia(File file, String token, String type, String url) {
        if(file==null || token==null || type==null || url==null){
            logger.error("file==null || token==null || type==null || url==null,请检查!");
            return null;
        }

        if(!file.exists()){
            logger.error("上传文件不存在,请检查!");
            return null;
        }


        JSONObject jsonObject = null;
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        FilePart media = null;

        HttpClient httpClient = new HttpClient();
        try {
            media = new FilePart("media", file);

            Part[] parts = new Part[] { new StringPart("access_token", token),
                    new StringPart("type", type), media };
            MultipartRequestEntity entity = new MultipartRequestEntity(parts,post.getParams());
            post.setRequestEntity(entity);
            int status = httpClient.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                jsonObject = JSONObject.parseObject(text);
            } else {
                logger.error("upload Media failure status is:" + status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
//
//    public static String HttpPost(String _$url, String _$content)
//    {
//        return HttpPost(_$url, _$content, 5000, 5000, "UTF-8");
//    }
//    public static String HttpPost(String _$url, byte[] _$content)
//    {
//        return HttpPost(_$url, _$content, 10000, 10000, "UTF-8");
//    }
//
//    public static String HttpPost(String _$url, String _$content, String _$charsetName)
//    {
//        return HttpPost(_$url, _$content, 10000, 10000, _$charsetName);
//    }
//    public static String HttpPost(String _$url, String _$content, int _$connectTimeout, int _$readTimeout, String _$charsetName)
//    {
//        return HttpPost(_$url,_$content.getBytes(),_$connectTimeout,_$readTimeout,_$charsetName);
//    }
//    public static String HttpPost(String _$url, byte[] _$content, int _$connectTimeout, int _$readTimeout, String _$charsetName)
//    {
//        String responseString = null;
//        URL url = null;
//        HttpURLConnection http = null;
//        try
//        {
//            url = new URL(_$url);
//            http = (HttpURLConnection)url.openConnection();
//
//            http.setDoOutput(true);
//
//            http.setDoInput(true);
//
//            http.setUseCaches(false);
//
//            http.setRequestMethod("POST");
//
//            http.setConnectTimeout(_$connectTimeout);
//
//            http.setReadTimeout(_$readTimeout);
//
//            byte[] requestStringBytes = _$content;
//            String boundary = "======================";
//            String prifix = "--";
//            String newLine = "\r\n";
//            byte[] start_data = ( prifix + boundary + newLine ).getBytes("UTF-8");
//            byte[] end_data = (newLine + prifix + boundary + prifix + newLine).getBytes("UTF-8");
////            http.setRequestProperty("Content-length", ""+(requestStringBytes.length+"media".getBytes("UTF-8").length));
////            http.setRequestProperty("Content-Type", "application/octet-stream");
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;boundary=" + boundary);
//
//            http.setRequestProperty("Connection", "Keep-Alive");
//            http.setRequestProperty("Charset", _$charsetName);
//
//            OutputStream outputStream = http.getOutputStream();
//            outputStream.write(start_data);
//            outputStream.write(("Content-Disposition: form-data;name=\"media\""+ newLine + newLine).getBytes("UTF-8"));
//            outputStream.write(requestStringBytes);
//            outputStream.write(newLine.getBytes("UTF-8"));
//            outputStream.write(end_data);
//            outputStream.close();
//
//            int responseCode = http.getResponseCode();
//
//            if (200 == responseCode)
//            {
//                StringBuffer sb = new StringBuffer();
//
//                BufferedReader responseReader = new BufferedReader(new InputStreamReader(
//                        http.getInputStream(), _$charsetName));
//                String readLine;
//                while ((readLine = responseReader.readLine()) != null)
//                {
//                    sb.append(readLine);
//                }
//                responseReader.close();
//
//                responseString = sb.toString();
//            }
//        } catch (Exception e) {
//            System.out.println("HttpPost error:" + e);
//            return "";
//        } finally {
//            if (http != null) {
//                http.disconnect();
//            }
//        }
//        return responseString;
//    }
}


