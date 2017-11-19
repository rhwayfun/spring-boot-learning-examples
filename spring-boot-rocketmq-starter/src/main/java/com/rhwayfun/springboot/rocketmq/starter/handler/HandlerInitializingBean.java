package com.rhwayfun.springboot.rocketmq.starter.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class HandlerInitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(HandlerInitializingBean.class);

    @Autowired(required = false)
    public void init(List<MessageHandler> messageHandlers) throws Exception {
        if (null == messageHandlers) {
            return;
        }
        for (MessageHandler handler : messageHandlers) {
            if (null == handler) {
                return;
            }
            if (null == handler.getTopic() || "".equals(handler.getTopic().trim())) {
                throw new IllegalArgumentException(handler.getClass() + " handler's topic is empty.");
            }
            if (null == handler.getTag() || "".equals(handler.getTag().trim())) {
                throw new IllegalArgumentException(handler.getClass() + " handler's tag is empty.");
            }
            String key = handler.getTopic() + handler.getTag();
            HandlerHolder.addHandler(key, handler);
            LOGGER.info("init rocket mq handler process, add topic:{}, tags:{}, handler:{}, handlerKey:{}",
                    handler.getTopic(), handler.getTag(), handler.getClass(), key.toLowerCase());
        }
    }

}
