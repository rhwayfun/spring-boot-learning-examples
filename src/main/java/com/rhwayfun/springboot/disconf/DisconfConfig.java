package com.rhwayfun.springboot.disconf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by chubin on 2017/6/11.
 */
@Configuration
@ImportResource(locations = {"classpath:spring/app-context-common.xml", "classpath:spring/disconf.xml"})
public class DisconfConfig {
}
