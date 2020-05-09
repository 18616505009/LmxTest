package com.lmx.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 可以使用@SpringCloudApplication实现断路器
 * 其功能也包含了@EnableCircuitBreaker、@EnableEurekaClient、@SpringBootApplication
 */
//@EnableCircuitBreaker
//@EnableEurekaClient
//@SpringBootApplication
@EnableSwagger2
@SpringCloudApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
