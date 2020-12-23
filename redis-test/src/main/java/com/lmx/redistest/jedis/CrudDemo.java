package com.lmx.redistest.jedis;

import com.lmx.redistest.client.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author lmx
 * @date 2020/12/23 3:26 下午
 */
@Service(value = "jedisCrudDemo")
public class CrudDemo {

    @Autowired
    JedisService jedisService;

    public void run() {

        System.out.println("set lmx limengxiao");
        jedisService.set("lmx", "limengxiao");
        System.out.println("get key lmx->" + jedisService.get("lmx"));

        System.out.println("set(update) lmx lmxlmx");
        jedisService.set("lmx", "lmxlmx");
        System.out.println("get key lmx->" + jedisService.get("lmx"));

        System.out.println("delete key lmx");
        jedisService.del("lmx");
        System.out.println("get key lmx->" + jedisService.get("lmx"));

        System.out.println("设置key lmx，10sec后过期");
        jedisService.set("lmx", "limengxiao");
        jedisService.setExipre("lmx", 10);

        System.out.println("存储map testMap");
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("testDate", "20201223");
        testMap.put("testProjectName", "LmxTest");
        testMap.put("testModuleName", "redis-test");
        jedisService.hmset("testMap", testMap);

        List<String> testMapValues = jedisService.hmget("testMap", new String[]{"testDate", "testProjectName"});
        System.out.println("map查询结果-->" + testMapValues.toString());

    }

}
