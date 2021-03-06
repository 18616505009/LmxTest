package com.lmx.chapter6api.api;

import com.lmx.chapter6api.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmx
 * @date 2020-05-23 20:52
 * 2.定义HelloService接口,通过@FeignClient注解指定服务名来绑定服务
 * 服务名不区分大小写,对应服务必须启动注册到eureka后才能通过feign调用
 */
@FeignClient("chapter5-hello-service")
public interface HelloFeignClient {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello1(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    User hello2(@RequestHeader("name") String name, @RequestHeader("age") int age);

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    String hello3(@RequestBody User user);


}
