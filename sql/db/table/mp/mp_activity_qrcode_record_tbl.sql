CREATE TABLE `mp_activity_qrcode_record_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号活动二维码表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`open_id` varchar(120) NOT NULL COMMENT '微信公众号openId',
`code` varchar(20) NOT NULL COMMENT '活动编码',
`event` varchar(20) NOT NULL COMMENT '事件(scan 扫码 subscribe 关注)',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;