package com.winning.apiinspector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020-06-14 18:11
 * 测试接口,临时方法计次无法持久化
 */
@RestController
@RequestMapping(value="/test")
@Slf4j
public class TestController {

    int count=0;

    @GetMapping(value = "/api")
    private String api(){
        count++;
        return "启动后调用次数->"+count+"次";
    }

}
