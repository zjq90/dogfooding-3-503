# 酒店管理系统 - 重构版

## 项目概述

本项目是一个前后端分离的酒店管理系统，由原有的SSM+JSP架构重构为SpringBoot+Vue架构。

## 技术栈

### 后端
- Spring Boot 2.7.18
- MyBatis-Plus 3.5.3.1
- MySQL 8.0
- Druid 连接池

### 前端
- Vue 3
- Vue Router 4
- Element Plus
- Axios
- Vite

## 项目结构

```
glm/
├── hotel-backend/          # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/hotel/
│   │   │   │   ├── controller/   # 控制器
│   │   │   │   ├── service/      # 服务层
│   │   │   │   ├── mapper/       # 数据访问层
│   │   │   │   ├── entity/       # 实体类
│   │   │   │   ├── config/       # 配置类
│   │   │   │   └── common/       # 公共类
│   │   │   └── resources/
│   │   │       ├── mapper/       # MyBatis XML
│   │   │       └── application.yml
│   │   └── sql/                  # 数据库脚本
│   └── pom.xml
│
└── hotel-frontend/         # 前端项目
    ├── src/
    │   ├── api/            # API接口
    │   ├── views/          # 页面组件
    │   ├── layouts/        # 布局组件
    │   ├── router/         # 路由配置
    │   └── utils/          # 工具类
    ├── package.json
    └── vite.config.js
```

## 快速开始

### 1. 数据库配置

1. 创建数据库 `jiudian`
2. 执行 `hotel-backend/sql/jiudian.sql` 脚本

### 2. 启动后端

```bash
cd hotel-backend
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 3. 启动前端

```bash
cd hotel-frontend
npm install
npm run dev
```

前端服务将在 http://localhost:3000 启动

## 功能模块

### 管理员端
- 管理员登录
- 客房类型管理
- 客房信息管理
- 预定信息管理
- 入住信息管理
- 会员信息管理
- 评论管理

### 用户端
- 用户登录/注册
- 客房浏览
- 客房预定
- 评论查看/发表

## 默认账号

- 管理员: admin / 123456
- 用户: zyg / 123456
