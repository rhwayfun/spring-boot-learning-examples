package com.rhwayfun.springboot.configuration.random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 随机值
 * 使用@Configuration注解声明这是一个配置文件
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
public class RandomProperty {

    @Value("${my.secret}")
    private String randomValue;

    @Value("${my.number}")
    private int randomInt;

    @Value("${my.bignumber}")
    private long randomLong;

    @Value("${my.uuid}")
    private String randomUUID;

    @Value("${my.number.less.than.ten}")
    private int randomIntRange;

    @Value("${my.number.in.range}")
    private int randomIntMaxMinRange;

    public String getRandomValue() {
        return randomValue;
    }

    public int getRandomInt() {
        return randomInt;
    }

    public long getRandomLong() {
        return randomLong;
    }

    public String getRandomUUID() {
        return randomUUID;
    }

    public int getRandomIntRange() {
        return randomIntRange;
    }

    public int getRandomIntMaxMinRange() {
        return randomIntMaxMinRange;
    }

}
