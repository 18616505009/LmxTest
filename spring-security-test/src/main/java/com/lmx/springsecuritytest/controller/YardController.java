package com.lmx.springsecuritytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020/8/25 5:00 下午
 */
@RequestMapping("/yard")
@RestController
public class YardController {

    @GetMapping("/cage")
    public String cage() {
        return "walk around cage";
    }

}
