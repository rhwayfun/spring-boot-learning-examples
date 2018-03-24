package com.rhwayfun.springboot.dubbo.annotation.consumer;

import com.rhwayfun.springboot.dubbo.annotation.consumer.service.DemoConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboTest {

    private static final String PARAM = "Dubbo";

    @Resource
    private DemoConsumer demoConsumer;

    @Test
    public void testDubbo() throws Exception {
        String result = null;
        try {
            result = demoConsumer.sayHi(PARAM);
            if (result != null) {
                assertEquals("Hello " + PARAM, result);
            }
        } catch (Exception e) {
            assertNull(result);
        }
    }

}