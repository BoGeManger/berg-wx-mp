CREATE TABLE `mp_short_url_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号短链接表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`long_url` varchar(255) NOT NULL COMMENT '长链接',
`short_url` varchar(255) DEFAULT NULL COMMENT '短链接',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '创建用户',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信公众号短链接表';