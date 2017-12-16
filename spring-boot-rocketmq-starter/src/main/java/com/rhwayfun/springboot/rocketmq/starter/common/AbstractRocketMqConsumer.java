package com.rhwayfun.springboot.rocketmq.starter.common;

import com.alibaba.fastjson.JSON;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTag;
import com.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Abstract message listener.
 * For consume message, you should inherit this class to implement three methods.
 * @author rhwayfun
 * @since 0.0.1
 */
public abstract class AbstractRocketMqConsumer
        <Topic extends RocketMqTopic, Tag extends RocketMqTag, Content extends RocketMqContent>
        implements MessageListenerConcurrently
        //implements MessageListener
{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<Topic> topicClazz;

    protected Class<Tag> tagClazz;

    protected Class<Content> contentClazz;

    public abstract Map<String, Set<String>> subscribeTopicTags();

    public abstract boolean handle(String topic, String tag, Content content, MessageExt msg);

    @PostConstruct
    public void init() {
        Class<? extends AbstractRocketMqConsumer> parentClazz = this.getClass();
        Type genType = parentClazz.getGenericSuperclass();// 得到泛型父类
        Type[] types = ((ParameterizedType) genType).getActualTypeArguments();//一个泛型类可能有多个泛型形参，比如ClassName<T,K> 这里有两个泛型形参T和K，Class Name<T> 这里只有1个泛型形参T
        topicClazz = (Class<Topic>) types[0];
        contentClazz = (Class<Content>) types[2];
        logger.info("topicClazz:{}, contentClazz:{}", topicClazz, contentClazz);
    }

    public Class<?> getModelClass(Class modelClass, int index) {
        Type genType = this.getClass().getGenericSuperclass();// 得到泛型父类
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();//一个泛型类可能有多个泛型形参，比如ClassName<T,K> 这里有两个泛型形参T和K，Class Name<T> 这里只有1个泛型形参T
        if (params.length - 1 < index) {
            modelClass = null;
        } else {
            modelClass = (Class) params[index];
        }
        return modelClass;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        MessageExt msg = msgs.get(0);
        byte[] body = msg.getBody();
        String topic = msg.getTopic();
        String tags = msg.getTags();

        long bornTimestamp = msg.getBornTimestamp();
        long currentTimeMillis = System.currentTimeMillis();
        long timeElapsedFromStoreInMqToReceiveMsg = currentTimeMillis - bornTimestamp;
        ConsumeConcurrentlyStatus consumeConcurrentlyStatus = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

        // 300s = 5min 作为一个消费阈值，超过这个值的消息都作为无效消息判断
        if (timeElapsedFromStoreInMqToReceiveMsg >= 300000) {
            logger.warn("msg:{} is invalid, it was born {}s ago", msg, timeElapsedFromStoreInMqToReceiveMsg / 1000);
            return consumeConcurrentlyStatus;
        }

        try {
            consumeConcurrentlyStatus = handle(topic, tags, parseMsg(body, contentClazz), msg) ? ConsumeConcurrentlyStatus.CONSUME_SUCCESS : ConsumeConcurrentlyStatus.RECONSUME_LATER;
        } catch (Throwable t) {
            logger.warn("mq handler error, msg info:{}", msg, t);
        }

        return consumeConcurrentlyStatus;
    }

    private String getBodyString(byte[] body) {
        String bodyString = null;
        if (null != body) {
            try {
                bodyString = new String(body, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("can not parse to string with UTF-8", e);
            }
        }
        return bodyString;
    }

    private <T> T parseMsg(byte[] body, Class<? extends RocketMqContent> clazz){
        T t = null;
        if (body != null) {
            try {
                t = JSON.parseObject(body, clazz);
            } catch (Exception e) {
                logger.error("can not parse to object", e);
            }
        }
        return t;
    }

}
