package com.rhwayfun.springboot.rocketmq.starter.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by ZhongCB on 2017/8/2.
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
    @ConditionalOnBean(value = MessageListener.class)
    public DefaultMQPushConsumer defaultMQPushConsumer(MessageListener messageListener) {
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

        try {
            String subscribes = rocketMqProperties.getSubscribes();
            if (StringUtils.hasText(subscribes)) {
                String[] topicAndExpressions = subscribes.split(";");
                for (String tes : topicAndExpressions) {
                    String[] te = tes.split(",");
                    consumer.subscribe(te[0], te[1]);
                    LOGGER.info("subscribe, top:{}, expression:{}", te[0], te[1]);
                }
            }
        } catch (MQClientException e) {
            LOGGER.error("consumer subscribe error", e);
        }

        consumer.registerMessageListener(messageListener);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("consumer shutdown");
                consumer.shutdown();
                LOGGER.info("consumer has shutdown");
            }
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
