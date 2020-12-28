/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : railway

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 27/12/2020 23:11:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for baluster
-- ----------------------------
DROP TABLE IF EXISTS `baluster`;
CREATE TABLE `baluster`  (
  `balusterId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '栏木机编号',
  `balusterName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '栏木机名称',
  `balusterStatus` tinyint(2) UNSIGNED ZEROFILL NOT NULL COMMENT '栏木机状态',
  `crossingId` int(10) NOT NULL COMMENT '道口id',
  PRIMARY KEY (`balusterId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of baluster
-- ----------------------------
INSERT INTO `baluster` VALUES (1, 'A1', 01, 1);
INSERT INTO `baluster` VALUES (2, 'A2', 01, 1);
INSERT INTO `baluster` VALUES (3, 'A3', 01, 1);
INSERT INTO `baluster` VALUES (4, 'A4', 01, 1);
INSERT INTO `baluster` VALUES (5, 'A5', 01, 1);
INSERT INTO `baluster` VALUES (6, 'A6', 01, 1);
INSERT INTO `baluster` VALUES (7, 'A7', 01, 1);
INSERT INTO `baluster` VALUES (8, 'A8', 01, 1);
INSERT INTO `baluster` VALUES (9, 'B1', 00, 2);
INSERT INTO `baluster` VALUES (10, 'B2', 00, 2);
INSERT INTO `baluster` VALUES (11, 'B3', 00, 2);
INSERT INTO `baluster` VALUES (12, 'B4', 00, 2);
INSERT INTO `baluster` VALUES (13, 'B5', 00, 2);
INSERT INTO `baluster` VALUES (14, 'B6', 00, 2);
INSERT INTO `baluster` VALUES (15, 'B7', 00, 2);
INSERT INTO `baluster` VALUES (16, 'C1', 00, 3);
INSERT INTO `baluster` VALUES (17, 'C2', 00, 3);

-- ----------------------------
-- Table structure for crossing
-- ----------------------------
DROP TABLE IF EXISTS `crossing`;
CREATE TABLE `crossing`  (
  `crossingId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '路口编号',
  `crossingName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路口名称',
  `crossingGPS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路口GPS',
  `crossingMode` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '路口状态(自动0，手动1)',
  PRIMARY KEY (`crossingId`) USING BTREE,
  INDEX `crossingId`(`crossingId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crossing
-- ----------------------------
INSERT INTO `crossing` VALUES (1, '凌志西', NULL, 0);
INSERT INTO `crossing` VALUES (2, '石头村', NULL, 0);
INSERT INTO `crossing` VALUES (3, '石头油库', NULL, 0);

-- ----------------------------
-- Table structure for instruction
-- ----------------------------
DROP TABLE IF EXISTS `instruction`;
CREATE TABLE `instruction`  (
  `InstructionId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '指令编号',
  `userId` int(10) NOT NULL COMMENT '用户编号',
  `crossingId` int(10) NOT NULL COMMENT '道口编号',
  `locomotiveId` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机车编号',
  `instructionContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '指令内容',
  `instructionTime` datetime(0) DEFAULT NULL COMMENT '指令发布时间',
  `passTime` int(11) DEFAULT NULL COMMENT '机车通过时间（秒）',
  `valid` tinyint(1) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '是否有效，0为无效，1为有效',
  PRIMARY KEY (`InstructionId`) USING BTREE,
  INDEX `crossingId`(`crossingId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instruction
-- ----------------------------
INSERT INTO `instruction` VALUES (1, 1, 1, '1', '允许通过', '2020-12-15 21:57:44', 10, 1);

-- ----------------------------
-- Table structure for locomotive
-- ----------------------------
DROP TABLE IF EXISTS `locomotive`;
CREATE TABLE `locomotive`  (
  `locomotiveId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '机车编号',
  `locomotiveName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机车名称',
  `locomotiveGPS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机车GPS位置',
  `nextCrossingId` int(10) UNSIGNED DEFAULT NULL COMMENT '前方道口',
  `trackId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '股道编码',
  `responseStatus` tinyint(1) UNSIGNED DEFAULT NULL COMMENT '机车应答状态(0为不通过，1为通过)',
  PRIMARY KEY (`locomotiveId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of locomotive
-- ----------------------------
INSERT INTO `locomotive` VALUES (1, '东风0313', NULL, NULL, 'E3A', 0);

-- ----------------------------
-- Table structure for railwayblinker
-- ----------------------------
DROP TABLE IF EXISTS `railwayblinker`;
CREATE TABLE `railwayblinker`  (
  `railwayBlinkerId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '铁路同行信号灯编码',
  `railwayBlinkerName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '铁路同行信号灯名称',
  `railwayBlinkerStatus` tinyint(2) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '铁路同行信号灯状态',
  `crossingId` int(10) DEFAULT NULL COMMENT '道口编号',
  PRIMARY KEY (`railwayBlinkerId`) USING BTREE,
  INDEX `crossingId`(`crossingId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of railwayblinker
-- ----------------------------
INSERT INTO `railwayblinker` VALUES (1, '遮断信号灯1', 01, 1);
INSERT INTO `railwayblinker` VALUES (2, '遮断信号灯2', 01, 1);
INSERT INTO `railwayblinker` VALUES (3, '遮断信号灯1', 00, 2);
INSERT INTO `railwayblinker` VALUES (4, '遮断信号灯2', 00, 2);
INSERT INTO `railwayblinker` VALUES (5, '遮断信号灯1', 00, 3);
INSERT INTO `railwayblinker` VALUES (6, '遮断信号灯2', 00, 3);

-- ----------------------------
-- Table structure for roadblinker
-- ----------------------------
DROP TABLE IF EXISTS `roadblinker`;
CREATE TABLE `roadblinker`  (
  `roadBlinkerId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公路信号灯编码',
  `roadBlinkerName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公路信号灯名称',
  `roadBlinkerStatus` tinyint(2) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '公路信号灯状态',
  `crossingId` int(10) DEFAULT NULL COMMENT '道口编号',
  PRIMARY KEY (`roadBlinkerId`) USING BTREE,
  INDEX `crossingId`(`crossingId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roadblinker
-- ----------------------------
INSERT INTO `roadblinker` VALUES (1, '信号灯1', 00, 1);
INSERT INTO `roadblinker` VALUES (2, '信号灯2', 00, 1);
INSERT INTO `roadblinker` VALUES (3, '信号灯1', 01, 2);
INSERT INTO `roadblinker` VALUES (4, '信号灯2', 01, 2);
INSERT INTO `roadblinker` VALUES (5, '信号灯1', 00, 3);
INSERT INTO `roadblinker` VALUES (6, '信号灯2', 00, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userAccount` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账户',
  `userName` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐值',
  `lastLoginTime` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  `lastLoginLength` int(20) UNSIGNED DEFAULT NULL COMMENT '最后登录时长（秒）',
  PRIMARY KEY (`userId`, `userAccount`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '271880', NULL, '6B2E362E4B344941937D461553C7A578', '39322103-CD73-4D50-8CEB-C3E6746931EF', NULL, NULL);
INSERT INTO `user` VALUES (5, '271881', NULL, '9D4A0F970CBE0429DECAB7468D5ADEDA', '20D49FFE-EFC7-4C27-A5AF-69D02B0CD9D0', NULL, NULL);

-- ----------------------------
-- Table structure for warning
-- ----------------------------
DROP TABLE IF EXISTS `warning`;
CREATE TABLE `warning`  (
  `warningId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '警报id',
  `warningContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '警报内容',
  `crossingId` int(10) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '道口id(若为0则为RFID错误）',
  PRIMARY KEY (`warningId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
