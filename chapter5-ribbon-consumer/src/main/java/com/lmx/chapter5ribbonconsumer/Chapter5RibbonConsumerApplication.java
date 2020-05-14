package com.lmx.chapter5ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
public class Chapter5RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter5RibbonConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate resttemplate() {
        return new RestTemplate();
    }

}
