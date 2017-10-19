package com.rhwayfun.springboot.quickstart.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by chubin on 2017/7/30.
 */
public class SimpleKafkaProducer {

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();

        //broker地址
        props.put("bootstrap.servers", "localhost:9092");

        //请求时候需要验证
        props.put("acks", "all");

        //请求失败时候需要重试
        props.put("retries", 0);

        //内存缓存区大小
        props.put("buffer.memory", 33554432);

        //指定消息key序列化方式
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        //指定消息本身的序列化方式
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        /*for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)));*/
        producer.send(new ProducerRecord<>("hello-kafka", "Test consumer group 01", "Test consumer group 01"));
        producer.send(new ProducerRecord<>("hello-kafka", "Test consumer group 02", "Test consumer group 02"));
        System.out.println("Message sent successfully");
        producer.close();
    }
}
