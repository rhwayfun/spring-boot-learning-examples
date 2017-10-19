package com.rhwayfun.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnClass(DemoProperties.class)
@ConditionalOnProperty(prefix = "demo.user", value = "enabled", matchIfMissing = true)
public class DemoServiceAutoConfiguration {

    @Autowired
    private DemoProperties demoProperties;

    @Bean
    @ConditionalOnMissingBean(DemoService.class)
    public DemoService demoService() {
        DemoService demoService = new DemoService(demoProperties);
        return demoService;
    }

}
