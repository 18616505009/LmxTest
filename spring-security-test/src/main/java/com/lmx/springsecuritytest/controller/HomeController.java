package com.lmx.springsecuritytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020/9/16 4:40 下午
 */
@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "welcome home";
    }

}
