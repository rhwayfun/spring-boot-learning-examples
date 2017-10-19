package com.rhwayfun.testng;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * Created by chubin on 2017/3/28.
 */
public class TestngDemo2 {

    @Test
    public void testParameter(String test1){
        System.out.println(test1);
    }
}
