package com.rhwayfun.springboot.configuration;

import com.rhwayfun.springboot.configuration.property.SimpleProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * TEST
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ProfilePropertyTest {

    @Autowired
    private SimpleProperty profileProperty;

    @Test
    public void getApp() throws Exception {
        assertEquals("spring-boot-configuration", profileProperty.getApp());
    }

    @Test
    public void getUser() throws Exception {
        assertEquals("springboot", profileProperty.getUser());
    }

    @Test
    public void getAge() throws Exception {
        assertEquals(1, profileProperty.getAge());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("springboot@gmail.com", profileProperty.getEmail());
    }

    @Test
    public void getBlog() throws Exception {
        assertEquals("http://blog.csdn.net/springboot", profileProperty.getBlog());
    }

    @Test
    public void getGithub() throws Exception {
        assertEquals("https://github.com/springboot/spring-boot-samples", profileProperty.getGithub());
    }

}