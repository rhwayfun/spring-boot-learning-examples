package com.rhwayfun.springboot.dubbo.annotation.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.rhwayfun.springboot.dubbo.annotation.api.DemoProvider;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoProviderImpl implements DemoProvider {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
