/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - expenditure
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`expenditure` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `expenditure`;

/*Table structure for table `epdt_acc_ffy` */

DROP TABLE IF EXISTS `epdt_acc_ffy`;

CREATE TABLE `epdt_acc_ffy` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `SUM` double DEFAULT NULL,
  `USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_jtltu2085xmwarg2q1n0y1mcm` (`USER_ID`),
  CONSTRAINT `FK_jtltu2085xmwarg2q1n0y1mcm` FOREIGN KEY (`USER_ID`) REFERENCES `epdt_id_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `epdt_acc_ffy` */

insert  into `epdt_acc_ffy`(`ID`,`PASSWORD`,`REMARK`,`SUM`,`USER_ID`) values (1,'827ccb0eea8a706c4c34a16891f84e7b',NULL,210,'admin'),(2,'827ccb0eea8a706c4c34a16891f84e7b',NULL,0,'baibai'),(3,'827ccb0eea8a706c4c34a16891f84e7b',NULL,0,'hu0hu');

/*Table structure for table `epdt_acc_record` */

DROP TABLE IF EXISTS `epdt_acc_record`;

CREATE TABLE `epdt_acc_record` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(500) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `TRAN_DATE` datetime DEFAULT NULL,
  `FFY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_kjiirhjwtudwya5sb7wanoqbn` (`FFY_ID`),
  CONSTRAINT `FK_kjiirhjwtudwya5sb7wanoqbn` FOREIGN KEY (`FFY_ID`) REFERENCES `epdt_acc_ffy` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `epdt_acc_record` */

insert  into `epdt_acc_record`(`ID`,`CONTENT`,`REMARK`,`TRAN_DATE`,`FFY_ID`) values (1,'测试','测试','2017-02-28 23:44:29',1),(2,'测试2','测试2','2017-02-28 23:44:20',2),(3,'测试','测试','2017-02-24 23:44:40',1),(4,'总额：￥0.0 微信 充值：￥10.0 余额：￥10.0','','2017-03-30 13:35:24',1),(5,'总额：￥10.0 支付宝 充值：￥200.0 余额：￥210.0','','2017-03-30 13:36:02',1),(6,'总额：￥210.0 电费 支出：￥6.0 余额：￥210.0','','2017-04-04 09:05:34',1),(7,'总额：￥204.0 admin -- 何风 收入 -- 电费 -- 英德市九龙镇供电局：￥6.0 余额：￥210.0','','2017-04-04 09:05:34',1),(8,'总额：￥210.0 水费 支出：￥100.0 余额：￥210.0','','2017-04-04 09:35:39',1),(9,'总额：￥110.0 admin -- 何风 收入 -- 水费 -- 英德市九龙镇水务：￥100.0 余额：￥210.0','','2017-04-04 09:35:39',1);

/*Table structure for table `epdt_bus_bill` */

DROP TABLE IF EXISTS `epdt_bus_bill`;

CREATE TABLE `epdt_bus_bill` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APPEAR_DATE` datetime DEFAULT NULL,
  `CHECK_DATE` datetime DEFAULT NULL,
  `COST_STYLE` varchar(50) DEFAULT NULL,
  `HANDLE_DATE` datetime DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `SUM_PRICE` double DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `USAGE_AMOUNT` double DEFAULT NULL,
  `CHECKER` varchar(50) DEFAULT NULL,
  `COMPANY_ID` bigint(20) DEFAULT NULL,
  `PERTAIN` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_BILL_CHECKER` (`CHECKER`),
  KEY `FK_BILL_COMPANY` (`COMPANY_ID`),
  KEY `FK_BILL_PERTAIN` (`PERTAIN`),
  CONSTRAINT `FK_BILL_CHECKER` FOREIGN KEY (`CHECKER`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_BILL_COMPANY` FOREIGN KEY (`COMPANY_ID`) REFERENCES `epdt_bus_company` (`ID`),
  CONSTRAINT `FK_BILL_PERTAIN` FOREIGN KEY (`PERTAIN`) REFERENCES `epdt_id_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `epdt_bus_bill` */

insert  into `epdt_bus_bill`(`ID`,`APPEAR_DATE`,`CHECK_DATE`,`COST_STYLE`,`HANDLE_DATE`,`REMARK`,`SUM_PRICE`,`TYPE`,`USAGE_AMOUNT`,`CHECKER`,`COMPANY_ID`,`PERTAIN`) values (1,'2017-03-16 00:48:47',NULL,'付费易','2017-04-04 09:05:34',NULL,6,1,10,NULL,1,'admin'),(2,'2017-03-24 02:09:04',NULL,NULL,NULL,NULL,75,2,15,NULL,3,'hu0hu'),(3,'2017-03-24 13:33:13',NULL,'付费易','2017-04-04 09:35:39',NULL,100,1,20,NULL,3,'admin');

/*Table structure for table `epdt_bus_company` */

DROP TABLE IF EXISTS `epdt_bus_company`;

CREATE TABLE `epdt_bus_company` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `epdt_bus_company` */

insert  into `epdt_bus_company`(`ID`,`NAME`,`PRICE`,`TYPE`) values (1,'英德市九龙镇供电局',0.6,'电费'),(2,'英德市青塘镇供电局',0.6,'电费'),(3,'英德市九龙镇水务',5,'水费');

/*Table structure for table `epdt_id_module` */

DROP TABLE IF EXISTS `epdt_id_module`;

CREATE TABLE `epdt_id_module` (
  `CODE` varchar(100) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `MODIFY_DATE` datetime DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `CREATER` varchar(50) DEFAULT NULL,
  `MODIFIER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODE`),
  KEY `FK_MODULE_CREATER` (`CREATER`),
  KEY `FK_MODULE_MODIFIER` (`MODIFIER`),
  CONSTRAINT `FK_MODULE_CREATER` FOREIGN KEY (`CREATER`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_MODULE_MODIFIER` FOREIGN KEY (`MODIFIER`) REFERENCES `epdt_id_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `epdt_id_module` */

insert  into `epdt_id_module`(`CODE`,`CREATE_DATE`,`MODIFY_DATE`,`NAME`,`REMARK`,`URL`,`CREATER`,`MODIFIER`) values ('0001','2017-02-21 21:53:19',NULL,'系统管理','系统管理模块','#',NULL,NULL),('00010001','2017-02-23 22:35:19',NULL,'----用户管理','用户管理','/admin/identity/selectUser.jspx',NULL,NULL),('000100010001','2017-02-23 22:54:43','2017-02-28 22:55:07','--------添加操作','添加用户的方法','/admin/identity/addUser.jspx',NULL,NULL),('000100010002','2017-02-27 22:13:46','2017-02-28 22:55:21','--------修改操作','修改用户的方法\r\n','/admin/identity/updateUser.jspx',NULL,NULL),('000100010003','2017-02-27 22:14:34','2017-02-28 22:55:29','--------删除操作','批量删除用户的方法','/admin/identity/deleteUser.jspx',NULL,NULL),('000100010004','2017-02-27 22:15:30','2017-02-28 22:55:45','--------审核操作','批量审核用户的方法','/admin/identity/checkUser.jspx',NULL,NULL),('000100010005','2017-03-02 00:21:49',NULL,'--------查询操作','查询用户的方法','/admin/identity/selectUser.jspx','zhouxx',NULL),('00010002','2017-02-23 22:37:28',NULL,'----角色管理','角色管理','/admin/identity/selectRole.jspx',NULL,NULL),('000100020001','2017-02-27 22:17:46','2017-04-05 12:21:46','--------添加操作','添加角色的方法','/admin/identity/addRole.jspx',NULL,'zhouxx'),('000100020002','2017-02-27 22:18:12','2017-02-28 22:56:08','--------修改操作','修改角色的方法','/admin/identity/updateRole.jspx',NULL,NULL),('000100020003','2017-02-27 22:18:49','2017-02-28 22:56:16','--------删除操作','删除角色的方法','/admin/identity/deleteRole.jspx',NULL,NULL),('000100020004','2017-03-02 00:39:35',NULL,'--------查询操作','查询角色的方法','/admin/identity/selectRole.jspx','zhouxx',NULL),('00010003','2017-02-23 22:38:37','2017-02-23 22:39:10','----操作管理','操作管理','/admin/identity/mgrModule.jspx',NULL,NULL),('000100030001','2017-02-27 22:20:02',NULL,'--------添加操作','添加操的方法','/admin/identity/addModule.jspx',NULL,NULL),('000100030002','2017-02-27 22:20:37',NULL,'--------修改操作','修改操作的方法','/admin/identity/updateModule.jspx',NULL,NULL),('000100030003','2017-02-27 22:21:06',NULL,'--------删除操作','删除操作的方法','/admin/identity/deleteModule.jspx',NULL,NULL),('000100030004','2017-03-02 23:32:10',NULL,'--------查询操作','查看操作管理的方法','/admin/identity/mgrModule.jspx','zhouxx',NULL),('0002','2017-03-04 16:45:21',NULL,'付费易管理','付费易帐号模块','#','zhouxx',NULL),('00020001','2017-03-04 16:46:51',NULL,'----帐号管理','帐号管理','/admin/account/countFFY.jspx','zhouxx',NULL),('000200010001','2017-03-04 16:49:07',NULL,'--------查询操作','异步加载帐号信息','/admin/account/loadFFYAjax.jspx','zhouxx',NULL),('00020002','2017-03-04 16:51:06',NULL,'----交易记录','交易记录管理','/admin/account/countRecord.jspx','zhouxx',NULL),('000200020001','2017-03-04 16:52:10',NULL,'--------查询操作','异步按条件加载交易记录','/admin/account/loadRecordAjax.jspx','zhouxx',NULL),('0003','2017-03-05 13:47:58',NULL,'业务管理','管理出帐机构与帐单','#','zhouxx',NULL),('00030001','2017-03-05 13:50:17','2017-03-05 13:53:07','----机构管理','出帐机构管理','/admin/business/countCompany.jspx','zhouxx','zhouxx'),('000300010001','2017-03-05 13:52:45',NULL,'--------查询操作','查询出帐机构','/admin/business/countCompany.jspx','zhouxx',NULL),('000300010002','2017-03-05 13:54:13',NULL,'--------添加操作','添加出帐机构','/admin/business/addCompany.jspx','zhouxx',NULL),('000300010003','2017-03-05 13:55:34',NULL,'--------修改操作','修改出帐机构','/admin/business/updateCompany.jspx','zhouxx',NULL),('00030002','2017-03-05 13:51:32','2017-03-05 13:53:25','----帐单管理','帐单管理','/admin/business/countBill.jspx','zhouxx','zhouxx'),('000300020001','2017-03-05 13:56:19',NULL,'--------查询操作','查询帐单','/admin/business/countBill.jspx','zhouxx',NULL),('000300020002','2017-03-05 13:57:36',NULL,'--------修改操作','修改帐单','/admin/business/updateBill.jspx','zhouxx',NULL),('000300020003','2017-03-06 00:38:46',NULL,'--------添加操作','添加帐单','/admin/business/addBill.jspx','zhouxx',NULL);

/*Table structure for table `epdt_id_popedom` */

DROP TABLE IF EXISTS `epdt_id_popedom`;

CREATE TABLE `epdt_id_popedom` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATER` varchar(50) DEFAULT NULL,
  `MODULE_CODE` varchar(100) DEFAULT NULL,
  `OPERA_CODE` varchar(100) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POPEDOM_CREATER` (`CREATER`),
  KEY `FK_POPEDOM_MODULE` (`MODULE_CODE`),
  KEY `FK_POPEDOM_OPERA` (`OPERA_CODE`),
  KEY `FK_POPEDOM_ROLE` (`ROLE_ID`),
  CONSTRAINT `FK_POPEDOM_CREATER` FOREIGN KEY (`CREATER`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_POPEDOM_MODULE` FOREIGN KEY (`MODULE_CODE`) REFERENCES `epdt_id_module` (`CODE`),
  CONSTRAINT `FK_POPEDOM_OPERA` FOREIGN KEY (`OPERA_CODE`) REFERENCES `epdt_id_module` (`CODE`),
  CONSTRAINT `FK_POPEDOM_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `epdt_id_role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `epdt_id_popedom` */

