# AdminESP- 企业服务治理平台

# Introduction简介
**AdminESP**是基于Spring-boot+Spring cloud neffix+mybatis+ vue.js的企业微服务框架，使用该框架可快速实现企业微服务应用。

该框架包含基于Eureka的服务注册中心、Feign的服务调用、Hystrix的服务熔断和性能监控、Config的服务配置中心与配置刷新、Seluth+Zipkin的服务链路追踪、Zuul的API网关。
**AdminEAP**

![AdminESP](http://upload-images.jianshu.io/upload_images/6944619-f12f11e6a22d093d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

建议该框架在docker环境下部署，该框架目前在持续完善中。。。。

Github地址：[AdminESP](https://github.com/bill1012/AdminESP)

# System architecture 系统架构图
**AdminESP系统架构**
![AdminESP系统架构](http://upload-images.jianshu.io/upload_images/6944619-bfbfd02604a62bba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# Functions 功能
**功能列表-菜单管理**
![功能列表-菜单管理](http://upload-images.jianshu.io/upload_images/6944619-f262e6752f4258aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**用户管理**
![用户管理](http://upload-images.jianshu.io/upload_images/6944619-be0d34a1328badb1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**服务镜像与容器管理—在线创建、启动、停止服务**
![服务镜像与容器管理—在线创建、启动、停止服务](http://upload-images.jianshu.io/upload_images/6944619-2d540158b5c3793a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 启动顺序

 register->track（可不启动）->config->ucenter(admin)->gateway


## 依赖

- gitlab

> esp-config 中配置git地址和密码

- rabbitmq

> esp-gateway 中配置rabbitmq连接地址

- mysql

> esp-ucenter 中配置mysql连接

- redis

> esp-gateway 中配置redis连接









