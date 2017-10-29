/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的权限表*/
CREATE TABLE `sys_permission` (
  `uuid`            VARCHAR(32) NOT NULL,
  `code`            VARCHAR(64) NOT NULL
  COMMENT '权限标识,如: systemmange:user:page',
  `permission_name` VARCHAR(40) DEFAULT NULL
  COMMENT '权限名称',
  `remark`          VARCHAR(50) DEFAULT NULL
  COMMENT '备注信息',
  PRIMARY KEY (`uuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='权限表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的用户表*/
CREATE TABLE `sys_user` (
  `uuid`        VARCHAR(32) NOT NULL
  COMMENT '主键',
  `login_name`  VARCHAR(40) NOT NULL
  COMMENT '用户登陆名',
  `pwd`         VARCHAR(64) NOT NULL
  COMMENT '登陆密码',
  `real_name`   VARCHAR(20)  DEFAULT NULL
  COMMENT '姓名',
  `emp_no`      VARCHAR(40)  DEFAULT NULL
  COMMENT '工号',
  `org_id`      VARCHAR(32)  DEFAULT NULL
  COMMENT '所属部门',
  `telephone`   VARCHAR(40)  DEFAULT NULL
  COMMENT '电话',
  `mobile`      VARCHAR(40)  DEFAULT NULL
  COMMENT '手机',
  `email`       VARCHAR(40)  DEFAULT NULL
  COMMENT '邮箱',
  `qq`          VARCHAR(20)  DEFAULT NULL
  COMMENT 'QQ号码',
  `create_time` DATETIME     DEFAULT NULL
  COMMENT '添加时间',
  `status`      VARCHAR(2)   DEFAULT NULL
  COMMENT '用户状态:(-1 锁定,0 正常)',
  `delete_flag` CHAR(1)      DEFAULT '0'
  COMMENT '0 未删除，1 已删除',
  `remark`      VARCHAR(512) DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`uuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='系统用户';


/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的角色表*/
CREATE TABLE `sys_role` (
  `uuid`        VARCHAR(32) NOT NULL
  COMMENT '主键',
  `code`        VARCHAR(64)  DEFAULT NULL
  COMMENT '角色标识，如admin/manager等',
  `role_name`   VARCHAR(16) NOT NULL
  COMMENT '角色名',
  `create_time` DATETIME     DEFAULT NULL
  COMMENT '添加时间',
  `delete_flag` CHAR(1)      DEFAULT '0'
  COMMENT '0 未删除，1 已删除',
  `remark`      VARCHAR(512) DEFAULT NULL
  COMMENT '备注'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='角色表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的 角色-权限 表*/
CREATE TABLE `sys_role_permission` (
  `uuid`          VARCHAR(32) NOT NULL
  COMMENT '主键',
  `role_id`       VARCHAR(32) NOT NULL
  COMMENT '角色主键',
  `permission_id` VARCHAR(32) NOT NULL
  COMMENT '权限主键',
  PRIMARY KEY (`uuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='角色权限关联表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的 用户-角色 表*/
CREATE TABLE `sys_user_role` (
  `uuid`    VARCHAR(32) NOT NULL
  COMMENT '主键',
  `user_id` VARCHAR(32) NOT NULL
  COMMENT '用户主键',
  `role_id` VARCHAR(32) NOT NULL
  COMMENT '角色主键',
  PRIMARY KEY (`uuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='用户角色关联表';

/*RBAC(基于资源的访问控制)(Resource-Based Access Control)模型的菜单表*/
CREATE TABLE `sys_resource` (
  `uuid`        VARCHAR(32)  NOT NULL
  COMMENT '主键',
  `code`        VARCHAR(64)  NOT NULL
  COMMENT '资源标识码',
  `name`        VARCHAR(40)  NOT NULL
  COMMENT '资源名称',
  `url`         VARCHAR(512) NOT NULL
  COMMENT '标识资源的url',
  `parent_id`   VARCHAR(32)  DEFAULT NULL
  COMMENT '父级ID',
  `icon`        VARCHAR(256) DEFAULT NULL
  COMMENT '图标',
  `sort`        INT(4)       DEFAULT NULL
  COMMENT '排序值',
  `create_time` DATETIME     DEFAULT NULL
  COMMENT '添加时间',
  `delete_flag` CHAR(1)      DEFAULT '0'
  COMMENT '0 未删除，1 已删除',
  `remark`      VARCHAR(512) DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`uuid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='菜单表';

ALTER TABLE sys_resource ADD UNIQUE INDEX idx_uuid(uuid);
ALTER TABLE sys_permission ADD UNIQUE INDEX idx_uuid(uuid);

/*权限和资源的关联表*/
CREATE TABLE sys_resource_permission (
  id            VARCHAR(32) NOT NULL PRIMARY KEY COMMENT '资源-权限关系表的主id',
  resource_id   VARCHAR(32) NOT NULL COMMENT '资源的id',
  permission_id VARCHAR(32) NOT NULL COMMENT '权限的id'
);