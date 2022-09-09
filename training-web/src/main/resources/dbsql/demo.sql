/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 11/11/2021 11:46:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `money` decimal(25, 2) DEFAULT 0.00,
  `created_by_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime(0) NOT NULL,
  `updated_by_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  `deleted_by_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deleted_at` datetime(0) NULL DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx-uni-login-name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES (1, '3232', 'test666', 10.00, '2a87989a-171d-4766-8462-971bade45083', '2021-10-12 15:11:53', '2a87989a-171d-4766-8462-971bade45083', '2021-10-12 15:12:52', NULL, NULL, 0);
INSERT INTO `demo` VALUES (2, 'test666', 'test666', 10.00, '2a87989a-171d-4766-8462-971bade45083', '2021-10-12 16:42:44', NULL, NULL, NULL, NULL, 0);
INSERT INTO `demo` VALUES (14, '12323', '1111232', 10.00, '2a87989a-171d-4766-8462-971bade45083', '2021-10-22 16:12:13', NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
