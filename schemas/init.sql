/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的权限表*/
CREATE TABLE `sys_permission` (
  `uuid` varchar(32) NOT NULL,
  `code` varchar(64) NOT NULL COMMENT '权限标识,如: systemmange:user:page',
  `permission_name` varchar(40) DEFAULT NULL COMMENT '权限名称',
  `remark` VARCHAR(50) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的用户表*/
CREATE TABLE `sys_user` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `login_name` varchar(40) NOT NULL COMMENT '用户登陆名',
  `pwd` varchar(64) NOT NULL COMMENT '登陆密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `emp_no` varchar(40) DEFAULT NULL COMMENT '工号',
  `org_id` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `telephone` varchar(40) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(40) DEFAULT NULL COMMENT '手机',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` varchar(2) DEFAULT NULL COMMENT '用户状态:(-1 锁定,0 正常)',
  `delete_flag` char(1) DEFAULT '0' COMMENT '0 未删除，1 已删除',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';


/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的角色表*/
CREATE TABLE `sys_role` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(64) DEFAULT NULL COMMENT '角色标识，如admin/manager等',
  `role_name` varchar(16) NOT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `delete_flag` char(1) DEFAULT '0' COMMENT '0 未删除，1 已删除',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的 角色-权限 表*/
CREATE TABLE `sys_role_permission` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `role_id` varchar(32) NOT NULL COMMENT '角色主键',
  `permission_id` varchar(32) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的 用户-角色 表*/
CREATE TABLE `sys_user_role` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户主键',
  `role_id` varchar(32) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的菜单表*/
CREATE TABLE `sys_resource` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(64) NOT NULL COMMENT '资源标识码',
  `name` varchar(40) NOT NULL COMMENT '资源名称',
  `url` varchar(512) NOT NULL COMMENT '标识资源的url',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标',
  `sort` int(4) DEFAULT NULL COMMENT '排序值',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `delete_flag` char(1) DEFAULT '0' COMMENT '0 未删除，1 已删除',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
