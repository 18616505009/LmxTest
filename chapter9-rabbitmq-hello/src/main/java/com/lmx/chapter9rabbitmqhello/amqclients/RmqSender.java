package com.lmx.chapter9rabbitmqhello.amqclients;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lmx
 * @date 2020-06-06 19:22
 * rabb
 */
@Component
public class RmqSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(String content) {
        amqpTemplate.convertAndSend("hello",content);
    }

}
