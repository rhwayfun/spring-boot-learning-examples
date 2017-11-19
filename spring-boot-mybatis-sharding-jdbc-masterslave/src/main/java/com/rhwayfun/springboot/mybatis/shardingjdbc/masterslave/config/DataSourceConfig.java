package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.constants.DataSourceConstants;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * 分库分表 + 读写分离
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
//@Configuration
//@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE_PREFIX)
//@MapperScan(basePackages = {DataSourceConstants.MAPPER_PACKAGE}, sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class DataSourceConfig {

    private String url;

    private String username;

    private String password;

    @Bean(name = "mybatisDataSource")
    public DataSource getDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        DataSource masterDataSource0 = mybatisDataSource("ds_master_0");
        DataSource slaveDataSource00 = mybatisDataSource("ds_master_0_slave_0");
        DataSource slaveDataSource01 = mybatisDataSource("ds_master_0_slave_1");
        dataSourceMap.put("masterDataSource0", masterDataSource0);
        dataSourceMap.put("slaveDataSource00", slaveDataSource00);
        dataSourceMap.put("slaveDataSource01", slaveDataSource01);

        DataSource masterDataSource1 = mybatisDataSource("ds_master_1");
        DataSource slaveDataSource10 = mybatisDataSource("ds_master_1_slave_0");
        DataSource slaveDataSource11 = mybatisDataSource("ds_master_1_slave_1");
        dataSourceMap.put("masterDataSource1", masterDataSource1);
        dataSourceMap.put("slaveDataSource10", slaveDataSource10);
        dataSourceMap.put("slaveDataSource11", slaveDataSource11);

        // 构建读写分离配置
        MasterSlaveRuleConfiguration masterSlaveRuleConfig0 = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig0.setName("ds_0");
        masterSlaveRuleConfig0.setMasterDataSourceName("masterDataSource0");
        masterSlaveRuleConfig0.getSlaveDataSourceNames().add("slaveDataSource00");
        masterSlaveRuleConfig0.getSlaveDataSourceNames().add("slaveDataSource01");

        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig1.setName("ds_1");
        masterSlaveRuleConfig1.setMasterDataSourceName("masterDataSource1");
        masterSlaveRuleConfig1.getSlaveDataSourceNames().add("slaveDataSource10");
        masterSlaveRuleConfig1.getSlaveDataSourceNames().add("slaveDataSource11");

        // 通过ShardingSlaveDataSourceFactory继续创建ShardingDataSource
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfig0);
        shardingRuleConfig.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfig1);

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig);
        return dataSource;
    }

    private DataSource mybatisDataSource(final String dataSourceName) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
        dataSource.setUrl(String.format(url, dataSourceName));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        /* 配置初始化大小、最小、最大 */
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);

        /* 配置获取连接等待超时的时间 */
        dataSource.setMaxWait(60000);

        /* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        dataSource.setTimeBetweenEvictionRunsMillis(60000);

        /* 配置一个连接在池中最小生存的时间，单位是毫秒 */
        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        /* 配置监控统计拦截的filters */
        dataSource.setFilters("stat,wall,log4j");
        return dataSource;
    }

    /**
     * Sharding-jdbc的事务支持
     *
     * @return
     */
    @Bean(name = "mybatisTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("mybatisDataSource") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisSqlSessionFactory")
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
