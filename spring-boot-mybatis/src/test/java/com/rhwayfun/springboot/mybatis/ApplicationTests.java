package com.rhwayfun.springboot.mybatis;

import com.rhwayfun.springboot.mybatis.entity.User;
import com.rhwayfun.springboot.mybatis.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		String sql = "CREATE TABLE IF NOT EXISTS `user` (\n" +
				"  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增，主键',\n" +
				"  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',\n" +
				"  `user_name` varchar(15) DEFAULT NULL,\n" +
				"  `age` int(11) DEFAULT NULL COMMENT '年龄',\n" +
				"  `birth` date DEFAULT NULL COMMENT '生日',\n" +
				"  PRIMARY KEY (`id`),\n" +
				"  UNIQUE KEY `id_UNIQUE` (`id`)\n" +
				") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';\n";
		jdbcTemplate.update(sql);
	}

	@Test
	public void insertTest() {
		User user = new User();
		user.setUserId(1L);
		user.setUserName("springboot");
		user.setAge(1);
		user.setBirth(new Date());
		assertTrue(userMapper.insertSelective(user) > 0);
	}

}
