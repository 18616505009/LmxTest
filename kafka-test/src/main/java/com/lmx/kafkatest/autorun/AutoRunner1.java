package com.lmx.kafkatest.autorun;

import com.lmx.kafkatest.consumer.LmxKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lmx
 * @date 2020/8/27 3:15 下午
 */
@Slf4j
@Component
@Order(value = 1)
public class AutoRunner1 implements ApplicationRunner {

    @Autowired
    LmxKafkaConsumer consumer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始消费服务");
        new Thread(() -> {
            consumer.consumeMessage();
        }).start();
    }
}
