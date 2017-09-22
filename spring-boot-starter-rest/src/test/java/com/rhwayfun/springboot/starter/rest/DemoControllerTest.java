package com.rhwayfun.springboot.starter.rest;

import com.rhwayfun.springboot.starter.DemoService;
import com.rhwayfun.springboot.starter.rest.web.controller.DemoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author happyxiaofan
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DemoService demoService;

    private static final String USER_GET = "/user-info/1";

    private static final String USER = "User[id=1, name=happyxiaofan, age=12, email=rhwayfun@gmail.com]";

    @Test
    public void userInfo() throws Exception {
        given(this.demoService.userInfo()).willReturn(USER);
        this.mvc.perform(get(USER_GET)
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(USER));
    }

}