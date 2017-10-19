package com.rhwayfun.springboot.quickstart.security.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.rhwayfun.springboot.quickstart.datasource.constants.DataSourceConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by chubin on 2017/7/29.
 */
@Configuration
@ConfigurationProperties(prefix = "security.datasource")
@MapperScan(basePackages = DataSourceConstants.MAPPER_SECURITY_PACKAGE, sqlSessionFactoryRef = "securitySqlSessionFactory")
public class SecurityDataSourceConfig {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    @Bean(name = "securityDataSource")
    public DataSource securityDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "securityTransactionManager")
    public DataSourceTransactionManager securityTransactionManager() {
        return new DataSourceTransactionManager(securityDataSource());
    }

    @Bean(name = "securitySqlSessionFactory")
    public SqlSessionFactory securitySqlSessionFactory(@Qualifier("securityDataSource") DataSource securityDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(securityDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConstants.MAPPER_SECURITY_LOCATION));
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

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
