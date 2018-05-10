package com.rhwayfun.springboot.logging.log4j2.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * 程序入口
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@SpringBootApplication
//@ConditionalOnClass(SpecificLog4j2Configuration.class)
public class Application {

	static {
		//System.setProperty("log4j2.configurationFactory", "com.rhwayfun.springboot.logging.log4j2.config.SpecificLog4j2Configuration");
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
