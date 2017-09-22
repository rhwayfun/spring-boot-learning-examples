package com.rhwayfun.springboot.starter.rest.web.controller;

import com.rhwayfun.springboot.starter.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author happyxiaofan
 * @since 0.0.1
 */
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping("user-info/{id}")
    @ResponseBody
    public String userInfo(@PathVariable String id) {
        return demoService.userInfo();
    }

}
