# 最全的Spring Boot实践指南
![travis](https://travis-ci.org/rhwayfun/spring-boot-learning-examples.svg?branch=develop)
[![codecov](https://codecov.io/gh/rhwayfun/spring-boot-learning-examples/branch/develop/graph/badge.svg)](https://codecov.io/gh/rhwayfun/spring-boot-learning-examples)
[![Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/spring-boot-learning-examples/chat)
[![license](https://img.shields.io/badge/license-EPL%201.0-green.svg)](https://choosealicense.com/licenses/epl-1.0/)

Spring Boot知识点整理、工程实践，并结合工作案例进行深入

* 使用travis-ci持续集成
* 使用codecov进行代码覆盖率检查
* 学习案例以模块方式划分，每隔模块都是独立可执行项目，直接运行Application即可

## 分享平台

> 博客：http://blog.csdn.net/u011116672

> Github：https://github.com/rhwayfun

## 项目模块
```
└── 基础入门
└── Web开发
    └── Weex
    └── Bootstrap
    └── thymeleaf
└── 缓存使用
    └── Ehcache
    └── Caffeine
    └── Guava Cache
└── 数据库
    └── Mybatis
    └── MySQL
└── Spring其他功能
    └── Spring Task
    └── Spring Retry
    └── Spring AOP
└── 企业开发进阶
    └── Dubbo（阿里开源的分布式服务治理框架）
    └── Kafka
    └── RocketMQ
    └── Sharding-JDBC
    └── Disconf
    └── Elsaticsearch
    └── Elsatic-job
    └── Redis
    └── MongoDB
    └── Mockito
    └── InfluxDB
    └── Ignite
    └── Geode
    └── ...
```

### 基础入门

* spring-boot-quickstart（Spring Boot快速入门案例）
* spring-boot-configuration（了解下Spring Boot配置管理）

### web开发

* spring-boot-web-jsp（使用JSP作为开发）
* spring-boot-web-thymeleaf（使用模板引擎thymeleaf开发）
* spring-boot-web-bootstrap（bootstrap入门demo）
* spring-boot-security（权限控制项目实战）
    
    [spring security实战](http://blog.csdn.net/u011116672/article/details/77428049)
    
* spring-boot-security-cas（集成CAS搭建自己的认证中心）
    

### 缓存使用
* spring-boot-cache-caffeine（高性能本地缓存框架caffeine实践）
* spring-boot-cache-ehcache（Java应用最多的本地缓存Ehcache实践）
* spring-boot-redis（分布式KV缓存redis实践）

### 数据库
* spring-boot-mybatis（mybatis使用快速入门）
* spring-boot-mybatis-annotation（mybatis全注解使用示例）
* spring-boot-mybatis-multidatasource（mybatis多数据库解决方案）
* spring-boot-mybatis-sharding-jdbc（使用sharding-jdbc对数据库进行分库分表）
    
    [Sharding-JDBC分库分表使用实例](http://blog.csdn.net/u011116672/article/details/78374724)
    
* spring-boot-mybatis-sharding-jdbc-masterslave（使用sharding-jdbc完成分库分表+读写分离）
    
    [Sharding-JDBC读写分离探秘](http://blog.csdn.net/u011116672/article/details/78576117)

### Spring其他功能
* spring-boot-task（定时任务）

    [Spring定时任务源码分析](http://blog.csdn.net/u011116672/article/details/77132205)
    
    [深入浅出Spring task定时任务](http://blog.csdn.net/u011116672/article/details/52517247)
    
* spring-boot-retry（重试和熔断）

    [重试框架Spring retry实践](http://blog.csdn.net/u011116672/article/details/77823867)
    
* spring-boot-aspect（aop相关，静态织入、动态织入）
    
    [AspectJ切面执行两次原因分析](http://blog.csdn.net/u011116672/article/details/63685340)


### 企业开发进阶
* spring-boot-dubbo（服务治理框架dubbo使用案例）
* spring-boot-dubbo-annotation（服务治理框架dubbo案例，基于注解实现）

    [dubbo-spring-boot-project](https://github.com/apache/incubator-dubbo-spring-boot-project)

* spring-boot-dubbo-extension（基于duboo扩展点实现自定义扩展）
* spring-boot-disconf（分布式配置管理disconf使用案例）
* spring-boot-elasticsearch（全文搜索引擎elasticsearch实践）
* spring-boot-mongodb（NoSQL数据库mongodb实战）
* spring-boot-kafka（消息中间件kafka实践）
* spring-boot-rocketmq（阿里开源消息中间件RocketMQ实践）

    spring-boot-rocketmq-starter使用案例

* spring-boot-rocketmq-starter（阿里开源消息中间件RocketMQ Spring Boot Starter）
    
    [spring-boot-rocketmq-starter 使用指南](apache-rocketmq-starter-guide.md)
    
    [spring-boot-rocketmq-starter](https://github.com/rhwayfun/spring-boot-rocketmq-starter)
    
* spring-boot-mockito（Java社区最火的测试框架Mockito使用实战）
* spring-boot-hibernate-validation（Hibernate出品的校验框架使用实战）
* spring-boot-geode（内存数据库geode实战，目前应用与内部地址位置距离的计算）
* spring-boot-ignite（内存数据库ignite实战）
* spring-boot-elastic-job（分布式任务调度框架elastic-job实战）
* spring-boot-starter（自定义spring boot starter）
* spring-boot-starter-rest（自定义spring boot starter）
* spring-boot-logging-log4j2（使用log4j2）
* spring-boot-influxdb（时序数据库influxDB实践）
* spring-boot-mybatis-sharding-jdbc（分库分表Sharding-JDBC实践）

## 准备工作

> [数据库脚本](docs/sql/springboot/spring-boot-mybatis.sql)

### 安装MySQL

### 添加用户`travis`

 ```
 create user travis@localhost;
 ```
### 授权

```
grant all privileges on *.* to travis@localhost;/* mac系统下localhost要改成127.0.0.1 */      
```

### 查看权限

```
/*      查看MySQL所有用户      */
SELECT DISTINCT CONCAT('User: ''',user,'''@''',host,''';') AS query FROM mysql.user;
/*      查看travis用户的权限      */
show grants for travis@localhost; 
```

### 编译运行

```
mvn clean compile
mvn clean package
```


## 未完待续
更多案例不断补充中。。。如果您觉得对你有用，就给我点个赞吧\(^o^)/~


