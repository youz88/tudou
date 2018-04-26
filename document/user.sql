-- 导出 tudou 的数据库结构
CREATE DATABASE IF NOT EXISTS `tudou` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tudou`;

-- 导出  表 tudou.base_log 结构
CREATE TABLE IF NOT EXISTS `base_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
  `user_name` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作用户ID',
  `start_time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `spend_time` int(11) DEFAULT NULL COMMENT '消耗时间',
  `url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` mediumtext,
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `permissions` varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- 导出  表 tudou.base_permission 结构
CREATE TABLE IF NOT EXISTS `base_permission` (
  `permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` bigint(20) NOT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` int(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` varchar(50) DEFAULT NULL COMMENT '权限值',
  `uri` varchar(100) DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `enable` tinyint(1) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- 导出  表 tudou.base_role 结构
CREATE TABLE IF NOT EXISTS `base_role` (
  `role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- 导出  表 tudou.base_role_permission 结构
CREATE TABLE IF NOT EXISTS `base_role_permission` (
  `role_permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 导出  表 tudou.base_user 结构
CREATE TABLE IF NOT EXISTS `base_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `gender` int(1) DEFAULT NULL COMMENT '性别(0:女,1:男)',
  `is_lock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0:正常,1:锁定)',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- 导出  表 tudou.base_user_permission 结构
CREATE TABLE IF NOT EXISTS `base_user_permission` (
  `user_permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- 导出  表 tudou.base_user_role 结构
CREATE TABLE IF NOT EXISTS `base_user_role` (
  `user_role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

