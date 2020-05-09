package com.lmx.testserviceprovier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/service")
public class ServiceController {

    Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Value("${server.port}")
    int servicePort;

    @RequestMapping(value = "/provideService", method = RequestMethod.GET)
    private String provideService() {

        try {
            //模拟随机延迟,最长3秒,超过两秒后未返回,就会触发hystrix的断路
            int delay = new Random().nextInt(3000);
            System.out.println("随机延时->" + delay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "service provided,server port->" + servicePort;
    }

}
