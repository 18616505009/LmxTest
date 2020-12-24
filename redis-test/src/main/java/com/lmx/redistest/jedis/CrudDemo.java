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

        /***key过期测试***/
        System.out.println("设置key lmx，10sec后过期");
        jedisService.set("lmx", "limengxiao");
        jedisService.setExipre("lmx", 10);

        /***map测试***/
        System.out.println("存储map testMap");
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("testDate", "20201223");
        testMap.put("testProjectName", "LmxTest");
        testMap.put("testModuleName", "redis-test");
        jedisService.hmset("testMap", testMap);

        List<String> testMapValues = jedisService.hmget("testMap", new String[]{"testDate", "testProjectName"});
        System.out.println("map查询结果-->" + testMapValues.toString());

        System.out.println("testMap内元素数量->" + jedisService.hlen("testMap"));
        System.out.println("testMap内的健->" + jedisService.hkey("testMap").toString());

        /***list测试***/
        System.out.println("list测试,先尝试删除testList避免有残留数据");
        jedisService.del("testList");

        System.out.println("在testList尾部写入两次，分别为A,B,C 和 D");
        jedisService.rpush("testList", new String[]{"A", "B", "C"});
        jedisService.rpush("testList", "D");

        System.out.println("在testList头部写入两次，分别为4 和 1,2,3");
        jedisService.lpush("testList", "4");
        jedisService.lpush("testList", new String[]{"1", "2", "3"});

        System.out.println("提取testList第4个元素-->"
                + jedisService.lindex("testList", 3));
        System.out.println("提取testList的第3-5位->"
                + jedisService.lrange("testList", 2, 4));
        System.out.println("提取testList的全部内容->"
                + jedisService.lrange("testList", 0, -1));

    }

}
