package com.rhwayfun.springboot.rocketmq.starter.handler;


import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public interface MessageHandler {

    String getTopic();

    String getTag();

    ConsumeConcurrentlyStatus handle(String key, byte[] body);
}
