package com.lmx.chapter5helloservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
