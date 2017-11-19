package com.rhwayfun.springboot.rocketmq.starter.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class HandlerHolder {

    private static Map<String, MessageHandler> cacheHandlers = new ConcurrentHashMap<>();

    private HandlerHolder() {}

    public static MessageHandler getHandler(String key) {
        return cacheHandlers.get(key);
    }

    public static void addHandler(String key, MessageHandler handler) {
        cacheHandlers.put(key, handler);
    }

}
