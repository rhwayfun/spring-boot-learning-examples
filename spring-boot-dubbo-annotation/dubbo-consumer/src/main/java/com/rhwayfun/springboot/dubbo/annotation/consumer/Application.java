package com.rhwayfun.springboot.dubbo.annotation.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}
}
