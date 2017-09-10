package com.rhwayfun.springboot.quickstart.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chubin on 2017/9/10.
 */
@RestController
public class DemoController {

    @GetMapping("/now")
    public Date now(){
        return Calendar.getInstance().getTime();
    }
}
