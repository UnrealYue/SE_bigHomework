/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-22 22:23:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `dict_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dict_describe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', '酒店号码', '6666-6666666');
INSERT INTO `dictionary` VALUES ('2', '酒店地址', '北京市解放大道一号');
INSERT INTO `dictionary` VALUES ('3', '酒店介绍', '七星级大酒店');
INSERT INTO `dictionary` VALUES ('4', '酒店介绍', '七星级大酒店');

-- ----------------------------
-- Table structure for guest
-- ----------------------------
DROP TABLE IF EXISTS `guest`;
CREATE TABLE `guest` (
  `guest_id` int(11) NOT NULL,
  `guest_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户姓名',
  `gendar` int(1) DEFAULT NULL COMMENT '性别；男0，女1，    为什么用int类型自己体会=_=',
  `id_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号码',
  `phone_num` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话号码',
  `is_vip` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否为会员；否0，是1；',
  PRIMARY KEY (`guest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of guest
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL COMMENT '订单完成时间',
  `guest_id` int(11) NOT NULL COMMENT '用户编号',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `finish_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单完成时间',
  `stay_days` tinyint(1) unsigned NOT NULL COMMENT '入住天数',
  `num_of_people` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '客人人数',
  `notes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `is_reservation` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否是预约订单\r\n；是（1）\r\n否（0）',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '订单是否被取消；是（1）否（0）\r\n',
  `is_finished` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否完成；是1，否0',
  `is_checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已入住；是1，否0',
  `checkout_method` tinyint(2) unsigned DEFAULT '0' COMMENT '付款方式；现金（1）银行卡（2）微信支付宝（3）',
  PRIMARY KEY (`order_id`),
  KEY `fk_guest_order` (`guest_id`),
  CONSTRAINT `fk_guest_order` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_room
-- ----------------------------
DROP TABLE IF EXISTS `order_room`;
CREATE TABLE `order_room` (
  `order_room_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `room_id` int(5) NOT NULL,
  `checkin_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入住时间',
  `checkout_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '退房时间',
  `notes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_room_id`),
  KEY `fk_order-room_order` (`order_id`),
  KEY `fk_order-room_room` (`room_id`),
  CONSTRAINT `fk_order-room_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order-room_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_room
-- ----------------------------

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `order_id` int(11) NOT NULL,
  `pre_checkin_time` datetime NOT NULL COMMENT '预计入住时间',
  `pre_stay_days` tinyint(2) NOT NULL COMMENT '预计入住天数',
  `guest_num` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '客人人数',
  KEY `fk_reservation_order` (`order_id`),
  CONSTRAINT `fk_reservation_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` int(5) NOT NULL COMMENT '房间号；（楼层  2位 + 编号  2位）',
  `romm_type` tinyint(2) unsigned NOT NULL COMMENT '房间类型；（单人房、双人房 ...)',
  `room_price` smallint(6) NOT NULL COMMENT '房间价格',
  `room_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '房间状态；（空闲0，入住1，维修2）',
  `room_style` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房间特点',
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of room
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admini', '123456', '0');
INSERT INTO `users` VALUES ('2', 'liutianhong', '3F40BF80B59ABA195F745E2BCF37CE06', '1');
INSERT INTO `users` VALUES ('3', '123456', 'EA48576F30BE1669971699C09AD05C94', '1');
INSERT INTO `users` VALUES ('4', '123456', 'EA48576F30BE1669971699C09AD05C94', '1');
INSERT INTO `users` VALUES ('5', '123456', 'EA48576F30BE1669971699C09AD05C94', '1');
INSERT INTO `users` VALUES ('6', '123456', 'EA48576F30BE1669971699C09AD05C94', '1');
INSERT INTO `users` VALUES ('7', '123456', 'EA48576F30BE1669971699C09AD05C94', '1');
INSERT INTO `users` VALUES ('8', '123456', 'EA48576F30BE1669971699C09AD05C94', '1');
INSERT INTO `users` VALUES ('9', 'liutianhong', '51A0151C91E25C09F78D1E36A76E7B88', '0');

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `guest_id` int(11) NOT NULL,
  `birthday` date DEFAULT NULL COMMENT '会员生日',
  `job` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员工作',
  `balance` int(11) unsigned DEFAULT '0' COMMENT '会员余额',
  `total_costs` int(11) unsigned DEFAULT '0' COMMENT '总消费',
  KEY `fk_vip_guest` (`guest_id`),
  CONSTRAINT `fk_vip_guest` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of vip
-- ----------------------------
