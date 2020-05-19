package com.lmx.chapter5helloservice.dto;

import lombok.Data;

/**
 * @author lmx
 * @date 2020-05-18 22:00
 * 第六章用的返回数据,一般这种类可以写在一个新的feignClient的模块中。
 * 然后服务提供方（本章第五章）和服务调用方（第六章）都引入feign模块
 * 这样的优势在于-调用方（第六章）、服务提供方（本章）使用同一个数据对象（User）
 * <p>
 * 本次没有单独做feign模块,所以User要在本章和第六章中各写一遍，维护时还不好保证两个模块中数据对象User的一致
 * 除非第六章引入第五章,资源开销太大
 */
@Data
public class User {
    String name;
    int age;
}
