//package com.hualala;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//
///**
// * Created by renjianfei on 2017/10/30.
// */
//@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan
//public class WechatSDKApplication extends SpringBootServletInitializer implements CommandLineRunner,EmbeddedServletContainerCustomizer {
//
//    private static Logger logger = LoggerFactory.getLogger( WechatSDKApplication.class);
//
//    @Override
//    public void run(String... strings) throws Exception {
//
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources( WechatSDKApplication.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//        ApplicationContext ctx = SpringApplication.run( WechatSDKApplication.class, args);
//        logger.info("service start success .....");
//    }
//
//    @Override
//    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        configurableEmbeddedServletContainer.setPort(8884);
//    }
//
//}
