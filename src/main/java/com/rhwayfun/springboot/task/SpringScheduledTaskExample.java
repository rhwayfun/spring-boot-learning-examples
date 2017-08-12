package com.rhwayfun.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by chubin on 2017/8/12.
 */
@Configuration
@Component
@EnableScheduling
public class SpringScheduledTaskExample {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(SpringScheduledTaskExample.class);

    private LinkedBlockingQueue<Long> q = new LinkedBlockingQueue<>(1024);

    public SpringScheduledTaskExample() {
        List<JobThread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new JobThread(i, q));
        }
        for (JobThread jobThread : threads) {
            jobThread.start();
        }
    }

    @Scheduled(cron = "3/10 * * * * ?")
    public void execute() throws InterruptedException {
        log.info("check schedule task!");
        for (int i = 0; i < 5; i++) {
            long time = System.currentTimeMillis() - i * 1000;
            q.offer(time, 10, TimeUnit.MILLISECONDS);
        }
        //模拟耗时操作
        Thread.sleep(ThreadLocalRandom.current().nextInt(10000));
    }

    @Scheduled(cron = "0/20 * * * * ?")
    public void execute2() throws InterruptedException {
        log.info("check schedule task2!");
        for (int i = 0; i < 5; i++) {
            long time = System.currentTimeMillis() + i * 1000;
            q.offer(time, 10, TimeUnit.MILLISECONDS);
        }
        //模拟耗时操作
        Thread.sleep(ThreadLocalRandom.current().nextInt(10000));
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.setThreadNamePrefix("Pool-askScheduler-");
        poolTaskScheduler.setPoolSize(100);
        return poolTaskScheduler;
    }

    private class JobThread extends Thread {

        private int threadNo;
        private LinkedBlockingQueue<Long> q;

        JobThread(int threadNo, LinkedBlockingQueue<Long> q) {
            this.threadNo = threadNo;
            this.q = q;
        }

        @Override
        public void run() {
            while (true) {

                Long time = null;
                try {
                    time = q.poll(50, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    log.error("poll timestamp error, ", e);
                }

                if (time == null) {
                    continue;
                }

                log.info("queue size:{}, poll time: {}", q.size(), new Date(time));

            }
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(SpringScheduledTaskExample.class);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            configApplicationContext.close();
        }
    }

}
