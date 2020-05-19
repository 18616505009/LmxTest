package com.lmx.chapter5helloservice.controller;

import com.lmx.chapter5helloservice.dto.User;
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
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    int serverPort;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello() throws InterruptedException {
        int sleepTime = new Random().nextInt(2000);
        logger.debug("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);
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
    private String hello1(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    private User hello2(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }

    @RequestMapping(value = "hello3", method = RequestMethod.POST)
    private String hello3(@RequestBody User user) {
        return "Hello " + user.getName() + "," + user.getAge();
    }

}
