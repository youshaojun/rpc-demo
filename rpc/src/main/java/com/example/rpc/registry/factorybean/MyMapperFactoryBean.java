package com.example.rpc.registry.factorybean;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author yousj
 * @since 2021/8/7
 */
public class MyMapperFactoryBean<T> implements FactoryBean {

    private Class<T> interfaceType;

    public MyMapperFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public Object getObject() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{interfaceType}, (proxy, method, args) -> {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }
            Object result = JSON.toJSONString(args);
            System.out.println("方法[" + method.getName() + "]被调用, 参数" + result);
            return args;
        });
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }

}
