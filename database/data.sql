CREATE DATABASE  IF NOT EXISTS `exercise3` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `exercise3`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: exercise3
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment_building`
--

DROP TABLE IF EXISTS `assignment_building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9j2n5bw1rgp110o03q7a6jsfb` (`building_id`),
  KEY `FKlyufdwxbev9mj9drwkuy5rqvl` (`staff_id`),
  CONSTRAINT `FK9j2n5bw1rgp110o03q7a6jsfb` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`),
  CONSTRAINT `FKlyufdwxbev9mj9drwkuy5rqvl` FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_building`
--

LOCK TABLES `assignment_building` WRITE;
/*!40000 ALTER TABLE `assignment_building` DISABLE KEYS */;
INSERT INTO `assignment_building` VALUES (1,NULL,NULL,NULL,NULL,1,2),(2,NULL,NULL,NULL,NULL,3,2),(3,NULL,NULL,NULL,NULL,1,3),(4,NULL,NULL,NULL,NULL,4,3);
/*!40000 ALTER TABLE `assignment_building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `bokerage_fee` int DEFAULT NULL,
  `car_fee` varchar(255) DEFAULT NULL,
  `decoration_time` varchar(255) DEFAULT NULL,
  `deposit` varchar(255) DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `electricity_fee` varchar(255) DEFAULT NULL,
  `floor_area` int DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `link_of_building` varchar(255) DEFAULT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `moto_fee` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `number_of_basement` int DEFAULT NULL,
  `over_time_fee` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `rent_price` int DEFAULT NULL,
  `rent_price_description` varchar(255) DEFAULT NULL,
  `rent_time` varchar(255) DEFAULT NULL,
  `service_fee` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `structure` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,NULL,NULL,'admin','2023-03-12 15:19:26',NULL,'','','','','QUAN_1','',500,'','','Ngô Hoàng Nam','','Nam Giao Building Tower','',2,'','','0935693144,0901176685',15,'15 triệu/m2','','','59 phan xích long','','TANG_TRET,NGUYEN_CAN','Phường 2'),(2,NULL,NULL,'admin','2023-03-12 15:31:54',NULL,'','','','','QUAN_2','',650,'','','Nguyễn Văn A ','','ACM Tower','',2,'','','0953633215',18,'18 triệu/m2','','','96 cao thắng','','NGUYEN_CAN','Phường 4'),(3,NULL,NULL,'admin','2023-03-12 15:32:23',NULL,'','','','','QUAN_1','',200,'','','Phạm Văn B ','','Alpha 2 Building Tower','',1,'','','0935468632',20,'20 triệu/m2','','','153 nguyễn đình chiểu','','NOI_THAT','Phường 6'),(4,NULL,NULL,'admin','2023-03-12 13:02:56',NULL,'','','','','QUAN_4','',200,'','','Lê Thị G','','IDD 1 Building','',1,'','','0915423753',12,'12 triệu/m2','','','111 Lý Chính Thắng','','NOI_THAT,TANG_TRET,NGUYEN_CAN','Phường 7'),(79,NULL,NULL,'admin','2023-03-12 20:09:13',NULL,'0đ','2 tuần','0đ','Đông Nam','QUAN_3','0đ',1000,'A','fb.com/xNamNgo','Ngô Hoàng Nam,Hoàng Phi Hùng','0đ','Quang Ngai Town','Bàn giao sớm cho đối tác',2,'0đ','50 triệu','0935693144,0901176685',200,'triệu/m2','10 năm','0đ','19 Phan Đình Phùng','3 tầng','NOI_THAT,TANG_TRET,NGUYEN_CAN','Trần Hưng Đạo');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent_area`
--

DROP TABLE IF EXISTS `rent_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rent_area` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `value` int DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqlqa56enlmodahpsw6q2x76nv` (`building_id`),
  CONSTRAINT `FKqlqa56enlmodahpsw6q2x76nv` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent_area`
--

LOCK TABLES `rent_area` WRITE;
/*!40000 ALTER TABLE `rent_area` DISABLE KEYS */;
INSERT INTO `rent_area` VALUES (184,'admin','2023-03-12 13:02:56','admin','2023-03-12 13:02:56',250,4),(185,'admin','2023-03-12 13:02:56','admin','2023-03-12 13:02:56',300,4),(186,'admin','2023-03-12 13:02:56','admin','2023-03-12 13:02:56',400,4),(190,'admin','2023-03-12 15:19:26','admin','2023-03-12 15:19:26',100,1),(191,'admin','2023-03-12 15:31:54','admin','2023-03-12 15:31:54',300,2),(192,'admin','2023-03-12 15:31:54','admin','2023-03-12 15:31:54',400,2),(193,'admin','2023-03-12 15:31:54','admin','2023-03-12 15:31:54',500,2),(194,'admin','2023-03-12 15:32:23','admin','2023-03-12 15:32:23',1000,3),(195,'admin','2023-03-12 15:32:23','admin','2023-03-12 15:32:23',200,3),(196,'admin','2023-03-12 15:32:23','admin','2023-03-12 15:32:23',523,3),(197,'admin','2023-03-12 20:09:13','admin','2023-03-12 20:09:13',300,79),(198,'admin','2023-03-12 20:09:13','admin','2023-03-12 20:09:13',400,79),(199,'admin','2023-03-12 20:09:13','admin','2023-03-12 20:09:13',1200,79);
/*!40000 ALTER TABLE `rent_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c36say97xydpmgigg38qv5l2p` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'MANAGER','Quản lý'),(2,NULL,NULL,NULL,NULL,'STAFF','Nhân viên');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,NULL,'nguyen van a','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvana'),(2,NULL,NULL,NULL,NULL,NULL,'nguyen van b','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanb'),(3,NULL,NULL,NULL,NULL,NULL,'nguyen van c','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanc'),(4,NULL,NULL,NULL,NULL,NULL,'nguyen van d','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvand'),(5,NULL,NULL,NULL,NULL,NULL,'admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (5,1),(1,2),(2,2),(3,2),(4,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-12 22:05:44
