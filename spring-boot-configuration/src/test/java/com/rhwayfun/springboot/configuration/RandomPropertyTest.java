package com.rhwayfun.springboot.configuration;

import com.rhwayfun.springboot.configuration.random.RandomProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 随机值属性TEST
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomPropertyTest {

    @Autowired
    private RandomProperty randomProperty;

    @Test
    public void getRandomValue() throws Exception {
        assertNotNull(randomProperty.getRandomValue());
    }

    @Test
    public void getRandomInt() throws Exception {
        assertTrue(randomProperty.getRandomInt() > Integer.MIN_VALUE);
    }

    @Test
    public void getRandomLong() throws Exception {
        assertTrue(randomProperty.getRandomLong() > Long.MIN_VALUE);
    }

    @Test
    public void getRandomUUID() throws Exception {
        assertNotNull(randomProperty.getRandomUUID());
    }

    @Test
    public void getRandomIntRange() throws Exception {
        assertTrue(randomProperty.getRandomIntRange() < 10);
    }

    @Test
    public void getRandomIntMaxMinRange() throws Exception {
        assertTrue(randomProperty.getRandomIntMaxMinRange() > 10);
        assertTrue(randomProperty.getRandomIntMaxMinRange() < 100);
    }

}