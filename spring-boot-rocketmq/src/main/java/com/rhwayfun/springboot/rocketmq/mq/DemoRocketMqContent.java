package com.rhwayfun.springboot.rocketmq.mq;


import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class DemoRocketMqContent extends RocketMqContent {

    private int cityId;
    private String desc;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
