package com.example.rpc.registry;


import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 利用FactoryBean接口实现动态注册BeanDefinition
 * 注入自定义redisTemplate
 *
 * <p>
 * Created by yousj on 2021/8/6 16:16
 * @author yousj
 */
@Component
public class MyBeanDefinitionRegistryRedis implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        for (int i = 0; i <= 15; i++) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RedisTemplate.class);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            String beanName = "redisTemplate-" + i;
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanName);
            definition.setBeanClass(MyRedisTemplateFactoryBean.class);
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(beanName, definition);
        }
    }

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setResourceLoader(@NotNull ResourceLoader resourceLoader) {

    }


    class MyRedisTemplateFactoryBean implements FactoryBean {

        private String beanName;

        public MyRedisTemplateFactoryBean(String beanName) {
            this.beanName = beanName;
        }

        @Override
        public Object getObject() {
            RedisTemplate redisTemplate = new StringRedisTemplate();
            RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
            standaloneConfiguration.setPassword("123456");
            standaloneConfiguration.setDatabase(Integer.valueOf(beanName.split("-")[1]));
            RedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfiguration);
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }

        @Override
        public Class<?> getObjectType() {
            return RedisTemplate.class;
        }
    }

}
