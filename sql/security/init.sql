CREATE TABLE `g_authority` (
  `id` int(11) NOT NULL,
  `auth_id` int(11) DEFAULT NULL,
  `auth_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AUTHORITY` (`auth_id`,`auth_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `g_users` (
  `id` int(11) NOT NULL COMMENT 'Id，自增主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`),
  KEY `IDX_USER_ID` (`user_id`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `r_auth_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_USER_AUTH` (`user_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;