package com.lmx.chapter9rabbitmqhello.config;

import com.lmx.chapter9rabbitmqhello.amqclients.RmqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lmx
 * @date 2020-06-06 20:12
 */
@Slf4j
@Component
public class AutoLaunch implements ApplicationRunner {

    static int delay = 5;
    static int period = 5;

    @Autowired
    RmqSender rmqSender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("5 seconds before sending");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                rmqSender.send("lmx->" + new Date());
            }
        }, delay * 1000, period * 1000);

    }

}
