SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `base_operation_log`;
CREATE TABLE `base_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `start_time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `spend_time` int(11) DEFAULT NULL COMMENT '消耗时间',
  `base_path` varchar(500) DEFAULT NULL COMMENT '根路径',
  `uri` varchar(500) DEFAULT NULL COMMENT 'URI',
  `url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` mediumtext,
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `result` mediumtext,
  `permissions` varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Records of base_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for base_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_permission`;
CREATE TABLE `base_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` bigint(20) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` int(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `method` varchar(50) DEFAULT NULL COMMENT '权限方法(get,head,post,put,delete)',
  `url` varchar(100) DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` int(4) DEFAULT NULL COMMENT '状态(0:禁用,1:正常)',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `create_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `modify_time` bigint(20) NOT NULL COMMENT '修改时间',
  `modify_id` bigint(20) NOT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of base_permission
-- ----------------------------
INSERT INTO `base_permission` VALUES ('1', '0', '权限管理', '1', null, null, 'layui-icon-user', '1', '1', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('2', '1', '角色列表', '2', null, '/role/list.html', '', '1', '2', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('3', '1', '用户列表', '2', null, '/user/list.html', '', '1', '1', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('4', '1', '权限列表', '2', null, '/permission/list.html', '', '1', '3', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('5', '2', '编辑角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('6', '2', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('7', '2', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('8', '2', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('9', '3', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('10', '3', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('11', '3', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('12', '3', '编辑用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('13', '3', '编辑用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('14', '4', '新增权限', '3', 'post', '/permission', '', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('15', '4', '编辑权限', '3', 'put', '/permission', '', '1', '0', '1488120554175', '1', '1488120554175', '1');
INSERT INTO `base_permission` VALUES ('16', '4', '删除权限', '3', 'delete', '/permission', '', '1', '0', '1488120554175', '1', '1488120554175', '1');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `title` varchar(20) DEFAULT NULL COMMENT '角色标题',
  `description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `orders` bigint(20) NOT NULL COMMENT '排序',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `create_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `modify_time` bigint(20) NOT NULL COMMENT '修改时间',
  `modify_id` bigint(20) NOT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', 'super', '超级管理员', '拥有所有权限', '1', '1553415260955', '1', '1553415260955', '1');
INSERT INTO `base_role` VALUES ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '2', '1553415260955', '1', '1553415260955', '1');

-- ----------------------------
-- Table structure for base_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_role_permission`;
CREATE TABLE `base_role_permission` (
  `role_permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`),
  CONSTRAINT `base_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `base_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of base_role_permission
-- ----------------------------
INSERT INTO `base_role_permission` VALUES ('1', '1', '1');
INSERT INTO `base_role_permission` VALUES ('2', '1', '2');
INSERT INTO `base_role_permission` VALUES ('3', '1', '3');
INSERT INTO `base_role_permission` VALUES ('4', '1', '4');
INSERT INTO `base_role_permission` VALUES ('5', '1', '5');
INSERT INTO `base_role_permission` VALUES ('6', '1', '6');
INSERT INTO `base_role_permission` VALUES ('7', '1', '7');
INSERT INTO `base_role_permission` VALUES ('8', '1', '8');
INSERT INTO `base_role_permission` VALUES ('9', '1', '9');
INSERT INTO `base_role_permission` VALUES ('10', '1', '10');
INSERT INTO `base_role_permission` VALUES ('11', '1', '11');
INSERT INTO `base_role_permission` VALUES ('12', '1', '12');
INSERT INTO `base_role_permission` VALUES ('13', '1', '13');
INSERT INTO `base_role_permission` VALUES ('14', '1', '14');
INSERT INTO `base_role_permission` VALUES ('15', '1', '15');
INSERT INTO `base_role_permission` VALUES ('16', '1', '16');

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(128) NOT NULL COMMENT '密码MD5(密码+盐)',
  `realname` varchar(20) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `gender` int(4) DEFAULT NULL COMMENT '性别',
  `status` int(4) DEFAULT NULL COMMENT '状态(0:禁用,1:正常)',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `create_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `modify_time` bigint(20) NOT NULL COMMENT '修改时间',
  `modify_id` bigint(20) NOT NULL COMMENT '修改用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', 'super', 'k1Q4BdDbb1X3u1H6L3cd11c0ee/6AfM7leJe/dXf4bwd=1=8', '超级管理员', null, null, '13000000000', '2333@qq.com', '1', '1', '1537594348498', '1', '1538646535807', '1');
INSERT INTO `base_user` VALUES ('2', 'admin', 'k1Q4BdDbb1X3u1H6L3cd11c0ee/6AfM7leJe/dXf4bwd=1=8', '管理员', null, null, '13000000000', '123@163.com', '1', '1', '1537594348498', '1', '1538646535807', '1');

-- ----------------------------
-- Table structure for base_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_user_permission`;
CREATE TABLE `base_user_permission` (
  `user_permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限编号',
  `type` int(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- ----------------------------
-- Records of base_user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `user_role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('1', '1', '1');
