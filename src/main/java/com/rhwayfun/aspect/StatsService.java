package com.rhwayfun.aspect;

import java.lang.annotation.*;

/**
 * Created by chubin on 2017/3/19.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface StatsService {
}
