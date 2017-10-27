package com.hualala.app.wechat.config.datasource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by renjianfei on 2017/8/28.
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "Spring.datasource.multipartite")
public class RountingDataSourceConfig {
private Sem sem;
private Shop shop;

    @Data
    public static class Sem {
        String url;
        String username;
        String password;
        String driverClassName;
        Integer initialSize;
        Integer maxActive;
        Integer minIdle;
        String validationQuery;
        String testOnBorrow;
        String testWhileIdle;
        Long timeBetweenEvictionRunsMillis;
        Long minEvictableIdleTimeMillis;
    }
    @Data
    public static class Shop {
        String url;
        String username;
        String password;
        String driverClassName;
        Integer initialSize;
        Integer maxActive;
        Integer minIdle;
        String validationQuery;
        String testOnBorrow;
        String testWhileIdle;
        Long timeBetweenEvictionRunsMillis;
        Long minEvictableIdleTimeMillis;
    }
    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource shopDataSource() {
        return DataSourceConfigSupport.getDataSource(
                shop.getDriverClassName(),
                shop.getUrl(),
                shop.getUsername(),
                shop.getPassword(),
                shop.getInitialSize(),
                shop.getMinIdle(),
                shop.getMaxActive(),
                shop.getValidationQuery());
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource semDataSource() {
        return DataSourceConfigSupport.getDataSource(
                sem.getDriverClassName(),
                sem.getUrl(),
                sem.getUsername(),
                sem.getPassword(),
                sem.getInitialSize(),
                sem.getMinIdle(),
                sem.getMaxActive(),
                sem.getValidationQuery());
    }



    @Bean
    public ThreadLocalRountingDataSource rountingDataSource(
            @Qualifier("shopDataSource") DataSource shopDataSource,
            @Qualifier("semDataSource") DataSource semDataSource

    ){
        ThreadLocalRountingDataSource threadLocalRountingDataSource = new ThreadLocalRountingDataSource();
        threadLocalRountingDataSource.setDefaultTargetDataSource(shopDataSource);
        HashMap<Object, Object> dataSourcesDataSourceHashMap = new HashMap<>();
        dataSourcesDataSourceHashMap.put(DataSources.SHOP,shopDataSource);
        dataSourcesDataSourceHashMap.put(DataSources.SEM,semDataSource);
        threadLocalRountingDataSource.setTargetDataSources(dataSourcesDataSourceHashMap);
        return threadLocalRountingDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(
            @Qualifier("rountingDataSource") DataSource rountingDataSource

    ){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(rountingDataSource);
        sqlSessionFactoryBean.setConfigLocation( new ClassPathResource("mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/com.hualala.app.wechat.mapper/*.xml"));
        }catch (IOException e){
            log.error("mapper文件加载异常",e);
        }
        return sqlSessionFactoryBean;
    }
}
