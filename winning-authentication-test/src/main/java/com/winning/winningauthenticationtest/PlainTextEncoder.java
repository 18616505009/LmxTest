package com.winning.winningauthenticationtest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lmx
 * @date 2020-06-13 10:54
 * 明文的加密器
 */
@Component
public class PlainTextEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        String plainPassword = rawPassword.toString();
        return plainPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String inputPassword = rawPassword.toString();
        return inputPassword.equals(encodedPassword);
    }
}
