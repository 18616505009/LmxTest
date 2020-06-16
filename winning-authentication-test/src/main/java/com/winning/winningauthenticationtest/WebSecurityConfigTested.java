package com.winning.winningauthenticationtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

/**
 * @author lmx
 * @date 2020-06-12 09:51
 * 实现自定义加密认证
 * 部分接口需要认证用户身份后访问
 * 提供默认登录页面给用户登录，用户登录完成后，可以访问接口
 * 默认页面发送的登录请求不知道发哪去了，不知道已认证的用户信息保存在哪里
 * 但是用户名、密码是通过数据库配置的
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigTested extends WebSecurityConfigurerAdapter {

    @Autowired
    PlainTextEncoder plainTextEncoder;

    @Autowired
    DataSource dataSource;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存的认证
        /*auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");*/
        auth.jdbcAuthentication().dataSource(dataSource)
                //自定义用户信息查询语句，适用于自己的表结构，
                .usersByUsernameQuery("select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username,'ROLE_USER' from users where username=?")
                .passwordEncoder(plainTextEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.authorizeRequests()
//                .anyRequest().permitAll();
        //sbInAction-配置前者覆盖后者，即前者优先
        http.authorizeRequests()
                //指定该接口需要认证后才能访问
                .antMatchers("/query/users").authenticated()
                .anyRequest().permitAll()
                .and().formLogin();  //给一个默认登录页面,可以替换的
    }
}
