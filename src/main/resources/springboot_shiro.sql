/*
Navicat MySQL Data Transfer

Source Server         : loacalhost
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : springboot_shiro

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-06-25 15:44:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `current_menu` varchar(255) DEFAULT NULL,
  `second_menu` varchar(32) DEFAULT NULL,
  `first_menu` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` varchar(32) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('20180606112041523135168', '0', '权限管理', '系统信息', '系统管理', 'system', '0', '/admin/sysPermission');
INSERT INTO `sys_permission` VALUES ('20180606111529514135168', '0', '角色管理', '系统信息', '系统管理', 'system', '0', '/admin/sysRole');
INSERT INTO `sys_permission` VALUES ('20180606111710573135168', '0', '角色权限关联', '系统信息', '系统管理', 'system', '0', '/admin/rolePermission');
INSERT INTO `sys_permission` VALUES ('20180606111847416135168', '0', '系统用户', '系统信息', '系统管理', 'system', '0', '/admin/userInfo');
INSERT INTO `sys_permission` VALUES ('20180606111929101135168', '0', '用户角色关联', '系统信息', '系统管理', 'system', '0', '/admin/userRole');
INSERT INTO `sys_permission` VALUES ('20180606115635526135168', '0', '商户详情', '商户信息', '商户管理', 'trader', '0', '/trader/query');
INSERT INTO `sys_permission` VALUES ('20180606115802219135168', '0', '商户业务', '商户信息', '商户管理', 'trader', '0', '/traderBusi/query');
INSERT INTO `sys_permission` VALUES ('20180606120024386135168', '0', '订单查询', '订单信息', '订单管理', 'order', '0', '/order/query');
INSERT INTO `sys_permission` VALUES ('20180606120236426135168', '0', '还款流水查询', '订单信息', '订单管理', 'order', '0', '/repayFlow/query');
INSERT INTO `sys_permission` VALUES ('20180606120807497135168', '0', '用户查询', '用户信息', '用户管理', 'user', '0', '/user/query');
INSERT INTO `sys_permission` VALUES ('20180606120919041135168', '0', '用户额度', '用户信息', '用户管理', 'user', '0', '/userLimit/query');
INSERT INTO `sys_permission` VALUES ('20180606121003685135168', '0', '逾期信息', '用户信息', '用户管理', 'user', '0', '/userOverdue/query');
INSERT INTO `sys_permission` VALUES ('20180606121923951135168', '0', '渠道查询', '渠道管理', '催收管理', 'recycleChannel', '0', '/recycleChannel/query');
INSERT INTO `sys_permission` VALUES ('20180606152238830135168', '0', '委案分配', '渠道管理', '催收管理', 'recycleChannel', '0', '/batchGate/delegate');
INSERT INTO `sys_permission` VALUES ('20180607104604809135168', '0', '委案查询', '渠道管理', '催收管理', 'recycleChannel', '0', '/batchGate/batchQuery');
INSERT INTO `sys_permission` VALUES ('20180608140753937135168', '0', '委案反馈', '渠道管理', '催收管理', 'recycleChannel', '0', '/recycleTrack/query');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `available` varchar(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('222', '0', '管理员角色', 'admin');
INSERT INTO `sys_role` VALUES ('20180606113258047135168', '0', '商户管理', 'trader');
INSERT INTO `sys_role` VALUES ('20180606113308724135168', '0', '用户管理', 'user');
INSERT INTO `sys_role` VALUES ('20180606113329579135168', '0', '订单管理', 'order');
INSERT INTO `sys_role` VALUES ('20180621111349737135168', '0', '催收管理', 'recycleChannel');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permission_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('20180606152238830135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180608140753937135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180607104604809135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606121003685135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606120919041135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606120807497135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606120236426135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606120024386135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606115802219135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606115635526135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606111929101135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606111847416135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606111710573135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606111529514135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606112041523135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606121923951135168', '222');
INSERT INTO `sys_role_permission` VALUES ('20180606115635526135168', '20180606113258047135168');
INSERT INTO `sys_role_permission` VALUES ('20180606115802219135168', '20180606113258047135168');
INSERT INTO `sys_role_permission` VALUES ('20180606120807497135168', '20180606113308724135168');
INSERT INTO `sys_role_permission` VALUES ('20180606120024386135168', '20180606113329579135168');
INSERT INTO `sys_role_permission` VALUES ('20180606120236426135168', '20180606113329579135168');
INSERT INTO `sys_role_permission` VALUES ('20180606121923951135168', '20180621111349737135168');
INSERT INTO `sys_role_permission` VALUES ('20180606152238830135168', '20180621111349737135168');
INSERT INTO `sys_role_permission` VALUES ('20180607104604809135168', '20180621111349737135168');
INSERT INTO `sys_role_permission` VALUES ('20180608140753937135168', '20180621111349737135168');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('11111', '222');
INSERT INTO `sys_user_role` VALUES ('20180606112430886135168', '20180606113329579135168');
INSERT INTO `sys_user_role` VALUES ('20180606112430886135168', '20180606113308724135168');
INSERT INTO `sys_user_role` VALUES ('20180606112430886135168', '20180621111349737135168');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` varchar(4) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('11111', 'admin', '708a9b57697027db5f5b6b189e64e2ce', '141ea50524d63336442af7660d0808d8', '0', 'admin');
INSERT INTO `user_info` VALUES ('20180606112430886135168', 'dev', 'a984c3a5e18b9c4e8db3a86d29fbb58f', '1452402966c4eb59fccf09a2ada5d0f5', '0', 'dev');
