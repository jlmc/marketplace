-- THIS FILE WAS BEEN GENERATED USING REVERSE ENGENERING, THE COMMAND USED IS THE FOWLING
-- > mysqldump -u root -p --databases marketplacerepository > 0_create_data_base.sql
-- > # mysqldump -u root -p[root_password] [database_name] > dumpfilename.sql

-- TO RESTORE THE DATA BASE USING THIS DUMP FILE WE CAN USE THE NEXT COMMAND
-- > # mysql -u root -p[root_password] [database_name] < dumpfilename.sql
 
-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: marketplacerepository
-- ------------------------------------------------------
-- Server version       5.6.23-log

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
-- Current Database: `marketplacerepository`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `marketplacerepository` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `marketplacerepository`;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(60) NOT NULL,
  `masterCategory` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ikyv5d38q282kl2utnromtrta` (`masterCategory`),
  CONSTRAINT `FK_ikyv5d38q282kl2utnromtrta` FOREIGN KEY (`masterCategory`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Computers',NULL),(2,'Gadgets',NULL),(3,'Software',NULL),(4,'Books',NULL),(5,'Others',NULL),
(6,'Desktop',1),(7,'Laptop',1),(8,'Printer',1),(9,'Tablet',1),(10,'SO',3),(11,'Office Application',3),(12,'Driver',3),(13,'Ho
use Application',3),(14,'Computer sciencie',4),(15,'Biographical',4),(16,'Adventure',4),(17,'Science fiction',4),(18,'Romance
',4),(19,'Science fiction',4),(20,'Police',4);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_type` varchar(32) NOT NULL,
  `cnjp` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (2,'PERSON','122. 345.454-56','costa@gdhj.com','Joao'),(3,'FOUNDATION','677. 887.990-00','his@his
.pt','HIS'),(4,'PERSON','112. 344.567-78','alvaro23@sapo.pt','alvaro costa');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_address`
--

DROP TABLE IF EXISTS `client_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `doorNumber` varchar(15) DEFAULT NULL,
  `street` varchar(155) DEFAULT NULL,
  `zipCode` varchar(15) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cwn3pv9oxxk3hqx39kn2im57e` (`client_id`),
  CONSTRAINT `FK_cwn3pv9oxxk3hqx39kn2im57e` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_address`
--

LOCK TABLES `client_address` WRITE;
/*!40000 ALTER TABLE `client_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m3j6m9ksltume23qomatoes1r` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'Admin permission','Admin'),(2,'Client permission','Client'),(3,'Seller permission','Selle
r'),(4,'Guest permission','Guest');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `stock_qty` int(11) NOT NULL,
  `unit_value` decimal(10,2) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_epc2gbv2w8k1p76rugng6xre3` (`sku`),
  KEY `FK_b7afq93qsn7aoydaftixggf14` (`category_id`),
  CONSTRAINT `FK_b7afq93qsn7aoydaftixggf14` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'test','AA123456',212,1.23,6),(2,'JAVA EE 7','AA23456999',900,53.20,14),(3,'Java EE 7','JA000
1',1245,70.00,14),(4,'Spring in Action','AS98989',100,10000.00,14),(5,'p234','as36734484',1211,111.11,10);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requisition`
--

DROP TABLE IF EXISTS `requisition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requisition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_Date` datetime DEFAULT NULL,
  `delivery_city` varchar(100) NOT NULL,
  `delivery_country` varchar(100) NOT NULL,
  `delivery_Name` varchar(200) NOT NULL,
  `delivery_number_door` varchar(15) DEFAULT NULL,
  `delivery_street` varchar(200) DEFAULT NULL,
  `delivery_zip_code` varchar(15) NOT NULL,
  `delivery_date` date NOT NULL,
  `notes` text,
  `payment_method` varchar(20) NOT NULL,
  `rebate_value` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total_value` decimal(10,2) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `seller_id` bigint(20) NOT NULL,
  `shipping_value` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9rfw2eooq0i0g27x8te7u0vit` (`client_id`),
  KEY `FK_4hkbefnqetoxvh7a8dnjbx775` (`seller_id`),
  CONSTRAINT `FK_4hkbefnqetoxvh7a8dnjbx775` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_9rfw2eooq0i0g27x8te7u0vit` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requisition`
--

LOCK TABLES `requisition` WRITE;
/*!40000 ALTER TABLE `requisition` DISABLE KEYS */;
INSERT INTO `requisition` VALUES (1,'2015-05-04 02:40:42','Soure','Portugal','CM Soure','123','rua da ALMEIDA','3130-541','20
15-05-14','entregar sem estragar... :)','CASH',50.00,'ISSUED',602610.00,4,3,600000.00);
/*!40000 ALTER TABLE `requisition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requisition_item`
--

DROP TABLE IF EXISTS `requisition_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requisition_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Qty` int(11) NOT NULL,
  `unit_value` decimal(10,2) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `requisition_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9vm73gg3g6sgmg6utfd9qntoq` (`product_id`),
  KEY `FK_nqkl9ia49ejixo3keoftnu02g` (`requisition_id`),
  CONSTRAINT `FK_9vm73gg3g6sgmg6utfd9qntoq` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_nqkl9ia49ejixo3keoftnu02g` FOREIGN KEY (`requisition_id`) REFERENCES `requisition` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requisition_item`
--

LOCK TABLES `requisition_item` WRITE;
/*!40000 ALTER TABLE `requisition_item` DISABLE KEYS */;
INSERT INTO `requisition_item` VALUES (1,50,53.20,2,1);
/*!40000 ALTER TABLE `requisition_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `password` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e6gkqunxajvyxl5uctpl2vl2p` (`email`),
  UNIQUE KEY `UK_jreodf78a7pl5qidfh43axdfb` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'funny8086@gmail.com','admin','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `user_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`permission_id`),
  KEY `FK_5jfoyhv0p0tg0d5dwf8oo4rjh` (`permission_id`),
  CONSTRAINT `FK_5jfoyhv0p0tg0d5dwf8oo4rjh` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FK_hd22i4a2n0s2ntvvv09c14r7y` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
INSERT INTO `user_permission` VALUES (3,2),(3,3);
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-07 23:20:59














