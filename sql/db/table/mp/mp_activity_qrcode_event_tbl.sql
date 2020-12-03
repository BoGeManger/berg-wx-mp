CREATE TABLE `mp_activity_qrcode_event_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号活动二维码事件表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`activity_id` varchar(64) NOT NULL COMMENT '活动id',
`event` varchar(20) NOT NULL COMMENT '事件(scan 扫码 subscribe 关注)',
`keys` varchar(255) NOT NULL COMMENT '绑定关键字',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信公众号活动二维码事件表';