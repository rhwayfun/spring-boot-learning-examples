package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用配置的方式添加druid数据源监控
 * @link http://localhost:8080/springboot/druid-stats/index.html
 *
 * @author rhwayfun
 * @since 0.0.1
 */
@Configuration
public class DruidStatsConfig {

    /**
     * 注册StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid-stats/*");
        /** 初始化参数配置，initParams**/
        //白名单
        bean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        bean.addInitParameter("deny", "192.168.125.12");
        //登录查看信息的账号密码.
        bean.addInitParameter("loginUsername", "happyxiaofan");
        bean.addInitParameter("loginPassword", "springboot");
        //是否能够重置数据.
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /**
     * 注册WebStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid-stats/*");
        return bean;
    }

}
