package com.lmx.chapter6feign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020-05-16 12:43
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @HystrixCommand(fallbackMethod = "checkServerStatus")
    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return helloService.hello();
    }

    private String checkServerStatus(Throwable throwable) {
        String errorMsg = "chapter6的/feign-consumer接口进入异常降级fallback,请检查服务提供者chapter5是否打开,异常内容->" + throwable.getMessage();
        System.out.println(errorMsg);
        return errorMsg;
    }

}
