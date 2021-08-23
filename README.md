# simple-rpc

##### 使用docker & docker-compose部署项目
docker & docker-compose安装和使用请参考 
[Docker version 20.10.8 & docker-compose version 1.8.1]
[Docker官网](https://www.docker.com)       [菜鸟](https://www.runoob.com/docker/docker-tutorial.html)

#### 启动服务
/// 1. 使用命令后台启动服务 docker-compose up -d  
1. 使用脚本启动服务  ./start.sh -e dev  (脚本添加了环境激活支持, 使用 -e 环境 来激活对应环境)
2. [访问consumer服务](http://localhost:8082/test/get?param=xiaoming)

#### 使用说明
register-center 注册中心
api 接口
producer 生产者
consumer 消费者
rpc 远程调用实现

#### 相关代码说明

1. 
    com.example.rpc.registry.MyBeanPostProcessor
    简易版的rpc 利用BeanPostProcessor实现自定义属注解性注入
    参考 com.example.consumer.service.TestRpc 测试验证
  
2. 
    com.example.rpc.registry.MyBeanDefinitionRegistryRedis
    com.example.rpc.registry.MyMapperScannerRegistrar
    BeanDefinition和FactoryBean
    利用BeanDefinitionRegistry实现动态注册BeanDefinition
    注册的BeanDefinition的beanClass为FactoryBean类型
    属性填充的是调用FactoryBean的getObject()方法获取的实例
    参考 com.example.consumer.service.TestBeanDefinitionRegistry 测试验证
  

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





