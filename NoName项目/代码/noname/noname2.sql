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

/*Data for the table `admin_admin` */

insert  into `admin_admin`(`ad_id`,`ad_username`,`ad_password`,`ad_role_id`,`ad_status`,`ad_creat_time`,`ad_update_time`,`ad_phone`,`ad_email`) values (1,'admin','123456',1,1,'2020-07-30 17:10:05','2020-08-10 12:40:20','16611110725','314817101@sina.com'),(2,'guest','123456',2,1,'2020-07-30 09:10:47','2020-08-10 12:40:58','16683800000','314817101@qq.com');

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

/*Data for the table `admin_role` */

insert  into `admin_role`(`role_id`,`role_name`,`role_power`,`role_describe`,`role_status`,`rule_perms`) values (1,'超级管理员','可查看和编辑','至高无上的权利',1,'admin:all'),(2,'普通管理员','只能查看不能编辑','查看信息的权力',1,'admin:see'),(3,'超级管理员','可查看和编辑','至高无上的权利',0,'admin:all'),(4,'普通管理员','只能查看不能编辑','查看信息的权力',0,'admin:see');

/*Table structure for table `log_log` */

DROP TABLE IF EXISTS `log_log`;

CREATE TABLE `log_log` (
  `l_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `l_content` varchar(255) NOT NULL DEFAULT 'null' COMMENT '内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `log_log` */

insert  into `log_log`(`l_id`,`l_content`,`create_time`) values (22,'完成更新日志模块的添加和显示','2020-07-30 07:50:57'),(23,'添加完成-角色权限管理-该模块只展示两种角色的权限、不同的管理员只能在这两种角色权限中选择一种','2020-07-30 08:47:25'),(24,'管理员列表的编辑功能完成','2020-07-31 07:40:51'),(25,'对管理员列表的状态启用和停用使用动态SQL判断后执行修改、对删除管理员采用的是将数据从数据库中删除、而不是逻辑删除','2020-07-31 08:41:56'),(26,'对管理员列表的添加完成、（对用户名是否已存在做验证、计划根据输入的数据使用动态sql来添加到数据库、但下载模板自带JS会验证输入是否为空、添加完成后需要手动刷新）','2020-07-31 10:53:15'),(27,'修复管理员添加bug、对sql语句进行了修改','2020-07-31 17:48:00'),(28,'完成管理员列表选择搜索功能、解决了三个条件为空和在get请求下参数的值不为null和修改了跳转页面、使用 forward 不经过视图解析器进行请求转发。','2020-07-31 18:49:29'),(29,'完成登录、通过使用 Shiro 执行对页面访问的认证和部分功能需要授权才可以使用、这是对Shiro的第二次使用、原理只懂得些皮毛、关于对功能实施授权准备结合Thymeleaf对功能隐藏和现实、然后在Shiro配置类中也添加对应的授权规则。','2020-08-02 20:41:47'),(30,'补充、对用户名错误和密码错误采用了明确的提示、重定向到主页面后显示用户名、也完成了用户退出访问需要认证的页面需要进行登录验证。','2020-08-02 20:44:50'),(31,'完成对、更新日志的添加日志功能和管理员列表的添加、修改状态、删除、编辑信息需要验证权限、','2020-08-04 06:24:26'),(32,'完成了订单表和用户表的设计、和一个订单一个用户一对一的分步查询取消懒加载测试、一个用户一对多分步查询启用懒加载获取数据的测试。重温了很多忘掉的知识。','2020-08-04 12:04:17'),(33,'完成会员列表的展示、之前采用的分布查询由于没有查询订单信息、所以就没有发送 sql 语句、测试成功、达到目的','2020-08-04 16:39:34'),(34,'完成会员列表中的 状态的修改、编辑信息采用可选编辑、修改密码、(  暂时不考虑删除、因为删除的 id 作为 订单表 某个字段的外键，如果要删除那么应该先删除相关 id 的订单再删除用户  ) ','2020-08-04 19:30:47'),(35,'完成了会员列表中的根据用户名查找出一个唯一用户、和添加用户','2020-08-05 13:59:01'),(36,'修改了根据不同的权限对管理员列表密码是否可以查看、对会员列表的某些功能添加了权限验证、（未补全删除功能是否需要验证、原因：还没写删除功能）','2020-08-05 14:50:50'),(37,'完成订单管理项的添加、通过权限删除、和订单列表的展示、订单的添加放在了会员列表里面、通过会员添加订单直接关联两者的关系','2020-08-06 09:26:10'),(38,'所有模块的基本功能完成、现在准备加入 Redis 对 添加、删除、查询 做缓存。 ','2020-08-06 09:31:52'),(39,'完成了使用 Redis 对会员列表的 搜索、添加、编辑、后的数据 都将放入缓存。','2020-08-07 16:51:23'),(40,'完成从会员模块添加订单、将订单数据添加到数据库并且添加到缓存、删除订单（删除数据库的订单数据、然后删除缓存中对应的订单数据），和查询订单缓存的问题。','2020-08-07 18:21:53'),(41,'未完成功能-------------------------系统状态-----------------统计页面','2020-08-07 18:24:54'),(42,'添加 Druid 作为数据库的连接池','2020-08-08 06:36:09'),(43,'关于我的桌面模块的数据、设计数据不时时更新、因为每次更新都要发送四条sql，所以将这个数据缓存到 redis 展示数据的时候从redis缓存中读取、当更新是、获取最新数据然后更新缓存、展示依旧是从缓存中读取。','2020-08-08 08:38:45'),(44,'对整体进行小调整、分页功能待开发','2020-08-08 09:05:52'),(45,'为管理员列表、订单列表、会员列表添加了分页功能','2020-08-10 12:46:18');

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

/*Data for the table `order_orders` */

insert  into `order_orders`(`o_id`,`o_oder_number`,`o_consignee`,`o_amount`,`o_amount_payable`,`o_order_status`,`o_delivery_status`,`o_payment_method`,`o_distribution_mode`,`o_order_creat_time`,`o_vip_id`) values (11,'1292792596674121728','none:+8612345678900','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:09',1),(12,'1292792620631986176','none:+8612345678900','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:14',1),(13,'1292792643071512576','none:+8612345678900','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:20',1),(14,'1292792698780258304','bbb:+8612345678900','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:33',2),(15,'1292792718715785216','none:16683800725','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:38',5),(16,'1292792741339860992','none:+8612345678900','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:43',7),(17,'1292792775385026560','none:16683800725','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 11:59:51',5),(18,'1292793321986723840','none:+8612345678900','555.00','555.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:02:01',1),(19,'1292796479454580736','none:+8612345678900','55.00','55.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:14:34',1),(20,'1292798203930087424','none:+8612345678900','456.00','456.00','已支付','未发货','支付宝','顺丰物流','2020-08-10 12:21:25',10),(21,'1292802163986403328','张三:+8612345678900','54541.00','54541.00','已支付','未发货','货到付款','顺丰物流','2020-08-10 12:37:10',1),(22,'1292804563447058432','张三:+8612345678900','545.00','545.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:46:42',1),(23,'1292804612050653184','张三:+8612345678900','45.00','45.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:46:53',1),(24,'1292804910261473280','wfx:+8612345678900','445.00','445.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:48:04',2),(25,'1292805004427792384','李小二:16683800725','5.00','5.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:48:27',5),(26,'1292805054784606208','wfx:+8612345678900','5.00','5.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:48:39',2),(27,'1292805087491788800','李小二:16683800725','454.00','455.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:48:47',5),(28,'1292805192932397056','ems:+8612345678900','55.00','55.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:49:12',6),(29,'1292805243645726720','ems:+8612345678900','55.50','55.50','未支付','已发货','支付宝','申通物流','2020-08-10 12:49:24',6),(30,'1292805290772926464','李小二:16683800725','545.00','545.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:49:35',5),(31,'1292805346938851328','wfx:+8612345678900','45.00','45.00','未支付','已发货','支付宝','申通物流','2020-08-10 12:49:48',2);

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

/*Data for the table `vip_vips` */

insert  into `vip_vips`(`v_id`,`v_username`,`v_password`,`v_gender`,`v_phone`,`v_email`,`v_creat_time`,`v_update_time`,`v_status`,`v_name`) values (1,'314817101','111111',0,'+8612345678900','314817101@qq.com','2020-08-04 07:19:21','2020-08-10 12:36:56',1,'张三'),(2,'123456789','123456',0,'+8612345678900','314817101@qq.com','2020-08-04 07:19:43','2020-08-10 12:47:54',1,'wfx'),(3,'3148171011','123456',0,'+8612345678900','314817101@qq.com','2020-08-05 13:27:18','2020-08-07 16:21:06',1,'none'),(5,'555','123456',1,'16683800725','314817101@qq.com','2020-08-07 16:09:18','2020-08-10 12:48:21',1,'李小二'),(6,'10000001','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:05:58','2020-08-10 12:49:04',1,'/ems'),(7,'10000002','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:06:46','2020-08-10 11:06:46',1,'none'),(8,'10000003','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:08:28','2020-08-10 11:08:28',1,'none'),(9,'10000005','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:08:37','2020-08-10 11:08:37',1,'none'),(10,'10000004','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:08:45','2020-08-10 11:08:45',1,'none'),(11,'10000008','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:08:58','2020-08-10 11:08:58',1,'none'),(12,'10001000','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:11:43','2020-08-10 12:03:57',1,'none'),(13,'456456456','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 11:52:46','2020-08-10 11:52:46',1,'none'),(14,'314817101444','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 12:14:52','2020-08-10 12:14:52',1,'none'),(15,'315451561','123456',0,'+8612345678900','314817101@qq.com','2020-08-10 12:37:38','2020-08-10 12:37:38',1,'里斯');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
