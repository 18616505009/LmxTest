package com.lmx.consumer.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lmx
 * @date 2020-05-13 16:09
 * 该接口是客户端,通过hystrixCommand命令调用lmx-service-provider中的接口
 */
@RestController
@RequestMapping("/consumer")
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getUserInfo/{id}")
    private String getUserInfo(@PathVariable("id") int id) {
        UserCommand userCommand = new UserCommand(null, restTemplate, id);
        userCommand.run();
    }

}
