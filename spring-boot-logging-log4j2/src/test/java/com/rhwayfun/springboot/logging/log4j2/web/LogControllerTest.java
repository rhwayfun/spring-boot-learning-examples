package com.rhwayfun.springboot.logging.log4j2.web;

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

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LogController.class)
public class LogControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void now() throws Exception {
        ResultActions resultActions = this.mvc.perform(new RequestBuilder() {
            @Override
            public MockHttpServletRequest buildRequest(ServletContext servletContext) {
                return new MockHttpServletRequest(RequestMethod.GET.name(), "/now/1");
            }
        }).andExpect(status().isOk());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertNotNull(contentAsString);
    }

}