show databases ;

use mydatabase;

DROP TABLE IF EXISTS `t_user_info`;

CREATE TABLE `t_user_info` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT 'user_id',
    `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'user_name',
    `black_status` int NOT NULL DEFAULT 0 COMMENT 'black status 1 is , 0 not',
    `create_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '加入时间',
    `update_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '修改时间',

    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

show create table t_user_info;

select * from t_user_info;

update t_user_info set black_status = 0 where  user_id = 1181616590913600;