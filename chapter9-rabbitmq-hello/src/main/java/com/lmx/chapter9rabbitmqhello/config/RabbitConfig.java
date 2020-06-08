package com.lmx.chapter9rabbitmqhello.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author lmx
 * @date 2020-06-06 19:36
 * 配置类，用于配置队列、交换器、路由等
 * 此处仅配置了一个基本的队列
 * 注意使用 -   org.springframework.amqp.core.Queue
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue testQueue() {
        Queue queue = new Queue("hello");
        return queue;
    }

}
