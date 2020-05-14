package com.lmx.testserviceprovier.controller;

import com.lmx.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmx
 * @date 2020-05-06 20:14
 * 对应《微服务实战》第五章hystrix的User例子，作为-服务提供者
 */
@Api(description = "用户信息接口")
@RequestMapping("/users")
@RestController
public class UserController {

    @ApiOperation("根据id查询用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto getUserById(@PathVariable("id") long id) {
        UserDto dto = new UserDto();
        switch ((int) id) {
            case 1:
                dto.setId(1);
                dto.setName("lmx1");
                break;
            case 5:
                dto.setId(5);
                dto.setName("lmx5");
                break;
        }
        return dto;
    }

}
