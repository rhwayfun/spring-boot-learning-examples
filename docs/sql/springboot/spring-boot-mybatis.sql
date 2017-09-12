CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增，主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(15) DEFAULT NULL,
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birth` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
