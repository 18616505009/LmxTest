package com.lmx.consumer.controller;

import com.lmx.consumer.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    ConsumeService consumeService;

    @GetMapping("/consume")
    private String consume() {
        return consumeService.sendRequestToServer();
    }

}
