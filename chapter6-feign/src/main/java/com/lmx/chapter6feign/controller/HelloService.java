package com.lmx.chapter6feign.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lmx
 * @date 2020-05-14 15:54
 * 2.定义HelloService接口,通过@FeignClient注解指定服务名来绑定服务
 * 服务名不区分大小写,对应服务必须启动注册到eureka后才能通过feign调用
 */
@FeignClient("chapter5-hello-service")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

}
