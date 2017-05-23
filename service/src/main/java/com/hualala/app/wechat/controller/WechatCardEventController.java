package com.hualala.app.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by renjianfei on 2017/5/22.
 */
@RestController
public class WechatCardEventController {

    @RequestMapping("/card/event")
    public JSONObject event(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(JSONObject.toJSONString(parameterMap));
        ServletInputStream is = null;
        StringBuilder sb = null;
        try {
             is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
             sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
             sb.append(line);
             }

    } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
        String json = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject;
    }

}
