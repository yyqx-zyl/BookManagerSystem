/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.22-log : Database - system_book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`system_book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `system_book`;

/*Table structure for table `system_book_category` */

DROP TABLE IF EXISTS `system_book_category`;

CREATE TABLE `system_book_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(32) NOT NULL COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `system_book_category` */

insert  into `system_book_category`(`id`,`category`) values (2,'历史'),(17,'国际'),(5,'旅游'),(6,'恐怖小说'),(7,'文学');

/*Table structure for table `system_book_info` */

DROP TABLE IF EXISTS `system_book_info`;

CREATE TABLE `system_book_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `bookName` varchar(128) DEFAULT NULL COMMENT '图书名字',
  `author` varchar(128) DEFAULT NULL COMMENT '作者',
  `categoryId` int(11) DEFAULT NULL COMMENT '图书分类',
  `publisher` varchar(128) DEFAULT NULL COMMENT '图书出版社',
  `price` double DEFAULT NULL COMMENT '价格',
  `photo` varchar(128) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `system_book_info` */

insert  into `system_book_info`(`id`,`bookName`,`author`,`categoryId`,`publisher`,`price`,`photo`) values (13,'大豆腐干豆腐','梵蒂冈地方是大法官',2,'梵蒂冈地方法规是大法官',16,'fgdf.jpg'),(7,'非洲的旋律','李艳玲',5,'金城出版社',128,'fzdxl.jpg'),(8,'尸鬼','（日）小野不由美 ',6,'吉林出版集团',17,'sg.jpg'),(9,'红楼小讲','周汝昌 ',7,'北京出版社',10,'hlxj.jpg'),(10,'三国演义','吴承恩',2,'白马时光出版社',25,'fzdxl.jpg'),(11,'红楼梦','曹雪芹',2,'百花洲出版社',30,'hlxj.jpg');

/*Table structure for table `system_book_orders` */

DROP TABLE IF EXISTS `system_book_orders`;

CREATE TABLE `system_book_orders` (
  `oid` varchar(32) NOT NULL COMMENT '订单编号',
  `bid` int(11) DEFAULT NULL COMMENT '图书编号',
  `count` double DEFAULT NULL COMMENT '数量',
  `curPrice` double DEFAULT NULL COMMENT '总价',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日期',
  `userId` varchar(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`oid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `system_book_orders` */

insert  into `system_book_orders`(`oid`,`bid`,`count`,`curPrice`,`date`,`userId`) values ('20200413114851315217',7,1,128,'2020-04-13 11:48:51','123'),('20200413120850702482',8,1,17,'2020-04-13 12:08:51','123'),('20200413120854111329',9,1,10,'2020-04-13 12:08:55','123'),('20200413130338501561',10,1,25,'2020-04-13 15:40:01','zangshan'),('20200413133427235263',8,1,17,'2020-04-13 16:06:13','zangshan'),('20200413142922645054',7,1,128,'2020-04-13 14:29:23','123'),('20200413152306589001',7,1,128,'2020-04-13 16:06:19','zangshan'),('20200413152929662743',7,1,128,'2020-04-13 16:06:22','zangshan');

/*Table structure for table `system_book_user` */

DROP TABLE IF EXISTS `system_book_user`;

CREATE TABLE `system_book_user` (
  `userId` varchar(64) NOT NULL COMMENT '账户',
  `userPsw` varchar(64) NOT NULL COMMENT '密码',
  `userName` varchar(128) DEFAULT NULL COMMENT '姓名',
  `role` int(11) DEFAULT '1' COMMENT '角色',
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `system_book_user` */

insert  into `system_book_user`(`userId`,`userPsw`,`userName`,`role`) values ('7e1c02568f3d1bbf9a5e889aebb86b52','202cb962ac59075b964b07152d234b70','zangshan',1),('74fa9dfa33ee9cfc2f870a2f8052bff0','202cb962ac59075b964b07152d234b70','123',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
