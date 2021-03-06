package com.hualala;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by xiangbin on 2016/10/19.
 */
@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan("com.hualala.**")
@ComponentScan
public class ServiceApplication extends SpringBootServletInitializer implements CommandLineRunner,EmbeddedServletContainerCustomizer {

    private static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

    @Override
    public void run(String... strings) throws Exception {

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServiceApplication.class);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(ServiceApplication.class, args);
        logger.info("service start success .....");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8881);
    }
//    @Bean
//    @Primary
//    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory rcf) {
//
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(rcf);
//        template.setKeySerializer(new StringRedisSerializer());
//       // template.setStringSerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        return template;
//    }
}
