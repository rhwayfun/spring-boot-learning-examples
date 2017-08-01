package com.rhwayfun.springboot.kafka.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * Created by chubin on 2017/7/30.
 */
@Configurable
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {

    private String bootstrapServers;

    @Bean
    public KafkaProducer kafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);

        props.put("key.serializer",
                "org.apache.kafka.common.serializa-tion.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serializa-tion.StringSerializer");

        return new KafkaProducer<>(props);
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

}
