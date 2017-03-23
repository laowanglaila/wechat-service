package com.hualala.app.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class WechatCacheUtil {

    private static final Map<String, String> WE_CACHE = new HashMap<String, String>();

    private final Logger logger = LoggerFactory.getLogger(WechatCacheUtil.class);
    //private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static RedisTemplate<String, String> redisTemplateStatic;

    {
        WE_CACHE.put("ticket", "component_ticket_");
        WE_CACHE.put("authorizerAcToken", "authorizer_acToken_");
        WE_CACHE.put("preAuthCode", "pre_auth_code_");
        WE_CACHE.put("componentAcToken", "component_acToken_");
        WE_CACHE.put("authorizerReToken", "authorizer_reToken_");
        WE_CACHE.put("jsticket", "jsapi_ticket_");
        WE_CACHE.put("wechatMpID", "wechat_mpid_");
        WE_CACHE.put("wechatAppID", "wechat_appID_");
        WE_CACHE.put("wechatAcToken", "wechat_acToken_");
    }
    @PostConstruct
    private void init() {
        redisTemplateStatic  = this.stringRedisTemplate;
    }


    /**
     * 更新redis缓存
     *
     * @param record
     */
//    public void updateRedisMpData(Record record) {
//
//        redisTemplate.opsForValue().get()
//
//        if (ContextBeanFactory.containsBean(DataManager.BEAN_ID)) {
//
//            Dataset dataset = DatasetFactory.buildDataset();
//            dataset.addRecord(record);
//            String mpid = record.getString("mpID");
//            String appID = record.getString("appID");
//
//            DataManager dataManager = ContextBeanFactory.getBean(DataManager.BEAN_ID, DataManager.class);
//            dataManager.set(WE_CACHE.get("wechatMpID") + mpid, dataset);
//            dataManager.set(WE_CACHE.get("wechatAppID") + appID, dataset);
//
//        } else {
//            logger.error("not found config dataManager");
//        }
//    }
//
//    /**
//     * 删除redis缓存
//     *
//     * @param key
//     */
//    public void deleteRedisMpDatae(String key) {
//        String recordMpID = "wechat_mpid_" + key;
//        if (ContextBeanFactory.containsBean(DataManager.BEAN_ID)) {
//            DataManager dataManager = ContextBeanFactory.getBean(DataManager.BEAN_ID, DataManager.class);
//            if (logger.isDebugEnabled())
//                logger.debug(">>>deleteRedisMpData mpid:" + key);
//
//            dataManager.delete(recordMpID);
//        } else {
//            logger.error("not found config dataManager");
//        }
//    }


    /**
     * 更新accessToken缓存
     *
     * @param key
     * @param value
     * @param timeout
     */
    public static void setCacheAccessToken(String key, String value, Long timeout) {

        String keyName = "wechat_acToken_" + key;
        setData(keyName, value, timeout);
    }

    /**
     * 获取accessToken缓存
     *
     * @param key
     * @return
     */
    public static String getCacheAccessToken(String key) {
        String keyName = "wechat_acToken_" + key;
        return redisTemplateStatic.opsForValue().get(keyName);
    }

    /**
     * 删除redis缓存
     *
     * @param key
     */
    public static void delData(String key) {
        redisTemplateStatic.delete(key);
    }

    /**
     * redis中添加缓存
     *
     * @param keyName
     * @return
     */
    public static void setData(String keyName, String type, String value) {
        redisTemplateStatic.opsForValue().set(WE_CACHE.get(type) + keyName, value);
    }

    public static void setData(String keyName, String type, String value, Long timeout) {
        setData(WE_CACHE.get(type) + keyName, value, timeout);
    }

    /**
     * 获取redi缓存信息
     *
     * @param keyName
     * @return
     */
    public static String getData(String keyName, String type) {
        return redisTemplateStatic.opsForValue().get(WE_CACHE.get(type) + keyName);
    }

    public static void setData(String keyName, String value, Long timeout) {
        redisTemplateStatic.opsForValue().set(keyName,value,timeout);
    }

    public static JSONObject getMpInfo(String mpID,String appID) {
        String value = null;
        if(StringUtils.isNotEmpty(mpID)) {
            value = redisTemplateStatic.opsForValue().get(WE_CACHE.get("wechatMpID") + mpID);
        } else {
            value = redisTemplateStatic.opsForValue().get(WE_CACHE.get("wechatAppID") + mpID);
        }
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return JSON.parseObject(value);
    }
}
