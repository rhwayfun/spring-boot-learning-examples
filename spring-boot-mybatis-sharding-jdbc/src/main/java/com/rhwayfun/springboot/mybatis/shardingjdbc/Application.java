package com.rhwayfun.springboot.mybatis.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 程序入口
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@SpringBootApplication
/**
 * 使用注解@ServletComponentScan
 * 如果不是使用配置的方式，注解@WebServlet/@WebFilter添加Servlet或Filter，需要添加这个注解启用Servlet扫描
 * @see com.rhwayfun.springboot.mybatis.shardingjdbc.druid.DruidStatsConfig
 * @see com.rhwayfun.springboot.mybatis.shardingjdbc.druid.DruidStatsServlet
 * @see com.rhwayfun.springboot.mybatis.shardingjdbc.druid.DruidStatsFilter
 */
public class Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}
}
