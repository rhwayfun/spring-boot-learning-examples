/*       授权语句（所有数据库的所有权限） */
grant all privileges on *.* to travis@127.0.0.1;

/*       Database springboot         */
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增，主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(15) DEFAULT NULL,
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birth` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

ALTER TABLE `springboot`.`user`
  CHANGE COLUMN `user_id` `user_id` BIGINT(20) NOT NULL COMMENT '用户id' ,
  ADD UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC);

/*       Database springboot2        */
CREATE TABLE `city` (
  `id` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '记录ID',
  `name` varchar(128) DEFAULT NULL COMMENT '城市名称',
  `is_active` tinyint(3) unsigned DEFAULT '0' COMMENT '是否开放',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市信息表';
