package com.lmx.consumer.test;

import com.lmx.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author lmx
 * @date 2020-05-06 20:11
 */
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserDto getUserById(long id) {
        return restTemplate.getForObject("",UserDto.class);
    }

}
