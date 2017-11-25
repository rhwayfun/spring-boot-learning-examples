package com.rhwayfun.springboot.rocketmq.starter.config;

import com.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Configuration
@ConditionalOnClass({ DefaultMQPushConsumer.class })
@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqAutoConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(RocketMqAutoConfiguration.class);

    @Resource
    private RocketMqProperties rocketMqProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(value = AbstractRocketMqConsumer.class)
    public DefaultMQPushConsumer defaultMQPushConsumer(List<AbstractRocketMqConsumer> messageListeners) {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMqProperties.getConsumerGroupName());
        if (rocketMqProperties.getConsumeThreadMin() != null) {
            consumer.setConsumeThreadMin(rocketMqProperties.getConsumeThreadMin());
        }
        if (rocketMqProperties.getConsumeThreadMax() != null) {
            consumer.setConsumeThreadMax(rocketMqProperties.getConsumeThreadMax());
        }
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setNamesrvAddr(rocketMqProperties.getNameServer());

        messageListeners.forEach(messageListener -> {
            String topic = messageListener.getTopic();
            String tags = messageListener.getTags();
            try {
                consumer.subscribe(topic, tags);
            } catch (MQClientException e) {
                LOGGER.error("consumer subscribe error", e);
            }
            LOGGER.info("subscribe, topic:{}, tags:{}", topic, tags);
            consumer.registerMessageListener(messageListener);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("consumer shutdown");
            consumer.shutdown();
            LOGGER.info("consumer has shutdown");
        }));

        try {
            consumer.start();
            LOGGER.info("rocketmq consumer started, nameserver:{}, group:{}", rocketMqProperties.getNameServer(),
                    rocketMqProperties.getConsumerGroupName());
        } catch (MQClientException e) {
            LOGGER.error("consumer start error, nameserver:{}, group:{}", rocketMqProperties.getNameServer(),
                    rocketMqProperties.getConsumerGroupName(), e);
        }
        return consumer;
    }
}
