/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.19 : Database - noname
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`noname` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `noname`;

/*Table structure for table `admin_admin` */

DROP TABLE IF EXISTS `admin_admin`;

CREATE TABLE `admin_admin` (
  `ad_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `ad_username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录管理员名',
  `ad_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '登录密码',
  `ad_role_id` int NOT NULL DEFAULT '2' COMMENT '管理员所拥有的权限',
  `ad_status` int DEFAULT '1' COMMENT '管理员信息的逻辑删除',
  `ad_creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员创建的时间',
  `ad_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '管理员信息的更新时间',
  `ad_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '+8612345678900' COMMENT '管理员的手机号码',
  `ad_email` varchar(64) DEFAULT '314817101@qq.com' COMMENT '管理员的邮箱',
  PRIMARY KEY (`ad_id`),
  KEY `ad` (`ad_role_id`),
  CONSTRAINT `ad` FOREIGN KEY (`ad_role_id`) REFERENCES `admin_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `role_name` varchar(5) DEFAULT '普通管理员' COMMENT '管理员级别',
  `role_power` char(12) DEFAULT '只能查看不能编辑' COMMENT '管理员权限',
  `role_describe` char(40) DEFAULT '权力描述' COMMENT '权力描述',
  `role_status` bigint DEFAULT '0' COMMENT '是否使用中',
  `rule_perms` varbinary(64) DEFAULT 'admin:see' COMMENT 'shiro权限',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `log_log` */

DROP TABLE IF EXISTS `log_log`;

CREATE TABLE `log_log` (
  `l_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `l_content` varchar(255) NOT NULL DEFAULT 'null' COMMENT '内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Table structure for table `order_orders` */

DROP TABLE IF EXISTS `order_orders`;

CREATE TABLE `order_orders` (
  `o_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键自增',
  `o_oder_number` varchar(64) NOT NULL COMMENT '唯一订单号',
  `o_consignee` varchar(64) NOT NULL DEFAULT '姓名:12345678900' COMMENT '收货人和及其手机号码',
  `o_amount` decimal(12,2) DEFAULT NULL COMMENT '总金额',
  `o_amount_payable` decimal(12,2) DEFAULT NULL COMMENT '应付金额',
  `o_order_status` varchar(8) DEFAULT '支付状态' COMMENT '支付状态',
  `o_delivery_status` varchar(8) DEFAULT '发货状态' COMMENT '发货状态',
  `o_payment_method` varchar(12) DEFAULT '支付方式' COMMENT '支付方式',
  `o_distribution_mode` varchar(12) DEFAULT '顺丰等方式' COMMENT '配送快递',
  `o_order_creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `o_vip_id` int NOT NULL COMMENT '谁下的订单',
  PRIMARY KEY (`o_id`),
  KEY `order_vip_fk` (`o_vip_id`),
  CONSTRAINT `order_vip_fk` FOREIGN KEY (`o_vip_id`) REFERENCES `vip_vips` (`v_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Table structure for table `vip_vips` */

DROP TABLE IF EXISTS `vip_vips`;

CREATE TABLE `vip_vips` (
  `v_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `v_username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称',
  `v_password` varchar(64) DEFAULT '123456' COMMENT '密码',
  `v_gender` int DEFAULT '0' COMMENT '性别0:女性1:男性',
  `v_phone` varchar(16) DEFAULT '+8612345678900' COMMENT '手机号码',
  `v_email` varchar(64) DEFAULT '314817101@qq.com' COMMENT 'Email',
  `v_creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `v_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息发生改变的时间',
  `v_status` int DEFAULT '1' COMMENT '状态，1表示在用，0表示停用',
  `v_name` varchar(64) DEFAULT 'none' COMMENT '姓名',
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
