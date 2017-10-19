package com.rhwayfun.springboot.mybatis.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}

}
