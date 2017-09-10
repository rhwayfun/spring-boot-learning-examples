package com.rhwayfun.springboot.quickstart.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chubin on 2017/9/10.
 */
@RestController
public class DemoController {

    @RequestMapping("/now")
    public String now(){
        return "hello";
    }
}
