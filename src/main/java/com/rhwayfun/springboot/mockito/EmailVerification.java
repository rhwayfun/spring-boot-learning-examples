package com.rhwayfun.springboot.mockito;

/**
 * Created by chubin on 2017/4/16.
 */
public class EmailVerification{
    public boolean verifyEmail(String email){
        return "zhongchubin@dianwoba.com".equals(email);
    }
}