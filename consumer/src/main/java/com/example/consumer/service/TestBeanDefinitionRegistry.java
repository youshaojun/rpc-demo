package com.example.consumer.service;

import com.example.rpc.registry.mapper.TestMapper01;
import com.example.rpc.registry.mapper.TestMapper02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 测试
 *
 * @author yousj
 * @date 2021/8/7
 */
@Component
public class TestBeanDefinitionRegistry {


    //================================ 测试注入redisTemplate ======================

    @Resource
    private RedisTemplate redisTemplate0;
    @Resource
    private RedisTemplate redisTemplate1;
    @Resource
    private RedisTemplate redisTemplate2;

    @PostConstruct
    public void testRedisTemplate(){
        redisTemplate0.opsForValue().set("test0","test0");
        redisTemplate1.opsForValue().set("test1","test1");
        redisTemplate2.opsForValue().set("test2","test2");
    }



    //================================ 测试注入mapper ======================


    @Autowired
    TestMapper01 testMapper01;
    @Autowired
    TestMapper02 testMapper02;

    @PostConstruct
    public void testMapper() {
        testMapper01.test01("1111");
        testMapper02.test02("2222");
        testMapper02.test03("3333");
    }

}
