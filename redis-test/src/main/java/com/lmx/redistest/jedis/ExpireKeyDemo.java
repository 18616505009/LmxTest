package com.lmx.redistest.jedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author lmx
 * @date 2020/12/23 3:39 下午
 * 设置超时、更新有效时间、超时回调
 */
@Service
public class ExpireKeyDemo {

    @Value("${spring.redis.host}")
    String redisHost;

    public void run() {

        Jedis jedis = new Jedis(redisHost);

        System.out.println("仅为key设置过期时间->key lmx在30sec后过期");
        jedis.expire("lmx", 30);

        jedis.setex("lmx", 30, "limengxiao");


    }

}
