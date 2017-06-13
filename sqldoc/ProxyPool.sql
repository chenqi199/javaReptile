/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : ipproxy

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-06-13 09:58:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `proxypool`
-- ----------------------------
DROP TABLE IF EXISTS `proxypool`;
CREATE TABLE `proxypool` (
  `IPAdress` varchar(32) NOT NULL DEFAULT '' COMMENT 'ip信息表',
  `IPPort` varchar(8) DEFAULT NULL,
  `serverAddress` varchar(32) DEFAULT NULL,
  `IPType` varchar(32) DEFAULT NULL,
  `IPSpeed` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`IPAdress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proxypool
-- ----------------------------
