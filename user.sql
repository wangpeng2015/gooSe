/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.38 : Database - meifa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meifa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `meifa`;

/*Table structure for table `hair_customers` */

DROP TABLE IF EXISTS `hair_customers`;

CREATE TABLE `hair_customers` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '顾客主键',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '顾客姓名',
  `customer_password` varchar(50) DEFAULT NULL COMMENT '顾客密码用来提交订单的',
  `customer_code` varchar(50) DEFAULT NULL COMMENT '顾客编码',
  `customer_phoneNumber` varchar(20) DEFAULT NULL COMMENT '顾客电话',
  `customer_address` varchar(50) DEFAULT NULL COMMENT '顾客地址',
  `customer_money` decimal(20,2) DEFAULT '0.00' COMMENT '顾客金钱',
  `customer_isVip` varchar(10) DEFAULT '0' COMMENT '顾客vip等级',
  `customer_integral` decimal(20,0) DEFAULT '0' COMMENT '顾客积分',
  `customer_storeCode` varchar(50) DEFAULT NULL COMMENT '门店code',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `phoneNumber` (`customer_phoneNumber`),
  KEY `customer_storeCode` (`customer_storeCode`),
  CONSTRAINT `customer_storeCode` FOREIGN KEY (`customer_storeCode`) REFERENCES `hair_stores` (`store_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `hair_cuttype` */

DROP TABLE IF EXISTS `hair_cuttype`;

CREATE TABLE `hair_cuttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `hairType` varchar(20) DEFAULT NULL COMMENT '理发种类',
  `hairPrice` decimal(18,2) DEFAULT NULL COMMENT '非会员价格',
  `hairPriceVip` decimal(18,2) DEFAULT NULL COMMENT '会员价格',
  `discount` decimal(10,2) DEFAULT '0.80' COMMENT '默认会员是非会员的8折',
  `store_Code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hairtype_store` (`store_Code`),
  CONSTRAINT `hairtype_store` FOREIGN KEY (`store_Code`) REFERENCES `hair_stores` (`store_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hair_danju` */

DROP TABLE IF EXISTS `hair_danju`;

CREATE TABLE `hair_danju` (
  `danju_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `danju_code` varchar(50) DEFAULT NULL COMMENT '单据的单号',
  `danju_name` varchar(20) DEFAULT NULL COMMENT '单据名称',
  `danju_message` varchar(100) DEFAULT NULL COMMENT '单据信息备注',
  `danju_price` decimal(20,2) DEFAULT '0.00' COMMENT '单据价格',
  `danju_applicant` varchar(20) DEFAULT NULL COMMENT '申请人',
  `danju_applicant_id` bigint(20) DEFAULT NULL COMMENT '申请人id',
  `danju_deal` varchar(20) DEFAULT NULL COMMENT '处理人',
  `danju_deal_code` varchar(20) DEFAULT NULL COMMENT '处理人编码',
  `danju_isConfirm` varchar(10) DEFAULT '进行中' COMMENT '是否完成',
  `danju_invalid` varchar(20) DEFAULT '0' COMMENT '单据是否作废 0正常，1作废',
  `danju_isVip` varchar(10) DEFAULT '0' COMMENT '是否是会员 0非会员1会员',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `customer_phone` varchar(20) DEFAULT NULL COMMENT '会员的电话',
  `danju_storesCode` varchar(50) DEFAULT NULL COMMENT '单据所属的店的code',
  `danju_createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '单据产生时间',
  `danju_dealTime` timestamp NULL DEFAULT NULL COMMENT '单据处理时间',
  PRIMARY KEY (`danju_id`),
  KEY `danju_store` (`danju_storesCode`),
  CONSTRAINT `danju_store` FOREIGN KEY (`danju_storesCode`) REFERENCES `hair_stores` (`store_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `hair_employee` */

DROP TABLE IF EXISTS `hair_employee`;

CREATE TABLE `hair_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userCode` varchar(50) DEFAULT NULL COMMENT '员工编号',
  `userName` varchar(20) DEFAULT NULL COMMENT '员工姓名',
  `passWord` varchar(100) DEFAULT NULL COMMENT '密码',
  `roles` varchar(20) DEFAULT '1' COMMENT '1代表店员  2代表店长',
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮件',
  `phoneNumber` varchar(20) DEFAULT NULL COMMENT '电话',
  `realMoney` int(20) DEFAULT '0' COMMENT '金额',
  `integral` int(20) DEFAULT '0' COMMENT '积分',
  `salary` decimal(18,2) DEFAULT '0.00' COMMENT '工资',
  `jixiao` decimal(18,2) DEFAULT '0.00' COMMENT '绩效',
  `storeCode` varchar(50) DEFAULT NULL COMMENT '门店的code',
  `storeName` varchar(20) DEFAULT NULL COMMENT '门店名称',
  `idCard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`),
  KEY `user_storeCode` (`storeCode`),
  CONSTRAINT `user_storeCode` FOREIGN KEY (`storeCode`) REFERENCES `hair_stores` (`store_code`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `hair_employee_dan` */

DROP TABLE IF EXISTS `hair_employee_dan`;

CREATE TABLE `hair_employee_dan` (
  `empDan_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL COMMENT '店员的id',
  `employee_danjuCode` varchar(50) DEFAULT NULL COMMENT '单据的code',
  `employee_money` decimal(20,2) DEFAULT NULL COMMENT '产生金额',
  `employee_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '产生时间',
  PRIMARY KEY (`empDan_id`),
  KEY `employee_danju` (`employee_danjuCode`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `hair_store_income` */

DROP TABLE IF EXISTS `hair_store_income`;

CREATE TABLE `hair_store_income` (
  `storeincomeid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_code` varchar(50) DEFAULT NULL COMMENT '门店编码',
  `store_actMoney` decimal(20,2) DEFAULT '0.00' COMMENT '所有充值的钱和非会员消费的钱',
  `store_actOrderMoney` decimal(20,2) DEFAULT '0.00' COMMENT '门店所有订单的钱除去充值',
  `store_income_day` decimal(20,2) DEFAULT '0.00' COMMENT '门店日营业额',
  `store_income_month` decimal(20,2) DEFAULT '0.00' COMMENT '门店月营业额',
  `store_income_year` decimal(20,2) DEFAULT '0.00' COMMENT '门店年营业额',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`storeincomeid`),
  KEY `income` (`store_code`),
  CONSTRAINT `income` FOREIGN KEY (`store_code`) REFERENCES `hair_stores` (`store_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `hair_stores` */

DROP TABLE IF EXISTS `hair_stores`;

CREATE TABLE `hair_stores` (
  `store_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `store_code` varchar(50) NOT NULL COMMENT '门店编码',
  `store_name` varchar(20) DEFAULT NULL COMMENT '门店名称',
  `store_phone` varchar(20) DEFAULT NULL COMMENT '门店电话',
  `store_address` varchar(50) DEFAULT NULL COMMENT '门店地址',
  `store_createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '门店产生时间',
  `store_updateTime` timestamp NULL DEFAULT NULL COMMENT '门店修改时间',
  PRIMARY KEY (`store_code`),
  UNIQUE KEY `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
