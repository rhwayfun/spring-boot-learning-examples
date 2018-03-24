package com.rhwayfun.springboot.dubbo.annotation.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rhwayfun.springboot.dubbo.annotation.api.DemoProvider;
import org.springframework.stereotype.Component;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Component
public class DemoConsumer {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private DemoProvider demoProvider;

    public String sayHi(String name) {
        return demoProvider.sayHello(name);
    }

}
