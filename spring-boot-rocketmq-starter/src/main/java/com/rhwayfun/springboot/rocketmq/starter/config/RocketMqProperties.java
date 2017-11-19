package com.rhwayfun.springboot.rocketmq.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@ConfigurationProperties(prefix = RocketMqProperties.ROCKETMQ_PREFIX)
public class RocketMqProperties {

    public static final String ROCKETMQ_PREFIX = "dispatch.rocketmq";
    private String nameServer;
    private String producerGroupName;
    private String consumerGroupName;
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;
    private String subscribes;

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public Integer getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(Integer consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public Integer getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(Integer consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public String getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(String subscribes) {
        this.subscribes = subscribes;
    }
}
