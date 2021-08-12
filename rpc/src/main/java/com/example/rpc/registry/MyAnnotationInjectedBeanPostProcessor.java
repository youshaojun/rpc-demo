package com.example.rpc.registry;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * Created by admin on 2021/3/31 9:44
 * @author admin
 */
@Component
public class MyAnnotationInjectedBeanPostProcessor extends AutowiredAnnotationBeanPostProcessor {

//    @Override
//    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) {
//
//        // 注解注入属性支持@Autowired(spring提供), @Resource(JSR250规范), @Inject(JSR330规范)
//        // @Autowired和@Inject使用AutowiredAnnotationBeanPostProcessor类实现属性注入
//        // @Resource使用CommonAnnotationBeanPostProcessor类实现属性注入
//        // 重写此方法实现属性注入逻辑
//
//        return pvs;
//    }


}
