package com.rhwayfun.springboot.dubbo.consumer;

import com.rhwayfun.springboot.dubbo.api.DemoProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboTest {

    private static final String PARAM = "Dubbo";

    @Resource
    private DemoProvider demoProvider;

    @Test
    public void testDubbo() throws Exception {
        Assert.assertEquals("Hello " + PARAM, demoProvider.sayHello(PARAM));
    }

}