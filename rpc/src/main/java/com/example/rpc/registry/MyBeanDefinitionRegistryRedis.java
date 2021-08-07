package com.example.rpc.registry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.rpc.registry.config.MyRedisStandaloneConfiguration;
import com.example.rpc.registry.config.RedisTemplateConfig;
import com.example.rpc.registry.factorybean.MyRedisTemplateFactoryBean;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 利用FactoryBean接口实现动态注册BeanDefinition
 * 注入自定义redisTemplate
 * <p>
 * <p>
 * Created by yousj on 2021/8/6 16:16
 *
 * @author yousj
 */
public class MyBeanDefinitionRegistryRedis implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        registerBeanDefinition(registry);
    }

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private void registerBeanDefinition(BeanDefinitionRegistry registry) {
        BindResult<RedisTemplateConfig> bindResult = Binder.get(environment).bind("multi-redis", RedisTemplateConfig.class);
        bindResult.ifBound(redisTemplateConfigs -> {
            Map<String, MyRedisStandaloneConfiguration> multiRedisTemplateConfigs = redisTemplateConfigs.getRedisTemplateConfigs();
            for (Map.Entry<String, MyRedisStandaloneConfiguration> redisStandaloneConfiguration : multiRedisTemplateConfigs.entrySet()) {
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RedisTemplate.class);
                GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                definition.getConstructorArgumentValues().addGenericArgumentValue(redisStandaloneConfiguration.getValue());
                definition.setBeanClass(MyRedisTemplateFactoryBean.class);
                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
                registry.registerBeanDefinition(redisStandaloneConfiguration.getKey(), definition);
            }
        });
    }
}
