package com.rhwayfun.springboot.retry;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/**
 * Created by chubin on 2017/8/27.
 */
public class RetryService {

    @Retryable(IllegalArgumentException.class)
    public int calc(int len) throws Exception {
        System.out.println("retry success!");
        if (len > 2) throw new IllegalArgumentException(len + " is " + len  + "!");
        return len;
    }

    @Recover
    public int recover(IllegalArgumentException e, int len){
        System.out.println("recover method call success！");
        return len;
    }

}
