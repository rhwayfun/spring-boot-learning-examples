package com.rhwayfun.springboot.mybatis.shardingjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.rhwayfun.springboot.mybatis.shardingjdbc.algorithm.ModuloDatabaseShardingAlgorithm;
import com.rhwayfun.springboot.mybatis.shardingjdbc.algorithm.ModuloTableShardingAlgorithm;
import com.rhwayfun.springboot.mybatis.shardingjdbc.costants.DataSourceConstants;
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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @Bean(name = "mybatisDataSource")
    @Primary
    public DataSource getDataSource() throws SQLException {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("springboot_0", mybatisDataSource("springboot"));
        dataSourceMap.put("springboot_1", mybatisDataSource("springboot2"));

        //设置默认库，两个库以上时必须设置默认库。默认库的数据源名称必须是dataSourceMap的key之一
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "springboot_0");

        //设置分表映射
        TableRule userTableRule = TableRule.builder("user")
                .actualTables(Arrays.asList("user_0", "user_1"))
                .dataSourceRule(dataSourceRule)
                .build();

        //具体分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Collections.singletonList(userTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("city_id", new ModuloDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("user_id", new ModuloTableShardingAlgorithm())).build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

        //return new ShardingDataSource(shardingRule);
        return dataSource;
    }

    private DataSource mybatisDataSource(final String dataSourceName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
        dataSource.setUrl(String.format(url, dataSourceName));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Sharding-jdbc的事务需要单独的依赖支持
     *
     * @return
     */
    // TODO: 2017/10/2  这里先留个口子，后续添加事务使用案例
    @Bean(name = "mybatisTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager() {
        return new DataSourceTransactionManager(mybatisDataSource("springboot"));
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
