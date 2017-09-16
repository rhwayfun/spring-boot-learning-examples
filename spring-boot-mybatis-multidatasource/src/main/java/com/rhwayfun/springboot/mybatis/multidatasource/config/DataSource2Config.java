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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 另外一个数据源配置
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE2_PREFIX)
@MapperScan(basePackages = { DataSourceConstants.MAPPER2_PACKAGE }, sqlSessionFactoryRef = "mybatisSqlSessionFactory2")
public class DataSource2Config {

    private String url;

    private String username;

    private String password;

    @Bean(name = "mybatisDataSource2")
    public DataSource mybatisDataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "mybatisTransactionManager2")
    public DataSourceTransactionManager mybatisTransactionManager2() {
        return new DataSourceTransactionManager(mybatisDataSource2());
    }

    @Bean(name = "mybatisSqlSessionFactory2")
    public SqlSessionFactory mybatisSqlSessionFactory2(@Qualifier("mybatisDataSource2") DataSource mybatisDataSource2)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource2);
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
