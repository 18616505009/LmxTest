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
import java.util.Set;

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

    /***hashmap操作***/

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

    /**
     * 查看指定名称的hashmap内字段数量
     *
     * @param mapName
     */
    public long hlen(String mapName) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hlen(mapName);
        }
    }

    /**
     * 返回指定名称的hashmap内的所有key
     *
     * @param mapName
     * @return
     */
    public Set<String> hkey(String mapName) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hkeys(mapName);
        }
    }

    /***redis列表操作***/

    /**
     * 在list头部增加一条数据
     *
     * @param listName
     * @param value
     */
    public void lpush(String listName, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.lpush("testList", value);
        }
    }

    /**
     * 在list头部增加一组数据
     * 注意:lpush会依次在头部写入数据,因此list写入后与一般理解不太相同:
     * eg lpush testList 1,2,3
     * 实测testList左侧将会是 3,2,1
     *
     * @param listName
     * @param values
     */
    public void lpush(String listName, String[] values) {
        try (Jedis jedis = pool.getResource()) {
            jedis.lpush(listName, values);
        }
    }

    /**
     * 在list尾部增加一条数据
     *
     * @param listName
     * @param value
     */
    public void rpush(String listName, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.rpush(listName, value);
        }
    }

    /**
     * 在list尾部增加一串数据
     *
     * @param listName
     * @param values
     */
    public void rpush(String listName, String[] values) {
        try (Jedis jedis = pool.getResource()) {
            jedis.rpush(listName, values);
        }
    }

    /**
     * 获取list长度
     *
     * @param listName
     * @return
     */
    public long llen(String listName) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.llen(listName);
        }
    }

    /**
     * 指定下标提取list内的元素
     *
     * @param listName
     * @param index    redis的list下标支持负值，表示从后往前找,-1为最后一位
     * @return
     */
    public String lindex(String listName, long index) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lindex(listName, index);
        }
    }

    /**
     * 根据起始index、结束index提取list的子list
     * 可以通过redis命令: lrange ${listname} 0 -1 提取到全部list
     *
     * @param listName
     * @param start
     * @param stop
     * @return
     */
    public List<String> lrange(String listName, long start, long stop) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lrange(listName, start, stop);
        }
    }


}
