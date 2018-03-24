package com.rhwayfun.springboot.dubbo.annotation.api;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class DemoProviderMock implements DemoProvider {

    @Override
    public String sayHello(String name) {
        // 容错数据，此方法只在出现RpcException时被执行
        return null;
    }

}
