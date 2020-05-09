package com.lmx.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author lmx
 * @date 2020-04-26 14:54
 */
@Service
public class ConsumeService {

    @Autowired
    RestTemplate restTemplate;

    //    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER, fallbackMethod = "sendRequestToServerFallback")
//    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY, fallbackMethod = "sendRequestToServerFallback")
    @HystrixCommand(ignoreExceptions = {IOException.class, NullPointerException.class})   //使用ignoreExceptions-忽略指定异常类型
    public String sendRequestToServer() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://LMX-SERVICE-PROVIDER/service/provideService", String.class);
        String result = entity.getBody();
        return result;
    }

    public String sendRequestToServerFallback() {
        return "向服务器发送请求异常";
    }

}
