package com.rhwayfun.springboot.rocketmq.starter.common;

import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTag;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;

import java.util.Map;
import java.util.Set;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public abstract class AbstractRocketMqSubscribe
        <Topic extends RocketMqTopic, Tag extends RocketMqTag, Content extends RocketMqContent>
        {

    protected Topic topic;

    protected Tag tag;

    protected Content content;

    public abstract Map<Topic, Set<Tag>> subscribeTopicTags();

}
