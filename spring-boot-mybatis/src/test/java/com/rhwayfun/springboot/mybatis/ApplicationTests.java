package com.rhwayfun.springboot.mybatis;

import com.rhwayfun.springboot.mybatis.entity.User;
import com.rhwayfun.springboot.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void insertTest() {
		User user = new User();
		user.setUserId(1L);
		user.setUserName("springboot");
		user.setAge(1);
		user.setBirth(new Date());
		assertTrue(userMapper.insert(user) > 0);
	}

}
