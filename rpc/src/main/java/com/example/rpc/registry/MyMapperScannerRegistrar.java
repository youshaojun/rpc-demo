package com.example.rpc.registry;

import com.example.rpc.annotation.MapperScan;
import com.example.rpc.registry.factorybean.MyMapperFactoryBean;
import com.example.rpc.registry.mapper.BaseMapper;
import org.reflections.Reflections;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * 扫描mapper, 注册BeanDefinition
 *
 * @author yousj
 * @date 2021/8/7
 */
public class MyMapperScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        doScan(registry, importingClassMetadata);
    }

    private void doScan(BeanDefinitionRegistry registry, AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName()));
        Set<Class<? extends BaseMapper>> beanClasses = new Reflections(mapperScanAttrs.get("value")).getSubTypesOf(BaseMapper.class);
        registerBeanDefinitions(registry, beanClasses);
    }

    private void registerBeanDefinitions(BeanDefinitionRegistry registry, Set<Class<? extends BaseMapper>> beanClasses) {
        for (Class<?> beanClass : beanClasses) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClass);
            definition.setBeanClass(MyMapperFactoryBean.class);
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(beanClass.getSimpleName(), definition);
        }
    }
}
