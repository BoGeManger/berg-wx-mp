CREATE TABLE `mp_msg_subscribe_tbl` (
`id` int(11) NOT NULL COMMENT '微信公众号消息订阅表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`publish_id` varchar(64) NOT NULL COMMENT '微信公众号消息发布表id',
`union_id` varchar(120) DEFAULT NULL COMMENT '微信开放平台unionId',
`open_id` varchar(120) NOT NULL COMMENT '微信公众号openId',
`member_id` varchar(120) DEFAULT NULL COMMENT '会员表id',
`execute_num` int(11) NOT NULL COMMENT '执行次数',
`execute_time` datetime DEFAULT NULL COMMENT '执行时间',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
`modify_time` datetime NOT NULL COMMENT '更新时间',
`status` tinyint(10) NOT NULL COMMENT '状态(0 有效,1 无效)',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信公众号消息订阅表';