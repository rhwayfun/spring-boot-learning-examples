package com.rhwayfun.springboot.quickstart.retry;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.retry.*;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.*;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Created by chubin on 2017/8/27.
 */
@Configuration
@EnableRetry(proxyTargetClass = true)
@EnableScheduling
@Component
public class RetryExamples {

    private static Logger log = LoggerFactory.getLogger(RetryExamples.class);

    @Bean
    public RetryTestService retryTestService() {
        return new RetryTestService();
    }

    @Bean
    public RetryAnnotationService retryAnnotationService() {
        return new RetryAnnotationService();
    }

    private int len = 3;

    private static final int TYPE = 1;

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void retry() throws Exception {
        switch (TYPE) {
            case 1:
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
            case 7:
                retryExample7();
                break;
            case 8:
                retryExample8();
                break;
            case 9:
                retryExample9();
                break;
            case 10:
                retryExample10();
                break;
            case 11:
                retryExample11();
                break;
            case 12:
                retryExample12();
                break;
            default:
                break;
        }

    }

    private void retryExample1() {
        int calc = 0;
        try {
            calc = retryTestService().calc(len++);
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
                log.info("retry count: {}", retryContext.getRetryCount());
                return len(i++);
            }
        }, new RecoveryCallback<Integer>() {
            @Override
            public Integer recover(RetryContext retryContext) throws Exception {
                log.info("after retry: {}, recovery method called!", retryContext.getRetryCount());
                return Integer.MAX_VALUE;
            }
        });
        log.info("final result: {}", result);
    }

    private void retryExample4() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExceptionClassifierRetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy();

        Map<Class<? extends Throwable>, RetryPolicy> policyMap = Maps.newHashMap();

        // 如果发生空指针异常，则最大重试2ms，然后退出重试
        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(500L);
        policyMap.put(NullPointerException.class, timeoutRetryPolicy);

        // 如果发生 1/0 异常，则最多重试3次，然后退出重试
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);
        policyMap.put(ArithmeticException.class, simpleRetryPolicy);

        // 以上两种异常有可能交替出现，直到某一中类型的异常重试达到终止状态，或者被重试方法返回正确结果
        retryPolicy.setPolicyMap(policyMap);

        retryTemplate.setRetryPolicy(retryPolicy);

        String result = retryTemplate.execute(new RetryCallback<String, Exception>() {
            @Override
            public String doWithRetry(RetryContext retryContext) throws Exception {
                log.info("retry count: {}", retryContext.getRetryCount());
                Thread.sleep(1000L);
                return randomException();
            }
        }, new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) throws Exception {
                return "default";
            }
        });
        log.info("final result: {}", result);
    }

    private void retryExample5() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000);

        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            int i = 0;

            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                log.info("retry count: {}", retryContext.getRetryCount());
                return len(i++);
            }
        }, new RecoveryCallback<Integer>() {
            @Override
            public Integer recover(RetryContext context) throws Exception {
                return -1;
            }
        });
        log.info("final result: {}", result);
    }

    private void retryExample6() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        CompositeRetryPolicy compositeRetryPolicy = new CompositeRetryPolicy();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(10000000);

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(10L);

        // 如果重试总耗时超过1s，或重试次数超过3次，重试中止；如果总耗时未超过1s，但是重试次数超过3次，重试中止
        compositeRetryPolicy.setPolicies(new RetryPolicy[]{
                simpleRetryPolicy,
                timeoutRetryPolicy,
        });

        retryTemplate.setRetryPolicy(compositeRetryPolicy);

        Integer result = retryTemplate.execute(new RetryCallback<Integer, Exception>() {
            int i = 0;

            @Override
            public Integer doWithRetry(RetryContext retryContext) throws Exception {
                log.info("retry count: {}", retryContext.getRetryCount());
                return len2(i++);
            }
        }, new RecoveryCallback<Integer>() {
            @Override
            public Integer recover(RetryContext context) throws Exception {
                return -1;
            }
        });
        log.info("final result: {}", result);
    }

    private void retryExample7() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();

        CompositeRetryPolicy compositeRetryPolicy = new CompositeRetryPolicy();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(1000L);

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
                log.info("retry count: {}", retryContext.getRetryCount());
                return len2(i++);
            }
        }, new RecoveryCallback<Integer>() {
            @Override
            public Integer recover(RetryContext context) throws Exception {
                return -1;
            }
        });
        log.info("final result: {}", result);
    }

    private void retryExample8() throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate();
        //当前状态的名称，当把状态放入缓存时，通过该key查询获取
        Object key = "mykey";
        //是否每次都重新生成上下文还是从缓存中查询，即全局模式（如熔断器策略时从缓存中查询）
        boolean isForceRefresh = true;
        //对DataAccessException进行回滚，即如果抛出对DataAccessException不进行重试
        BinaryExceptionClassifier rollbackClassifier =
                new BinaryExceptionClassifier(Collections.singleton(DataAccessException.class));
        RetryState state = new DefaultRetryState(key, isForceRefresh, rollbackClassifier);

        String result = retryTemplate.execute(new RetryCallback<String, RuntimeException>() {
            @Override
            public String doWithRetry(RetryContext context) throws RuntimeException {
                System.out.println("retry count:" + context.getRetryCount());
                throw new TypeMismatchDataAccessException("");
                //throw new IllformedLocaleException("");
            }
        }, new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) throws Exception {
                return "default";
            }
        }, state);

        System.out.println("result: " + result);
    }

    private void retryExample9() throws Exception {
        RetryTemplate template = new RetryTemplate();
        CircuitBreakerRetryPolicy retryPolicy =
                new CircuitBreakerRetryPolicy(new SimpleRetryPolicy(1));
        retryPolicy.setOpenTimeout(20000);
        retryPolicy.setResetTimeout(1);
        template.setRetryPolicy(retryPolicy);

        for (int i = 0; i < 10; i++) {
            //Thread.sleep(1000);
            //log.info("index: {}", i);
            try {
                Object key = "circuit";
                boolean isForceRefresh = false;
                RetryState state = new DefaultRetryState(key, isForceRefresh);
                int finalI = i;
                String result = template.execute(new RetryCallback<String, RuntimeException>() {
                    @Override
                    public String doWithRetry(RetryContext context) throws RuntimeException {
                        log.info("index:{}, retry count: {}", finalI, context.getRetryCount());
                        throw new RuntimeException("timeout");
                    }
                }, new RecoveryCallback<String>() {
                    @Override
                    public String recover(RetryContext context) throws Exception {
                        return "default";
                    }
                }, state);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void retryExample10() {
        String result;
        try {
            for (int i = 0; i < 10; i++) {
                result = retryAnnotationService().service1();
                log.info("result: {}", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retryExample11() {
        String result;
        try {
            for (int i = 0; i < 10; i++) {
                result = retryAnnotationService().service2();
                log.info("result: {}", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retryExample12() {
        String result;
        try {
            for (int i = 0; i < 10; i++) {
                result = retryAnnotationService().service3();
                log.info("result: {}", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String randomException() throws Exception {
        int random = (int) (Math.random() * 10);
        if (random < 8) {
            throw new NullPointerException();
        } else if (random < 10) {
            throw new ArithmeticException();
        }
        return "ok";
    }

    private int len(int i) throws Exception {
        if (i < 10) throw new Exception(i + " le 10");
        return i;
    }

    private int len2(int i) throws Exception {
        throw new Exception(i + " exception");
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
