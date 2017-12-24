package com.rhwayfun.springboot.rocketmq.mq;

import io.github.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Component
public class DemoRocketMqConsumerExample
        extends AbstractRocketMqConsumer<DemoRocketMqTopic, DemoRocketMqContent> {

    @Override
    public Map<String, Set<String>> subscribeTopicTags() {
        Map<String, Set<String>> topicSetMap = new HashMap<>();
        Set<String> tagSet = new HashSet<>();
        tagSet.add("TagA");
        tagSet.add("TagB");
        topicSetMap.put("TopicA", tagSet);
        return topicSetMap;
    }

    @Override
    public String getConsumerGroup() {
        return "spring-boot-test-consumer-group";
    }

    @Override
    public boolean consumeMsg(DemoRocketMqContent content, MessageExt msg) {
        logger.info("receive msg[{}], topic:{}, tag:{}, content:{}", msg, content);
        return false;
    }
}
