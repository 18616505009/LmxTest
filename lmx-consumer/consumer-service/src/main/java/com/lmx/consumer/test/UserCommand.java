package com.lmx.consumer.test;

import com.lmx.dto.UserDto;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author lmx
 * @date 2020-05-05 17:35
 */
public class UserCommand extends HystrixCommand<UserDto> {

    RestTemplate restTemplate;
    long id;

    protected UserCommand(Setter setter, RestTemplate restTemplate, long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected UserDto run() throws Exception {
        return restTemplate.getForObject("testUrl", UserDto.class, id);
    }
}
