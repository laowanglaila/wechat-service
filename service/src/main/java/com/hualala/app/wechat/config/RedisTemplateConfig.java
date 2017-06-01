package com.hualala.app.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ben on 4/11/17.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redisSku")
public class RedisTemplateConfig {
    public static final String COLON = ":";
    private String host;

    private Integer port;

    private Integer db;

    private Pool pool;

    @Data
    public static class Pool {
        private Integer maxActive;
        private Integer maxWait;
        private Integer maxIdle;
        private Integer minIdle;
    }

    @Bean(name = "skuRedisTemplate")
    public StringRedisTemplate getSkuRedisTemplate() {
        //实例化链接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        //设置host
        connectionFactory.setHostName(host);
        //设置端口
        connectionFactory.setPort(port);
//        //设置密码
//        connectionFactory.setPassword("redispasswd");
        connectionFactory.setDatabase(db);
        connectionFactory.setUsePool(true);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait());
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        connectionFactory.setPoolConfig(jedisPoolConfig);
        //初始化connectionFactory
        connectionFactory.afterPropertiesSet();
        //实例化
        StringRedisTemplate redis = new StringRedisTemplate(connectionFactory);
        //初始化StringRedisTemplate
        redis.afterPropertiesSet();
        return redis;
    }
}
