/*
 Navicat MySQL Data Transfer

 Source Server         : lou
 Source Server Type    : MySQL
 Source Server Version : 50612
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50612
 File Encoding         : 65001

 Date: 06/11/2019 16:42:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for info_user
-- ----------------------------
DROP TABLE IF EXISTS `info_user`;
CREATE TABLE `info_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nikename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `role` int(2) NULL DEFAULT NULL COMMENT '用户角色1.普通用户2.高级用户3.管理员4.系统管理员',
  `createdate` datetime(0) NULL DEFAULT NULL COMMENT '用户创建日期',
  `status` int(2) NULL DEFAULT NULL COMMENT '用户状态',
  `delstatus` int(2) NULL DEFAULT NULL COMMENT '删除状态',
  `deluser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除人',
  `deldate` datetime(0) NULL DEFAULT NULL COMMENT '删除日期',
  `moduser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `moddate` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of info_user
-- ----------------------------
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f64741f0000', 'admin1', 'admin1', 'admin1', 1, '2019-11-06 02:26:18', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f649b6f0001', 'admin2', 'admin2', 'admin2', 1, '2019-11-06 02:26:28', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f64c0aa0002', 'admin3', 'admin3', 'admin3', 2, '2019-11-06 02:26:37', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f64f8c10003', 'admin4', 'admin4', 'admin4', 2, '2019-11-06 02:26:52', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f654e380004', 'admin5', 'admin5', 'admin5', 2, '2019-11-06 02:27:14', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f657b7a0005', 'admin6', 'admin6', 'admin6', 2, '2019-11-06 02:27:25', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f659c410006', 'admin7', 'admin7', 'admin7', 2, '2019-11-06 02:27:34', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f65c4620007', 'admin8', 'admin8', 'admin8', 1, '2019-11-06 02:27:44', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f65e0800008', 'admin9', 'admin9', 'admin9', 1, '2019-11-06 02:27:51', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f6474016e3f65fe1f0009', 'admin10', 'admin10', 'admin10', 1, '2019-11-06 02:27:59', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f9933016e3f9933b60000', 'admin11', 'admin11', 'admin11', 1, '2019-11-06 03:23:55', 1, 1, 'admin', '2019-11-06 03:24:10', NULL, NULL);
INSERT INTO `info_user` VALUES ('4028b8816e3f9933016e3f9951fd0001', 'admin12', 'admin12', 'admin12', 2, '2019-11-06 03:24:02', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `info_user` VALUES ('admin', 'admin', 'admin', 'admin', 3, '2019-11-04 16:37:37', 1, 0, '', '2019-11-05 08:02:55', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
