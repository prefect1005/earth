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
-- Table structure for table `service_provider_identification_company`
--

DROP TABLE IF EXISTS `service_provider_identification_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_provider_identification_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `corporate_representative` varchar(45) NOT NULL,
  `company_location_province_id` int(11) NOT NULL,
  `company_location_city_id` int(11) NOT NULL,
  `company_location_town_id` int(11) NOT NULL,
  `company_location` varchar(200) NOT NULL,
  `company_location_detail` varchar(500) NOT NULL,
  `contacts_name` varchar(255) NOT NULL,
  `company_profile` text NOT NULL,
  `company_email` varchar(255) NOT NULL,
  `company_bank_account` varchar(255) NOT NULL,
  `phone_number` varchar(100) NOT NULL,
  `socialUnifiedCreditCode` varchar(100) NOT NULL,
  `img_src_list` varchar(800) NOT NULL,
  `store_display_img_src` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_identification_company`
--

LOCK TABLES `service_provider_identification_company` WRITE;
/*!40000 ALTER TABLE `service_provider_identification_company` DISABLE KEYS */;
INSERT INTO `service_provider_identification_company` VALUES (1,3,'第一家公司','我自己',1,2,10,'北京市,北京市,海淀区','海淀区学清路','我自己','很好的服务团吨很好的服务团吨很好的服务团吨很好的服务团吨很好的服务团吨很好的服务团吨很好的服务团吨很好的服务团吨很好的服务团吨','sdfasd','456245325345345324324','18614087661','3532532453425324532453245342','IMG_20150913_162501_9bncA5dXzo.jpg,IMG_20150913_162501_1448109412686_TQ4DZfiIPL.jpg','IMG_20150626_170336_HvBIRGZjNN.jpg','2016-08-16 20:28:57','2016-08-16 20:28:57'),(2,4,'我自己的工作室','我自己',108,229,232,'河北省,廊坊市,霸州市','啊哈哈哈哈哈地址','8888','是到付哈大师傅撒旦发是到付哈大师傅撒旦发是到付哈大师傅撒旦发是到付哈大师傅撒旦发是到付哈大师傅撒旦发','1111111@1711.com','wer534545643643532','18210673513','3453253253245123424234234','IMG_20150626_101452_NUndIu6is8.jpg,IMG_20150626_170336_AHs5Qe5CQF.jpg,IMG_20150626_170449_U6Z5YNzKIv.jpg','IMG_20150626_075142_Nx4XMte1Qq.jpg','2016-08-20 12:44:48','2016-08-20 12:44:48');
/*!40000 ALTER TABLE `service_provider_identification_company` ENABLE KEYS */;
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
