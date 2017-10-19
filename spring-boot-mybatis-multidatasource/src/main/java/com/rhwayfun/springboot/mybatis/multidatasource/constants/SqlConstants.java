package com.rhwayfun.springboot.mybatis.multidatasource.constants;

/**
 * Sql语句
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public abstract class SqlConstants {

    /**
     * TABLE >>>>>>>>>>  user
     */

    public static final String GET_ALL_SQL = "SELECT * FROM user";

    public static final String GET_ONE_SQL = "SELECT * FROM user WHERE user_id = #{userId}";

    public static final String INSERT_SQL = "INSERT INTO user(user_id,user_name,age,birth) VALUES(#{userId}, #{userName}, #{age}, #{birth})";

    public static final String UPDATE_SQL = "UPDATE user SET user_name=#{userName},age=#{age},birth=#{birth} WHERE user_id =#{userId}";

    public static final String DELETE_SQL = "DELETE FROM user WHERE user_id =#{userId}";

    public static final String DELETE_ALL_SQL = "DELETE FROM user";


    /**
     * TABLE >>>>>>>>>>  city
     */

    public static final String CITY_CREATE_SQL = "CREATE TABLE IF NOT EXISTS `city` (`id` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '记录ID',`name` varchar(128) DEFAULT NULL COMMENT '城市名称',`is_active` tinyint(3) unsigned DEFAULT '0' COMMENT '是否开放',`create_time` datetime DEFAULT NULL COMMENT '创建时间',`modify_time` datetime DEFAULT NULL COMMENT '修改时间',PRIMARY KEY (`id`),KEY `idx_name` (`name`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市信息表';";

    public static final String CITY_GET_ALL_SQL = "SELECT * FROM city";

    public static final String CITY_GET_ONE_SQL = "SELECT * FROM city WHERE id = #{id}";

    public static final String CITY_INSERT_SQL = "INSERT INTO city(name,is_active,create_time,modify_time) VALUES(#{name}, #{isActive}, #{createTime}, #{modifyTime})";

    public static final String CITY_UPDATE_SQL = "UPDATE city SET name=#{name},is_active=#{isActive},create_time=#{createTime},modify_time=#{modifyTime} WHERE id =#{id}";

    public static final String CITY_DELETE_SQL = "DELETE FROM city WHERE id =#{id}";

    public static final String CITY_DELETE_ALL_SQL = "DELETE FROM city";

}
