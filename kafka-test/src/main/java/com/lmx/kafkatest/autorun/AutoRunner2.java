package com.lmx.kafkatest.autorun;

import com.lmx.kafkatest.producer.LmxKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lmx
 * @date 2020/8/27 4:32 下午
 */
@Slf4j
@Order(value = 2)
@Component
public class AutoRunner2 implements ApplicationRunner {
    @Autowired
    LmxKafkaProducer producer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("5秒后生产消息");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("开始生产消息");
                System.out.println("开始生产消息");
                producer.produceMessage();
            }
        }, 5 * 1000,5*1000);
    }
}
