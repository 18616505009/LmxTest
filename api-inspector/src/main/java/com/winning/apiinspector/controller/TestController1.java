package com.winning.apiinspector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020-06-15 09:29
 */
@RequestMapping("/test1")
@RestController
public class TestController1 {

    int count = 0;

    @GetMapping("/api")
    public String apiWithNewName() {
        count++;
        return "启动后调用次数->" + count + "次";
    }

}
