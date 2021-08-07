package com.example.rpc.registry.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author yousj
 * @date 2021/8/7
 */
public class MyRedisTemplateFactoryBean implements FactoryBean {

    private RedisStandaloneConfiguration standaloneConfiguration;

    public MyRedisTemplateFactoryBean(RedisStandaloneConfiguration redisStandaloneConfiguration) {
        this.standaloneConfiguration = redisStandaloneConfiguration;
    }

    @Override
    public Object getObject() {
        RedisTemplate redisTemplate = new StringRedisTemplate();
        RedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(this.standaloneConfiguration);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return RedisTemplate.class;
    }
}