insert  into `epdt_id_popedom`(`ID`,`CREATE_DATE`,`CREATER`,`MODULE_CODE`,`OPERA_CODE`,`ROLE_ID`) values (18,'2017-03-02 00:25:16','zhouxx','00010001','000100010001',1),(19,'2017-03-02 00:25:16','zhouxx','00010001','000100010002',1),(20,'2017-03-02 00:25:16','zhouxx','00010001','000100010003',1),(21,'2017-03-02 00:25:16','zhouxx','00010001','000100010004',1),(22,'2017-03-02 00:25:16','zhouxx','00010001','000100010005',1),(23,'2017-03-04 16:53:14','zhouxx','00020001','000200010001',1),(24,'2017-03-04 16:53:17','zhouxx','00020002','000200020001',1),(25,'2017-03-05 13:58:01','zhouxx','00030001','000300010001',1),(26,'2017-03-05 13:58:01','zhouxx','00030001','000300010002',1),(27,'2017-03-05 13:58:01','zhouxx','00030001','000300010003',1),(30,'2017-03-06 00:41:22','zhouxx','00030002','000300020001',1),(31,'2017-03-06 00:41:22','zhouxx','00030002','000300020002',1),(32,'2017-03-06 00:41:22','zhouxx','00030002','000300020003',1),(33,'2017-03-22 23:40:13','zhouxx','00010002','000100020001',1),(34,'2017-03-22 23:40:13','zhouxx','00010002','000100020002',1),(35,'2017-03-22 23:40:13','zhouxx','00010002','000100020003',1),(36,'2017-03-22 23:40:13','zhouxx','00010002','000100020004',1),(37,'2017-03-22 23:41:37','zhouxx','00010003','000100030001',1),(38,'2017-03-22 23:41:37','zhouxx','00010003','000100030002',1),(39,'2017-03-22 23:41:37','zhouxx','00010003','000100030003',1),(40,'2017-03-22 23:41:37','zhouxx','00010003','000100030004',1);

