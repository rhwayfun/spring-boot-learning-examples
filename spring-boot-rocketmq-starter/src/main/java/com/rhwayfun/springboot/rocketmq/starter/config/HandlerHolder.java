package com.rhwayfun.springboot.rocketmq.starter.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZhongCB on 2017/8/2.
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
