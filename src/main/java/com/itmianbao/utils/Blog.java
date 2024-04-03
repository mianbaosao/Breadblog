package com.itmianbao.utils;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class Blog {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void name() {
        // 存入键值对
        redisTemplate.opsForValue().set("testKey", "hello");
        // 获取键值对
        Object retrievedValue = redisTemplate.opsForValue().get("testKey");
        System.out.println(retrievedValue);
    }


}



