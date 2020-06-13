package com.winning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmx
 * @date 2020-06-12 09:06
 */
@SpringBootApplication
@RestController
//@EnableAuthorizationServer //is used to configure the OAuth 2.0 Authorization Server mechanism
//@EnableResourceServer
@EnableWebSecurity
@Slf4j
public class WinningAuthenticationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinningAuthenticationTestApplication.class, args);
    }

    @RequestMapping(value = {"/user"}, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getOAuth2Request().getAuthorities()));
        return userInfo;
    }

}
