show databases ;

use mydatabase;

DROP TABLE IF EXISTS `t_blacklist_user_info`;

CREATE TABLE `t_blacklist_user_info` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `project_id`  bigint(20) NOT NULL DEFAULT 0 COMMENT '归属项目ID',
    `user_id` varchar(31) NOT NULL DEFAULT '' COMMENT 'user_id',
    `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'user_name',
    `black_status` int NOT NULL DEFAULT 0 COMMENT 'black status 1 is , 0 not',
    `effect_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '生效时间',
    `expired_time;` bigint(20) NOT NULL DEFAULT 0 COMMENT '失效时间',
    `create_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '加入时间',
    `update_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',

    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_blacklist_config`;

CREATE TABLE `t_blacklist_config` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `project_id`  bigint(20) NOT NULL DEFAULT 0 COMMENT '归属项目ID',
    `project_desc`  varchar(255) NOT NULL DEFAULT '' COMMENT '归属项目描述',
    `effect_status` int NOT NULL DEFAULT 1 COMMENT '生效状态 1 is , 0 not',
    `effect_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '项目生效时间 小于或等于0 则 无穷小',
    `expired_time;` bigint(20) NOT NULL DEFAULT 0 COMMENT '项目失效时间 小于或等于0 则 无穷大',
    `create_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '加入时间',
    `update_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',

    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT AUTO_INCREMENT = 1 CHARSET=utf8;

show create table t_blacklist_user_info;
show create table t_blacklist_config;

select * from t_blacklist_user_info;
select * from t_blacklist_config;
