package com.lmx.chapter6feign;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//1.使用@EnableFeignClients注解开启SpringCloudFeign的支持功能
@EnableFeignClients(basePackages = "com.lmx")
@ComponentScan
@SpringCloudApplication
public class Chapter6FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter6FeignApplication.class, args);
    }

}
