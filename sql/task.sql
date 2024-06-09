/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3305
 Source Schema         : task

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 09/06/2024 16:10:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '5bb=', 'fb151fa9-223a-11ef-8418-f48e38f5cf3f', '2024-06-04 14:23:38', '2024-06-09 15:57:18', '2024-06-09 15:57:18', b'1');
INSERT INTO `task` VALUES (4, 'a2', '44f74225-225c-11ef-8418-f48e38f5cf3f', '2024-06-04 18:21:56', '2024-06-09 00:08:10', '2024-06-09 00:08:10', b'1');
INSERT INTO `task` VALUES (5, 'a3', 'b3287da8-227c-11ef-8418-f48e38f5cf3f', '2024-06-04 22:14:04', '2024-06-09 00:06:58', '2024-06-09 00:06:58', b'1');
INSERT INTO `task` VALUES (6, 'a6', '8dba2357-8e1a-48f0-b77c-9bcd71a7fc15', '2024-06-05 01:20:02', '2024-06-09 00:07:41', '2024-06-09 00:07:41', b'1');
INSERT INTO `task` VALUES (7, '一个任务', '19506458-2297-11ef-8418-f48e38f5cf3f', '2024-06-05 01:23:03', '2024-06-09 00:55:40', '2024-06-09 00:55:40', b'1');
INSERT INTO `task` VALUES (9, 'TEST', 'c797c8c7-2297-11ef-8418-f48e38f5cf3f', '2024-06-05 01:27:55', '2024-06-08 23:12:28', '2024-06-08 23:02:00', b'0');
INSERT INTO `task` VALUES (10, 'AA=', '71dccfdf-2308-11ef-8418-f48e38f5cf3f', '2024-06-05 14:54:25', '2024-06-09 02:45:31', NULL, b'0');
INSERT INTO `task` VALUES (11, 's', '23b7d5c2-24da-11ef-8418-f48e38f5cf3f', '2000-03-27 15:52:30', '2024-06-08 23:12:30', '2012-01-31 07:38:52', b'0');
INSERT INTO `task` VALUES (12, 'w', 'c69b5e11-24dd-11ef-8418-f48e38f5cf3f', '2024-06-07 22:54:01', '2024-06-08 23:13:16', '2024-06-08 23:13:16', b'1');
INSERT INTO `task` VALUES (13, 'Aa', 'cb536111-262d-11ef-a90e-f48e38f5cf3f', '2024-06-09 14:59:20', '2024-06-09 15:57:26', '2024-06-09 15:57:26', b'1');
INSERT INTO `task` VALUES (14, 'bb', '6484d958-262e-11ef-a90e-f48e38f5cf3f', '2024-06-09 15:03:37', '2024-06-09 15:03:37', NULL, b'0');
INSERT INTO `task` VALUES (15, 'cc', '8111b9a4-262e-11ef-a90e-f48e38f5cf3f', '2024-06-09 15:04:25', '2024-06-09 15:04:25', NULL, b'0');
INSERT INTO `task` VALUES (16, 'abc', '0ecb0adc-2633-11ef-a90e-f48e38f5cf3f', '2024-06-09 15:37:01', '2024-06-09 15:57:41', '2024-06-09 15:57:41', b'1');
INSERT INTO `task` VALUES (17, 'abcd', '1bfe851e-2633-11ef-a90e-f48e38f5cf3f', '2024-06-09 15:37:23', '2024-06-09 15:57:35', '2024-06-09 15:57:35', b'1');
INSERT INTO `task` VALUES (18, '新', 'e6c07764-2634-11ef-a90e-f48e38f5cf3f', '2024-06-09 15:50:12', '2024-06-09 15:50:12', NULL, b'0');

-- ----------------------------
-- Triggers structure for table task
-- ----------------------------
DROP TRIGGER IF EXISTS `code_uuid_task`;
delimiter ;;
CREATE TRIGGER `code_uuid_task` BEFORE INSERT ON `task` FOR EACH ROW BEGIN
	SET new.CODE = UUID();
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
