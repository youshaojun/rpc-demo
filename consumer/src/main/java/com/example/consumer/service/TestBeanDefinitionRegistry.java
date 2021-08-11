package com.example.consumer.service;

import com.example.rpc.registry.mapper.TestMapper01;
import com.example.rpc.registry.mapper.TestMapper02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    /**
     * 自动注入redisTemplate, @Autowired默认按类型注入, 类型有多个或未找到按名称注入
     */
    @Autowired
    private StringRedisTemplate strRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * FactoryBean方式注入redisTemplate, @Resource默认按名称注入, 名称未找到按类型注入
     * 但是RedisTemplate类型默认有多个<RedisTemplate, StringRedisTemplate> 会报错
     * 需要指定@Primary
     * 当前指定RedisTemplate为主, 即按类型注入默认使用redisTemplate
     * 注释 multi-redis 配置, 则会使用默认redisTemplate注入
     */
    @Resource
    private RedisTemplate redisTemplate0;
    @Resource
    private RedisTemplate redisTemplate1;
    @Resource
    private RedisTemplate redisTemplate2;


    @PostConstruct
    public void testRedisTemplate() {

        strRedisTemplate.opsForValue().set("stringRedisTemplate", "stringRedisTemplate");
        redisTemplate.opsForValue().set("redisTemplate", "redisTemplate");

        redisTemplate0.opsForValue().set("test0", "test0");
        redisTemplate1.opsForValue().set("test1", "test1");
        redisTemplate2.opsForValue().set("test2", "test2");

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
