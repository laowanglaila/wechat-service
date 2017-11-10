package com.hualala.app.wechat.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by renjianfei on 2017/8/28.
 */
@Aspect    // for aop
@Component // for auto scan
public class DataSourceInterceptor {

    @Pointcut("execution(public * com.hualala.app.wechat.mapper.sem.*.*(..))")
    public void dataSourceSlave(){}

    @Before("dataSourceSlave()")
    public void before(JoinPoint jp) {
        DataSourceTypeManager.set(DataSources.SEM);
    }
    @After("dataSourceSlave()")
    public void after(JoinPoint jp) {
        DataSourceTypeManager.set(DataSources.SHOP);
    }

}
