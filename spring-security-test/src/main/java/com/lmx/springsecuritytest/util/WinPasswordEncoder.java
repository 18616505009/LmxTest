package com.lmx.springsecuritytest.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lmx
 * @date 2020/8/26 11:21 上午
 */
public class WinPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
