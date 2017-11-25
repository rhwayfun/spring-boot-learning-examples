package com.rhwayfun.springboot.rocketmq.starter.handler;


import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public interface MessageHandler{

    ConsumeConcurrentlyStatus handle(MessageExt msg);

}
