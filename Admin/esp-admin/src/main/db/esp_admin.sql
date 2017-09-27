/*
 Navicat Premium Data Transfer

 Source Server         : 15
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 192.168.1.15:3306
 Source Schema         : esp_admin

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 27/09/2017 08:43:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `admin_dictionary`;
CREATE TABLE `admin_dictionary`  (
  `id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `code` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '唯一校验',
  `level_code` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `deleted` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT 'Y表示删除 N表示未删除',
  `parentid` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '上级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log`  (
  `id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `userid` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `username` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `opt_ip` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '操作ip',
  `opt_time` date DEFAULT NULL COMMENT '操作时间',
  `opt_type` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '操作类型：删除，修改，新增',
  `note` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '备注',
  `menu` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '操作菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_org_admin
-- ----------------------------
DROP TABLE IF EXISTS `admin_org_admin`;
CREATE TABLE `admin_org_admin`  (
  `id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `code` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '编码  唯一校验  ',
  `level_code` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '层级编码',
  `org_type` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '数据字典ID',
  `deleted` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT 'Y删除 N不删除',
  `note` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '备注',
  `parentid` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '上级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `username` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `password` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `real_name` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '真实姓名',
  `deleted` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT 'Y 删除  N不删除 默认N',
  `birthday` date DEFAULT NULL,
  `email` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `telphone` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `office_phone` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `qq` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
