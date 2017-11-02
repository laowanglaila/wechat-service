package com.hualala.app.wechat.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by ben on 3/20/17.
 */
public class DataSourceConfigSupport {


    static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigSupport.class);

    static DataSource getDataSource(String driverClassName, String url, String username, String password, int initialSize, int minIdle, int maxActive, String validationQuery) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setValidationQuery(validationQuery);
        try {
            LOGGER.debug("Setting 'application.yml' into druid");
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not initial Druid DataSource\n" + e);
        }
        return druidDataSource;
    }

//    static FilterRegistrationBean getFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        LOGGER.debug("Registered druid filter");
//        return filterRegistrationBean;
//    }
}
