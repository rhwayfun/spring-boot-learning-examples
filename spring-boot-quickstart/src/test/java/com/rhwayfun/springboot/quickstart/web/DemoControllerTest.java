package com.rhwayfun.springboot.quickstart.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chubin on 2017/9/10.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void now() throws Exception {
        ResultActions resultActions = this.mvc.perform(new RequestBuilder() {
            @Override
            public MockHttpServletRequest buildRequest(ServletContext servletContext) {
                return new MockHttpServletRequest(RequestMethod.GET.name(), "/now");
            }
        }).andExpect(status().isOk()).andExpect(content().string("hello"));
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertEquals(contentAsString, "hello");
    }

}