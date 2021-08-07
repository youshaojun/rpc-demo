package com.example.rpc.registry;

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

    @Resource(name = "redisTemplate-0")
    private RedisTemplate redisTemplate0;
    @Resource(name = "redisTemplate-1")
    private RedisTemplate redisTemplate1;
    @Resource(name = "redisTemplate-2")
    private RedisTemplate redisTemplate2;
    @Resource(name = "redisTemplate-3")
    private RedisTemplate redisTemplate3;
    @Resource(name = "redisTemplate-4")
    private RedisTemplate redisTemplate4;
    @Resource(name = "redisTemplate-5")
    private RedisTemplate redisTemplate5;
    @Resource(name = "redisTemplate-6")
    private RedisTemplate redisTemplate6;
    @Resource(name = "redisTemplate-7")
    private RedisTemplate redisTemplate7;
    @Resource(name = "redisTemplate-8")
    private RedisTemplate redisTemplate8;
    @Resource(name = "redisTemplate-9")
    private RedisTemplate redisTemplate9;
    @Resource(name = "redisTemplate-10")
    private RedisTemplate redisTemplate10;
    @Resource(name = "redisTemplate-11")
    private RedisTemplate redisTemplate11;
    @Resource(name = "redisTemplate-12")
    private RedisTemplate redisTemplate12;
    @Resource(name = "redisTemplate-13")
    private RedisTemplate redisTemplate13;
    @Resource(name = "redisTemplate-14")
    private RedisTemplate redisTemplate14;
    @Resource(name = "redisTemplate-15")
    private RedisTemplate redisTemplate15;

    @PostConstruct
    public void testRedisTemplate(){
        redisTemplate0.opsForValue().set("test0","test0");
        redisTemplate1.opsForValue().set("test1","test1");
        redisTemplate2.opsForValue().set("test2","test2");
        redisTemplate3.opsForValue().set("test3","test3");
        redisTemplate4.opsForValue().set("test4","test4");
        redisTemplate5.opsForValue().set("test5","test5");
        redisTemplate6.opsForValue().set("test6","test6");
        redisTemplate7.opsForValue().set("test7","test7");
        redisTemplate8.opsForValue().set("test8","test8");
        redisTemplate9.opsForValue().set("test9","test9");
        redisTemplate10.opsForValue().set("test10","test10");
        redisTemplate11.opsForValue().set("test11","test11");
        redisTemplate12.opsForValue().set("test12","test12");
        redisTemplate13.opsForValue().set("test13","test13");
        redisTemplate14.opsForValue().set("test14","test14");
        redisTemplate15.opsForValue().set("test15","test15");
    }



    //================================ 测试注入mapper ======================

    interface BaseMapper {
    }

    interface TestMapper01 extends BaseMapper {
        Object test01(String content);
    }

    interface TestMapper02 extends BaseMapper {
        Object test02(String content);

        Object test03(String content);
    }


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
