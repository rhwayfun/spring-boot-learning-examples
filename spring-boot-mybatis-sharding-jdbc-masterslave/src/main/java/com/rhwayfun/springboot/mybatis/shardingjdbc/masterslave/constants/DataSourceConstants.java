package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.constants;

/**
 * 数据源相关常量
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public abstract class DataSourceConstants {

    /**
     * 数据源配置前缀
     *
     */
    public static final String DATASOURCE_PREFIX = "mybatis.datasource";

    /**
     * MySQL驱动
     *
     */
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /**
     * Mybatis Mapper接口路径
     *
     */
    public static final String MAPPER_PACKAGE = "com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.mapper";

}
