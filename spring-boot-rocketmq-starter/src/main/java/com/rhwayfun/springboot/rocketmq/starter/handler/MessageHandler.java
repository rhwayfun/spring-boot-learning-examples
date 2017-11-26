package com.rhwayfun.springboot.rocketmq.starter.handler;


import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTag;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public interface MessageHandler{

    boolean handle(RocketMqTopic topic, RocketMqTag tag, RocketMqContent content, MessageExt msg);

}
