package com.rhwayfun.springboot.mybatis.multidatasource.mapper;

import com.rhwayfun.springboot.mybatis.multidatasource.entity.CityEntity;
import com.rhwayfun.springboot.mybatis.multidatasource.mapper.city.CityMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CityEntity city;

    @Before
    public void setup() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS `city` (\n" +
                "  `id` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '记录ID',\n" +
                "  `name` varchar(128) DEFAULT NULL COMMENT '城市名称',\n" +
                "  `is_active` tinyint(3) unsigned DEFAULT '0' COMMENT '是否开放',\n" +
                "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idx_name` (`name`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市信息表';";
        jdbcTemplate.update(sql);
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