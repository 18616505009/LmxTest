# spring-security接口访问基础测试
## 1.两个用户-lmx(人),ldd(狗)
## 2.两个角色-bedroomRole,yardRole
## 3.三个接口
- /home(家庭)-拥有yardRole或bedroomRole的用户都可以访问
- /yard(庭院)-拥有yardRole角色的用户可以访问
- /bedroom(卧室)-拥有bedroomRole的用户可以访问
## 4.用户与角色的关系
- 一对一:    auth.inMemoryAuthentication().passwordEncoder(new WinPasswordEncoder())
                        .withUser("ldd").password("ldd").roles("yardRole");
- 一对多:    auth.inMemoryAuthentication().passwordEncoder(new WinPasswordEncoder())
                        .withUser("ldd").password("ldd").roles("yardRole");
## 5.接口与角色的关系
- 一对一:    http.authorizeRequests().antMatchers("/bedroom/**").hasRole("bedroomRole")
- 一对多:    http.authorizeRequests().antMatchers("/home").hasAnyRole("bedroomRole", "yardRole")