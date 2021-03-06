CREATE TABLE `sys_role_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统角色表id',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `remark` varchar(255) COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建用户',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `modify_user` varchar(20) NOT NULL COMMENT '修改用户',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_user` varchar(20) DEFAULT NULL COMMENT '删除用户',
  `isdel` tinyint(5) unsigned zerofill NOT NULL COMMENT '是否删除(0 否,1 是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';