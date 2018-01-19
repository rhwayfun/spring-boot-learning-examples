package com.rhwayfun.springboot.dubbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Configuration
@ImportResource("dubbo-provider.xml")
public class AppConfig {
}
