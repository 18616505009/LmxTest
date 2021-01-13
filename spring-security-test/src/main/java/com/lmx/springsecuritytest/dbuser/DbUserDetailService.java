package com.lmx.springsecuritytest.dbuser;

import com.lmx.springsecuritytest.dbuser.dao.UserCrudDao;
import com.lmx.springsecuritytest.dbuser.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author lmx
 * @date 2020/9/16 3:32 下午
 * 配置基于数据库的用户详情服务
 */
@Component
public class DbUserDetailService implements UserDetailsService {

    @Autowired
    UserCrudDao userCrudDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userCrudDao.findByUsername(username);
        if (userEntity == null)
            return null;

        String authorityStr = userEntity.getAuthority();
        String[] authorityArr = authorityStr.split(",");

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (String authority : authorityArr) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + authority));
        }

        UserDetails userDetails = new User(username, userEntity.getPassword(), authorityList);
        return userDetails;
    }
}
