package com.example.rpc.registry;

import com.alibaba.fastjson.JSON;
import com.example.rpc.registry.mapper.BaseMapper;
import com.example.rpc.registry.mapper.TestMapper01;
import com.example.rpc.registry.mapper.TestMapper02;
import com.sun.istack.internal.NotNull;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * 利用FactoryBean接口实现bean动态注册BeanDefinition
 *
 * Created by yousj on 2021/8/6 16:16
 * @author yousj
 */
@Component
public class MyBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        Reflections reflections = new Reflections("com.example.rpc.registry.mapper");
        Set<Class<? extends BaseMapper>> beanClasses = reflections.getSubTypesOf(BaseMapper.class);
        for (Class<?> beanClass : beanClasses) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClass);
            definition.setBeanClass(MyFactoryBean.class);
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(beanClass.getSimpleName(), definition);
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


    class MyFactoryBean<T> implements FactoryBean {

        private Class<T> interfaceType;

        public MyFactoryBean(Class<T> interfaceType) {
            this.interfaceType = interfaceType;
        }

        @Override
        public Object getObject() {
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{interfaceType}, (proxy, method, args) -> {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
                Object result = JSON.toJSONString(args);
                System.out.println("方法[" + method.getName() + "]被调用, 参数[" + result + "]");
                return args[0];
            });
        }

        @Override
        public Class<?> getObjectType() {
            return interfaceType;
        }
    }

    @SuppressWarnings("NullableProblemsunqualified-field-access")
    @Autowired
    TestMapper01 testMapper01;
    @Autowired
    TestMapper02 testMapper02;

    @PostConstruct
    public void test(){
        testMapper01.test01("1111");
        testMapper02.test02("2222");
        testMapper02.test03("3333");
    }


}
