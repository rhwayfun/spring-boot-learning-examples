package com.rhwayfun.springboot.mybatis.multidatasource.mapper;

import com.rhwayfun.springboot.mybatis.multidatasource.entity.UserEntity;
import com.rhwayfun.springboot.mybatis.multidatasource.mapper.user.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserEntity user;

    @Before
    public void setup() throws Exception {
        userMapper.deleteAll();
        user = new UserEntity();
        user.setUserId(1L);
        user.setUserName("insertTest3");
        user.setAge(10);
        user.setBirth(new Date());
    }

    private void testUp() throws Exception {
        assertTrue(userMapper.insert(user) > 0);
    }

    private void tearDown() throws Exception {
        assertTrue(userMapper.delete(1L) > 0);
    }

    @Test
    public void getAll() throws Exception {
        testUp();
        List<UserEntity> all = userMapper.getAll();
        assertTrue(all.size() > 0);
        tearDown();
    }

    @Test
    public void getOne() throws Exception {
        testUp();
        UserEntity one = userMapper.getOne(1L);
        assertEquals("insertTest3", one.getUserName());
        tearDown();
    }

    @Test
    public void update() throws Exception {
        testUp();
        UserEntity user = new UserEntity();
        user.setUserId(1L);
        user.setUserName("insertTest4");
        assertTrue(userMapper.update(user) > 0);
        UserEntity one = userMapper.getOne(1L);
        assertEquals("insertTest4", one.getUserName());
        tearDown();
    }

}