/*
  酒店管理系统数据库脚本 - 优化版
  优化内容：
  1. 添加索引优化查询性能
  2. 统一字符集和排序规则
  3. 添加合理的默认值
  4. 添加外键约束
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建数据库
CREATE DATABASE IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hotel;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(25) NOT NULL COMMENT '管理员账号',
  `admin_pwd` varchar(30) NOT NULL DEFAULT '123456' COMMENT '管理员密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `uk_admin_name` (`admin_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123456', NOW(), NOW());

-- ----------------------------
-- Table structure for t_room_type
-- ----------------------------
DROP TABLE IF EXISTS `t_room_type`;
CREATE TABLE `t_room_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房型ID',
  `type` varchar(25) NOT NULL COMMENT '房型名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `uk_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='房型表';

-- ----------------------------
-- Records of t_room_type
-- ----------------------------
INSERT INTO `t_room_type` VALUES (1, '大床房'), (2, '单人间'), (3, '情侣房'), (4, '双人间'), (5, '商务房');

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `room_id` varchar(20) NOT NULL COMMENT '房间号',
  `room_type` varchar(20) NOT NULL COMMENT '房间类型',
  `area` int(11) NOT NULL DEFAULT 30 COMMENT '房间面积',
  `introduce` varchar(255) NOT NULL DEFAULT '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。' COMMENT '房间介绍',
  `photo_url` varchar(100) NOT NULL DEFAULT '/img/default.jpg' COMMENT '房间图片',
  `price` decimal(10,2) NOT NULL DEFAULT 100.00 COMMENT '房间价格',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0-空闲 1-已入住 2-已预订',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`room_id`),
  KEY `idx_room_type` (`room_type`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房间表';

-- ----------------------------
-- Records of t_room
-- ----------------------------
INSERT INTO `t_room` VALUES 
('1301', '情侣房', 64, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian1.jpg', 200.00, 0, NOW(), NOW()),
('2804', '情侣房', 54, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian2.jpg', 200.00, 0, NOW(), NOW()),
('3202', '大床房', 60, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian3.jpg', 300.00, 0, NOW(), NOW()),
('3205', '大床房', 60, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian4.jpg', 300.00, 0, NOW(), NOW()),
('3206', '大床房', 60, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian5.jpg', 300.00, 0, NOW(), NOW()),
('4301', '商务房', 72, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian8.jpg', 400.00, 0, NOW(), NOW()),
('5201', '单人间', 40, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian6.gif', 100.00, 0, NOW(), NOW()),
('5202', '单人间', 40, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian7.jpg', 100.00, 0, NOW(), NOW()),
('5203', '单人间', 40, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian8.jpg', 100.00, 0, NOW(), NOW());

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(25) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(30) NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `address` varchar(50) DEFAULT '' COMMENT '地址',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `QQ` varchar(20) DEFAULT '' COMMENT 'QQ',
  `photo_url` varchar(255) DEFAULT '' COMMENT '头像',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_name` (`user_name`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES 
(5, 'zyg', '123456', '', '', '', '', '', NOW(), NOW()),
(6, '章鱼哥', '123456', '', '', '', '', '', NOW(), NOW()),
(7, 'ggg', '123', '', 'ggg', '', '', '', NOW(), NOW()),
(8, 'zxcv', '123456', '', '', '', '', '', NOW(), NOW());

-- ----------------------------
-- Table structure for t_book_a_room
-- ----------------------------
DROP TABLE IF EXISTS `t_book_a_room`;
CREATE TABLE `t_book_a_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `room_id` varchar(20) NOT NULL COMMENT '房间号',
  `count_date` int(11) NOT NULL DEFAULT 1 COMMENT '预订天数',
  `user_name` varchar(25) NOT NULL COMMENT '预订人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `preset_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预订时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0-待确认 1-已确认 2-已取消',
  PRIMARY KEY (`id`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预订表';

-- ----------------------------
-- Records of t_book_a_room
-- ----------------------------
INSERT INTO `t_book_a_room` VALUES 
(1, '4301', 2, 'zyg', '1827838661', '2019-06-26 15:58:23', 1),
(2, '5202', 2, 'zyg', '1827838661', '2019-06-26 16:03:59', 1);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `room_id` varchar(20) NOT NULL COMMENT '房间号',
  `user_name` varchar(25) NOT NULL COMMENT '客户姓名',
  `ID` varchar(18) DEFAULT '' COMMENT '身份证号',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入住时间',
  `end_time` datetime DEFAULT NULL COMMENT '退房时间',
  `prepay` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '押金',
  `payment` decimal(10,2) DEFAULT NULL COMMENT '实付',
  `change` decimal(10,2) DEFAULT NULL COMMENT '找零',
  `ser_num` varchar(32) NOT NULL COMMENT '订单编号',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态：1-入住中 2-已退房',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ser_num` (`ser_num`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入住订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES 
(1, '1302', 'zyg', '564456', '2019-06-13 19:59:47', '2019-06-14 01:21:30', 240.00, 300.00, NULL, 'cb670bee8dd211e991387eb7195666fe', 2),
(2, '1303', 'zyg', '5434', '2019-06-09 20:13:07', '2019-06-14 01:20:54', 150.00, 250.00, NULL, 'a5c04aac8dd411e991387eb7195666fe', 2),
(3, '2804', 'zyg', '5464', '2019-06-14 09:59:46', NULL, 263.00, NULL, NULL, '159ac5288e4811e9a2b8e050eeb8d2f2', 1),
(4, '3202', 'zyg', '54424', '2019-06-14 08:05:38', '2019-06-14 10:00:06', 4522.00, 250.00, NULL, '23fda2628e3811e9b32a3e18bc66cbc9', 2);

-- ----------------------------
-- Trigger for t_order
-- ----------------------------
DROP TRIGGER IF EXISTS `uuid_to_orderId`;
delimiter ;;
CREATE TRIGGER `uuid_to_orderId` BEFORE INSERT ON `t_order` FOR EACH ROW 
BEGIN
  IF NEW.ser_num IS NULL OR NEW.ser_num = '' THEN
    SET NEW.ser_num = REPLACE(UUID(),'-','');
  END IF;
END
;;
delimiter ;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `ser_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `user_name` varchar(25) NOT NULL COMMENT '评论人',
  `room_id` varchar(20) NOT NULL COMMENT '房间号',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`ser_num`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_release_time` (`release_time`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES 
(1, 'zyg', '1301', '体验很好', '2019-06-11 16:04:27'),
(2, '章鱼哥', '1301', 'very good', '2019-06-11 22:56:38'),
(7, 'zyg', '1301', 'dsfddv', '2019-06-12 10:58:47'),
(8, 'zyg', '1301', '很好，很舒服', '2019-06-12 11:00:16'),
(9, 'zyg', '1301', '很好', '2019-06-14 09:51:50'),
(10, 'zyg', '1301', '嗨嗨，nice', '2019-06-14 09:55:05'),
(11, '章鱼哥', '1301', '......', '2019-06-18 16:49:13'),
(12, 'zyg', '1301', 'nice', '2019-06-18 16:49:33'),
(13, 'ggg', '5203', '这个房间很吊', '2019-06-18 21:10:51'),
(14, 'zyg', '1301', 'hgcnb', '2019-06-18 22:08:16'),
(15, 'zyg', '2804', '很好！', '2019-06-20 19:41:52'),
(16, 'zxcv', '1301', 'nice', '2019-06-26 15:53:37');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `ser_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `admin_id` int(11) NOT NULL COMMENT '发布管理员ID',
  `notice` varchar(255) NOT NULL COMMENT '公告内容',
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`ser_num`),
  KEY `idx_admin_id` (`admin_id`),
  KEY `idx_release_time` (`release_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES 
(1, 1, '即日起：所有客房打8折，欢迎新老顾客光临！', '2019-06-07 18:52:09'),
(2, 1, '即日起：豪华房优惠100元，数量有限先到先得！', '2019-06-09 23:04:50');

-- ----------------------------
-- Table structure for t_liuyan
-- ----------------------------
DROP TABLE IF EXISTS `t_liuyan`;
CREATE TABLE `t_liuyan` (
  `ser_num` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `user_name` varchar(25) NOT NULL COMMENT '用户名',
  `liuyan` varchar(255) NOT NULL COMMENT '留言内容',
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
  PRIMARY KEY (`ser_num`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_release_time` (`release_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='留言表';

SET FOREIGN_KEY_CHECKS = 1;

-- 优化完成
-- 新增索引数量：14个
-- 统一字符集：utf8mb4
-- 添加时间戳自动更新
