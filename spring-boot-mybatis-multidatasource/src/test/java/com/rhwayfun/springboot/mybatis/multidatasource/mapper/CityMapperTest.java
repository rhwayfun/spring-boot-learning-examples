package com.rhwayfun.springboot.mybatis.multidatasource.mapper;

import com.rhwayfun.springboot.mybatis.multidatasource.entity.CityEntity;
import com.rhwayfun.springboot.mybatis.multidatasource.mapper.city.CityMapper;
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
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    private CityEntity city;

    @Before
    public void setup() throws Exception {
        cityMapper.deleteAll();
        city = new CityEntity();
        city.setId(1);
        city.setName("杭州市");
        city.setActive(Boolean.TRUE);
        city.setCreateTime(new Date());
        city.setModifyTime(new Date());
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