/*Table structure for table `epdt_id_role` */

DROP TABLE IF EXISTS `epdt_id_role`;

CREATE TABLE `epdt_id_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `MODIFY_DATE` datetime DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `CREATER` varchar(50) DEFAULT NULL,
  `MODIFIER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ROLE_CREATER` (`CREATER`),
  KEY `FK_ROLE_MODIFIER` (`MODIFIER`),
  CONSTRAINT `FK_ROLE_CREATER` FOREIGN KEY (`CREATER`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_ROLE_MODIFIER` FOREIGN KEY (`MODIFIER`) REFERENCES `epdt_id_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `epdt_id_role` */

insert  into `epdt_id_role`(`ID`,`CREATE_DATE`,`MODIFY_DATE`,`NAME`,`REMARK`,`CREATER`,`MODIFIER`) values (1,'2017-02-19 20:07:24','2017-02-19 22:35:19','系统管理员','用于管理后台系统的角色',NULL,NULL),(2,'2017-02-19 20:08:18',NULL,'普通用户','普通用户',NULL,NULL);

/*Table structure for table `epdt_id_user` */

DROP TABLE IF EXISTS `epdt_id_user`;

CREATE TABLE `epdt_id_user` (
  `USER_ID` varchar(50) NOT NULL,
  `ANSWER` varchar(200) DEFAULT NULL,
  `CHECK_DATE` datetime DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `MODIFY_DATE` datetime DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `PHONE` varchar(50) DEFAULT NULL,
  `QQ_NUM` varchar(50) DEFAULT NULL,
  `QUESTION` smallint(6) DEFAULT NULL,
  `SEX` smallint(6) DEFAULT NULL,
  `STATUS` smallint(6) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `CHECKER` varchar(50) DEFAULT NULL,
  `CREATER` varchar(50) DEFAULT NULL,
  `MODIFIER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FK_USER_CHECKER` (`CHECKER`),
  KEY `FK_USER_CREATER` (`CREATER`),
  KEY `FK_USER_MODIFIER` (`MODIFIER`),
  CONSTRAINT `FK_USER_CHECKER` FOREIGN KEY (`CHECKER`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_USER_CREATER` FOREIGN KEY (`CREATER`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_USER_MODIFIER` FOREIGN KEY (`MODIFIER`) REFERENCES `epdt_id_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `epdt_id_user` */

insert  into `epdt_id_user`(`USER_ID`,`ANSWER`,`CHECK_DATE`,`CREATE_DATE`,`EMAIL`,`MODIFY_DATE`,`NAME`,`PASSWORD`,`PHONE`,`QQ_NUM`,`QUESTION`,`SEX`,`STATUS`,`TEL`,`CHECKER`,`CREATER`,`MODIFIER`) values ('admin','二月','2017-04-04 16:35:56','2017-03-04 17:35:38','123123@123.com','2017-04-05 13:14:20','何风','e10adc3949ba59abbe56e057f20f883e','15912312222','531112223',0,1,1,'020-1231231','zhouxx',NULL,'zhouxx'),('baibai','二月',NULL,'2017-03-04 17:50:40','123123@123.com','2017-04-05 13:14:26','白摆','e10adc3949ba59abbe56e057f20f883e','15922222345','522223112',0,1,0,'020-1231231',NULL,NULL,'zhouxx'),('hu0hu','二月',NULL,'2017-03-04 17:52:17','539999@qq.com',NULL,'虎虎','e10adc3949ba59abbe56e057f20f883e','13612312229','545234234',0,1,0,'020-1231231',NULL,NULL,NULL),('lishi','二月','2017-02-11 23:39:11','2017-02-11 00:41:59','123123@qq.com','2017-04-05 13:14:15','李师','e10adc3949ba59abbe56e057f20f883e','15922211111','544444444',0,2,1,'020-1111111',NULL,NULL,'zhouxx'),('zhouxx','二月','2017-02-11 23:39:11','2017-02-10 23:52:33','123123@qq.com','2017-04-05 13:14:06','周星星','e10adc3949ba59abbe56e057f20f883e','15912222222','5555565555',0,1,1,'020-1113334',NULL,NULL,'zhouxx');

/*Table structure for table `epdt_id_user_role` */

DROP TABLE IF EXISTS `epdt_id_user_role`;

CREATE TABLE `epdt_id_user_role` (
  `ROLE_ID` bigint(20) NOT NULL,
  `USER_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`USER_ID`),
  KEY `FK_2f16kl0p9egt7o2y8tad7p78k` (`USER_ID`),
  CONSTRAINT `FK_2f16kl0p9egt7o2y8tad7p78k` FOREIGN KEY (`USER_ID`) REFERENCES `epdt_id_user` (`USER_ID`),
  CONSTRAINT `FK_m19ebo69t3866nbecbfys5mjo` FOREIGN KEY (`ROLE_ID`) REFERENCES `epdt_id_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `epdt_id_user_role` */

insert  into `epdt_id_user_role`(`ROLE_ID`,`USER_ID`) values (1,'admin'),(1,'zhouxx');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
