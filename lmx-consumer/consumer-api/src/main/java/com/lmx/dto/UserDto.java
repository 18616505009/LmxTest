package com.lmx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lmx
 * @date 2020-05-06 20:20
 */
@ApiModel
@Data
public class UserDto {

    @ApiModelProperty("id")
    long id;
    @ApiModelProperty("用户名")
    String name;

}
