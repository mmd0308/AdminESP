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
-- Table structure for table `base_element`
--

DROP TABLE IF EXISTS `base_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_element` (
  `id` varchar(36) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '资源编码',
  `type` varchar(255) DEFAULT NULL COMMENT '资源类型',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `uri` varchar(255) DEFAULT NULL,
  `menu_id` varchar(255) NOT NULL COMMENT '资源关联菜单',
  `method` varchar(10) DEFAULT NULL COMMENT '资源请求类型',
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `create_host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_element`
--

LOCK TABLES `base_element` WRITE;
/*!40000 ALTER TABLE `base_element` DISABLE KEYS */;
INSERT INTO `base_element` VALUES ('28c8a6beb4a911e78e02a4c494befaeb','roleManager:btn_add','button','新增角色','/admin/role','1001636bb2de11e79fe6a4c494befaeb','POST',NULL,'2017-10-19 16:40:28','1','billJiang','127.0.0.1'),('38949a0fb4a911e78e02a4c494befaeb','roleManager:btn_del','button','删除角色','/admin/role','1001636bb2de11e79fe6a4c494befaeb','DELETE',NULL,'2017-10-19 16:40:55','1','billJiang','127.0.0.1'),('3e700d39b61a11e7b412a4c494befaeb','menuManager:btn_add','button','新增菜单','/admin/menu','0323de15b21211e7bf8da4c494befaeb','POST',NULL,'2017-10-21 12:42:29','1','billJiang','127.0.0.1'),('4ca4b6d7b61a11e7b412a4c494befaeb','menuManager:btn_update','button','编辑菜单','/admin/menu','0323de15b21211e7bf8da4c494befaeb','PUT',NULL,'2017-10-21 12:42:53','1','billJiang','127.0.0.1'),('4cffce86b4a911e78e02a4c494befaeb','roleManager:btn_edit','button','修改角色','/admin/role','1001636bb2de11e79fe6a4c494befaeb','PUT',NULL,'2017-10-19 16:41:29','1','billJiang','127.0.0.1'),('5cbae3a0b61a11e7b412a4c494befaeb','menuManager:btn_delete','button','删除菜单','/admin/menu','0323de15b21211e7bf8da4c494befaeb','DELETE',NULL,'2017-10-21 12:43:20','1','billJiang','127.0.0.1'),('6c067d61b4a911e78e02a4c494befaeb','roleManager:btn_user_add','button','添加用户','/admin/role/user','1001636bb2de11e79fe6a4c494befaeb','POST',NULL,'2017-10-19 16:42:21','1','billJiang','127.0.0.1'),('7af79c61b4a911e78e02a4c494befaeb','roleManager:btn_user_del','button','移除用户','/admin/role/user','1001636bb2de11e79fe6a4c494befaeb','DELETE',NULL,'2017-10-19 16:42:46','1','billJiang','127.0.0.1'),('9e5aa8e6b25911e7bf8da4c494befaeb','userManager:btn_update','button','更新','/admin/user','d7c3e5bcb21111e7bf8da4c494befaeb','PUT','test','2017-10-16 18:06:04','1','billJiang','127.0.0.1'),('ac5413f6b25911e7bf8da4c494befaeb','userManager:btn_delete','button','删除','/admin/user','d7c3e5bcb21111e7bf8da4c494befaeb','DELETE',NULL,'2017-10-16 18:06:27','1','billJiang','127.0.0.1'),('f58703c0b23a11e7bf8da4c494befaeb','userManager:btn_add','button','新增','/admin/index','d7c3e5bcb21111e7bf8da4c494befaeb','POST',NULL,'2017-10-16 14:26:36','1','billJiang','127.0.0.1');
/*!40000 ALTER TABLE `base_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_menu`
--

DROP TABLE IF EXISTS `base_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_menu` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '标题',
  `code` varchar(45) DEFAULT NULL,
  `levelcode` varchar(500) DEFAULT NULL COMMENT '菜单上下级关系',
  `parent_id` varchar(36) NOT NULL COMMENT '父级节点',
  `href` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `component` varchar(255) DEFAULT NULL COMMENT '前端组件',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` varchar(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `enabled` char(1) DEFAULT NULL COMMENT '启用禁用',
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
-- Dumping data for table `base_menu`
--

LOCK TABLES `base_menu` WRITE;
/*!40000 ALTER TABLE `base_menu` DISABLE KEYS */;
INSERT INTO `base_menu` VALUES ('0323de15b21211e7bf8da4c494befaeb','菜单管理','menuManager','000001000001000002','af8d8531b0b211e78e3fa4c494befaec','/admin/menu','/admin/menu/index','table','menu',NULL,'1','2017-10-16 09:33:29','1','billJiang','127.0.0.1','2017-10-16 09:33:29','1','billJiang','127.0.0.1'),('1001636bb2de11e79fe6a4c494befaeb','角色管理','roleManager','000001000001000004','af8d8531b0b211e78e3fa4c494befaec','/admin/role','/admin/role/index',NULL,'menu',NULL,'1','2017-10-17 09:54:08','1','billJiang','127.0.0.1','2017-10-17 09:54:08','1','billJiang','127.0.0.1'),('18920f93b21211e7bf8da4c494befaeb','组织机构管理','orgManager','000001000001000003','af8d8531b0b211e78e3fa4c494befaec','/admin/org','/admin/org/index','org','menu',NULL,'1','2017-10-16 09:34:05','1','billJiang','127.0.0.1','2017-10-17 16:22:22','1','billJiang','127.0.0.1'),('af8d8531b0b211e78e3fa4c494befaeb','用户中心','usercenter','000001','-1','','','people','menu','用户中心系统','1',NULL,NULL,NULL,NULL,'2017-10-16 09:28:29','1','billJiang','127.0.0.1'),('af8d8531b0b211e78e3fa4c494befaec','系统管理','system','000001000001','af8d8531b0b211e78e3fa4c494befaeb','','','people','menu','用户中心系统','1',NULL,NULL,NULL,NULL,'2017-10-23 10:22:34','1','billJiang','127.0.0.1'),('d7c3e5bcb21111e7bf8da4c494befaeb','用户管理','userManager','000001000001000001','af8d8531b0b211e78e3fa4c494befaec','/admin/user','/admin/user/index','people','menu',NULL,'1','2017-10-16 09:32:16','1','billJiang','127.0.0.1','2017-10-16 09:32:16','1','billJiang','127.0.0.1');
/*!40000 ALTER TABLE `base_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Table structure for table `base_role`
--

DROP TABLE IF EXISTS `base_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_role` (
  `id` varchar(36) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(36) NOT NULL,
  `levelcode` varchar(255) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `enabled` char(1) DEFAULT '1',
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
-- Dumping data for table `base_role`
--

LOCK TABLES `base_role` WRITE;
/*!40000 ALTER TABLE `base_role` DISABLE KEYS */;
INSERT INTO `base_role` VALUES ('665b7a7eb61c11e7b412a4c494befaeb','test','一般管理员','f169935db31d11e79fe6a4c494befaeb','000001000002',NULL,'1','test','2017-10-21 12:57:55','1','billJiang','127.0.0.1','2017-10-21 12:57:55','1','billJiang','127.0.0.1'),('79b56abab33711e79fe6a4c494befaeb','Manager','管理员','f169935db31d11e79fe6a4c494befaeb','000001000001',NULL,'1','test','2017-10-17 20:34:10','1','billJiang','127.0.0.1','2017-10-22 17:07:28','1','billJiang','127.0.0.1'),('f169935db31d11e79fe6a4c494befaeb','system','系统管理员','-1','000001',NULL,'1','test','2017-10-17 17:31:24','1','billJiang','127.0.0.1','2017-10-17 17:31:24','1','billJiang','127.0.0.1');
/*!40000 ALTER TABLE `base_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_role_resource`
--

DROP TABLE IF EXISTS `base_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_role_resource` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `resource_id` varchar(36) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT '-1',
  `path` varchar(2000) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `create_host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_role_resource`
--

LOCK TABLES `base_role_resource` WRITE;
/*!40000 ALTER TABLE `base_role_resource` DISABLE KEYS */;
INSERT INTO `base_role_resource` VALUES ('6ef5d494b61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','9e5aa8e6b25911e7bf8da4c494befaeb','element','d7c3e5bcb21111e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('6ef5d628b61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','ac5413f6b25911e7bf8da4c494befaeb','element','d7c3e5bcb21111e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('6ef5d676b61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','f58703c0b23a11e7bf8da4c494befaeb','element','d7c3e5bcb21111e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('6f9e2120b61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','d7c3e5bcb21111e7bf8da4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('6f9e22efb61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','af8d8531b0b211e78e3fa4c494befaec','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('6f9e234bb61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','af8d8531b0b211e78e3fa4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('afc96069b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','3e700d39b61a11e7b412a4c494befaeb','element','0323de15b21211e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('afc96224b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','4ca4b6d7b61a11e7b412a4c494befaeb','element','0323de15b21211e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('afc96278b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','5cbae3a0b61a11e7b412a4c494befaeb','element','0323de15b21211e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('b2996a92b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','d7c3e5bcb21111e7bf8da4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('b2996befb79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','1001636bb2de11e79fe6a4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('b2996c3fb79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','af8d8531b0b211e78e3fa4c494befaec','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('b2996c79b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','0323de15b21211e7bf8da4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('b2996cb0b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','af8d8531b0b211e78e3fa4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('b2996ce0b79611e7bb94a4c494befaeb','79b56abab33711e79fe6a4c494befaeb','18920f93b21211e7bf8da4c494befaeb','menu','-1',NULL,NULL,NULL,NULL,NULL,NULL),('b96567f6b60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','28c8a6beb4a911e78e02a4c494befaeb','element','1001636bb2de11e79fe6a4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('b96569abb60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','38949a0fb4a911e78e02a4c494befaeb','element','1001636bb2de11e79fe6a4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('b9656a5fb60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','4cffce86b4a911e78e02a4c494befaeb','element','1001636bb2de11e79fe6a4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('b9656abfb60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','6c067d61b4a911e78e02a4c494befaeb','element','1001636bb2de11e79fe6a4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('b9656b15b60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','7af79c61b4a911e78e02a4c494befaeb','element','1001636bb2de11e79fe6a4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('bacde060b60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','9e5aa8e6b25911e7bf8da4c494befaeb','element','d7c3e5bcb21111e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('bacde1d2b60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','ac5413f6b25911e7bf8da4c494befaeb','element','d7c3e5bcb21111e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL),('bacde22db60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','f58703c0b23a11e7bf8da4c494befaeb','element','d7c3e5bcb21111e7bf8da4c494befaeb',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `base_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_role_user`
--

DROP TABLE IF EXISTS `base_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_role_user` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
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
-- Dumping data for table `base_role_user`
--

LOCK TABLES `base_role_user` WRITE;
/*!40000 ALTER TABLE `base_role_user` DISABLE KEYS */;
INSERT INTO `base_role_user` VALUES ('6af2e9a1b61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('6af4876eb61c11e7b412a4c494befaeb','665b7a7eb61c11e7b412a4c494befaeb','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ce3e7531b60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ce3fe807b60111e7830aa4c494befaeb','79b56abab33711e79fe6a4c494befaeb','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `base_role_user` ENABLE KEYS */;
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
INSERT INTO `base_user` VALUES ('0c9c2ec5ae8f11e79b79a4c494befaeb','tester','$2a$12$a9WJZHJKJVKVu2oMT75Mbevp23cgydmBRz2kdX3pyPo18FmbAQ0CW','tester',NULL,'',NULL,NULL,NULL,'男',NULL,NULL,'tester','2017-10-11 22:18:27','1','billJiang','127.0.0.1','2017-10-12 10:33:41','1','billJiang','127.0.0.1'),('1','admin','$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie','billJiang','2017-10-01T16:00:00.000Z','北京市海淀区xxxxtest','13601047560',NULL,'475572229@qq.com','男',NULL,NULL,'admin',NULL,NULL,NULL,NULL,'2017-10-11 21:36:00','1','billJiang','127.0.0.1'),('2','test','$2a$12$zWe6knO6rGp15UVfdWTTxu.Ykt.k3QnD5FPoj6a1cnL63csHY2A1S','测试账户','2017-10-01T16:00:00.000Z',NULL,'',NULL,'','男',NULL,NULL,'tester',NULL,NULL,NULL,NULL,'2017-10-10 23:55:27','1','billJiang','127.0.0.1'),('4','blog','$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie','blog','2017-10-03T16:00:00.000Z','test-test','13601047568',NULL,'hello@test.com','男',NULL,NULL,'test',NULL,NULL,NULL,NULL,'2017-10-11 22:18:20','1','billJiang','127.0.0.1'),('4a824074ae9011e79b79a4c494befaeb','tester','$2a$12$6KYPuuqoBGqF/aobdnKG4eAZIhIl6epCdFcyr4kq.hR565E7zWyri','tester','','',NULL,NULL,NULL,'男',NULL,NULL,'test','2017-10-11 22:27:21','1','billJiang','127.0.0.1','2017-10-16 20:50:40','1','billJiang','127.0.0.1'),('5274150eae9011e79b79a4c494befaeb','tester','$2a$12$Oq8iblGk/ScHvGrCDfgLnePQN/mTdDkR4aBN2T6XqgngMlvYIPwcK','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:27:34','1','billJiang','127.0.0.1','2017-10-11 22:27:34','1','billJiang','127.0.0.1'),('557218c4ae9011e79b79a4c494befaeb','tester','$2a$12$4Bsf9nngdbUu9PbZ/xQAFu1HD98r2x0gPDBCqX50T81XshZduqCz6','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:27:39','1','billJiang','127.0.0.1','2017-10-11 22:27:39','1','billJiang','127.0.0.1'),('591eb1beae9011e79b79a4c494befaeb','tester','$2a$12$wGF6bWyGgvarFk1KhkfkB.kJNH89cs0CLYQi1196MtitOmevYVWaK','tester','','',NULL,NULL,NULL,'男',NULL,NULL,NULL,'2017-10-11 22:27:45','1','billJiang','127.0.0.1','2017-10-11 22:27:45','1','billJiang','127.0.0.1'),('6157f1d6ae9011e79b79a4c494befaeb','tester','$2a$12$JNWmnW21vT38iZfi3DOeRuj0zpWA5MyGqAry9GiFTs5EZpRyA5ovK','tester','','',NULL,NULL,NULL,'男',NULL,NULL,'tester','2017-10-11 22:27:59','1','billJiang','127.0.0.1','2017-10-11 22:46:07','1','billJiang','127.0.0.1'),('6497bf19ae9011e79b79a4c494befaeb','tester','$2a$12$0xOxb8ER04Eq1356HGHgZeOxgalwBlq22MsoEQuuFM183UDOnW2qC','tester','','',NULL,NULL,NULL,'男',NULL,NULL,'test','2017-10-11 22:28:04','1','billJiang','127.0.0.1','2017-10-12 14:34:42','1','billJiang','127.0.0.1');
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

-- Dump completed on 2017-10-23 10:30:59
