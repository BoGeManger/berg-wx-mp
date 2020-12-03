CREATE TABLE `mp_qrcode_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号二维码表id',
`app_id` varchar(120) NOT NULL COMMENT '微信公众号appId',
`scene_str` varchar(255) NOT NULL COMMENT '场景id',
`expire_seconds` int(11) NOT NULL DEFAULT '0' COMMENT '该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）',
`ticket` varchar(255) NOT NULL COMMENT '二维码ticket，凭借此ticket可以在有效时间内换取二维码',
`url` varchar(255) NOT NULL COMMENT '二维码图片解析后的地址',
`remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
`type` tinyint(10) NOT NULL DEFAULT '0' COMMENT '类型(0 临时二维码 1 永久二维码)',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信公众号二维码表';