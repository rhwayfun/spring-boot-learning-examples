package com.rhwayfun;

import com.alibaba.fastjson.JSON;
import com.rhwayfun.springboot.quickstart.Application;
import com.rhwayfun.springboot.quickstart.datasource.dao.CarDAO;
import com.rhwayfun.springboot.quickstart.datasource.mapper.CarMapper;
import com.rhwayfun.springboot.quickstart.datasource.model.Car;
import com.rhwayfun.springboot.quickstart.doamin.CarBO;
import com.rhwayfun.springboot.quickstart.security.datasource.mapper.UserMapper;
import com.rhwayfun.springboot.quickstart.security.datasource.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by chubin on 2017/7/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("default")
public class CarDAOTest {

    @Resource
    private CarDAO carDAO;

    @Resource
    private CarMapper carMapper;

    @Resource
    private UserMapper userMapper;

    @Test
    public void save() throws Exception {
        CarBO car = new CarBO( null, "DD-AB-123", 4 );
        carDAO.saveOrUpdate(car);
    }

    @Test
    public void save2() throws Exception {
        Car record = new Car();
        record.setLicensePlate("test");
        record.setManuFacturer("test");
        record.setSeatCount(2);
        carMapper.insert(record);
    }

    @Test
    public void findUser() throws Exception {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSON(user));
    }

}
