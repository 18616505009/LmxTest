package com.lmx.springsecuritytest.dbuser.dao;

import com.lmx.springsecuritytest.dbuser.data.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lmx
 * @date 2020/9/16 3:40 下午
 */
@Repository
public interface UserCrudDao extends CrudRepository<UserEntity, Integer> {

    public UserEntity findByUsername(String username);

}
