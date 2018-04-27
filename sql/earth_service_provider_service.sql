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
-- Table structure for table `service_provider_service`
--

DROP TABLE IF EXISTS `service_provider_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_provider_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `first_level` int(11) NOT NULL,
  `second_level` int(11) NOT NULL,
  `keyword` varchar(500) DEFAULT NULL,
  `one_introduction` varchar(500) DEFAULT NULL,
  `introduction_detail` text,
  `original_price` varchar(100) DEFAULT NULL,
  `group_price` varchar(100) DEFAULT NULL,
  `img_src_list` varchar(500) DEFAULT NULL,
  `online` int(11) DEFAULT '1' COMMENT '0下架，1在线',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_service`
--

LOCK TABLES `service_provider_service` WRITE;
/*!40000 ALTER TABLE `service_provider_service` DISABLE KEYS */;
INSERT INTO `service_provider_service` VALUES (2,3,'第一个服务',2,19,'第一个 首个','这是最棒的','这是最棒的这是最棒的这是最棒的这是最棒的这是最棒的这是最棒的这是最棒的这是最棒的','10000','8800','IMG_20150913_162501_KmfO3dHQ91.jpg,IMG_20150913_162501_1448109412686_tmHi5lMTYf.jpg,IMG_20150913_162507_OLLFWTlEr5.jpg,IMG_20150913_162507_1448109380083_uGNY8bSFKX.jpg',1,'2016-08-14 18:32:30','2016-08-14 18:32:30'),(3,3,'第二个服务',2,19,'很重要的服务','很好很棒的服务','很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务','20000','18900','IMG_20150913_162501_ZIZD7MHLeZ.jpg,IMG_20150913_162501_1448109412686_8NqaF5B1jR.jpg,IMG_20150913_162507_Ir6bhVd4Dg.jpg,IMG_20150913_162507_1448109380083_sx6LPW77CU.jpg,IMG_20150913_162501_1448109412686_yV3POaxhyL.jpg',1,'2016-08-14 19:07:12','2016-08-14 19:07:12'),(4,3,'第三个服务',2,19,'很好很棒的服务','很好很棒的服务很好很棒的服务','很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务很好很棒的服务','20000','18990','IMG_20150913_162501_YdAslFRQGt.jpg,IMG_20150913_162501_1448109412686_aurvBIWnkl.jpg,IMG_20150913_162507_eYVqwfBUNv.jpg,IMG_20150913_162507_1448109380083_eiZqEFRmY4.jpg',1,'2016-08-14 19:21:10','2016-08-14 19:21:10');
/*!40000 ALTER TABLE `service_provider_service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-21 23:31:40
