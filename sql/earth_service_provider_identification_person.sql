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
-- Table structure for table `service_provider_identification_person`
--

DROP TABLE IF EXISTS `service_provider_identification_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_provider_identification_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `id_card_number` varchar(100) NOT NULL COMMENT '身份证号',
  `bank_card_id` varchar(100) NOT NULL COMMENT '银行卡号',
  `phone_number` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `img_src_list` varchar(500) NOT NULL,
  `store_display_img_src` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='服务商个人实名认证';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_identification_person`
--

LOCK TABLES `service_provider_identification_person` WRITE;
/*!40000 ALTER TABLE `service_provider_identification_person` DISABLE KEYS */;
INSERT INTO `service_provider_identification_person` VALUES (2,3,'孙附近','370987654565543552345','324767373839467754455443','18614087661','sun@163.com','IMG_20150913_162501_9Mnf3CxxaU.jpg,IMG_20150913_162507_mQP2FPi5xX.jpg',NULL,'2016-08-15 22:13:39','2016-08-15 22:13:39'),(4,4,'赵小妞','3453253253453532','45654天5453453','18210673513','','IMG_20150626_075142_CfcW7lv737.jpg,IMG_20150626_101459_us5WypLy8s.jpg','IMG_20150626_170449_IfRKggBnxN.jpg','2016-08-20 13:11:29','2016-08-20 13:11:29');
/*!40000 ALTER TABLE `service_provider_identification_person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-21 23:31:41
