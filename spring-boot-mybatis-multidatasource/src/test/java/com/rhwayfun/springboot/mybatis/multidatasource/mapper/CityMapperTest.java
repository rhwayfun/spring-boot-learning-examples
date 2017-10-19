package com.rhwayfun.springboot.mybatis.multidatasource.mapper;

import com.rhwayfun.springboot.mybatis.multidatasource.constants.SqlConstants;
import com.rhwayfun.springboot.mybatis.multidatasource.entity.CityEntity;
import com.rhwayfun.springboot.mybatis.multidatasource.mapper.city.CityMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Resource
    private DataSource mybatisDataSource2;

    private CityEntity city;

    @Before
    public void setup() throws Exception {
        createTable();
        cityMapper.deleteAll();
        city = new CityEntity();
        city.setId(1);
        city.setName("杭州市");
        city.setActive(Boolean.TRUE);
        city.setCreateTime(new Date());
        city.setModifyTime(new Date());
    }

    private void createTable() {
        Connection con = null;
        Statement stmt = null;
        try {
            con = mybatisDataSource2.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(SqlConstants.CITY_CREATE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void testUp() throws Exception {
        assertTrue(cityMapper.insert(city) > 0);
    }

    private void tearDown() throws Exception {
        assertTrue(cityMapper.delete(1L) > 0);
    }

    @Test
    public void getAll() throws Exception {
        testUp();
        List<CityEntity> all = cityMapper.getAll();
        assertTrue(all.size() > 0);
        tearDown();
    }

    @Test
    public void getOne() throws Exception {
        testUp();
        CityEntity one = cityMapper.getOne(1L);
        assertEquals("杭州市", one.getName());
        tearDown();
    }

    @Test
    public void update() throws Exception {
        testUp();
        CityEntity city = new CityEntity();
        city.setId(1);
        city.setName("上海市");
        assertTrue(cityMapper.update(city) > 0);
        CityEntity one = cityMapper.getOne(1L);
        assertEquals("上海市", one.getName());
        tearDown();
    }

}