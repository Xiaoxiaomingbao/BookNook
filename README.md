# BookNook 书驿

## 一、需求分析

1. 核心功能模块

   **用户服务**：注册/登录、实名认证、信用体系、消息通知

   **书籍服务**：图书信息管理（ISBN识别、多维度分类）、书籍状态跟踪（全新/二手/拍卖）、图片云端存储

   **交易服务**：普通交易（购物车/订单/评价）、拍卖系统（保证金机制、自动出价、倒计时提醒）、支付网关集成

   **扩展能力**：书籍回收估价系统（AI图像品相识别）、书籍漂流社交功能

2. 关键业务指标

   支持万级并发交易请求（Hystrix熔断+Redis集群）

   书籍搜索响应时间<200ms（ES分词+缓存预热）

   拍卖事务成功率>99.99%（分布式事务Seata）

## 二、微服务架构设计

| 微服务名称         | 端口    | 主要职责         | 技术特征                         |
|---------------|-------|--------------|------------------------------|
| book-gateway  | 8080  | 统一API入口/限流熔断 | Spring Cloud Gateway+JWT鉴权   |
| book-auth     | 	9001 | 	OAuth2认证中心  | 	Spring Security+Keycloak    |
| book-user     | 	9002 | 	用户档案/信用体系   | 	MyBatis-Plus+Redis Sentinel |
| book-product	 | 9003	 | 书籍元数据管理	     | Elasticsearch+OSS图片存储        |
| book-trade    | 	9004 | 普通订单交易	      | Seata AT模式+Redis分布式锁         |
| book-auction  | 	9005 | 拍卖流程引擎	      | Quartz定时任务+WebSocket推送       |
| book-payment	 | 9006	 | 支付对账中心       | 	微信/支付宝SDK+账单异步生成            |
| book-search	  | 9007  | 全文检索服务	      | ES聚合查询                       |
| book-monitor	 | 9008  | 	系统监控告警	     | Prometheus+Grafana+ELK日志     |

## 三、技术栈规划

   | 领域	    | 技术选型                                              |
   |--------|---------------------------------------------------|
   | 微服务框架	 | Spring Boot + Spring Cloud (Eureka/Feign/Hystrix) |
   | 容器编排   | 	Kubernetes + Containerd                          |
   | 数据存储	  | MySQL（分库分表）+ Redis（缓存）                            |
   | 搜索引擎	  | Elasticsearch + IK分词插件 + 向量检索插件                   |
   | 消息队列	  | RabbitMQ                                          |
   | 监控体系	  | Prometheus（指标采集）+ Grafana（可视化）+ AlertManager（告警）  |
