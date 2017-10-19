package com.rhwayfun;

import com.rhwayfun.springboot.quickstart.aspect.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Created by chubin on 2017/3/19.
 */
public class AspectTest {

    private static Logger log = LoggerFactory.getLogger(AspectTest.class);

    public static void main(String[] args) {
        AspectTest.print();
    }

    @StatsService
    public static void print(){
        log.info("Now: {}", LocalDateTime.now());
    }
}
