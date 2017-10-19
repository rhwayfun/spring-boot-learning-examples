package com.rhwayfun.springboot.mybatis.shardingjdbc.costants;

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

    public static final String USER_CREATE_SQL = "CREATE TABLE IF NOT EXISTS `user` (`id` bigint(20) AUTO_INCREMENT, `user_id` bigint(20) NOT NULL COMMENT '用户id',`city_id` int(11) DEFAULT NULL COMMENT '城市id',`user_name` varchar(15) DEFAULT NULL,`age` int(11) DEFAULT NULL COMMENT '年龄',`birth` date DEFAULT NULL COMMENT '生日',PRIMARY KEY (`id`))";

    public static final String GET_ALL_SQL = "SELECT * FROM user";

    public static final String GET_ONE_SQL = "SELECT * FROM user WHERE user_id = #{userId}";

    public static final String GET_LIMIT_ONE_SQL = "SELECT * FROM user LIMIT 1";

    public static final String INSERT_SQL = "INSERT INTO user(user_id,city_id,user_name,age,birth) VALUES(#{userId}, #{cityId}, #{userName}, #{age}, #{birth})";

    public static final String INSERT_SLAVE_SQL = "INSERT INTO user(city_id,user_name,age,birth) VALUES(#{cityId}, #{userName}, #{age}, #{birth})";

    public static final String UPDATE_SQL = "UPDATE user SET user_name=#{userName},age=#{age},birth=#{birth} WHERE user_id =#{userId}";

    public static final String DELETE_SQL = "DELETE FROM user WHERE user_id =#{userId}";

    public static final String DELETE_ALL_SQL = "DELETE FROM user";

}
