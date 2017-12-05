//package com.hualala.app.wechat.config;
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Created by renjianfei on 2017/7/3.
// */
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "redisLocker")
//public class JedisPoolConfiguration {
//    public static final String COLON = ":";
//    private String host;
//
//    private Integer port;
//
//    private Integer db;
//
//    private RedisTemplateConfig.Pool pool;
//
//    @Data
//    public static class Pool {
//        private Integer maxActive;
//        private Integer maxWait;
//        private Integer maxIdle;
//        private Integer minIdle;
//    }
//    @Bean(name = "jedisPool",destroyMethod = "destroy")
//    public JedisPool getJedisPool() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait());
//        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
//        jedisPoolConfig.setMinIdle(pool.getMinIdle());
//        //实例化连接池
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,1000,null,db);
//        //初始化StringRedisTemplate
//        return jedisPool;
//    }
//
//}
