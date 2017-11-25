package com.rhwayfun.springboot.rocketmq.starter.common;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public abstract class AbstractRocketMqSubscribe {

    public abstract String getTopic();

    public abstract String getTags();

}
