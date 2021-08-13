package com.example.rpc.registry;

import cn.hutool.http.HttpUtil;
import com.example.rpc.annotation.MyReference;
import com.example.rpc.annotation.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * 利用spring提供的生命周期回调
 * 实现自定义注解属性注入
 *
 * @author yousj
 * @since 2021/1/15
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor, EnvironmentAware, PriorityOrdered {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        // 注册服务
        MyService service = clazz.getAnnotation(MyService.class);
        if (service != null) {
            // 注册服务映射关系至注册中心
            HttpUtil.get(environment.getProperty("register.center") + "/set?k=" + service.value() + "&v=" + environment.getProperty("producer.url") + clazz.getAnnotation(RequestMapping.class).value()[0] + clazz.getMethods()[0].getAnnotation(RequestMapping.class).value()[0]);
        }
        // 属性注入
        // 给@MyReference注解过的属性赋值
        try {
            for (Field field : clazz.getDeclaredFields()) {
                MyReference reference = field.getAnnotation(MyReference.class);
                if (reference != null) {
                    // 获取注入属性的名称
                    String value = reference.value();
                    // 根据名称从注册中心获取映射关系
                    String rpcUrl = HttpUtil.get(environment.getProperty("register.center") + "/get?k=" + value);
					System.out.println("=============>>>" + rpcUrl + "<<<==============");
                    // 使用JDK动态代理生成rpc代理类
                    Object proxyInstance = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{field.getType()}, (proxy, method, args1) -> {
                        System.out.println("=============>>> rpc <<<=============");
                        return HttpUtil.get(rpcUrl + "?param=" + args1[0]);
                    });
                    // 修改属性的权限值
                    field.setAccessible(true);
                    // 将动态代理生成的proxyInstance类赋值给bean的@MyReference过的属性
                    field.set(bean, proxyInstance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
