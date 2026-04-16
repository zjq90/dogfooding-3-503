# 酒店管理系统（重构版）

这是一个前后端分离的酒店管理系统，后端使用Spring Boot架构，前端使用Vue 3 + Element Plus架构。

## 项目结构

```
hotel-management-refactored/
├── backend/                    # 后端项目
│   ├── src/main/java/com/hotel/
│   │   ├── common/            # 通用类（结果封装、异常处理等）
│   │   ├── config/            # 配置类
│   │   ├── controller/        # 控制器层
│   │   ├── entity/            # 实体类
│   │   ├── mapper/            # 数据访问层
│   │   ├── service/           # 服务层
│   │   └── utils/             # 工具类
│   ├── src/main/resources/
│   │   ├── mapper/            # MyBatis XML映射文件
│   │   ├── application.yml    # 主配置文件
│   │   ├── application-dev.yml   # 开发环境配置
│   │   └── application-prod.yml  # 生产环境配置
│   └── pom.xml                # Maven配置
│
└── frontend/                   # 前端项目
    ├── src/
    │   ├── api/               # API接口
    │   ├── assets/            # 静态资源
    │   ├── components/        # 公共组件
    │   ├── layouts/           # 布局组件
    │   ├── router/            # 路由配置
    │   ├── store/             # Pinia状态管理
    │   ├── utils/             # 工具函数
    │   └── views/             # 页面视图
    │       ├── admin/         # 管理员页面
    │       └── user/          # 用户页面
    ├── package.json
    └── vue.config.js
```

## 技术栈

### 后端
- Spring Boot 2.7.14
- MyBatis Plus 3.5.3.1
- MySQL 8.0
- Druid连接池
- Lombok
- JWT

### 前端
- Vue 3.3.4
- Vue Router 4
- Pinia 2
- Element Plus 2.3.14
- Axios
- Day.js

## 功能模块

### 用户端
- 用户注册/登录
- 浏览房间列表
- 房间预订
- 查看订单
- 发表评论
- 个人中心

### 管理端
- 管理员登录
- 房间管理（增删改查）
- 房型管理
- 入住管理（办理入住、退房结算）
- 预订管理
- 用户管理
- 评论管理
- 公告管理

## 快速开始

### 后端启动

1. 创建数据库并导入SQL文件
```sql
-- 使用原有的jiudian.sql文件创建数据库
mysql -u root -p < jiudian.sql
```

2. 修改数据库配置
编辑 `backend/src/main/resources/application-dev.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/jiudian?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

3. 启动项目
```bash
cd backend
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 前端启动

1. 安装依赖
```bash
cd frontend
npm install
```

2. 启动开发服务器
```bash
npm run serve
```

前端服务将在 http://localhost:3000 启动

## 默认账号

- 管理员账号: admin / 123456
- 用户账号: 需要注册

## API接口文档

所有API接口统一返回格式:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {},
  "timestamp": 1234567890
}
```

### 主要接口

#### 认证接口
- POST /api/auth/user/login - 用户登录
- POST /api/auth/user/register - 用户注册
- GET /api/auth/user/check - 检查用户名
- POST /api/auth/admin/login - 管理员登录

#### 用户接口
- GET /api/user/list - 获取用户列表
- GET /api/user/page - 分页查询用户
- GET /api/user/{userId} - 根据ID获取用户
- PUT /api/user/{userId} - 更新用户信息
- DELETE /api/user/{userName} - 删除用户

#### 房间接口
- GET /api/room/list - 获取房间列表
- GET /api/room/page - 分页查询房间
- GET /api/room/{roomId} - 根据ID获取房间
- GET /api/room/type/{roomType} - 根据类型获取房间
- GET /api/room/available - 获取可预订房间
- POST /api/room - 添加房间
- PUT /api/room/{roomId} - 更新房间
- DELETE /api/room/{roomId} - 删除房间

#### 订单接口
- GET /api/order/list - 获取订单列表
- GET /api/order/user/{userName} - 根据用户获取订单
- GET /api/order/active - 获取在住订单
- POST /api/order - 添加入住订单
- POST /api/order/checkout/{roomId} - 退房结算
- DELETE /api/order/{roomId} - 删除订单

#### 预订接口
- GET /api/reserve/list - 获取预订列表
- GET /api/reserve/user/{userName} - 根据用户获取预订
- POST /api/reserve - 预订房间
- DELETE /api/reserve/{roomId} - 取消预订

#### 评论接口
- GET /api/comment/list - 获取评论列表
- GET /api/comment/room/{roomId} - 根据房间获取评论
- GET /api/comment/user/{userName} - 根据用户获取评论
- POST /api/comment - 发表评论
- DELETE /api/comment/{serNum} - 删除评论

#### 公告接口
- GET /api/notice/list - 获取公告列表
- GET /api/notice/latest - 获取最新公告
- POST /api/notice - 发布公告
- PUT /api/notice/{serNum} - 更新公告
- DELETE /api/notice/{serNum} - 删除公告

## 重构优化点

### 后端优化
1. 使用Spring Boot 2.7替代传统SSM架构
2. 使用MyBatis Plus简化数据访问层代码
3. 统一响应结果封装（Result类）
4. 全局异常处理
5. 参数校验（JSR-303）
6. 跨域配置
7. 使用Lombok简化实体类
8. 规范的代码注释
9. RESTful API设计

### 前端优化
1. Vue 3 Composition API
2. Element Plus UI组件库
3. Pinia状态管理
4. 响应式布局设计
5. 页面美化（渐变背景、卡片设计）
6. 路由守卫实现权限控制
7. Axios请求封装和拦截器
8. 过渡动画效果

### SQL优化
1. 使用索引优化查询
2. 优化表结构
3. 使用连接池（Druid）

## 许可证

MIT License
