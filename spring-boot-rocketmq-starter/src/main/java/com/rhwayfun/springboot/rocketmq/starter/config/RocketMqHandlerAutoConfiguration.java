package com.rhwayfun.springboot.rocketmq.starter.config;

import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZhongCB on 2017/8/2.
 */

@Configuration
@ConditionalOnClass({ DispatchMessageListener.class })
@AutoConfigureBefore(RocketMqAutoConfiguration.class)
public class RocketMqHandlerAutoConfiguration {

    @Bean
    public HandlerInitializingBean initHandler() {
        return new HandlerInitializingBean();
    }

    @Bean
    @ConditionalOnMissingBean(MessageListener.class)
    public MessageListener messageListener() {
        return new DispatchMessageListener();
    }

}
