# seata启动包
主要修改了配置 conf/registry.conf文件
seata 整合nacos，nacos作为配置中心和注册中心

## 启动脚本
./seata-server.sh -p 8091 -n 1

## 部署指南
http://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html

## [client](https://github.com/seata/seata/tree/develop/script/client)

> 存放用于客户端的配置和SQL

- at: AT模式下的 `undo_log` 建表语句
- conf: 客户端的配置文件
- saga: SAGA 模式下所需表的建表语句
- spring: SpringBoot 应用支持的配置文件

## [server](https://github.com/seata/seata/tree/develop/script/server)

> 存放server侧所需SQL和部署脚本

- db: server 侧的保存模式为 `db` 时所需表的建表语句
- docker-compose: server 侧通过 docker-compose 部署的脚本
- helm: server 侧通过 Helm 部署的脚本
- kubernetes: server 侧通过 Kubernetes 部署的脚本

## [config-center](https://github.com/seata/seata/tree/develop/script/config-center)

> 用于存放各种配置中心的初始化脚本，执行时都会读取 `config.txt`配置文件，并写入配置中心

- nacos: 用于向 Nacos 中添加配置