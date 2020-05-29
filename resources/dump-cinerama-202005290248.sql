-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: cinerama
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.16-MariaDB

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

create database cinerama;
use cinerama;

-- === DB USER =================================================================================

create user 'cinerama'@'localhost' identified by 'overflow7020';
grant all privileges on cinerama.* to 'cinerama'@'localhost';

--
-- Table structure for table `ctg_availability`
--

DROP TABLE IF EXISTS `ctg_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_availability` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_availability`
--

LOCK TABLES `ctg_availability` WRITE;
/*!40000 ALTER TABLE `ctg_availability` DISABLE KEYS */;
INSERT INTO `ctg_availability` VALUES (1,'Available'),(2,'Out of Stock');
/*!40000 ALTER TABLE `ctg_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_director`
--

DROP TABLE IF EXISTS `ctg_director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_director` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_director`
--

LOCK TABLES `ctg_director` WRITE;
/*!40000 ALTER TABLE `ctg_director` DISABLE KEYS */;
INSERT INTO `ctg_director` VALUES (1,'Ari Aster'),(2,'Francis Ford Coppola'),(3,'John Carpenter'),(4,'Riddley Scott'),(5,'Wes Anderson'),(6,'Christopher Nolan'),(7,'Quentin Tarantino'),(8,'Alex Garland'),(9,'David Fincher'),(10,'Steven Spielberg'),(11,'Martin Scorsese');
/*!40000 ALTER TABLE `ctg_director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_genre`
--

DROP TABLE IF EXISTS `ctg_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_genre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_genre`
--

LOCK TABLES `ctg_genre` WRITE;
/*!40000 ALTER TABLE `ctg_genre` DISABLE KEYS */;
INSERT INTO `ctg_genre` VALUES (1,'Horror'),(2,'Drama'),(3,'SciFi'),(4,'Suspense'),(5,'Comedy'),(6,'Action');
/*!40000 ALTER TABLE `ctg_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_image`
--

DROP TABLE IF EXISTS `ctg_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(36) DEFAULT NULL,
  `path` varchar(256) DEFAULT NULL,
  `id_image_type` bigint(20) NOT NULL,
  `id_movie` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ctg_image_ctg_image_type` (`id_image_type`),
  KEY `fk_ctg_image_ctg_movie` (`id_movie`),
  CONSTRAINT `fk_ctg_image_ctg_image_type` FOREIGN KEY (`id_image_type`) REFERENCES `ctg_image_type` (`id`),
  CONSTRAINT `fk_ctg_image_ctg_movie` FOREIGN KEY (`id_movie`) REFERENCES `ctg_movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_image`
--

LOCK TABLES `ctg_image` WRITE;
/*!40000 ALTER TABLE `ctg_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctg_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_image_type`
--

DROP TABLE IF EXISTS `ctg_image_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_image_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_image_type`
--

LOCK TABLES `ctg_image_type` WRITE;
/*!40000 ALTER TABLE `ctg_image_type` DISABLE KEYS */;
INSERT INTO `ctg_image_type` VALUES (1,'cover'),(2,'background'),(3,'thumbnail');
/*!40000 ALTER TABLE `ctg_image_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_likes`
--

DROP TABLE IF EXISTS `ctg_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_likes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_movie` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ctg_likes_ctg_movie` (`id_movie`),
  KEY `fk_ctg_likes_sct_user` (`id_user`),
  CONSTRAINT `fk_ctg_likes_ctg_movie` FOREIGN KEY (`id_movie`) REFERENCES `ctg_movie` (`id`),
  CONSTRAINT `fk_ctg_likes_sct_user` FOREIGN KEY (`id_user`) REFERENCES `sct_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_likes`
--

LOCK TABLES `ctg_likes` WRITE;
/*!40000 ALTER TABLE `ctg_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctg_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_movie`
--

DROP TABLE IF EXISTS `ctg_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `description` text DEFAULT NULL,
  `id_director` bigint(20) NOT NULL,
  `id_genre` bigint(20) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  `rental_price` decimal(16,4) DEFAULT NULL,
  `sale_price` decimal(16,4) DEFAULT NULL,
  `id_availability` bigint(20) NOT NULL,
  `popularity` bigint(20) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ctg_movie_ctg_director` (`id_director`),
  KEY `fk_ctg_movie_ctg_genre` (`id_genre`),
  KEY `fk_ctg_movie_ctg_availability` (`id_availability`),
  CONSTRAINT `fk_ctg_movie_ctg_availability` FOREIGN KEY (`id_availability`) REFERENCES `ctg_availability` (`id`),
  CONSTRAINT `fk_ctg_movie_ctg_director` FOREIGN KEY (`id_director`) REFERENCES `ctg_director` (`id`),
  CONSTRAINT `fk_ctg_movie_ctg_genre` FOREIGN KEY (`id_genre`) REFERENCES `ctg_genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_movie`
--

LOCK TABLES `ctg_movie` WRITE;
/*!40000 ALTER TABLE `ctg_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctg_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_profile_permission`
--

DROP TABLE IF EXISTS `ctg_profile_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_profile_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_profile` bigint(20) NOT NULL,
  `id_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_profile_permission_profile` (`id_profile`),
  KEY `fk_profile_permission_permission` (`id_permission`),
  CONSTRAINT `fk_profile_permission_permission` FOREIGN KEY (`id_permission`) REFERENCES `sct_permission` (`id`),
  CONSTRAINT `fk_profile_permission_profile` FOREIGN KEY (`id_profile`) REFERENCES `sct_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_profile_permission`
--

LOCK TABLES `ctg_profile_permission` WRITE;
/*!40000 ALTER TABLE `ctg_profile_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctg_profile_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_purchase_state`
--

DROP TABLE IF EXISTS `ctg_purchase_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_purchase_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_purchase_state`
--

LOCK TABLES `ctg_purchase_state` WRITE;
/*!40000 ALTER TABLE `ctg_purchase_state` DISABLE KEYS */;
INSERT INTO `ctg_purchase_state` VALUES (1,'Complete'),(2,'Rented');
/*!40000 ALTER TABLE `ctg_purchase_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctg_purchase_type`
--

DROP TABLE IF EXISTS `ctg_purchase_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctg_purchase_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctg_purchase_type`
--

LOCK TABLES `ctg_purchase_type` WRITE;
/*!40000 ALTER TABLE `ctg_purchase_type` DISABLE KEYS */;
INSERT INTO `ctg_purchase_type` VALUES (1,'Buy'),(2,'Rent');
/*!40000 ALTER TABLE `ctg_purchase_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sct_permission`
--

DROP TABLE IF EXISTS `sct_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sct_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `display_name` varchar(64) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `route` varchar(64) DEFAULT NULL,
  `state` int(11) DEFAULT 1,
  `order_number` int(11) DEFAULT NULL,
  `father` int(11) DEFAULT NULL,
  `family` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sct_permission`
--

LOCK TABLES `sct_permission` WRITE;
/*!40000 ALTER TABLE `sct_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sct_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sct_profile`
--

DROP TABLE IF EXISTS `sct_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sct_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `display_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sct_profile`
--

LOCK TABLES `sct_profile` WRITE;
/*!40000 ALTER TABLE `sct_profile` DISABLE KEYS */;
INSERT INTO `sct_profile` VALUES (1,'ROLE_USER','Customer'),(2,'ROLE_ADMIN','Administrator');
/*!40000 ALTER TABLE `sct_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sct_user`
--

DROP TABLE IF EXISTS `sct_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sct_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `firstnames` varchar(64) DEFAULT NULL,
  `lastnames` varchar(64) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `token` varchar(512) DEFAULT NULL,
  `pwd` varchar(256) DEFAULT NULL,
  `id_profile` bigint(20) NOT NULL,
  `state` int(11) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_sct_user_sct_profile` (`id_profile`),
  CONSTRAINT `fk_sct_user_sct_profile` FOREIGN KEY (`id_profile`) REFERENCES `sct_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sct_user`
--

LOCK TABLES `sct_user` WRITE;
/*!40000 ALTER TABLE `sct_user` DISABLE KEYS */;
INSERT INTO `sct_user` VALUES (1,'42aaa66a-2cef-4d10-b471-f2bb229718f7','jorgesidgo','Jorge Luis','Sidgo Pimentel','jorge.sidgo@gmail.com','','$2a$10$FffwopRak8oa2G.6LCr1W.0zdgBIaqMv65YsGonACF0ceIPCsBeaG',2,1,'2020-05-29 08:47:05');
/*!40000 ALTER TABLE `sct_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wfk_movie_update_log`
--

DROP TABLE IF EXISTS `wfk_movie_update_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wfk_movie_update_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) NOT NULL,
  `id_movie` bigint(20) NOT NULL,
  `updated_prop` varchar(32) DEFAULT NULL,
  `old_value` text DEFAULT NULL,
  `new_value` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_update_log_ctg_movie` (`id_movie`),
  KEY `fk_update_log_sct_user` (`id_user`),
  CONSTRAINT `fk_update_log_ctg_movie` FOREIGN KEY (`id_movie`) REFERENCES `ctg_movie` (`id`),
  CONSTRAINT `fk_update_log_sct_user` FOREIGN KEY (`id_user`) REFERENCES `sct_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wfk_movie_update_log`
--

LOCK TABLES `wfk_movie_update_log` WRITE;
/*!40000 ALTER TABLE `wfk_movie_update_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `wfk_movie_update_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wkf_purchase`
--

DROP TABLE IF EXISTS `wkf_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wkf_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) NOT NULL,
  `id_movie` bigint(20) NOT NULL,
  `id_purchase_type` bigint(20) NOT NULL,
  `id_purchase_state` bigint(20) NOT NULL,
  `total` decimal(16,4) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `estimated_return` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_wkf_purchase_sct_user` (`id_user`),
  KEY `fk_wkf_purchase_ctg_movie` (`id_movie`),
  KEY `fk_wkf_purchase_ctg_purchase_type` (`id_purchase_type`),
  KEY `fk_wkf_purchase_ctg_purchase_state` (`id_purchase_state`),
  CONSTRAINT `fk_wkf_purchase_ctg_movie` FOREIGN KEY (`id_movie`) REFERENCES `ctg_movie` (`id`),
  CONSTRAINT `fk_wkf_purchase_ctg_purchase_state` FOREIGN KEY (`id_purchase_state`) REFERENCES `ctg_purchase_state` (`id`),
  CONSTRAINT `fk_wkf_purchase_ctg_purchase_type` FOREIGN KEY (`id_purchase_type`) REFERENCES `ctg_purchase_type` (`id`),
  CONSTRAINT `fk_wkf_purchase_sct_user` FOREIGN KEY (`id_user`) REFERENCES `sct_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wkf_purchase`
--

LOCK TABLES `wkf_purchase` WRITE;
/*!40000 ALTER TABLE `wkf_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `wkf_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cinerama'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-29  2:48:14
