# simple-rpc

#### 介绍

1. 简易版的rpc 利用BeanPostProcessor实现自定义属注解性注入

2. BeanDefinition和FactoryBean
    利用BeanDefinitionRegistry实现动态注册BeanDefinition
    注册的BeanDefinition的beanClass为FactoryBean类型
    属性填充的是调用FactoryBean的getObject()方法获取的实例

#### 使用说明
regist-center 注册中心
api 接口
producer 生产者
consumer 消费者
rpc 远程调用实现




