package com.rhwayfun.springboot.quickstart.retry.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhongCB on 2017/9/7.
 */
@RestController
public class RetryController {

    private final RetryService retryService;

    @Autowired
    public RetryController(RetryService retryService) {
        this.retryService = retryService;
    }

    @GetMapping("/randomNum")
    public int randomNum() throws Exception {
        return retryService.randomNum();
    }

}
