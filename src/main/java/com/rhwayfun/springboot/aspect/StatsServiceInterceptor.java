package com.rhwayfun.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chubin on 2017/3/19.
 */
@Aspect
public class StatsServiceInterceptor {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(StatsServiceInterceptor.class);

    @Around("execution(* *(..)) && @annotation(StatsService)")
    public Object invoke(ProceedingJoinPoint pjp) {
        try {
            log.info("before invoke target.");
            return pjp.proceed();
        } catch (Throwable e) {
            log.error("invoke occurs error:", e);
            return null;
        } finally {
            log.info("after invoke target.");
        }
    }

}
