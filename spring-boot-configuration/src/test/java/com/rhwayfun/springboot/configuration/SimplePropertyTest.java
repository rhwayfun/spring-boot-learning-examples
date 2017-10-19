package com.rhwayfun.springboot.configuration;

import com.rhwayfun.springboot.configuration.property.SimpleProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * TEST
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimplePropertyTest {

    @Autowired
    private SimpleProperty simpleProperty;

    @Test
    public void getApp() throws Exception {
        assertEquals("spring-boot-configuration", simpleProperty.getApp());
    }

    @Test
    public void getUser() throws Exception {
        assertEquals("happyxiaofan", simpleProperty.getUser());
    }

    @Test
    public void getAge() throws Exception {
        assertEquals(24, simpleProperty.getAge());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("rhwayfun@gmail.com", simpleProperty.getEmail());
    }

    @Test
    public void getBlog() throws Exception {
        assertEquals("http://blog.csdn.net/u011116672", simpleProperty.getBlog());
    }

    @Test
    public void getGithub() throws Exception {
        assertEquals("https://github.com/happyxiaofan/spring-boot-learning-examples", simpleProperty.getGithub());
    }

}