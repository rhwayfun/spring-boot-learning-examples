package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.mapper;

import com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(UserMapperTest.class);

    @Resource
    private UserMapper userMapper;

    @Before
    public void setup() throws Exception {
        create();
        clear();
    }

    private void create() throws SQLException {
        userMapper.createIfNotExistsTable();
    }

    private void clear() {
        userMapper.truncateTable();
    }

    @Test
    public void insert() throws Exception {
        UserEntity user = new UserEntity();
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());
        assertTrue(userMapper.insert(user) > 0);
        Long userId = user.getUserId();
        log.info("Generated Key--userId:" + userId);
        userMapper.delete(userId);
    }

    /*
    INSERT INTO t_user_0(user_id,city_id,user_name,age,birth) values(138734796783222784,1,'insertTest',10,'2017-11-18 00:00:00');
     */
    @Test
    public void find() throws Exception {
        UserEntity userEntity = userMapper.find(138734796783222784L);
        log.info("user:{}", userEntity);
    }

}