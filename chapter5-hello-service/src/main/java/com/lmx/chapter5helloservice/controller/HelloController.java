package com.lmx.chapter5helloservice.controller;

import com.lmx.chapter6api.api.HelloFeignClient;
import com.lmx.chapter6api.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author lmx
 * @date 2020-05-11 13:58
 */
@Slf4j
@RestController
public class HelloController implements HelloFeignClient {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    int serverPort;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        int sleepTime = new Random().nextInt(2000);
        logger.debug("sleepTime:" + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String respStr = "/hello,port->" + serverPort + ",sleepTime:" + sleepTime;
        logger.info(respStr);
        return respStr;
    }

    /**
     * 对第六章提供服务
     *
     * @return
     */
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello2(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }

    @RequestMapping(value = "hello3", method = RequestMethod.POST)
    public String hello3(@RequestBody User user) {
        return "Hello " + user.getName() + "," + user.getAge();
    }

}
