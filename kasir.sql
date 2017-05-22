-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: kasir
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.15.04.1

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
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang` (
  `kode_barang` int(7) NOT NULL AUTO_INCREMENT,
  `nama_barang` varchar(50) DEFAULT NULL,
  `harga` int(7) DEFAULT NULL,
  `stok` int(7) DEFAULT NULL,
  `tgl_input` datetime NOT NULL,
  `jumlah_barang_masuk` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_barang`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang`
--

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
INSERT INTO `barang` VALUES (1,'telur',2000,48,'2017-01-10 10:10:10',NULL),(3,'Kopiko white Coffe',12000,0,'2017-01-10 10:10:10',NULL),(6,'ayam',4000,74,'2017-01-10 10:10:10',300),(7,'Tahu',7000,74,'2017-01-10 10:10:10',200),(45,'es teh',10000,7,'2017-01-10 10:10:10',100),(46,'Es lilin',2000,18,'2017-01-10 10:10:10',NULL),(48,'Es Lemon',3000,90,'2017-01-10 10:10:10',NULL),(49,'Permen',1000,98,'2017-01-10 10:10:10',NULL),(52,'Susu Cokelat',20000,100,'2017-05-09 10:17:43',NULL),(56,'kupul',20000,116,'2017-05-09 12:41:46',200),(57,'sabun',20000,98,'2017-05-09 13:28:40',13),(58,'Kakao',100000,235,'2017-05-09 13:49:30',300),(59,'Gula',10000,156,'2017-05-10 10:04:55',200),(60,'handuk',10000,20,'2017-05-14 22:26:26',20);
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_transaksi`
--

