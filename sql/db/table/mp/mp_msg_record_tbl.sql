CREATE TABLE `mp_msg_record_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公众号模板消息记录表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`open_id` varchar(120) NOT NULL COMMENT '微信公众号openId',
`msg_id` varchar(120) NOT NULL COMMENT '消息id',
`template_id` varchar(120) NOT NULL COMMENT '模板消息id',
`data` text NOT NULL COMMENT '模板消息内容',
`url` varchar(255) DEFAULT NULL COMMENT '网站跳转地址',
`miniapp_appid` varchar(120) DEFAULT NULL COMMENT '关联小程序appid',
`miniapp_page` varchar(255) DEFAULT NULL COMMENT '关联小程序跳转地址',
`user_miniapp_path` tinyint(10) NOT NULL DEFAULT '0' COMMENT '是否使用小程序跳转(0 否 1 是)',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公众号模板消息记录表';