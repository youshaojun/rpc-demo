package com.example.rpc.annotation;

import com.example.rpc.registry.MyBeanDefinitionRegistryRedis;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yousj
 * @since 2021/8/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(MyBeanDefinitionRegistryRedis.class)
public @interface EnableMultiRedisTemplate {
}
