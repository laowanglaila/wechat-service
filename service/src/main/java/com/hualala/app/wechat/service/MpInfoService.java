package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.RedisKeys;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.model.mp.MpInfoCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xkia on 2017/3/21.
 */
@Service
public class MpInfoService {

    @Autowired
    private WechatMpMapper wechatMpMapper;
    @Resource(name = "skuRedisTemplate")
    private StringRedisTemplate skuRedisTemplate;
    private Long EXPIRES = 5L;

    /**
     * 获取缓存MpInfo
     */
    public MpInfoCache getMpInfoByMpID(String mpID) throws WechatInnerException {
        return getMpInfoByMpID( mpID ,true);
    }

    private MpInfoCache getMpInfoByMpID(String mpID,Boolean isUseCache) throws WechatInnerException {
        MpInfoCache mpInfoCache = null;
        BoundValueOperations<String, String> valueOps = skuRedisTemplate.boundValueOps( RedisKeys.WEHCHAT_MPINFO_KEY + mpID );
        if (isUseCache){
            String json = valueOps.get();
            if (StringUtils.isNotBlank( json ) ){
                return JSONObject.parseObject( json, MpInfoCache.class );
            }
        }
        mpInfoCache = wechatMpMapper.queryByMpID( mpID );
        if (mpInfoCache == null){
            throw new WechatInnerException("获取mpInfo失败");
        }
        String jsonString = JSONObject.toJSONString( mpInfoCache );
        valueOps.set( jsonString,EXPIRES, TimeUnit.MINUTES );
        return mpInfoCache;
    }

    /**
     * query mpInfo by mpID or appID
     *
     * @param param
     */
    public Map<String, Object> queryMpInfo(Map<String, Object> param) {

        List<Map<String, Object>> list = wechatMpMapper.queryByParams(param);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }


    /**
     * 获取具有api权限的公众号
     *
     * @param groupID
     * @param brandID
     * @param shopID
     * @return
     */
    public String queryMpIDAuth(long groupID, long brandID, long shopID) {
        String mpID = wechatMpMapper.queryMpIDAuth(groupID, brandID, shopID);

        if (StringUtils.isEmpty(mpID)) {
            return null;
        }
        return mpID;
    }

}
