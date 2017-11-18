package com.rhwayfun.springboot.rocketmq.starter.config;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by ZhongCB on 2017/8/2.
 */

public class DispatchMessageListener implements MessageListenerConcurrently {

    private static Logger logger = LoggerFactory.getLogger(DispatchMessageListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        MessageExt msg = msgs.get(0);
        byte[] body = msg.getBody();
        String bodyString = getBodyString(body);
        String key = msg.getKeys();
        String topic = msg.getTopic();
        String tags = msg.getTags();
        logger.info(Thread.currentThread().getName() + " Receive New Messages: " + bodyString + " ,key:" + key
                + ",tags:" + tags + ",topic:" + topic);

        long bornTimestamp = msg.getBornTimestamp();
        long currentTimeMillis = System.currentTimeMillis();
        long timeElapsedFromStoreInMqToReceiveMsg = currentTimeMillis - bornTimestamp;
        ConsumeConcurrentlyStatus consumeConcurrentlyStatus = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        // 300s = 5min 作为一个消费阈值，超过这个值的消息都作为无效消息判断
        if (timeElapsedFromStoreInMqToReceiveMsg >= 300000) {
            logger.warn("msg:{} is invalid, it was born {}s ago", msg, timeElapsedFromStoreInMqToReceiveMsg / 1000);
            return consumeConcurrentlyStatus;
        }
        String handlerKey = topic + tags;
        MessageHandler handler = HandlerHolder.getHandler(handlerKey);
        if (null != handler) {
            try {
                consumeConcurrentlyStatus = handler.handle(key, body);
            } catch (Throwable t) {
                logger.warn("mq handler error, msg info:{}", msg, t);
                if (msg.getReconsumeTimes() == 5) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return consumeConcurrentlyStatus;
    }

    private String getBodyString(byte[] body) {
        String bodyString = null;
        if (null != body) {
            try {
                bodyString = new String(body, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("can not parse to string with UTF-8", e);
            }
        }
        return bodyString;
    }
}
