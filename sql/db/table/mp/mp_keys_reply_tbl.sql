CREATE TABLE `mp_keys_reply_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员公众号关键字自动回复表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`key_content` varchar(120) DEFAULT NULL COMMENT '关键词内容',
`reply_content` varchar(255) NOT NULL COMMENT '回复内容',
`reply_content_html` varchar(255) DEFAULT NULL COMMENT '回复内容html格式',
`media_id` varchar(120) DEFAULT NULL COMMENT '素材媒体id',
`url` varchar(255) DEFAULT NULL COMMENT '图文类型点击跳转链接',
`pic_url` varchar(255) DEFAULT NULL COMMENT '图文类型封面图片链接',
`reply_type` tinyint(5) NOT NULL COMMENT '回复内容类型(0 文本 1 图文 2 素材)',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '创建用户',
`modify_time` datetime NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '修改用户',
`del_time` datetime DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) unsigned zerofill NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员公众号关键字自动回复表';