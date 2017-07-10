package com.hualala.app.wechat.config;

import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by renjianfei on 2017/6/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Value("${wechat.card.skuQuantity}")
    private String skuQuantityKey;

    public void setSkuQuantity(String skuQuantityKey) {
        this.skuQuantityKey = skuQuantityKey;
    }

    public String getSkuQuantity() {
        return skuQuantityKey;
    }

    private static final String COLON = ":";

    @Resource(name = "skuRedisTemplate")
    private StringRedisTemplate skuRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;

    @Test
    public void test(){
        Long cardKey = null;
        BaseInfoModelQuery baseInfoModelQuery = new BaseInfoModelQuery();
        List<BaseInfoModel> baseInfoModels = baseInfoModelMapper.selectByExample(baseInfoModelQuery);
        int i = 1;
        for (BaseInfoModel baseInfoModel : baseInfoModels){
            cardKey = baseInfoModel.getCardKey();
//            skuRedisTemplate.delete(skuQuantityKey + COLON + cardKey);
            BoundValueOperations<String, String> valueOps = skuRedisTemplate.boundValueOps(skuQuantityKey + COLON + cardKey);
            String value = valueOps.get();
            System.out.println("第 "+(i++)+" 个CardKey[ "+cardKey+" ]名称["+baseInfoModel.getTitle()+"]当前库存:"+value+"个");
        }

    }
}
