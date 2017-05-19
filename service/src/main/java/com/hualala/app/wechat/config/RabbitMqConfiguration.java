package com.hualala.app.wechat.config;

import com.hualala.core.app.Logger;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by renjianfei on 2017/5/15.
 */
@Configuration
public class RabbitMqConfiguration {

    private Logger logger = Logger.of(RabbitMqConfiguration.class);

    @Autowired
    private RabbitQueueProps queueProps;
    //缓存二维码exchangeName
    private static final String ORDER_QUERY_EXCHANGE_NAME = "hll:service:pay:exchange:orderQuery";
    //缓存二维码的队列名
    private static final String ORDER_QUERY_QUEUE_NAME = "hll:service:pay:queue:orderQuery";
    //缓存二维码routekey
    public static final String QRCODE_CACHE_ROUTE_KEY = "qrcode_cache_route_key";

    /**
     * 创建查单消息队形
     * @return
     */
    @Bean
    public Queue cacheQrcodeConsumerQueue(AmqpAdmin amqpAdmin) {
        String queueName = queueProps.getCacheQrcodeQueue();
        logger.info(() -> "init orderQuery consumer queue [" + queueName + "]");
        Queue queue = new Queue(queueName);
//        Queue queue  = QueueBuilder.nonDurable(queueName).build();
//        Map<String, Object> queueArguments = new HashMap<>();
//        queueArguments.put("x-message-ttl",5000);
//        queueArguments.put("x-max-length",1);

        return queue;
    }

    /**
     * 创建查询exchange
     * @return
     */
    @Bean
    public FanoutExchange cacheQrcodeConsumerExchange(AmqpAdmin amqpAdmin) {
        String exchangeName = queueProps.getCacheQrcodeExchange();
        logger.info(() -> "init orderQuery consumer exchange [" + exchangeName + "]");
        FanoutExchange exchange = new FanoutExchange(exchangeName);
        exchange.setDelayed(true);
        amqpAdmin.declareExchange(exchange);
        return exchange;
    }

    /**
     * 绑定查单队列
     * @param amqpAdmin
     * @return
     */
    @Bean
    public Binding cacheQrcodeConsumerBinding(AmqpAdmin amqpAdmin) {
        return BindingBuilder.bind(cacheQrcodeConsumerQueue(amqpAdmin)).to(cacheQrcodeConsumerExchange(amqpAdmin));
    }

}