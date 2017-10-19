package com.rhwayfun.springboot.mybatis.shardingjdbc.mapper;

import com.rhwayfun.springboot.mybatis.shardingjdbc.costants.SqlConstants;
import com.rhwayfun.springboot.mybatis.shardingjdbc.entity.UserEntity;
import com.rhwayfun.springboot.mybatis.shardingjdbc.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private DataSource mybatisDataSource;

    @Resource
    private UserService userService;

    private UserEntity user;

    @Before
    public void setup() throws Exception {
        create();
        clear();
        init();
    }

    private void clear() {
        userMapper.deleteAll();
    }

    private void init() {
        user = new UserEntity();
        user.setUserId(1L);
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());
    }

    private void create() throws SQLException {
        executeUpdate(mybatisDataSource, SqlConstants.USER_CREATE_SQL);
    }

    private void executeUpdate(final DataSource dataSource, final String sql) throws SQLException {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
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
    public void getOneSlave() throws Exception {
        UserEntity user = new UserEntity();
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());
        assertTrue(userMapper.insertSlave(user) > 0);
        Long userId = user.getUserId();
        System.out.println("Generated Key--userId:" + userId);
        UserEntity one = userMapper.getOne(userId);
        assertEquals("insertTest", one.getUserName());
        assertTrue(userMapper.delete(userId) > 0);
    }

    @Test
    public void getOne() throws Exception {
        testUp();
        UserEntity one = userMapper.getOne(1L);
        assertEquals("insertTest", one.getUserName());
        tearDown();
    }

    @Test
    public void update() throws Exception {
        testUp();
        UserEntity user = new UserEntity();
        user.setUserId(1L);
        user.setUserName("insertTest2");
        assertTrue(userMapper.update(user) > 0);
        UserEntity one = userMapper.getOne(1L);
        assertEquals("insertTest2", one.getUserName());
        tearDown();
    }

    @Test
    public void updateFailure() {
        try {
            userService.updateWithFail();
        } catch (Exception e) {
            System.out.println("rollback");
        }
        UserEntity one = userMapper.getOne(userService.getUserId());
        assertTrue(one == null);
    }

    @Test
    public void updateWithoutInsertFailure() {
        try {
            UserEntity user = new UserEntity();
            user.setCityId(1);
            user.setUserName("insertTest");
            user.setAge(10);
            user.setBirth(new Date());
            userMapper.insertSlave(user);
            userService.updateWithoutInsertFail();
        } catch (Exception e) {
            System.out.println("rollback");
        }
        UserEntity one = userMapper.getOne(userService.getUserId());
        assertEquals("insertTest", one.getUserName());
    }

}