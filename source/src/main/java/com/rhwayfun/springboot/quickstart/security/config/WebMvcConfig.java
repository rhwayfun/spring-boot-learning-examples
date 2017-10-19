package com.rhwayfun.springboot.quickstart.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chubin on 2017/8/19.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 统一注册纯RequestMapping跳转View的Controller
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
    }
}
