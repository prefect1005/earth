-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: earth
-- ------------------------------------------------------
-- Server version	5.7.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category_second`
--

DROP TABLE IF EXISTS `category_second`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `active` int(11) NOT NULL COMMENT '0不可用1可用',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_second`
--

LOCK TABLES `category_second` WRITE;
/*!40000 ALTER TABLE `category_second` DISABLE KEYS */;
INSERT INTO `category_second` VALUES (1,'Logo设计',1,'2016-08-11 22:59:31'),(2,'VI设计',1,'2016-08-11 22:59:31'),(3,'包装设计',1,'2016-08-11 22:59:31'),(4,'企业服装设计',1,'2016-08-11 22:59:31'),(5,'装修设计',1,'2016-08-11 22:59:31'),(16,'域名注册',1,'2016-08-11 22:59:31'),(17,'网站UI设计',1,'2016-08-11 22:59:31'),(18,'CRM系统开发',1,'2016-08-11 22:59:31'),(19,'网站建设',1,'2016-08-11 22:59:31'),(20,'网站交互设计',1,'2016-08-11 22:59:31'),(21,'数据库架构设计',1,'2016-08-11 22:59:31'),(22,'APP前端设计',1,'2016-08-11 22:59:31'),(23,'APP开发',1,'2016-08-11 22:59:31'),(24,'微信微网站建设',1,'2016-08-11 22:59:31'),(25,'微信公众号开发',1,'2016-08-11 22:59:31'),(26,'拟定合同',1,'2016-08-11 22:59:31'),(27,'商务咨询',1,'2016-08-11 22:59:31'),(28,'涉税鉴证',1,'2016-08-11 22:59:31'),(29,'税务筹划',1,'2016-08-11 22:59:31'),(30,'税务代理',1,'2016-08-11 22:59:31'),(31,'代办税务登记证',1,'2016-08-11 22:59:31'),(32,'地铁广告',1,'2016-08-11 22:59:31'),(33,'公交广告',1,'2016-08-11 22:59:31'),(34,'路牌广告',1,'2016-08-11 22:59:31'),(35,'微博大号推广',1,'2016-08-11 22:59:31'),(36,'百度推广',1,'2016-08-11 22:59:31'),(37,'网红广告',1,'2016-08-11 22:59:31'),(38,'公司注册',1,'2016-08-11 22:59:31'),(39,'住址变更',1,'2016-08-11 22:59:31');
/*!40000 ALTER TABLE `category_second` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-21 23:31:39
