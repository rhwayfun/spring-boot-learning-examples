package com.rhwayfun.springboot.rocketmq.starter.config;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;

/**
 * Created by ZhongCB on 2017/8/2.
 */

public interface MessageHandler {

    String getTopic();

    String getTag();

    ConsumeConcurrentlyStatus handle(String key, byte[] body);
}
