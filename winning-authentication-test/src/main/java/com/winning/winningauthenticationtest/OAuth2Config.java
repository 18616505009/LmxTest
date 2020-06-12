package com.winning.winningauthenticationtest;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author lmx
 * @date 2020-06-12 09:50
 */
@Slf4j
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {

        HashMap<String, String> map = new HashMap<>();
        map.put("username", "username");
        map.put("password", "123456");
        map.put("grant_type", "password");

        clientDetailsServiceConfigurer.inMemory()
                .withClient("clientId")
                .secret("{noop}clientSecret")
                .scopes("service")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient")
//                .additionalInformation(map)
                .accessTokenValiditySeconds(3600);

        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(getDataSource());
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
//        jdbcClientDetailsService.loadClientByClientId("clientId");
//        jdbcClientDetailsService.updateClientSecret("clientId","{noop}clientSecret");
        clientDetailsServiceConfigurer.withClientDetails(jdbcClientDetailsService);
//        jdbcClientDetailsService.setPasswordEncoder(new BCryptPasswordEncoder());

        /*ClientDetailsServiceBuilder clientDetailsServiceBuilder=new ClientDetailsServiceBuilder();
        clientDetailsServiceBuilder.withClient("clientId");
        clientDetailsServiceConfigurer.init(clientDetailsServiceBuilder);*/


        // 定义哪些客户端可以注册到了服务
        /*clientDetailsServiceConfigurer.inMemory()
                .withClient("clientId")
                .secret("{noop}clientSecret")
                // 支持的授权模式 密码模式和客户端凭证
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                // 定义访问作用域，也就是当用户使用某一个scope授权之后，可以根据不同的scope封装不同的user信息，比如webclient会封装角色，mobileclient封装角色和资源api，由开发人员定义即可
                .scopes("webclient", "mobileclient");*/
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 使用默认的验证管理器和用户信息服务
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    //密码加密器
    PasswordEncoder passwordEncoder = new PasswordEncoder() {
        //加密密码
        @Override
        public String encode(CharSequence rawPassword) {
            String encodedPwd = rawPassword.toString();
            log.info("不加密，密码->" + encodedPwd);
            return encodedPwd;
        }

        //比对已加密密码
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            String encodedPwd = rawPassword.toString();
            return encodedPassword.equals(encodedPwd);
        }
    };

    //数据源dataSource
    DataSource getDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/winning?");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("Lmx666");
        mysqlDataSource.setDatabaseName("winning");

        return mysqlDataSource;
    }
}
