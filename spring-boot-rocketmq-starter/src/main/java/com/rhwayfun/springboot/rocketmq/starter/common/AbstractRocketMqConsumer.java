package com.rhwayfun.springboot.rocketmq.starter.common;

import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTag;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
import com.rhwayfun.springboot.rocketmq.starter.handler.MessageHandler;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Abstract message listener.
 * For consume message, you should inherit this class to implement three methods.
 * @author rhwayfun
 * @since 0.0.1
 */
public abstract class AbstractRocketMqConsumer
        extends AbstractRocketMqSubscribe implements MessageHandler, MessageListenerConcurrently {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void init() {

    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        MessageExt msg = msgs.get(0);
        /*byte[] body = msg.getBody();
        String bodyString = getBodyString(body);
        String key = msg.getKeys();
        String topic = msg.getTopic();
        String tags = msg.getTags();
        logger.warn(Thread.currentThread().getName() + " Receive New Messages: " + bodyString + " ,key:" + key
                + ",tags:" + tags + ",topic:" + topic);*/

        long bornTimestamp = msg.getBornTimestamp();
        long currentTimeMillis = System.currentTimeMillis();
        long timeElapsedFromStoreInMqToReceiveMsg = currentTimeMillis - bornTimestamp;
        ConsumeConcurrentlyStatus consumeConcurrentlyStatus = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        // 300s = 5min 作为一个消费阈值，超过这个值的消息都作为无效消息判断
        if (timeElapsedFromStoreInMqToReceiveMsg >= 300000) {
            logger.warn("msg:{} is invalid, it was born {}s ago", msg, timeElapsedFromStoreInMqToReceiveMsg / 1000);
            return consumeConcurrentlyStatus;
        }
        try {
            consumeConcurrentlyStatus = handle(topic, tag, content, msg) ? ConsumeConcurrentlyStatus.CONSUME_SUCCESS : ConsumeConcurrentlyStatus.RECONSUME_LATER;
        } catch (Throwable t) {
            logger.warn("mq handler error, msg info:{}", msg, t);
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
