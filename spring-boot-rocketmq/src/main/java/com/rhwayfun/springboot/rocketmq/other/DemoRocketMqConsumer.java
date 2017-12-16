package com.rhwayfun.springboot.rocketmq.other;

import com.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
/*@Component
public class DemoRocketMqConsumer extends AbstractRocketMqConsumer {

    @Override
    public String getTopic() {
        return "TopicTest";
    }

    @Override
    public String getTags() {
        return "TagA";
    }

    @Override
    public ConsumeConcurrentlyStatus handle(MessageExt msg) {
        try {
            logger.info("start handle rocketmq msg, content:{}", new String(msg.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("handle rocketmq msg error.", e);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}*/
