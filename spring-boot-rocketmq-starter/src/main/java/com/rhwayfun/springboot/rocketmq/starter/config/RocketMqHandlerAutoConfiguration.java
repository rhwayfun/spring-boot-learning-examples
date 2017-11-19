package com.rhwayfun.springboot.rocketmq.starter.config;

import com.rhwayfun.springboot.rocketmq.starter.common.AbstractMessageListener;
import com.rhwayfun.springboot.rocketmq.starter.handler.HandlerInitializingBean;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Configuration
@ConditionalOnClass({ AbstractMessageListener.class })
@AutoConfigureBefore(RocketMqAutoConfiguration.class)
public class RocketMqHandlerAutoConfiguration {

    @Bean
    public HandlerInitializingBean initHandler() {
        return new HandlerInitializingBean();
    }

    @Bean
    @ConditionalOnMissingBean(MessageListener.class)
    public MessageListener messageListener() {
        return new AbstractMessageListener();
    }

}
