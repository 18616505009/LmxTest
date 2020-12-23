package com.lmx.redistest.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;

/**
 * @author lmx
 * @date 2020/12/23 4:02 下午
 * 配置jedis连接对象
 */
@Slf4j
@Service
public class JedisService implements ApplicationRunner {

    @Value("${spring.redis.host}")
    String redisHost;

    static JedisPool pool;

    /**
     * 程序启动后立即初始化redis连接池
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (pool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(8);
            jedisPoolConfig.setMaxIdle(8);
            pool = new JedisPool(jedisPoolConfig, redisHost);
        }
        log.info("jedispool初始化完成..");
    }

    public void set(String key, String value) {
        try (Jedis jedis = pool.getResource()) {    //try resource关闭jedis时,jedis会返回到JedisPool
            jedis.set(key, value);
        }
    }

    public String get(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    public void setExipre(String key, int seconds) {
        try (Jedis jedis = pool.getResource()) {
            jedis.expire(key, seconds);
        }
    }

    public void del(String key) {
        try (Jedis jedis = pool.getResource()) {
            jedis.del(key);
        }
    }

    public void hmset(String key, HashMap<String, String> map) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hmset(key, map);
        }
    }

    /**
     * 根据map名称，查出一串key对应的一串value
     *
     * @param mapName
     * @param keys
     * @return
     */
    public List<String> hmget(String mapName, String[] keys) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hmget(mapName, keys);
        }
    }

}
