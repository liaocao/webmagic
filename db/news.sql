/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50726
Source Host           : 192.168.25.128:3306
Source Database       : jdspider

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-05 17:56:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` varchar(32) NOT NULL,
  `news_title` varchar(64) NOT NULL COMMENT '新闻标题',
  `news_summary` varchar(128) NOT NULL COMMENT '新闻摘要',
  `news_keyword` varchar(32) NOT NULL COMMENT '新闻关键字',
  `news_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新闻关键字',
  `news_url` varchar(64) NOT NULL COMMENT '新闻链接',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻表';

-- ----------------------------
-- Records of news
-- ----------------------------
