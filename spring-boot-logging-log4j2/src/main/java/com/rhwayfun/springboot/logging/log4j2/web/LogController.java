package com.rhwayfun.springboot.logging.log4j2.web;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    private static ThreadPoolExecutor threadPool;
    private static MdcThreadPoolExecutor mdcThreadPool;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("fixed-thread-pool-%d").build();
        threadPool = new ThreadPoolExecutor(200, 200, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        mdcThreadPool = MdcThreadPoolExecutor.newWithInheritedMdc(100, 100, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
    }

    @RequestMapping("/now/{id}")
    @ResponseBody
    public LocalDateTime now(@PathVariable String id, HttpServletRequest request) {
        mdcThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                MDC.put("traceId", request.getRequestURI());
                logger.info("线程池任务执行-------1");
            }
        });
        logger.info("mdc context:{}", MDC.getCopyOfContextMap());
        return LocalDateTime.now();
    }

    @RequestMapping("/test/{id}")
    @ResponseBody
    public String test(@PathVariable String id, HttpServletRequest request) {
        MDC.put("traceId", request.getRequestURI());
        threadPool.execute(wrap(new Runnable() {
            @Override
            public void run() {
                logger.info("线程池任务执行--------2");
            }
        }, MDC.getCopyOfContextMap()));
        return request.getRequestURI();
    }

    @RequestMapping("/java/{id}")
    @ResponseBody
    public String java(@PathVariable String id, HttpServletRequest request) {
        doAll(request.getRequestURI());
        doAll(request.getMethod());
        return request.getRequestURI();
    }

    @RequestMapping("/mdc/{id}")
    @ResponseBody
    public String mdcController(@PathVariable String id, HttpServletRequest request) {
        MDC.put("traceId", request.getPathInfo());
        threadPool.execute(wrap(new Runnable() {
            @Override
            public void run() {
                logger.info("This is an mdc context controller");
            }
        }, MDC.getCopyOfContextMap()));
        return request.getPathInfo();
    }

    private static CompletableFuture<String> stepOne(String requestId) {
        return CompletableFuture.supplyAsync(() -> {
            MDC.put("traceId", requestId);
            logger.info("Step One");
            return "First";
        });
    }

    private static CompletableFuture<String> stepTwo(CompletableFuture<String> previous) {
        return previous.thenApplyAsync(x -> {
            logger.info("Step Two");
            return x + "Second";
        });
    }

    private static CompletableFuture<String> stepThree(CompletableFuture<String> previous) {
        return previous.thenApplyAsync(x -> {
            logger.info("Step Three");
            return x + "Third";
        });
    }

    private static String doAll(String requestId) {
        return stepThree(stepTwo(stepOne(requestId))).join();
    }

    static class RunnableWrapperWithMdc implements Runnable {
        private final Runnable wrapped;
        private final Map<String, String> map;

        public RunnableWrapperWithMdc(Runnable wrapped) {
            this.wrapped = wrapped;
            map = MDC.getCopyOfContextMap();
        }

        @Override
        public void run() {
            try {
                MDC.setContextMap(map);
                wrapped.run();
            } finally {
                MDC.clear();
            }
        }
    }

    private Runnable wrap(Runnable runnable, Map<String, String> contextMap) {
        return new Runnable() {
            @Override
            public void run() {
                Map<String, String> previous = MDC.getCopyOfContextMap();
                if (contextMap == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(contextMap);
                }
                try {
                    //调用run方法不会在主线程执行
                    runnable.run();
                } finally {
                    if (previous == null) {
                        MDC.clear();
                    } else {
                        MDC.setContextMap(previous);
                    }
                }
            }
        };
    }

}
