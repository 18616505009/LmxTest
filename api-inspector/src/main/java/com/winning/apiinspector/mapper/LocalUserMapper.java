package com.winning.apiinspector.mapper;

import com.winning.apiinspector.mapper.po.LocalUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lmx
 * @date 2020-06-15 10:47
 */
public interface LocalUserMapper extends CrudRepository<LocalUser,Integer> {

    LocalUser findByUsername(String username);

}
