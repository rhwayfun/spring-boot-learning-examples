package com.rhwayfun.springboot.quickstart.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLDataException;

/**
 * Created by chubin on 2017/9/2.
 */
public class RetryAnnotationService {

    private static Logger log = LoggerFactory.getLogger(RetryAnnotationService.class);

    @Retryable(maxAttempts = 3, backoff = @Backoff(random = true))
    public String service1() {
        log.info("service1 open BackoffBreaker");
        int random = (int) (Math.random() * 10);
        if (random < 4) {
            throw new NullPointerException();
        } else if (random < 9) {
            throw new ArithmeticException();
        }
        return "ok";
    }

    @Recover
    public String recover(NullPointerException ne) {
        return "null pointer recover";
    }

    @Recover
    public String recover(ArithmeticException ne) {
        return "ArithmeticException recover";
    }

    @CircuitBreaker(include = IllegalArgumentException.class, maxAttempts = 1, openTimeout = 5000, resetTimeout = 10000)
    public String service2() {
        log.info("service2 open CircuitBreaker");
        throw new IllegalArgumentException();
    }

    @Recover
    public String recover(IllegalArgumentException ne) {
        return "IllegalArgumentException recover";
    }

    @Retryable(value = SQLDataException.class, backoff = @Backoff(value = 0L))
    public String service3() throws SQLDataException {
        log.info("service3 open");
        throw new SQLDataException();
    }

    @Recover
    public String recover(SQLDataException ne) {
        return "SQLDataException recover";
    }

}
