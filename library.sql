/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-12-24 13:50:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_code`
-- ----------------------------
DROP TABLE IF EXISTS `admin_code`;
CREATE TABLE `admin_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_code
-- ----------------------------
INSERT INTO `admin_code` VALUES ('1', '12345678', '0');
INSERT INTO `admin_code` VALUES ('2', '输入这个神奇的密钥你就可以注册了', '1');

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'admin', '花花');
INSERT INTO `t_admin` VALUES ('2', 'demaxiya', 'demaxiya', '德玛西亚之力');
INSERT INTO `t_admin` VALUES ('3', 'guoqilin', 'guoqilin', '郭麒麟');

-- ----------------------------
-- Table structure for `t_books`
-- ----------------------------
DROP TABLE IF EXISTS `t_books`;
CREATE TABLE `t_books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(30) NOT NULL,
  `author` varchar(30) NOT NULL,
  `number` bigint(20) NOT NULL,
  `borrow` varchar(10) NOT NULL,
  `location` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_books
-- ----------------------------
INSERT INTO `t_books` VALUES ('1', '单片机原理及应用--基于Proteus和KeilC', '林立、张俊亮', '1234567891', '可以借阅', '二楼203');
INSERT INTO `t_books` VALUES ('2', 'Java语言程序设计', '胡森、李润荣', '8546564956', '已被借出', '三楼305');
INSERT INTO `t_books` VALUES ('3', '语文（大学）', '王花花、李莫愁', '154685123456', '已被借出', '四楼408');
INSERT INTO `t_books` VALUES ('7', '数据库原理及应用教程', '陈志泊', '987998956564', '可以借阅', '109');
INSERT INTO `t_books` VALUES ('8', '高等数学（上）', '同济大学教学系', '1212121212', '可以借阅', '图书馆301');
INSERT INTO `t_books` VALUES ('9', '高等数学（下）', '同济大学教学系', '1212121213', '可以借阅', '图书馆301');
INSERT INTO `t_books` VALUES ('10', '母猪的产后护理', '母猪出版社编', '797979797979', '可以借阅', '四楼的犄角旮旯');
INSERT INTO `t_books` VALUES ('12', '守塔与补刀', 'theshy', '5464686465456', '可以借阅', '505');
INSERT INTO `t_books` VALUES ('13', '郭麒麟奋斗史', '郭麒麟', '998998998', '可以借阅', '333');
INSERT INTO `t_books` VALUES ('14', '我为什么这么强？', 'god', '68686868', '已被借出', '465');
INSERT INTO `t_books` VALUES ('15', '英语', 'LiHua', '4948156456', '已被借出', '图书馆三楼的犄角旮旯');
INSERT INTO `t_books` VALUES ('17', '混子的素养', '混', '2222', '已被借出', '323');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '12345', '12345', '作妖');
INSERT INTO `t_user` VALUES ('2', 'demaxiya', 'demaxiya', '德玛西亚之力');
INSERT INTO `t_user` VALUES ('3', 'aiouniya', 'aiouniya', '艾欧尼亚');
INSERT INTO `t_user` VALUES ('5', 'demaxiyaa', 'demaxiya', 'sadasd');
INSERT INTO `t_user` VALUES ('6', 'guodegang', 'guodegang', '郭德纲');
