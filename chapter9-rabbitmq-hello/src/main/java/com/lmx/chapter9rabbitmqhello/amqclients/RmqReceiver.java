package com.lmx.chapter9rabbitmqhello.amqclients;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lmx
 * @date 2020-06-06 19:31
 */
@Component
@RabbitListener(queues = "hello")
public class RmqReceiver {

    @RabbitHandler
    public void proce(String receiveContent) {
        System.out.println("RmqReceiver收到消息->" + receiveContent);
    }

}