DROP TABLE IF EXISTS `detail_transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL AUTO_INCREMENT,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` bigint(20) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  PRIMARY KEY (`id_detail`),
  KEY `fk_detail_transaksi_1_idx` (`id_transaksi`),
  KEY `fk_id_barang_idx` (`id_barang`),
  CONSTRAINT `fk_detail_transaksi_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_barang` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_transaksi`
--

LOCK TABLES `detail_transaksi` WRITE;
/*!40000 ALTER TABLE `detail_transaksi` DISABLE KEYS */;
INSERT INTO `detail_transaksi` VALUES (1,11,7,1,7000,60),(2,11,45,1,10000,60),(3,11,48,1,3000,60),(4,11,6,1,4000,60),(6,12,46,1,2000,0),(7,12,7,1,7000,0),(8,12,46,1,2000,0),(9,13,7,2,7000,0),(10,13,3,1,12000,0),(12,13,46,1,2000,0),(14,13,46,1,2000,0),(15,13,48,1,3000,0),(16,13,7,1,7000,0),(18,15,7,1,7000,0),(19,15,45,1,10000,0),(20,15,7,1,7000,0),(21,15,6,1,4000,0),(22,16,6,1,4000,0),(24,16,7,1,7000,0),(25,16,46,1,2000,0),(29,18,45,1,10000,0),(30,18,46,1,2000,0),(31,18,3,1,12000,0),(32,19,48,1,3000,0),(33,19,45,1,10000,0),(34,19,49,1,1000,0),(35,20,48,1,3000,0),(36,20,45,1,10000,0),(37,20,49,1,1000,0),(38,21,48,1,3000,0),(39,21,45,1,10000,0),(40,21,49,1,1000,0),(41,21,46,1,2000,0),(42,22,48,1,3000,0),(43,22,46,1,2000,0),(44,22,45,1,10000,0),(45,22,45,6,10000,0),(46,23,46,1,2000,0),(47,23,7,1,7000,0),(48,23,48,1,3000,0),(49,23,3,1,12000,0),(50,23,45,5,10000,0),(51,24,1,3,2000,0),(52,24,6,1,4000,0),(53,24,45,1,10000,0),(54,25,45,1,10000,0),(55,25,46,1,2000,0),(56,25,7,1,7000,0),(57,25,48,2,3000,0),(58,26,48,1,3000,0),(59,26,49,1,1000,0),(60,26,48,1,3000,0),(61,26,46,1,2000,0),(62,27,1,10,2000,0),(63,28,1,10,2000,0),(64,29,1,10,2000,0),(65,30,1,10,2000,0),(66,30,6,1,4000,0),(67,31,1,10,2000,0),(68,31,3,1,12000,0),(69,31,1,1,2000,0),(70,32,1,10,2000,0),(71,33,1,10,2000,0),(72,34,1,10,2000,0),(73,35,7,1,7000,0),(74,35,46,1,2000,0),(75,35,6,1,4000,0),(76,36,7,1,7000,0),(77,36,46,1,2000,0),(78,36,6,1,4000,0),(79,36,1,1,2000,0),(80,37,7,1,7000,0),(81,37,6,1,4000,0),(82,37,48,1,3000,0),(83,38,49,90,1000,0),(86,40,48,1,3000,0),(87,40,46,1,2000,0),(88,40,7,1,7000,0),(89,40,49,1,1000,0),(90,40,7,1,7000,0),(91,40,46,1,2000,0),(92,40,6,1,4000,0),(93,40,1,1,2000,0),(94,40,49,1,1000,0),(95,40,45,1,10000,0),(96,40,6,1,4000,0),(97,40,3,1,12000,0),(98,40,1,1,2000,0),(99,40,46,1,2000,0),(100,41,7,1,7000,0),(101,41,48,1,3000,0),(102,42,7,1,7000,0),(103,42,48,1,3000,0),(104,43,7,1,7000,0),(105,43,46,1,2000,0),(106,43,7,1,7000,0),(107,43,3,1,12000,0),(108,43,46,1,2000,0),(109,44,45,1,10000,0),(110,44,7,1,7000,0),(111,44,46,1,2000,0),(112,44,3,1,12000,0),(113,45,45,1,10000,0),(114,45,7,1,7000,0),(115,45,46,1,2000,0),(116,45,3,1,12000,0),(117,46,6,1,4000,0),(118,46,3,1,12000,0),(119,46,7,1,7000,0),(120,47,6,1,4000,0),(121,47,45,1,10000,0),(122,47,48,1,3000,0),(123,47,45,1,10000,0),(124,48,1,1,2000,0),(125,48,3,1,12000,0),(126,48,7,1,7000,0),(127,48,46,1,2000,0),(128,48,49,1,1000,0),(130,48,46,1,2000,0),(131,48,6,1,4000,0),(132,51,45,1,10000,65),(133,51,7,1,7000,65),(134,51,6,1,4000,65),(135,52,45,1,10000,66),(136,52,7,1,7000,66),(137,52,6,1,4000,66),(138,52,49,1,1000,66),(139,53,6,10,4000,66),(140,53,6,1,4000,66),(141,53,58,10,100000,66),(142,53,59,1,10000,66),(143,53,57,1,20000,66),(144,53,56,1,20000,66),(145,53,57,10,20000,66),(146,53,58,1,100000,66),(147,53,56,1,20000,66),(148,53,57,1,20000,66),(149,53,59,1,10000,66),(150,53,3,1,12000,66),(151,53,1,1,2000,66),(152,54,6,10,4000,66),(153,54,6,1,4000,66),(154,54,58,10,100000,66),(155,54,59,1,10000,66),(156,54,57,1,20000,66),(157,54,56,1,20000,66),(158,54,57,10,20000,66),(159,54,58,1,100000,66),(160,54,56,1,20000,66),(161,54,57,1,20000,66),(162,54,59,1,10000,66),(163,54,3,1,12000,66),(164,54,1,1,2000,66),(165,55,58,12,100000,66),(166,55,59,13,10000,66),(167,55,49,12,1000,66),(168,56,58,10,100000,66),(169,56,59,9,10000,66),(170,56,58,18,100000,66),(171,56,59,12,10000,66),(172,57,52,1,20000,66),(173,57,56,1,20000,66),(174,57,57,1,20000,66),(175,58,46,1,2000,66),(176,58,48,1,3000,66),(177,58,49,1,1000,66),(178,58,46,1,2000,66),(179,58,52,1,20000,66),(180,59,46,1,2000,66),(181,59,48,1,3000,66),(182,59,49,1,1000,66),(183,59,46,1,2000,66),(184,59,52,1,20000,66),(185,60,46,1,2000,66),(186,60,48,1,3000,66),(187,60,52,1,20000,66),(188,61,46,1,2000,68),(189,61,49,1,1000,68),(190,61,45,1,10000,68),(191,62,46,1,2000,68),(192,62,49,1,1000,68),(193,62,45,1,10000,68),(194,63,59,1,10000,66),(195,64,59,1,10000,66),(196,65,59,1,10000,66),(197,66,59,1,10000,66),(198,67,7,1,7000,66),(199,68,7,1,7000,66),(200,69,7,1,7000,66),(201,70,52,1,20000,66),(202,70,56,1,20000,66),(203,70,59,1,10000,66),(204,71,52,1,20000,66),(205,71,56,1,20000,66),(206,72,52,1,20000,66),(207,73,52,1,20000,66),(208,74,52,1,20000,66),(209,75,52,1,20000,66),(210,76,56,1,20000,66),(211,76,52,1,20000,66),(212,77,58,1,100000,66),(213,79,56,1,20000,66),(214,79,57,1,20000,66),(215,79,49,1,1000,66),(216,80,56,1,20000,66),(217,80,57,1,20000,66),(218,80,49,1,1000,66),(219,81,46,1,2000,66),(220,82,56,1,20000,66),(221,82,58,1,100000,66),(222,82,56,1,20000,66),(223,82,59,1,10000,66),(224,83,6,2,4000,66),(225,83,7,1,7000,66),(226,83,45,1,10000,66),(227,84,46,1,2000,66),(228,84,45,1,10000,66);
/*!40000 ALTER TABLE `detail_transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `full_transaksi`
--

