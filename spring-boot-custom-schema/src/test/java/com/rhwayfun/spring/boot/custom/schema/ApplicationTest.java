package com.rhwayfun.spring.boot.custom.schema;

import com.rhwayfun.spring.boot.custom.schema.spring.PeopleBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void testGetPeopleBean() throws Exception {
        PeopleBean peopleBean = (PeopleBean) applicationContext.getBean("peopleBean");
        Assert.assertEquals(peopleBean.getId(), "peopleBean");
        Assert.assertEquals(peopleBean.getName(), "rhwayfun");
        Assert.assertEquals(String.valueOf(peopleBean.getAge()), "25");
    }

}