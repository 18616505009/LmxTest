package com.lmx.redistest;

import com.lmx.redistest.jedis.CrudDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class RedisTestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisTestApplication.class, args);
    }

    @Autowired
    CrudDemo crudDemo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                crudDemo.run(); //delay执行:让JedisServie先完成JedisPool的初始化
            }
        }, 3 * 1000);
    }
}
