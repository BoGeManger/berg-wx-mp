CREATE TABLE `mp_menu_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号菜单表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`menu` text NOT NULL COMMENT '菜单记录',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '创建用户',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信公众号菜单表';