CREATE TABLE `mp_activity_qrcode_tbl` (
`id` varchar(64) NOT NULL COMMENT '微信公众号活动二维码表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`name` varchar(20) NOT NULL COMMENT '活动名称',
`code` varchar(20) NOT NULL COMMENT '活动编码',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '活动描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '创建用户',
`modify_time` datetime NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '修改用户',
`del_time` datetime DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) unsigned zerofill NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信公众号活动二维码表';