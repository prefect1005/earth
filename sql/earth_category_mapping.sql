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
-- Table structure for table `category_mapping`
--

DROP TABLE IF EXISTS `category_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_first_id` int(11) NOT NULL,
  `category_second_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_mapping`
--

LOCK TABLES `category_mapping` WRITE;
/*!40000 ALTER TABLE `category_mapping` DISABLE KEYS */;
INSERT INTO `category_mapping` VALUES (1,1,1,'2016-08-11 22:59:31'),(2,1,2,'2016-08-11 22:59:31'),(3,1,3,'2016-08-11 22:59:31'),(4,1,4,'2016-08-11 22:59:31'),(5,1,5,'2016-08-11 22:59:31'),(6,2,16,'2016-08-11 22:59:31'),(7,2,17,'2016-08-11 22:59:31'),(8,2,18,'2016-08-11 22:59:31'),(9,2,19,'2016-08-11 22:59:31'),(10,2,20,'2016-08-11 22:59:31'),(11,2,21,'2016-08-11 22:59:31'),(12,2,22,'2016-08-11 22:59:31'),(13,2,23,'2016-08-11 22:59:31'),(14,2,24,'2016-08-11 22:59:31'),(15,2,25,'2016-08-11 22:59:31'),(16,3,26,'2016-08-11 22:59:31'),(17,3,27,'2016-08-11 22:59:31'),(18,4,28,'2016-08-11 22:59:31'),(19,4,29,'2016-08-11 22:59:31'),(20,4,30,'2016-08-11 22:59:31'),(21,4,31,'2016-08-11 22:59:31'),(22,5,32,'2016-08-11 22:59:31'),(23,5,33,'2016-08-11 22:59:31'),(24,5,34,'2016-08-11 22:59:31'),(25,5,35,'2016-08-11 22:59:31'),(26,5,36,'2016-08-11 22:59:31'),(27,5,37,'2016-08-11 22:59:31'),(28,6,38,'2016-08-11 22:59:31'),(29,6,39,'2016-08-11 22:59:31');
/*!40000 ALTER TABLE `category_mapping` ENABLE KEYS */;
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
