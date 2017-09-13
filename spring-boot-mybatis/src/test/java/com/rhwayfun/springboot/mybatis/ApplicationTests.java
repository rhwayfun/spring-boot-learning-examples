package com.rhwayfun.springboot.mybatis;

import com.rhwayfun.springboot.mybatis.entity.User;
import com.rhwayfun.springboot.mybatis.entity.UserExample;
import com.rhwayfun.springboot.mybatis.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

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

	@Test
	public void insertTest2() {
		User user = new User();
		user.setUserId(2L);
		user.setUserName("springboot2");
		user.setAge(1);
		user.setBirth(new Date());
		assertTrue(userMapper.insert(user) > 0);
	}

	@Test
	public void queryTest() {
		List<User> users = userMapper.selectByExample(new UserExample());
		if (!CollectionUtils.isEmpty(users)) {
			assertTrue(userMapper.selectByPrimaryKey(users.get(0).getId()).getAge() == 1);
		}
	}

	@Test
	public void countTest() {
		assertTrue(userMapper.countByExample(new UserExample()) > 0);
	}

	@Test
	public void updateTest() {
		List<User> users = userMapper.selectByExample(new UserExample());
		if (!CollectionUtils.isEmpty(users)) {
			User user = users.get(users.size() - 1);
			user.setUserName("updateTest");
			assertTrue(userMapper.updateByPrimaryKeySelective(user) > 0);
		}
	}

	@Test
	public void updateTest2() {
		List<User> users = userMapper.selectByExample(new UserExample());
		if (!CollectionUtils.isEmpty(users)) {
			User user = users.get(users.size() - 1);
			user.setUserName("updateTest2");
			assertTrue(userMapper.updateByPrimaryKey(user) > 0);
		}
	}

	@Test
	public void deleteTest() {
		List<User> users = userMapper.selectByExample(new UserExample());
		if (!CollectionUtils.isEmpty(users)) {
			User user = users.get(0);
			assertTrue(userMapper.deleteByPrimaryKey(user.getId()) > 0);
		}
	}

	@Test
	public void deleteTest2() {
		UserExample example = new UserExample();
		example.createCriteria().andUserNameEqualTo("springboot");
		assertTrue(userMapper.deleteByExample(example) > 0);
	}

	@Test
	public void distinctOrTest() {
		UserExample example = new UserExample();
		example.setDistinct(true);
		example.setOrderByClause("order by id asc");
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andAgeIsNotNull();
		UserExample.Criteria or = example.or();
		or.andIdIsNotNull();
		userMapper.selectByExample(example);
	}

}
