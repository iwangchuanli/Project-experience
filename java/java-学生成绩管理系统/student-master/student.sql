DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
INSERT INTO `admin` VALUES (1,'admin','admin','admin');
UNLOCK TABLES;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sno` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `hometown` varchar(20) NOT NULL,
  `mark` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `sex` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

LOCK TABLES `student` WRITE;
INSERT INTO `student` VALUES (18,'张三','001','信息科学技术学院','辽宁','80','zhangsan@163.com','13888888888','男'),(19,'李四','002','理学院','上海','70','lisi@sina.com','13812341234','男'),(20,'王五','003','外国语学院','北京','88','wangwu@126.com','13698765432','女');
UNLOCK TABLES;