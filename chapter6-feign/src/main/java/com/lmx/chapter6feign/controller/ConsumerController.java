package com.lmx.chapter6feign.controller;

import com.lmx.chapter6feign.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/feign-consumer1", method = RequestMethod.GET)
    String feignConsumer1(@RequestParam(value = "name") String name) {
        return helloService.hello1(name);
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    String feignConsumer2(@RequestHeader(value = "name") String name, @RequestHeader(value = "age") int age) {
        User user = helloService.hello2(name, age);
        return "feign-consumer2-data-->" + user.getName() + "," + user.getAge();
    }

    @RequestMapping(value = "/feign-consumer3", method = RequestMethod.POST)
    String feignConsumer3(@RequestBody User user) {
        return helloService.hello3(user);
    }

}
