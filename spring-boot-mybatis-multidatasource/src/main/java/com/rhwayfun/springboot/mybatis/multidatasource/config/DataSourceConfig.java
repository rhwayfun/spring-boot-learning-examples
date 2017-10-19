package com.rhwayfun.springboot.mybatis.multidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.rhwayfun.springboot.mybatis.multidatasource.constants.DataSourceConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE_PREFIX)
@MapperScan(basePackages = { DataSourceConstants.MAPPER_PACKAGE }, sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class DataSourceConfig {

    private String url;

    private String username;

    private String password;

    /**
     * 使用@Primary注解指定在有多个相同的beanname的时候会注入多个bean，而不会冲突
     *
     * @return
     */
    @Bean(name = "mybatisDataSource")
    @Primary
    public DataSource mybatisDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "mybatisTransactionManager")
    @Primary
    public DataSourceTransactionManager mybatisTransactionManager() {
        return new DataSourceTransactionManager(mybatisDataSource());
    }

    @Bean(name = "mybatisSqlSessionFactory")
    @Primary
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource);
        return sessionFactory.getObject();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
