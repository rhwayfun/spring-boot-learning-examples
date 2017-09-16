package com.rhwayfun.springboot.mybatis.multidatasource.constants;

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
     * 另外一个数据源配置前缀
     *
     */
    public static final String DATASOURCE2_PREFIX = "mybatis.datasource2";

    /**
     * MySQL驱动
     *
     */
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /**
     * Mybatis Mapper接口路径
     *
     */
    public static final String MAPPER_PACKAGE = "com.rhwayfun.springboot.mybatis.multidatasource.mapper.user";

    /**
     * Mybatis Mapper接口路径
     *
     */
    public static final String MAPPER2_PACKAGE = "com.rhwayfun.springboot.mybatis.multidatasource.mapper.city";

}
