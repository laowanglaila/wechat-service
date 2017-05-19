package com.hualala.app.wechat.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ben on 4/11/17.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redisStock")
public class RedissionConfig {
    public static final String COLON = ":";
    private String host;

    private Integer port;

    private Integer db;

    private Pool pool;

    @Data
    public static class Pool{
        private Integer maxActive;
        private Integer maxWait;
        private Integer maxIdle;
        private Integer minIdle;
    }

    @Bean
    public RedissonClient getRedissionClient(){
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(host+ COLON +port);
        singleServerConfig.setDatabase(db);
        return Redisson.create(config);
    }
}
