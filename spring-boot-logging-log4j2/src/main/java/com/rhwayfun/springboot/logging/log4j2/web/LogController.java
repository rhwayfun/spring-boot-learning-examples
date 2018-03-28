package com.rhwayfun.springboot.logging.log4j2.web;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author happyxiaofan
 * @since 0.0.1
 */
@RestController
public class LogController {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(LogController.class);

    private static final ThreadPoolExecutor FIXED_THREAD_POOL;

    private static final MdcThreadPoolExecutor MDC_FIXED_THREAD_POOL;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("fixed-thread-pool-%d").build();
        FIXED_THREAD_POOL = new ThreadPoolExecutor(200, 200, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        MDC_FIXED_THREAD_POOL = MdcThreadPoolExecutor.newWithCurrentMdc(10, 10, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
    }

    @RequestMapping("/now/{id}")
    @ResponseBody
    public LocalDateTime now(@PathVariable String id) {
        MDC.put("traceId", String.valueOf(ThreadLocalRandom.current().nextInt()));
        log.info("args:{}", id);
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        FIXED_THREAD_POOL.execute(wrap(new Runnable() {
            @Override
            public void run() {
                log.info("线程池任务执行");
            }
        }, contextMap));
        MDC_FIXED_THREAD_POOL.execute(MdcThreadPoolExecutor.wrap(new Runnable() {
            @Override
            public void run() {
                log.info("Mdc线程池任务执行");
            }
        }, contextMap));
        try {
            id.compareTo(null);
        } catch (Exception e) {
            log.error("异常", e);
        }
        return LocalDateTime.now();
    }

    private Runnable wrap(Runnable runnable, Map<String, String> contextMap) {
        return new Runnable() {
            @Override
            public void run() {
                if (contextMap == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(contextMap);
                }
                runnable.run();
            }
        };
    }

}
