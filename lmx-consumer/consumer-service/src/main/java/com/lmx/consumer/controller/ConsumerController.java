package com.lmx.consumer.controller;

import com.lmx.consumer.service.ConsumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("消费者接口")
@RestController
public class ConsumerController {

    @Autowired
    ConsumeService consumeService;

    @ApiOperation("作为消费者,调用lmx-service-provider模块的接口")
    @GetMapping("/consume")
    private String consume() {
        return consumeService.sendRequestToServer();
    }

}
