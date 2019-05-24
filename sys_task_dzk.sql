/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : 127.0.0.1:3306
 Source Schema         : bootdo

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 23/05/2019 08:16:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_task_dzk
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_dzk`;
CREATE TABLE `sys_task_dzk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task_dzk
-- ----------------------------
BEGIN;
INSERT INTO `sys_task_dzk` VALUES (1, '0/1 * * * * ? *', 'com.bootdo.common.task.WelcomeJob', '2017-05-19
18:30:56', 'stop', 'group1', '2017-05-19 18:31:07', 'welcomJob');
INSERT INTO `sys_task_dzk` VALUES (2, '0/3 * * * * ? *', 'com.bootdo.common.task.WelcomeJob', '2017-05-19
18:30:56', 'stop', 'group2', NULL, 'welcomJob');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
