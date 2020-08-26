package com.lmx.springsecuritytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020/8/25 2:54 下午
 */
@RequestMapping("/bedroom")
@RestController
public class BedroomController {

    @GetMapping("/bed")
    public String bed() {
        return "lay on bed";
    }

    @GetMapping("/chair")
    public String chair() {
        return "sit on chair";
    }

}