DROP TABLE IF EXISTS `full_transaksi`;
/*!50001 DROP VIEW IF EXISTS `full_transaksi`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `full_transaksi` AS SELECT 
 1 AS `id_transaksi`,
 1 AS `tgl_transaksi`,
 1 AS `id_petugas`,
 1 AS `kode_barang`,
 1 AS `nama_barang`,
 1 AS `harga`,
 1 AS `jumlah`,
 1 AS `username`,
 1 AS `nama_asli`,
 1 AS `status`,
 1 AS `barang_masuk`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategori` (
  `idkategori` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategori`
--

LOCK TABLES `kategori` WRITE;
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` VALUES (5,'Alat Tulisan'),(7,'Meja');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petugas`
--

DROP TABLE IF EXISTS `petugas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `petugas` (
  `idpetugas` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(60) COLLATE utf8_bin NOT NULL,
  `kategori` char(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idpetugas`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petugas`
--

LOCK TABLES `petugas` WRITE;
/*!40000 ALTER TABLE `petugas` DISABLE KEYS */;
INSERT INTO `petugas` VALUES (1,'admin','admin@aks.com','admin','1'),(2,'joko','joko@aks.com','joko','2'),(3,'mariona','mariona@aks.com','906ceea850ca1ea1feed2b5633445a11','2'),(4,'a','a@a','0cc175b9c0f1b6a831c399e269772661','2');
/*!40000 ALTER TABLE `petugas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `tgl_transaksi` datetime NOT NULL,
  `total_item` int(11) NOT NULL,
  `total_harga` bigint(20) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  PRIMARY KEY (`id_transaksi`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES (1,'2010-01-19 11:20:50',3,50000,2),(2,'2017-05-06 00:00:00',3,40000,0),(3,'2017-05-06 00:00:00',3,40000,0),(4,'2017-05-06 00:00:00',4,33000,0),(5,'2017-05-06 00:00:00',5,45000,0),(6,'2017-05-06 00:00:00',1,10000,0),(7,'2017-05-06 22:28:48',5,67000,0),(8,'2017-05-06 22:29:35',3,19000,0),(9,'2017-05-06 22:31:41',3,21000,0),(10,'2017-05-06 22:35:25',5,78000,60),(11,'2017-05-06 22:49:42',5,53000,60),(12,'2017-05-06 22:54:24',4,24000,60),(13,'2017-05-06 22:55:26',4,41000,60),(14,'2017-05-06 22:57:37',9,93000,60),(15,'2017-05-06 23:01:55',0,0,60),(16,'2017-05-06 23:05:59',4,43000,60),(18,'2017-05-07 01:17:17',3,24000,60),(19,'2017-05-07 01:19:28',3,14000,60),(20,'2017-05-07 01:19:36',3,14000,60),(21,'2017-05-07 01:20:02',4,16000,60),(22,'2017-05-07 23:30:48',9,25000,60),(23,'2017-05-07 23:35:53',9,34000,60),(24,'2017-05-07 23:38:04',5,16000,60),(25,'2017-05-07 23:42:44',5,22000,60),(26,'2017-05-07 23:50:54',4,9000,60),(27,'2017-05-07 23:51:51',10,2000,60),(28,'2017-05-07 23:52:07',10,2000,60),(29,'2017-05-07 23:52:46',10,2000,60),(30,'2017-05-07 23:54:46',11,6000,60),(31,'2017-05-08 00:01:21',12,16000,60),(32,'2017-05-08 00:01:52',10,2000,60),(33,'2017-05-08 00:02:22',10,2000,60),(34,'2017-05-08 00:02:39',10,2000,60),(35,'2017-05-08 00:08:53',3,13000,60),(36,'2017-05-08 00:12:37',4,15000,60),(37,'2017-05-08 00:20:53',3,14000,60),(38,'2017-05-08 00:23:40',90,1000,60),(39,'2017-05-08 03:01:22',1,9000,60),(40,'2017-05-08 03:02:02',15,68000,60),(41,'2017-05-08 03:05:00',2,10000,62),(42,'2017-05-08 03:06:23',2,10000,62),(43,'2017-05-08 11:46:00',5,30000,60),(44,'2017-05-08 15:10:36',4,31000,60),(45,'2017-05-08 15:11:21',4,31000,60),(46,'2017-05-08 15:12:21',3,23000,60),(47,'2017-05-08 17:54:58',4,27000,60),(48,'2017-05-08 06:47:08',8,39000,60),(49,'2017-05-09 22:47:11',2,120000,60),(50,'2017-05-09 23:04:33',3,127000,65),(51,'2017-05-09 23:06:19',3,21000,65),(52,'2017-05-09 23:12:39',4,22000,66),(53,'2017-05-11 23:11:24',40,342000,66),(54,'2017-05-11 23:12:32',40,342000,66),(55,'2017-05-11 23:25:48',37,4107000,66),(56,'2017-05-11 23:31:00',49,3010000,66),(57,'2017-05-13 21:56:47',3,60000,66),(58,'2017-05-13 21:59:41',5,28000,66),(59,'2017-05-13 22:00:06',5,28000,66),(60,'2017-05-13 22:02:19',3,25000,66),(61,'2017-05-13 22:27:50',3,13000,68),(62,'2017-05-13 22:28:04',3,13000,68),(63,'2017-05-14 18:50:30',1,10000,66),(64,'2017-05-14 18:50:40',1,10000,66),(65,'2017-05-14 18:51:09',1,10000,66),(66,'2017-05-14 18:52:15',1,10000,66),(67,'2017-05-14 18:53:26',1,7000,66),(68,'2017-05-14 18:54:06',1,7000,66),(69,'2017-05-14 18:57:51',1,7000,66),(70,'2017-05-14 18:58:39',3,50000,66),(71,'2017-05-14 19:01:05',2,40000,66),(72,'2017-05-14 19:03:30',1,20000,66),(73,'2017-05-14 19:04:08',1,20000,66),(74,'2017-05-14 19:04:43',1,20000,66),(75,'2017-05-14 19:05:14',1,20000,66),(76,'2017-05-14 19:08:56',2,40000,66),(77,'2017-05-14 19:37:01',1,100000,66),(78,'2017-05-15 19:51:52',0,0,66),(79,'2017-05-16 17:04:26',3,41000,66),(80,'2017-05-16 17:06:33',3,41000,66),(81,'2017-05-16 22:03:14',1,2000,66),(82,'2017-05-17 10:38:20',4,150000,66),(83,'2017-05-19 21:41:39',4,25000,66),(84,'2017-05-21 17:48:34',2,12000,66);
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userapp`
--

DROP TABLE IF EXISTS `userapp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userapp` (
  `kode_user` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nama_asli` varchar(50) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kode_user`,`username`),
  UNIQUE KEY `kode_user` (`kode_user`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userapp`
--

LOCK TABLES `userapp` WRITE;
/*!40000 ALTER TABLE `userapp` DISABLE KEYS */;
INSERT INTO `userapp` VALUES (1,'afani','rizal','Ahmad Rizal Afani','manager'),(2,'andriawan','as','aas','admin'),(3,'rusli_99','rusli','Rohman Rusli','Gudang'),(10,'andriawan','$2a$10$2uoFTKZrJZyHVE7jW5xaEeW03O0idzfjW9IVvMYcJR/wuEcId2/9K','aas','kasir'),(11,'admin','$2a$10$AdAwJni4fPkUHYFar8.FU.VqrEJfqyJ0gnnT99KPQmNt8gW3pyRcu','andriawan','admin'),(59,'irwan','$2a$10$cOKyXBj3vqUF8eW5iVkqFOTx0GtWKFwVO/Vxj86KbvlvpAZGk3Aqu','andriawan','admin'),(65,'andri','$2a$10$BxyND77jINS88Jr4j/irS.MCwNAj875WA2ziFyLu6dy0rp0w3i6VC','andriawan','kasir'),(66,'kasir','$2a$10$/dlfnPNV4BMKu1icZq7F2uIgZZm.LswnrDDdh3SES2gxsLV8Bq9u2','kasir','kasir'),(68,'daras','$2a$10$tdXLp5YD5e6VjrHJuA7NK.7N6IoYXx0reN3P.d6NyuyTBNUjUY.MS','daras','kasir');
/*!40000 ALTER TABLE `userapp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `full_transaksi`
--

/*!50001 DROP VIEW IF EXISTS `full_transaksi`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `full_transaksi` AS select `transaksi`.`id_transaksi` AS `id_transaksi`,`transaksi`.`tgl_transaksi` AS `tgl_transaksi`,`transaksi`.`id_petugas` AS `id_petugas`,`barang`.`kode_barang` AS `kode_barang`,`barang`.`nama_barang` AS `nama_barang`,`barang`.`harga` AS `harga`,`detail_transaksi`.`jumlah` AS `jumlah`,`userapp`.`username` AS `username`,`userapp`.`nama_asli` AS `nama_asli`,`userapp`.`status` AS `status`,`barang`.`jumlah_barang_masuk` AS `barang_masuk` from (((`detail_transaksi` join `transaksi` on((`detail_transaksi`.`id_transaksi` = `transaksi`.`id_transaksi`))) join `barang` on((`detail_transaksi`.`id_barang` = `barang`.`kode_barang`))) join `userapp` on((`detail_transaksi`.`id_petugas` = `userapp`.`kode_user`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-22 23:16:17
