/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2011-12-02 11:50:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tags`
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES ('28', '姓名^%aAfnVNla&^王青云', '33');
INSERT INTO `tags` VALUES ('29', 'nickname^%aAfnVNla&^rourou', '33');
INSERT INTO `tags` VALUES ('30', 'a^%aAfnVNla&^a', '34');
INSERT INTO `tags` VALUES ('31', 'a^%aAfnVNla&^a', '35');
INSERT INTO `tags` VALUES ('32', 'feature^%aAfnVNla&^pretty boy', '36');
INSERT INTO `tags` VALUES ('33', 'feature^%aAfnVNla&^sleep', '37');
INSERT INTO `tags` VALUES ('34', 'feature^%aAfnVNla&^stupid', '38');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('33', 'flacro', '1', '1322550944060-7113.jpg', '2008');
INSERT INTO `users` VALUES ('34', 'qiqi', '1', '1322735517757-7113.jpg', '2008');
INSERT INTO `users` VALUES ('35', 'bv', '1', '1322735550273-7113.jpg', '2008');
INSERT INTO `users` VALUES ('36', '金毛', '1', '1322746095853-7113.jpg', '2008');
INSERT INTO `users` VALUES ('37', 'ruirui', '1', '1322746371878-7113.jpg', '2008');
INSERT INTO `users` VALUES ('38', 'maoqiu', '1', '1322746396578-7113.jpg', '2008');
