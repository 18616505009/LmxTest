package com.lmx.springsecuritytest.config;

import com.lmx.springsecuritytest.util.WinPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author lmx
 * @date 2020/8/25 10:39 上午
 */

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        return manager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new WinPasswordEncoder())
                .withUser("lmx").password("lmx").roles("bedroomRole","yardRole");
        auth.inMemoryAuthentication().passwordEncoder(new WinPasswordEncoder())
                .withUser("ldd").password("ldd").roles("yardRole");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/bedroom/**").hasRole("bedroomRole") //访问 /user这个接口，需要有USER角色
                .antMatchers("/yard/**").hasRole("yardRole")
                .anyRequest().authenticated() //剩余的其他接口，登录之后就能访问
                .and()
                .formLogin().defaultSuccessUrl("/hello");

        /*http.authorizeRequests()
                .antMatchers("/login","/logout").permitAll()
                .antMatchers("/yard/**").hasRole("LDD")
                .antMatchers("/bedroom/**", "/yard/**").hasRole("LMX") //访问 /user这个接口，需要有USER角色
                .anyRequest().authenticated() //剩余的其他接口，登录之后就能访问
                .and()
                .logout();*/

    }
}
