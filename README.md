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

### BeanDefinitionRegistryPostProcessor和ImportBeanDefinitionRegistrar
#### 两个接口都可以用于动态注册bean到容器中

#### 1.BeanDefinitionRegistryPostProcessor
BeanDefinitionRegistryPostProcessor实现了BeanFactoryPostProcessor接口，
是Spring框架的BeanDefinitionRegistry的后处理器，用来注册额外的BeanDefinition。
postProcessBeanDefinitionRegistry方法会在所有的BeanDefinition已经被加载了，
但是所有的Bean还没有被创建前调用。BeanDefinitionRegistryPostProcessor经常被
用来注册BeanFactoryPostProcessor的BeanDefinition

#### 2.ImportBeanDefinitionRegistrar
@Import注解用来支持在Configuration类中引入其他的配置类，包括Configuration类，
ImportSelector和ImportBeanDefinitionRegistrar的实现类。
ImportBeanDefinitionRegistrar在ConfigurationClassPostProcessor处理Configuration类
期间被调用，用来生成该Configuration类所需要的BeanDefinition。
而ConfigurationClassPostProcessor正实现了BeanDefinitionRegistryPostProcessor
接口（所以支持mapper注册成bean，并注入到spring容器中）





