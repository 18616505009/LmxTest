package com.lmx.consumer.test;

import com.lmx.dto.UserDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author lmx
 * @date 2020-05-06 20:11
 */
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultUser")
    public UserDto getUserById(long id) {
        return restTemplate.getForObject("http://lmx-service-provider/users/{1}", UserDto.class, id);
    }

    public UserDto defaultUser() {
        return new UserDto();
    }

}
