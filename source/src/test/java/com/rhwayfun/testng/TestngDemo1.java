package com.rhwayfun.testng;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * Created by chubin on 2017/3/28.
 */
public class TestngDemo1 {

    @BeforeClass
    public void before(){
        System.out.println("before:" + LocalDateTime.now());
    }

    @Test
    public void test(){
        System.out.println("test:" + LocalDateTime.now());
    }

    @AfterClass
    public void after(){
        System.out.println("after:" + LocalDateTime.now());
    }
}
