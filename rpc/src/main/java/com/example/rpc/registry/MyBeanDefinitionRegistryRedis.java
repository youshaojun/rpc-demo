package com.example.rpc.registry;

import com.example.rpc.registry.config.MyRedisStandaloneConfiguration;
import com.example.rpc.registry.config.RedisConfig;
import com.example.rpc.registry.factorybean.MyRedisTemplateFactoryBean;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

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
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        BindResult<RedisConfig> bindResult = Binder.get(environment).bind("multi-redis", RedisConfig.class);
        bindResult.ifBound(redisTemplateConfig -> registerBeanDefinition(redisTemplateConfig, registry));
    }

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    private void registerBeanDefinition(RedisConfig redisTemplateConfigs, BeanDefinitionRegistry registry) {
        Map<String, MyRedisStandaloneConfiguration> multiRedisTemplateConfigs = redisTemplateConfigs.getRedisConfigs();
        for (Map.Entry<String, MyRedisStandaloneConfiguration> redisStandaloneConfiguration : multiRedisTemplateConfigs.entrySet()) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RedisTemplate.class);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            definition.getConstructorArgumentValues().addGenericArgumentValue(redisStandaloneConfiguration.getValue());
            definition.setBeanClass(MyRedisTemplateFactoryBean.class);
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
            registry.registerBeanDefinition(redisStandaloneConfiguration.getKey(), definition);
        }
    }

}
