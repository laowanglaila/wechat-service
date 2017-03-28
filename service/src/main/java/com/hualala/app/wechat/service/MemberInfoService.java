package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.mapper.WechatMemberInfoMapper;
import com.hualala.app.wechat.model.WechatMemberInfoModel;
import com.hualala.app.wechat.util.HttpApiUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/3/23.
 *
 * 根据本地的txt文件中cardID和Cord，从微信公众服务平台获取会员信息json，并保存到本地数据库。
 *
 */
@Service
public class MemberInfoService {



    private Logger logger = LoggerFactory.getLogger(ComponentTokenService.class);

    private static String accessToken = null;
    private static Long iflush = 0L;

    public static void setAccessToken(String accessToken) {
        MemberInfoService.accessToken = accessToken;
    }

    private static String appId = "wx58e9eacc01f7880c";
    private static String appSecret = "bb43ed30da3409fb1a8ef2b432019332";

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


    private int startLine = 0;
    @Autowired
    private WechatMemberInfoMapper memberInfoMapper;

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    /**
     * 获取本地的txt文件中cardID和Cord
     */
    public void loadInfo(String filePath,int startLine){
        setStartLine(startLine);
        loadInfo(filePath);
    }
    public void loadInfo(String filePath){
        Map<String, String> params = new HashMap<>();
        int lineNumber = 0;
        File file = null;
        try {
            String encoding="GBK";
            file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);
                String lineTxt = null;
                while((lineTxt = lineNumberReader.readLine()) != null){
                    lineNumber = lineNumberReader.getLineNumber();
                    //请求开始行以后的数据
                    if(lineNumber >= startLine) {
                        logger.info("在操作: [ "+file.getAbsolutePath()+" ]的第[ "+lineNumber+" ]行数据！");
                        String[] split = lineTxt.split(" ");
                        params.clear();
                        params.put("card_id", split[0]);
                        params.put("code", split[1]);
                        System.out.println(params);
                        // 访问微信接口
                        JSONObject jsonObject = visitWeChat(params);

                        //创建数据库参数
                        WechatMemberInfoModel memberInfoModel = new WechatMemberInfoModel();
                        memberInfoModel.setWechatJson(jsonObject.toJSONString());
                        memberInfoModel.setErrcode(Integer.parseInt(jsonObject.getString("errcode")));
                        memberInfoModel.setAppID(appId);
                        memberInfoModel.setCardID(split[0]);
                        memberInfoModel.setCode(split[1]);
                        //持久化到数据库
//                        System.out.println("---------------------------------------------" + memberInfoModel);
                    memberInfoMapper.insert(memberInfoModel);
                    }

                }
                read.close();
            }else{
                logger.debug("找不到指定的文件");
            }
        } catch (Exception e) {
            logger.error("在操作: [ "+file.getAbsolutePath()+" ]的第[ "+lineNumber+" ]行出错！");
            e.printStackTrace();
        }

    }

    /**
     *获取一条会员json，如果accessToken无效重新获取
     * @param params
     * @return
     */
    private JSONObject visitWeChat(Map<String,String> params) throws Exception {

        if(StringUtils.isBlank(accessToken)){
            getAccessToken();
        }

        String jsonParams = JSONObject.toJSONString(params);
        System.out.println("请求json ：------ "+jsonParams);
        String url = "https://api.weixin.qq.com/card/membercard/userinfo/get?access_token="+accessToken;
        JSONObject jsonObject = HttpApiUtil.httpPost(url, jsonParams);

        if(jsonObject == null){
            throw new RuntimeException("httpPost请求memberInfo异常！！！");
        }
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            //accessToken超时
            if("42001".equals(errcode)){
                getAccessToken();
                jsonObject = visitWeChat(params);
            }
            //appSecret不对，或者不合法的accessToken
            if("40014".equals(errcode) || "40001".equals(errcode)){
                throw new RuntimeException("请查询微信错误代码："+errcode);
            }
        }
        return jsonObject;
    }

    /**
     * 请求会员数据之前，判断accessToken是否失效
     * 如果失效重新获取
     */
    public synchronized String getAccessToken() {
        //获取一个新的accessToken
//     appid:   wx58e9eacc01f7880c
//     appSecret:   bb43ed30da3409fb1a8ef2b432019332
        if(iflush > System.currentTimeMillis()){
            return accessToken;
        }
        String url = WechatBaseApi.GET_ACCESS_TOKEN + "&appid=" + appId + "&secret=" + appSecret;
        JSONObject resultJson = HttpApiUtil.httpGet(url);
        accessToken = resultJson.getString("access_token");
        Long expiresIn = Long.parseLong(resultJson.getString("expires_in"));
        System.out.println("------------accessToken有效时间-------------: "+expiresIn+"s ");
        long currentTime = System.currentTimeMillis();
        iflush = currentTime + (expiresIn-200)*1000;
        logger.debug("获取到的AccessToken:["+accessToken+"]");
        return accessToken;
    }





}
