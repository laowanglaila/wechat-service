package com.hualala.app.wechat.service.Qrcode;

import com.hualala.app.wechat.mapper.WechatQrcodeTempMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/5/12.
 */
@Service
public class QrcodeCreateSceneIDService {

    public static final String WECHAT_SENCEID_TEMP = "Wechat_SenceID_Temp";
    public static final String COLON = ":";

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate2;

    @Autowired
    private WechatQrcodeTempMapper qrcodeTempMapper;


    public int getTempSenceID(String mpID) {
//        临时Wechat_SenceID_Temp
        BoundValueOperations<String, String> ops2 = stringRedisTemplate2.boundValueOps(WECHAT_SENCEID_TEMP + COLON + mpID);
        //获取去之前先判断redis是否存在key值==0
        if (StringUtils.isBlank(ops2.get())) {
            //获取SenceID最大值存入redis
            int i = qrcodeTempMapper.queryMaxSenceID(mpID);
            if (StringUtils.isBlank(ops2.get())) {
                ops2.set("" + i);
            }
        }
        return ops2.increment( 1L ).intValue();
    }

}
