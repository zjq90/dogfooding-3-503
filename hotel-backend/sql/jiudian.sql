/*
 酒店管理系统数据库脚本
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `admin_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '123456',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `t_admin` VALUES (1, 'admin', '123456');
COMMIT;

DROP TABLE IF EXISTS `t_book_a_room`;
CREATE TABLE `t_book_a_room` (
  `room_id` varchar(25) NOT NULL,
  `count_date` int(11) DEFAULT NULL,
  `user_name` varchar(25) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `preset_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `ser_num` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `room_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ser_num`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_liuyan`;
CREATE TABLE `t_liuyan` (
  `user_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `liuyan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ser_num` bigint(20) NOT NULL AUTO_INCREMENT,
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ser_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `ser_num` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `release_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ser_num`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `t_notice` VALUES (1, 1, '即日起：所有客房打8折', '2019-06-07 18:52:09');
INSERT INTO `t_notice` VALUES (2, 1, '即日起：豪华房优惠100元', '2019-06-09 23:04:50');
COMMIT;

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `room_id` varchar(20) NOT NULL,
  `user_name` varchar(25) DEFAULT NULL,
  `ID` varchar(18) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `prepay` decimal(10,2) DEFAULT NULL,
  `payment` decimal(10,2) DEFAULT NULL,
  `change` decimal(10,2) DEFAULT NULL,
  `ser_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `room_id` varchar(20) NOT NULL,
  `room_type` varchar(20) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。',
  `photo_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/img/jiudian4.jpg',
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `t_room` VALUES ('1301', '情侣房', 64, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian1.jpg', 200.00);
INSERT INTO `t_room` VALUES ('2804', '情侣房', 54, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian2.jpg', 200.00);
INSERT INTO `t_room` VALUES ('3202', '大床房', 60, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian3.jpg', 300.00);
INSERT INTO `t_room` VALUES ('3205', '大床房', 60, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian4.jpg', 300.00);
INSERT INTO `t_room` VALUES ('3206', '大床房', 60, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian5.jpg', 300.00);
INSERT INTO `t_room` VALUES ('4301', '商务房', 72, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian8.jpg', 400.00);
INSERT INTO `t_room` VALUES ('5201', '单人间', 40, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian6.gif', 100.00);
INSERT INTO `t_room` VALUES ('5202', '单人间', 40, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian7.jpg', 100.00);
INSERT INTO `t_room` VALUES ('5203', '单人间', 40, '环境优美，LED光影时尚造型格调装饰，装备顶级影音设备，床褥舒适。', '/img/jiudian8.jpg', 100.00);
COMMIT;

DROP TABLE IF EXISTS `t_room_type`;
CREATE TABLE `t_room_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `t_room_type` VALUES (1, '大床房');
INSERT INTO `t_room_type` VALUES (2, '单人间');
INSERT INTO `t_room_type` VALUES (3, '情侣房');
INSERT INTO `t_room_type` VALUES (4, '双人间');
INSERT INTO `t_room_type` VALUES (5, '商务房');
COMMIT;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `t_user` VALUES (5, 'zyg', '123456', '', '', '', '', '');
INSERT INTO `t_user` VALUES (6, 'test', '123456', '', '', '', '', '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
