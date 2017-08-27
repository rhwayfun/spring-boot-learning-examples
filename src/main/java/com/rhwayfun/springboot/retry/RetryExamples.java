package com.rhwayfun.springboot.retry;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.CompositeRetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by chubin on 2017/8/27.
 */
@Configuration
@EnableRetry
@Component
public class RetryExamples {

    /** Logger */
    private static Logger logger = LoggerFactory.getLogger(RetryExamples.class);

    @Bean
    public RetryService retryService() {
        return new RetryService();
    }

    private int len = 3;

    private static final int TYPE = 6;

    @PostConstruct
    public void retry() throws Exception {
        switch (TYPE) {
            case 1 :
                retryExample1();
                break;
            case 2:
                retryExample2();
                break;
            case 3:
                retryExample3();
                break;
            case 4:
                retryExample4();
                break;
            case 5:
                retryExample5();
                break;
            case 6:
                retryExample6();
                break;
            default:
                break;
        }

    }

    private void retryExample1() {
        int calc = 0;
        try {
            calc = retryService().calc(len++);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result: " + calc);
    }

    private void retryExample2() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(100);

        retryTemplate.setRetryPolicy(timeoutRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            int i = 0;
            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                System.out.println("retry context: " + retryContext.getParent()
                        + ", retryCount: " + retryContext.getRetryCount()
                        + ", attributes: " + Arrays.toString(retryContext.attributeNames())
                        + ", lastException: " + retryContext.getLastThrowable()
                        + ", isExhaustedOnly: " + retryContext.isExhaustedOnly());
                return len(i++);
            }
        });
        System.out.println("final result: " + result);
    }

    private void retryExample3() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);

        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            int i = 0;
            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                System.out.println("retry count: " + retryContext.getRetryCount());
                return len(i++);
            }
        }, new RecoveryCallback<Integer>() {
            @Override
            public Integer recover(RetryContext retryContext) throws Exception {
                System.out.println("after retry: " + retryContext.getRetryCount()
                        + ", recovery method called!");
                return Integer.MAX_VALUE;
            }
        });
        System.out.println("final result: " + result);
    }

    private void retryExample4() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExceptionClassifierRetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy();

        Map<Class<? extends Throwable>, RetryPolicy> policyMap = Maps.newHashMap();

        // 如果发生空指针异常，则最大重试2ms，然后退出重试
        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(2L);
        policyMap.put(NullPointerException.class, timeoutRetryPolicy);

        // 如果发生 1/0 异常，则最多重试3次，然后退出重试
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(10);
        policyMap.put(ArithmeticException.class, simpleRetryPolicy);

        // 以上两种异常有可能交替出现，直到某一中类型的异常重试达到终止状态，或者被重试方法返回正确结果
        retryPolicy.setPolicyMap(policyMap);

        retryTemplate.setRetryPolicy(retryPolicy);

        String result = retryTemplate.execute(new RetryCallback<String, Exception>() {
            @Override
            public String doWithRetry(RetryContext retryContext) throws Exception {
                System.out.println("retry count: " + retryContext.getRetryCount());
                return randomException();
            }
        });
        System.out.println("final result: " + result);
    }

    private void retryExample5() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(5000);

        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            int i = 0;
            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                System.out.println("retry count: " + retryContext.getRetryCount()
                        + ", time: " + LocalDateTime.now());
                return len(i++);
            }
        });
        System.out.println("final result: " + result);
    }

    private void retryExample6() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        CompositeRetryPolicy compositeRetryPolicy = new CompositeRetryPolicy();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(200); // 每次重试间隔200ms

        // 如果重试总耗时超过1s，重试次数不超过3次，重试中止；如果总耗时未超过1s，但是重试次数超过3次，重试中止
        compositeRetryPolicy.setPolicies(new RetryPolicy[]{
                simpleRetryPolicy,
                timeoutRetryPolicy,
        });

        retryTemplate.setRetryPolicy(compositeRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            int i = 0;
            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                System.out.println("retry count: " + retryContext.getRetryCount()
                        + ", time: " + LocalDateTime.now());
                return len(i++);
            }
        });
        System.out.println("final result: " + result);
    }

    private String randomException() throws Exception {
        int random = (int) (Math.random() * 10);
        if (random < 4) {
            logger.info("random={} Null Pointer", random);
            throw new NullPointerException();
        } else if (random < 10) {
            logger.info("random={} Arithmetic Excep", random);
            throw new ArithmeticException();
        }

        // 这段代码不会被调用 random:0-1
        logger.info("random={} ok !!!!", random);
        return "ok";
    }

    private int len(int i) throws Exception {
        if (i < 10) throw new Exception(i + " le 10");
        return i;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(RetryExamples.class);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            configApplicationContext.close();
        }
    }
}
