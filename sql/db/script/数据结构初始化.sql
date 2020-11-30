/*
Navicat Premium Data Transfer

Source Server         : localhost_3306
Source Server Type    : MySQL
Source Server Version : 50710
Source Host           : localhost:3306
Source Schema         : mpdb

Target Server Type    : MySQL
Target Server Version : 50710
File Encoding         : 65001

Date: 30/11/2020 16:16:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mb_member_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mb_member_tbl`;
CREATE TABLE `mb_member_tbl`  (
`id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员表id',
`phone` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员手机号码',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mb_mp_bind_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mb_mp_bind_tbl`;
CREATE TABLE `mb_mp_bind_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员公众号绑定表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`union_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信开放平台unionId',
`open_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号openId',
`member_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员表id',
`nickname` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
`head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像链接',
`gender` tinyint(5) NULL DEFAULT NULL COMMENT '性别(1为男性，2为女性)',
`country` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家',
`province` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
`city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
`privileges` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户特权信息，json数组，如微信沃卡用户为（chinaunicom）',
`subscribe` tinyint(2) NULL DEFAULT NULL COMMENT '是否已关注(0 否 1 是)',
`subscribe_time` datetime(0) NULL DEFAULT NULL COMMENT '关注时间',
`subscribe_scene` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返回用户关注的渠道来源(ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他)',
`tag_ids` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签信息,json数组',
`qr_scene` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码扫码场景（开发者自定义）',
`qr_scene_str` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码扫码场景描述（开发者自定义）',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员公众号绑定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_menu_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mp_menu_tbl`;
CREATE TABLE `mp_menu_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号菜单表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`menu` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单记录',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信公众号菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_msg_publish_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mp_msg_publish_tbl`;
CREATE TABLE `mp_msg_publish_tbl`  (
`id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号消息发布表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`publish_cycle` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '周期(self单次,day每天,week每周,month每月,year每年)',
`publish_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送具体时间',
`publish_date` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送时间段',
`publish_limit` int(11) NOT NULL DEFAULT 0 COMMENT '推送次数限制',
`publish_people` tinyint(5) NOT NULL DEFAULT 0 COMMENT '推送人群（0 向自定义人群推送 1 向订阅人群推送）',
`operate_type` tinyint(5) NOT NULL DEFAULT 0 COMMENT '操作类型(0 手动触发 1定时触发)',
`template_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板消息id',
`data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板消息内容',
`url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站跳转地址',
`miniapp_appid` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联小程序appid',
`miniapp_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联小程序跳转地址',
`user_miniapp_path` tinyint(10) NOT NULL DEFAULT 0 COMMENT '是否使用小程序跳转(0 否 1 是)',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`modify_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`status` tinyint(5) NOT NULL DEFAULT 0 COMMENT '状态(0 有效,1 无效)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信公众号消息发布表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_msg_record_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mp_msg_record_tbl`;
CREATE TABLE `mp_msg_record_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公众号模板消息记录表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`open_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号openId',
`msg_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息id',
`template_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板消息id',
`data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板消息内容',
`url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站跳转地址',
`miniapp_appid` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联小程序appid',
`miniapp_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联小程序跳转地址',
`user_miniapp_path` tinyint(10) NOT NULL DEFAULT 0 COMMENT '是否使用小程序跳转(0 否 1 是)',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公众号模板消息记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_msg_subscribe_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mp_msg_subscribe_tbl`;
CREATE TABLE `mp_msg_subscribe_tbl`  (
`id` int(11) NOT NULL COMMENT '微信公众号消息订阅表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`publish_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号消息发布表id',
`union_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信开放平台unionId',
`open_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号openId',
`member_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员表id',
`execute_num` int(11) NOT NULL COMMENT '执行次数',
`execute_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`status` tinyint(10) NOT NULL COMMENT '状态(0 有效,1 无效)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信公众号消息订阅表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_qrcode_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mp_qrcode_tbl`;
CREATE TABLE `mp_qrcode_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号二维码表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`open_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信公众号openId',
`scene_str` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '场景id',
`expire_seconds` int(11) NOT NULL DEFAULT 0 COMMENT '该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）',
`ticket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '二维码ticket，凭借此ticket可以在有效时间内换取二维码',
`url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '二维码图片解析后的地址',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`type` tinyint(10) NOT NULL DEFAULT 0 COMMENT '类型(0 临时二维码 1 永久二维码)',
`create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信公众号二维码表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_short_url_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mp_short_url_tbl`;
CREATE TABLE `mp_short_url_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信公众号短链接表id',
`app_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号appId',
`long_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '长链接',
`short_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短链接',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信公众号短链接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_component_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_component_tbl`;
CREATE TABLE `sys_component_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统组件表id',
`parent_id` int(11) NOT NULL COMMENT '父组件id',
`name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组件名称',
`perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
`path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应路由地址',
`icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件描述',
`type` int(11) NOT NULL COMMENT '组件类型(0=菜单,1=按钮)',
`no` int(11) NOT NULL COMMENT '排序',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统组件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_file_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_tbl`;
CREATE TABLE `sys_file_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统文件表id',
`name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
`code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务编码',
`path` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
`full_path` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件全路径',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
`type` int(11) NOT NULL COMMENT '类型(0 模板文件 1 其他)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_organization_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_tbl`;
CREATE TABLE `sys_organization_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统组织表id',
`parent_id` int(11) NOT NULL COMMENT '父组织id',
`name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织名称',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织描述',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统组织表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_quartz_job_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job_tbl`;
CREATE TABLE `sys_quartz_job_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统定时任务表id',
`name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
`remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '任务描述',
`job_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务类名',
`cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
`parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
`status` int(11) NOT NULL COMMENT '状态(0 进行中,1 已暂停)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统定时任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_component_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_component_tbl`;
CREATE TABLE `sys_role_component_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统角色组件表id',
`role_id` int(11) NOT NULL COMMENT '角色id',
`com_id` int(11) NOT NULL COMMENT '组件id',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色组件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_tbl`;
CREATE TABLE `sys_role_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统角色表id',
`name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_component_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_component_tbl`;
CREATE TABLE `sys_user_component_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户组件表id',
`user_id` int(11) NOT NULL COMMENT '用户id',
`com_id` int(11) NOT NULL COMMENT '组件id',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户组件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_tbl`;
CREATE TABLE `sys_user_role_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户角色表id',
`user_id` int(11) NOT NULL COMMENT '用户id',
`role_id` int(11) NOT NULL COMMENT '角色id',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tbl`;
CREATE TABLE `sys_user_tbl`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户信息表id',
`username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
`password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
`realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
`organization_id` int(11) NOT NULL COMMENT '所属组织id',
`organization_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属组织名称',
`last_login_time` datetime(0) NOT NULL COMMENT '最后登录时间',
`lock_time` datetime(0) NULL DEFAULT NULL COMMENT '锁定时间',
`lock_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '锁定人',
`islock` tinyint(5) NOT NULL COMMENT '是否锁定(0 否,1 是)',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
`modify_time` datetime(0) NOT NULL COMMENT '更新时间',
`modify_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改用户',
`del_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
`del_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
`isdel` tinyint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除(0 否,1 是)',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
