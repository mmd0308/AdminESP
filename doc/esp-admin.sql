-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: esp-admin
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

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
-- Table structure for table `base_org`
--

DROP TABLE IF EXISTS `base_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_org` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL COMMENT '编码  唯一校验  ',
  `level_code` varchar(128) DEFAULT NULL COMMENT '层级编码',
  `org_type` varchar(32) DEFAULT NULL COMMENT '数据字典ID',
  `deleted` char(1) DEFAULT NULL COMMENT 'Y删除 N不删除',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `parentid` varchar(32) DEFAULT NULL COMMENT '上级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_org`
--

LOCK TABLES `base_org` WRITE;
/*!40000 ALTER TABLE `base_org` DISABLE KEYS */;
INSERT INTO `base_org` VALUES ('A','A','A','001','001','N',NULL,'0'),('AA','AA','AA','AA','AA','N',NULL,'A'),('AAA','AAA','AAA',NULL,NULL,'N',NULL,'AA'),('B','NB','B',NULL,NULL,'N',NULL,'0');
/*!40000 ALTER TABLE `base_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user`
--

DROP TABLE IF EXISTS `base_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `create_host` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_name` varchar(255) DEFAULT NULL,
  `update_host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user`
--

LOCK TABLES `base_user` WRITE;
/*!40000 ALTER TABLE `base_user` DISABLE KEYS */;
INSERT INTO `base_user` VALUES ('0c9c2ec5ae8f11e79b79a4c494befaeb','tester','$2a$12$a9WJZHJKJVKVu2oMT75Mbevp23cgydmBRz2kdX3pyPo18FmbAQ0CW','tester',NULL,'',NULL,NULL,NULL,'男',NULL,NULL,'tester','2017-10-11 22:18:27','1','billJiang','127.0.0.1','2017-10-12 10:33:41','1','billJiang','127.0.0.1'),('1','admin','$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie','billJiang','2017-10-01T16:00:00.000Z','北京市海淀区xxxxtest','13601047560',NULL,'475572229@qq.com','男',NULL,NULL,'admin',NULL,NULL,NULL,NULL,'2017-10-11 21:36:00','1','billJiang','127.0.0.1'),('2','test','$2a$12$zWe6knO6rGp15UVfdWTTxu.Ykt.k3QnD5FPoj6a1cnL63csHY2A1S','测试账户','2017-10-01T16:00:00.000Z',NULL,'',NULL,'','男',NULL,NULL,'tester',NULL,NULL,NULL,NULL,'2017-10-10 23:55:27','1','billJiang','127.0.0.1'),('4','blog','$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie','blog','2017-10-03T16:00:00.000Z','test-test','13601047568',NULL,'hello@test.com','男',NULL,NULL,'test',NULL,NULL,NULL,NULL,'2017-10-11 22:18:20','1','billJiang','127.0.0.1'),('4a824074ae9011e79b79a4c494befaeb','tester','$2a$12$6KYPuuqoBGqF/aobdnKG4eAZIhIl6epCdFcyr4kq.hR565E7zWyri','tester','','',NULL,NULL,NULL,'男',NULL,NULL,'teste','2017-10-11 22:27:21','1','billJiang','127.0.0.1','2017-10-11 22:27:28','1','billJiang','127.0.0.1'),('5274150eae9011e79b79a4c494befaeb','tester','$2a$12$Oq8iblGk/ScHvGrCDfgLnePQN/mTdDkR4aBN2T6XqgngMlvYIPwcK','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:27:34','1','billJiang','127.0.0.1','2017-10-11 22:27:34','1','billJiang','127.0.0.1'),('557218c4ae9011e79b79a4c494befaeb','tester','$2a$12$4Bsf9nngdbUu9PbZ/xQAFu1HD98r2x0gPDBCqX50T81XshZduqCz6','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:27:39','1','billJiang','127.0.0.1','2017-10-11 22:27:39','1','billJiang','127.0.0.1'),('591eb1beae9011e79b79a4c494befaeb','tester','$2a$12$wGF6bWyGgvarFk1KhkfkB.kJNH89cs0CLYQi1196MtitOmevYVWaK','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:27:45','1','billJiang','127.0.0.1','2017-10-11 22:27:45','1','billJiang','127.0.0.1'),('6157f1d6ae9011e79b79a4c494befaeb','tester','$2a$12$JNWmnW21vT38iZfi3DOeRuj0zpWA5MyGqAry9GiFTs5EZpRyA5ovK','tester','','',NULL,NULL,NULL,'男',NULL,NULL,'tester','2017-10-11 22:27:59','1','billJiang','127.0.0.1','2017-10-11 22:46:07','1','billJiang','127.0.0.1'),('6497bf19ae9011e79b79a4c494befaeb','tester','$2a$12$0xOxb8ER04Eq1356HGHgZeOxgalwBlq22MsoEQuuFM183UDOnW2qC','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:28:04','1','billJiang','127.0.0.1','2017-10-11 22:28:04','1','billJiang','127.0.0.1');
/*!40000 ALTER TABLE `base_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'esp-admin'
--

--
-- Dumping routines for database 'esp-admin'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-12 14:03:26
