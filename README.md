# tudou

## 项目介绍

　　基于SpringBoot+Mybatis+dubbo分布式敏捷开发系统架构，主要目的是能够让初级的研发人员快速的开发出复杂的业务功能，让开发者注重专注业务，其余有平台来封装技术细节，降低技术难度，从而节省人力成本，缩短项目周期，提高软件安全质量
    
``` lua
tudou
├── tudou-common -- 公共包, 工具类等
├── tudou-global -- 全局服务模块
├── tudou-1-base -- 基础服务模块(主要包含用户角色权限的控制)
|    ├── base-model -- 基础实体
|    ├── base-server -- 基础服务
├── web-platform -- web服务模块，对外开放接口供前端调用
```
### 技术选型

#### 后端技术:
技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
SpringMVC | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
Spring session | 分布式Session管理  | [http://projects.spring.io/spring-session/](http://projects.spring.io/spring-session/)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MybatisGen | 代码生成  | [https://github.com/liuanxin/mybatis-gen](https://github.com/liuanxin/mybatis-gen)
MybatisPage | MyBatis物理分页插件  | [https://github.com/liuanxin/mybatis-page](https://github.com/liuanxin/mybatis-page)
Druid | 数据库连接池  | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
ZooKeeper | 分布式协调服务  | [http://zookeeper.apache.org/](http://zookeeper.apache.org/)
Dubbox | 分布式服务框架  | [http://dubbo.io/](http://dubbo.io/)
Redis | 分布式缓存数据库  | [https://redis.io/](https://redis.io/)
Elasticsearch | 分布式全文搜索引擎  | [http://www.elastic.co/products/elasticsearch](http://www.elastic.co/products/elasticsearch)
Quartz | 作业调度框架  | [http://www.quartz-scheduler.org/](http://www.quartz-scheduler.org/)
ActiveMQ | 消息队列  | [http://activemq.apache.org/](http://activemq.apache.org/)
BackLog | 日志组件  | [http://logging.apache.org/log4j/1.2/](http://logging.apache.org/log4j/1.2/)
Swagger2 | 接口测试框架  | [http://swagger.io/](http://swagger.io/)
kryo | 数据序列化  | [https://blog.csdn.net/fanjunjaden/article/details/72823866](https://blog.csdn.net/fanjunjaden/article/details/72823866)
Jenkins | 持续集成工具  | [https://jenkins.io/index.html](https://jenkins.io/index.html)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)
