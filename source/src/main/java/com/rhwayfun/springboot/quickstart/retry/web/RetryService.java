package com.rhwayfun.springboot.quickstart.retry.web;

import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by ZhongCB on 2017/9/7.
 */
@Service
public class RetryService {

    @Recover
    public int fallBack() {
        return 2;
    }

    @CircuitBreaker(include = IllegalArgumentException.class, maxAttempts = 1, openTimeout = 10000L, resetTimeout = 5000L)
    public int randomNum() throws Exception {
        System.out.println("call randomNum..." + LocalDateTime.now());
        if (Math.random() > .5) {
            throw new IllegalArgumentException("Exception");
        }
        return 1;
    }
}
