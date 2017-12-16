# Maven

引用RocketMQ Spring Boot Starter依赖:

```java

<dependency>
    <groupId>com.rhwayfun</groupId>
    <artifactId>spring-boot-rocketmq-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
 
```

# Consumer

继承自`com.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer`

使用示例：

```java
@Component
public class DemoRocketMqConsumerExample
        extends AbstractRocketMqConsumer<DemoRocketMqTopic, DemoRocketMqTag, DemoRocketMqContent> {

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
    public boolean handle(String topic, String tag, DemoRocketMqContent content, MessageExt msg) {
        logger.info("receive msg[{}], topic:{}, tag:{}, content:{}", msg, topic, tag, content);
        return true;
    }

}
```

# Producer
待补充。。。