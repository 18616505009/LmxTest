package com.lmx.springsecuritytest.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lmx
 * @date 2020/8/26 11:21 上午
 */
@Slf4j
public class WinPasswordEncoder implements PasswordEncoder {


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //加密密码
    @Override
    public String encode(CharSequence rawPassword) {
        //不加密
        /*log.info("自定义加密器WinPasswordEncoder,实际不会加密,将返回明文密码");
        return rawPassword.toString();*/

        //定义使用的加密方式-用户登录输入的密码会通过该方式被加密
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    //校验用户输入密码和正确的加密密码是否匹配
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //不加密
        /*log.info("encodedPassword->" + encodedPassword + ",rawPassword->" + rawPassword.toString());
        return rawPassword.toString().equals(encodedPassword);*/

        //使用一种加密
        String encodeIn = bCryptPasswordEncoder.encode(rawPassword);
        log.info("输入密码加密后->" + encodeIn + "\n正确加密密码-->" + encodedPassword);
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);

    }
}
