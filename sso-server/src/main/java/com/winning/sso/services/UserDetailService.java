package com.winning.sso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lmx
 * @date 2020/8/13 9:31 上午
 * 这里创建了一个用户名为codesheep，密码 123456的模拟用户，
 * 并且赋予了 普通权限（ROLE_NORMAL）和 中等权限（ROLE_MEDIUM）
 */
@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"codesheep".equals(username))
            throw new UsernameNotFoundException("用户" + username + "不存在");

        return new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_NORMAL,ROLE_MEDIUM"));
    }
}
