DROP DATABASE IF EXISTS `db_album`;
CREATE DATABASE `db_album` ;
USE `db_album`;

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) Not NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '用户名',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `mail` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(60) DEFAULT NULL COMMENT '电话号码',
  `role` varchar(40) DEFAULT 'ROLE_UP' COMMENT 'ROLE_UP,登录权限、ROLE_DOWN拒绝登录',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_album`;

CREATE TABLE `tb_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_album_user`;

CREATE TABLE `tb_album_user` (
  `album_id` int(11) NOT NULL COMMENT '相册ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role` varchar (40) DEFAULT NULL COMMENT 'ROLE_VIEW,查看权限、ROLE_ADD,添加权限、ROLE_DELETE,删除权限',
  CONSTRAINT `pk_albumuser_album` FOREIGN KEY(`album_id`) REFERENCES `tb_album`(`id`),
  CONSTRAINT `pk_albumuser_user` FOREIGN KEY(`user_id`) REFERENCES `tb_user`(`id`),
  UNIQUE KEY `index_album_user` (`album_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_photo_folder`;

CREATE TABLE `tb_photo_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(100) DEFAULT NULL COMMENT '照片组标题',
  `user_id` int(11) DEFAULT NULL COMMENT '创建者',
  `album_id` int(11) DEFAULT NULL COMMENT '所属相册',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `store_path` varchar(300) DEFAULT NULL COMMENT '存储路径',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `photo_list` varchar(1000) DEFAULT NULL COMMENT '前4照片',
  `photo_count` int(11) DEFAULT NULL COMMENT '一共多少张',
  PRIMARY KEY (`id`),
    CONSTRAINT `pk_photofolder_user` FOREIGN KEY(`user_id`) REFERENCES `tb_user`(`id`),
    CONSTRAINT `pk_photofolder_album` FOREIGN KEY(`album_id`) REFERENCES `tb_album`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_photo`;

CREATE TABLE `tb_photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `guid` varchar(40) NOT NULL COMMENT 'GUID',
  `photo_folder_id` int(11) DEFAULT NULL COMMENT '所属照片组',
  `store_path` varchar(300) DEFAULT NULL COMMENT '存储路径',
  `real_name` varchar(300) DEFAULT NULL COMMENT '原始文件名',
  `suffix` varchar(10) DEFAULT NULL COMMENT '后缀',
  `name` varchar(90) DEFAULT NULL COMMENT '存储名',
  `thumb` varchar(90) DEFAULT NULL COMMENT '预览图名',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
    PRIMARY KEY (`id`),
    CONSTRAINT `pk_photo_photofolder` FOREIGN KEY(`photo_folder_id`) REFERENCES `tb_photo_folder`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;





