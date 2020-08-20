package com.lmx.jwt.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lmx
 * @date 2020/8/20 9:37 上午
 * jwt自有键
 * iss: jwt签发者
 * sub: 面向的用户(jwt所面向的用户)
 * aud: 接收jwt的一方
 * exp: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/getToken")
    public String getToken(@RequestBody JSONObject loginObj, HttpServletRequest request, HttpServletResponse response) {
        //选择token加密算法
        Algorithm algorithm = Algorithm.HMAC256("secret");

        String username = loginObj.getString("username");
        String password = loginObj.getString("password");
        //暂定的用户名密码
        if (!("lmx".equals(username) && "abcd".equals(password)))
            return "login failed";

        JWTCreator.Builder jwtBuilder = JWT.create();
        //jwt几类自定义的特殊键
        //1.自有键-过期时间
        Calendar calendar = Calendar.getInstance();
        jwtBuilder.withExpiresAt(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        jwtBuilder.withExpiresAt(new Date(2020, 8, 21));
        //2.自有键-颁发时间
        jwtBuilder.withIssuedAt(new Date());
        //3.自有键-标题
        jwtBuilder.withSubject("sqlApi");
        //自定义键-用户信息
        jwtBuilder.withClaim("userId", "619627239");

        //根据算法加密以上payload内容(header部分依赖会自动填入jwt、type),获得token
        String token = jwtBuilder.sign(algorithm);

        //1.返回内容设置一个session，包含token；实际返回只有一个jsessionId,session内容在服务端内存中
        //request.getSession().setAttribute("jwt-token", token);

        //本次要求token放到header
        response.setHeader("win-token", token);

        return "success,token added in header!";
    }

    @PostMapping("/verifyToken")
    public String verifyToken(@RequestBody String username, HttpServletRequest request, HttpServletResponse response) {
        //提取header中的token
        //1.服务器中缓存session的方式
        //String token = (String) request.getSession().getAttribute("jwt-token");
        //本次要求token从header里拿
        String token = request.getHeader("win-token");
        log.info("获取请求附带的token->\n" + token);

        //验证token-设置jwt算法-加密&认证必须统一
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build(); //Reusable verifier instance
        try {
            if (token == null)
                throw new IllegalArgumentException("token不存在");
            DecodedJWT jwt = verifier.verify(token);
            log.info("token 匹配");
            return "sig match";
        } catch (IllegalArgumentException e) {
            //Invalid token
            log.error("token匹配度检测异常,错误token->" + e.toString());
            response.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
            return "wrong sig";
        }
    }

}
